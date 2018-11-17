package br.com.daniellima.refundservice.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

public class CorsFilter implements Filter{
	
	private String origin;
	
	public CorsFilter(String origin) {
		this.origin = origin;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String method = request.getMethod();
		response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PATCH,OPTIONS,DELETE,PUT");
		response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		
		if ("OPTIONS".equals(method)) {
			response.setStatus(HttpStatus.OK.value());
		} else {
			chain.doFilter(req, res);
		}
	}
	
	@Override
	public void destroy() {
		
	}

}
