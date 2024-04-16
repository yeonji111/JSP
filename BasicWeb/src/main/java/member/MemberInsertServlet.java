package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/member/add")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// forward
		req.getRequestDispatcher("/member/add.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@nextit.or.kr:1521:xe", "std115", "oracle21c");
			String sql = "insert into member (id, name, password, email) values (? ,?,?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, name);
			statement.setString(3, password);
			statement.setString(4, email);
			// 조회(select) : 결과 ResultSet이고 메소드명은 executeQuery();
			// 등록,수정,삭제 : 결과가 int 이고 성공 실패 여부만 리턴해줌. 메소드명은 executeUpdate();
			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				// redirect 사용 (insert 쿼리 이후의 멤버리스트를 보여주기 위해서는 MemberListServlet을 거쳐야하므로 foward로 곧장 뷰를 보여주는 게 아닌, redirect방식 사용)
				response.sendRedirect("/member/list"); // MemberListServlet의 매핑 주소 : @WebServlet("/member/list")
			} else {
				request.getRequestDispatcher("/member/add.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
