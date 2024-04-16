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


@WebServlet("/board/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	파라미터로 가져온 값들을 jsp에 보이도록
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ServletContext context = request.getServletContext();
//  		DataSource dataSource = (DataSource)context.getAttribute("dataSource");
//  		BoardService service = new BoardService(dataSource);
//
//  		// 파라미터값 가져오기
//  		int updateNo = Integer.parseInt(request.getParameter("updateNo"));
//		String writer = request.getParameter("writer");
//		String title = request.getParameter("title"); 
//		String content = request.getParameter("content"); 
//		
//		int updateBoard = service.updateBoard(new BoardVO(updateNo,writer,title,content));
//
//		
//		//http://localhost:8080/board/update?no=13
//		//13번 글 수정 
//		if (updateBoard > 0) {
//			// 수정 성공한다면
//			BoardVO vo = new BoardVO(updateNo,writer,title,content);
//  			response.sendRedirect("/board/list");
//  			request.setAttribute("board", vo);
//  			request.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(request, response);
//  		} else {
//  			request.setAttribute("msg", "수정 실패");
//  			request.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(request, response);
//  		}
		ServletContext context = request.getServletContext();
  		DataSource dataSource = (DataSource)context.getAttribute("dataSource");
  		BoardService service = new BoardService(dataSource);
  		
  		List<BoardVO> list = service.getBoardList();
  		request.setAttribute("board", list);
		request.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
  		DataSource dataSource = (DataSource)context.getAttribute("dataSource");
  		BoardService service = new BoardService(dataSource);

  		// 파라미터값 가져오기
  		int updateNo = Integer.parseInt(request.getParameter("updateNo"));
		String writer = request.getParameter("writer");
		String title = request.getParameter("title"); 
		String content = request.getParameter("content"); 
		
		int updateBoard = service.updateBoard(new BoardVO(updateNo,writer,title,content));
		if(updateBoard > 0) {
			response.sendRedirect("/board/list");
		}else {
			request.setAttribute("msg", "등록 실패");
  			request.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(request, response);
		}
	}

}
