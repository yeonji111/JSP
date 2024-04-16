package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteId = request.getParameter("id"); // getParameter는 무조건 문자열이다.
		// id에 해당하는 데이터를 조회하여 가져온다.
		Connection connection = null;
		PreparedStatement statement = null;
//		ResultSet resultSet = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "std115", "oracle21c");
			String sql = "delete from member where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, deleteId);
			
			
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate != 0) {
				response.sendRedirect("/member/list"); 
			} else {
				request.getRequestDispatcher("/member/delete.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}