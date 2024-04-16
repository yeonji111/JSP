package member;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/member/update/password")
public class UpdatePasswordServlet extends HttpServlet {
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
			request.getRequestDispatcher("/member/updatePassword.jsp").forward(request, response);

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
//        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (newPassword.equals(confirmPassword)) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "std115", "oracle21c");

                String sql = "UPDATE member SET password = ?, modify_date = sysdate WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, newPassword);
                statement.setString(2, id);

                int executeUpdate = statement.executeUpdate();

                if (executeUpdate > 0) {
                    response.sendRedirect("/member/list");
                } else {
                    request.getRequestDispatcher("/member/updatePassword.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            out.println("<script>alert('Fail: ');</script>");
            
        }
    }
}
