package com.zhku.base.util;


import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



/**
 * @author Administrator TODO To change the template for this generated type
 *         comment go to Window - Preferences - Java - Code Style - Code
 *         Templates
 */
public class EVFilter implements Filter {

	protected FilterConfig _filterConfig = null;

	protected static String CONTENT_TYPE = "text/html; charset=";

	private static final transient Logger log = Logger
			.getLogger(EVFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		_filterConfig = filterConfig;
	}

	/**
	 * doFilter() : doFilter() method called before the servlet to which this
	 * filter is mapped is invoked.
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException, ServletException {
		
		HttpServletRequest requ = (HttpServletRequest) request;
		HttpSession session = requ.getSession();
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String strUrl = requ.getRequestURL().toString();
		String cotenxt = requ.getContextPath();
		String strQuery = requ.getQueryString();
		
		/**
		 * 检查系统功能链接访问权限
		 * */
		String link = requ.getRequestURI();
		link = link.replace(cotenxt + "/", "");
		if(strQuery != null){
			if(link.endsWith(".do")){
				link += "?" + strQuery;
			}else{
				link  += "&" + strQuery;
			}
		}
		Map funcLinks = (Map)session.getAttribute("funcLinks");//获取系统启用的链接功能f
		Map currUserFuncCodes  = (Map)session.getAttribute("currUserFuncCodes");//获取当前用户系统功能code-link map
		if(funcLinks != null && funcLinks.containsKey(link)){//判断用户访问链接是否系统的启用链接功能
			if(currUserFuncCodes != null && !currUserFuncCodes.containsValue(link)){//判断当前用户是否拥护访问当前请求链接的权限
/*PrintWriter writer = resp.getWriter();
				StringBuilder script = new StringBuilder("<script>");
				script.append("alert('对不起，您无访问该功能的权限!');");
				script.append("</script>");
				writer.print(script.toString());
return;*/
				
			}
		}
		/***/
		
		if (isUrlThatShouldCheckUser(strUrl)) {
			// 如果是需要检验用户的请求资源

			

			// 判断是否是从portal 登陆进来的
			/*Object attr = session.getAttribute("isFromPortal");
			boolean isFromPortal = attr != null && (Boolean) attr;*/

			// 当前登陆用户
			Object loginUser = session.getAttribute("LoginUser");

		/*	if (isFromPortal) {
				// 如果从portal 登陆
				if (getCookie(requ, "unmpssotoken") == null
						|| loginUser == null) {
					// 没有用户, 则调回到信息资讯的首页
					redirectToPortal(resp);
					return;
				}
			}*/

			// 本地测试登陆
			if (loginUser == null) {
				session.setAttribute("EnterPageUrl", strUrl + "?" + strQuery);
				//resp.sendRedirect(requ.getContextPath() + "/index.jsp");
				resp.sendRedirect(requ.getContextPath() + "/common/jsp/noLoginUserError.jsp");
				return;
			}
		}
		
		
	
		//设置ThreadLocal级的当前用户
		//ContextManager.setCurrentUser((User)requ.getSession().getAttribute("LoginUser"));
		
		//设置ThreadLocal级的当前用户功能权限列表
	//	ContextManager.setCurrUserFuncCodes(currUserFuncCodes);
		//ContextManager.setCurrUserFuncCodes((Map)session.getAttribute("currUserFuncCodes"));
		
		//在系统Filter中增加了Open Session In View 模式
        //Connection conn = null;
        try{
        	//HibernateUtil.getSession().beginTransaction();
        	chain.doFilter(request, response);
        	//conn = HibernateUtil.getSession().connection();
        	//conn.commit();
        }catch(Exception e){
        	log.error(e);
        	throw new ServletException(e);
        }finally{
        	
        }
        //移除当前用户
		//ContextManager.removeCurrentUser();
		//移除当前用户功能编码映射
		//ContextManager.removeCurrUserFuncCodes();
	}

	


	/**
	 * 判断这个资源是否是需要检验用户
	 * 
	 * @param url
	 *            当前请求资源
	 * @return 如果这个资源是需要检验用户, 则返回true
	 */
	private boolean isUrlThatShouldCheckUser(String url) {
		if (isServletThatShouldCheckUser(url)) {
			return true;
		}

		int iIndex = url.lastIndexOf("/");
		if (iIndex <= 0) {
			return false;
		}
		// 注意, 这里所有字母都转成小写的形式了...
		String pageType = url.substring(iIndex + 1).toLowerCase();
		return isPageThatShouldCheckUser(pageType);
	}

	/**
	 * 判断这个资源是否是需要检验用户的Servlet
	 * 
	 * @param url
	 *            当前请求资源
	 * @return 如果这个资源是需要检验用户的Servlet, 则返回true
	 */
	private boolean isServletThatShouldCheckUser(String url) {
		return url.indexOf("/servlet/") > 0
				&& url.indexOf("/servlet/rpcrouter") < 0;
	}

	/**
	 * 判断这个页面是否是需要检验用户的页面
	 * 
	 * @param pageType
	 *            当前请求资源
	 * @return 如果这个页面是需要检验用户的页面, 则返回true
	 */
	private boolean isPageThatShouldCheckUser(String pageType) {
		if (pageType.endsWith(".do")) {
			return true;
		}
		if ("login.action".equals(pageType)) {
			return false;
		}
		if (isJspThatShouldCheckUser(pageType)) {
			return true;
		}
		if (pageType.endsWith(".html")) {
			return true;
		}
		if (pageType.endsWith(".action")) {
			return true;
		}
		if (pageType.equals("index.htm")) {
			return false;
		}
		return pageType.endsWith(".htm");
	}

	/**
	 * 判断这个页面是否是需要检验用户的jsp 页面
	 * 
	 * @param pageType
	 *            当前请求资源
	 * @return 如果这个页面是需要检验用户的jsp 页面, 则返回true
	 */
	private boolean isJspThatShouldCheckUser(String pageType) {
		if (!pageType.endsWith(".jsp")) {
			return false;
		}

		String[] passedJSPs = { "index.jsp",
				"top.jsp", "bottom.jsp", "login.jsp","nologinusererror.jsp"};
		Arrays.sort(passedJSPs);
		if (Arrays.binarySearch(passedJSPs, pageType) >= 0) {
			// 如果参数pageType 包含在passedJSPs 中
			return false;
		}

		return true;
	}

	/**
	 * destroy() : destroy() method called when the filter is taken out of
	 * service.
	 */
	public void destroy() {
		this._filterConfig = null;
	}

	public static void main(String[] args) {
	}
}