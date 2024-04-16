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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberVO;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/common/login.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchId = request.getParameter("id");
		String searchPassword = request.getParameter("password");
		// id에 해당하는 데이터를 조회하여 가져온다.
				Connection connection = null;
				PreparedStatement statement = null;
				ResultSet resultSet = null;
				try {
					Class.forName("oracle.jdbc.OracleDriver");
					connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "std115", "oracle21c");
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
						
						vo = new MemberVO(id, name,email,createDate.toLocalDate());
					}

					if (vo != null) {
						// 서버에 사용자 정보를 저장하고 /member/list로 이동
						// 세션은 HttpSession 객체를 통해서 관리할 수 있다.
						HttpSession session = request.getSession();
						session.setAttribute("member", vo); // 사용자가 저장하는건 parameter, 개발자가 저장하는건 attribute		
						
						response.sendRedirect("/member/list");
					}else {
						// 사용자가 없으면 로그인 페이지에 해당하는 사용자가 없다고 알려줌
						request.setAttribute("msg", "해당하는 사용자가 존재하지 않습니다.");
						request.getRequestDispatcher("/common/login.jsp").forward(request, response);
												
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
