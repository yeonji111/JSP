package common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;

//@WebFilter("/*")

/** 
 * 선생님 버전의 annotation Filter: web.xml 에서 작성한 Filter의 initParams을 사용.
 * */  

@WebFilter(urlPatterns="/*", initParams={@WebInitParam(name="encoding", value="UTF-8")})

public class CharacterEncodingFilter extends HttpFilter implements Filter {
  
	private FilterConfig config;
	
//	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	
	}
	
	
	// 실제 필터가 작동하는 부분
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
					
		// 실행해야할 내용 (필터작업) 기술 
		request.setCharacterEncoding(config.getInitParameter("encoding"));
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
	
		// 가장 마지막에 위치 필터링 후 다음 작업을 위해 연결해주는 작업 (필수)
		chain.doFilter(request, response); 
	}

}
