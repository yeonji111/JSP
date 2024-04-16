package common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서버에서 저장된 로그인 상태를 삭제 => session에 저장되어 있는 member를 삭제
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		// 만약 세션의 모든 정보를 삭제하고 싶으면 invalidate() 메소드를 호출한다.
		session.invalidate();
		
		response.sendRedirect("/member/list");
	
	}
	
	
}
