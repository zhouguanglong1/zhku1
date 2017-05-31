package com.zhku.base.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Administrator TODO To change the template for this generated type
 *         comment go to Window - Preferences - Java - Code Style - Code
 *         Templates
 */
public class EncodingFilter implements Filter {

	protected FilterConfig _filterConfig = null;

	protected static String CONTENT_TYPE = "text/html; charset=";

	public void init(FilterConfig filterConfig) throws ServletException {
		_filterConfig = filterConfig;
	}

	/**
	 * doFilter() : doFilter() method called before the servlet to which this
	 * filter is mapped is invoked.
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException, ServletException {

		String encoding = _filterConfig.getInitParameter("encoding");
		request.setCharacterEncoding(encoding);
		response.setContentType(CONTENT_TYPE + encoding);
		chain.doFilter(request, response);
	}

	/**
	 * destroy() : destroy() method called when the filter is taken out of
	 * service.
	 */
	public void destroy() {
		this._filterConfig = null;
	}
}