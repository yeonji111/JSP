package board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Controller 역할을 한다.
 */

@WebServlet("/board/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
  		DataSource dataSource = (DataSource)context.getAttribute("dataSource");
  		BoardService service = new BoardService(dataSource);
  		
		int deleteNo = Integer.parseInt(request.getParameter("deleteNo")); 
		int deleteBoard = service.deleteBoard(deleteNo);	
		
		//http://localhost:8080/board/delete?no=6
		//6번 글 삭제 완료 
		
		if (deleteBoard > 0) {
			// 삭제 성공
  			response.sendRedirect("/board/list");
  		} else {
  			request.setAttribute("msg", "삭제 실패");
  			request.getRequestDispatcher("/WEB-INF/views/board/delete.jsp").forward(request, response);
  		}


	
	}

}
