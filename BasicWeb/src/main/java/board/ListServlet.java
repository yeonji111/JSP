package board;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/board/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		ServletContext context = request.getServletContext();
  		DataSource dataSource = (DataSource)context.getAttribute("dataSource");
  		
  		BoardService service = new BoardService(dataSource);
  		
  		List<BoardVO> list = service.getBoardList();
  		request.setAttribute("boards", list);
  		request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
  	}

}
