package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberVO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchId = request.getParameter("id");
		String searchPassword = request.getParameter("password");
		boolean saveFlag = Boolean.parseBoolean(request.getParameter("saveId")); // 아이디 저장여부
		Cookie cookie = new Cookie("savedId", searchId);
		
		if (saveFlag) {
			// 쿠키를 생성해서 클라이언트에 보내줌 (p.208 참고)
			
			cookie.setMaxAge(60 * 60 * 24 * 365);
			//유효 시간 설정, 유효 시간 설정을 하지 않으면 session처럼 브라우저를 닫으면 사라진다.
//			cookie.setPath("/"); 경로 설정
		} else {
			// 기존의 쿠키를 삭제
			cookie.setMaxAge(0);
		}
		
//		유효시간을 1년으로 정한거 빼고는 같은 코드아님?
//		if(!saveFlag) {
//			cookie.setMaxAge(0);
//		}
		
		response.addCookie(cookie);
		
		// id에 해당하는 데이터를 조회하여 가져온다.
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "java", "oracle21c");
			String sql = "select id, name, email, create_date from member where id = ? and password = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, searchId);
			statement.setString(2, searchPassword);
			
			resultSet = statement.executeQuery();
			MemberVO vo = null;
			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				Date createDate = resultSet.getDate("create_date");
				vo = new MemberVO(id, name, email, createDate.toLocalDate());
			}
			if (vo != null) {
				// 서버에 사용자 정보를 저장하고 /member/list로 이동
				// 세션은 HttpSession 객체를 통해서 관리할 수 있다.
				HttpSession session = request.getSession();
				session.setAttribute("member", vo);
				
				response.sendRedirect("/member/list");
			} else {
				// 사용자가 없으면 로그인 페이지에 해당하는 사용자가 없다고 알려줌
				request.setAttribute("msg", "해당하는 사용자가 존재하지 않습니다.");
				request.getRequestDispatcher("/WEB-INF/views/common/login.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
	}
}
