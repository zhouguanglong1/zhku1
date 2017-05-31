package com.zhku.base.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.zhku.community.bean.luntan.Post;
import com.zhku.community.service.PostService;

public class IndexFilter implements Filter {

	private PostService postService;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		List<Post> twoPostList = postService.findTopTwo();
		request.setAttribute("twoPostList", twoPostList);
		request.getRequestDispatcher("${pageContext.request.contextPath}/index.jsp").forward(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext sc = filterConfig.getServletContext(); 
        XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
        
        if(cxt != null && cxt.getBean("postService") != null && postService == null){
        	postService = (PostService) cxt.getBean("postService");
        }
	}

}
