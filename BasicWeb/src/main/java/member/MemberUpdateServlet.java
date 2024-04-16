package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파라미터로 id값을 받아와야 한다.
		String searchId = request.getParameter("id"); // getParameter는 무조건 문자열이다.
		// id에 해당하는 데이터를 조회하여 가져온다.
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "std115", "oracle21c");
			String sql = "select id, name, password, email from member where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, searchId);
			resultSet = statement.executeQuery();
			MemberVO vo = null;

			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String password = resultSet.getString("password");
				String email = resultSet.getString("email");
				vo = new MemberVO(id, name, password, email);
			}

			// request에 회원목록 데이터를 보관한다.
			request.setAttribute("member", vo);

			request.getRequestDispatcher("/member/update.jsp").forward(request, response);

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "std115", "oracle21c");
			String sql = "update member set name = ?, password = ?, email = ? where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.setString(4, id);
			// 조회(select) : 결과 ResultSet이고 메소드명은 executeQuery();
			// 등록,수정,삭제 : 결과가 int 이고 성공 실패 여부만 리턴해줌. 메소드명은 executeUpdate();
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				// redirect 사용 (insert 쿼리 이후의 멤버리스트를 보여주기 위해서는 MemberListServlet을 거쳐야하므로 foward로
				// 곧장 뷰를 보여주는 게 아닌, redirect방식 사용)
				response.sendRedirect("/member/list"); // MemberListServlet의 매핑 주소 : @WebServlet("/member/list")
			} else {
				request.getRequestDispatcher("/member/update.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
