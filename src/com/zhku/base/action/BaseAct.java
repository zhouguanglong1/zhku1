package com.zhku.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseAct  {
	
	
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, HttpServletResponse response, ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
		
	}
	

  	
	/**
	 * 将数组join字符串转成拼接字符串用于IN查询
	 * @param "aaa,bbb,ccc"
	 * @return "'aaa','bbb','ccc'"
	 */
	public String stringForIn(String string){
		String instrings="''";
		if(string!=null&&string.trim().length()>0){
			instrings=stringArryForIn(string.split(","));
		}
		return instrings;
	}
	public static String stringArryForIn(String[] stringarry){
		String instrings="''";
		if(stringarry!=null&&stringarry.length>0){
			instrings="";
			for (int i = 0; i < stringarry.length; i++) {
				instrings+=("'"+stringarry[i]+"',");
			}
			instrings=instrings.substring(0, instrings.lastIndexOf(","));
		}
		return instrings;
	}
	

	
	
    /**
     * 文件下载 主要方法
     * @param request
     * @param response
     * @param storeName "template/schoolMarkTemplate.xlsx"
     * @param realName "校园营销用户信息导入模板.xlsx"  实际下载时文件名
     * @param contentType "application/octet-stream" 、"application/vnd.ms-excel"
     * @throws Exception
     */
	public static void download(HttpServletRequest request,HttpServletResponse response,
			String storeName,String realName, String contentType)throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		// 获取项目根目录
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		if (!ctxPath.endsWith(java.io.File.separator)) {
			ctxPath = ctxPath + java.io.File.separator;
	    }
		// 获取下载文件路径
		String downLoadPath = ctxPath + "upload/" + storeName;
//		storeName = storeName.substring(storeName.lastIndexOf("/") + 1,storeName.length());
		storeName = realName;
		// 获取文件的长度
		long fileLength = new File(downLoadPath).length();
		// 兼容IE和firefox下载文件中文乱码问题
		String agent = request.getHeader("User-Agent");
		boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
		if (isMSIE) {
			storeName = URLEncoder.encode(storeName, "UTF-8");
		} else {
			storeName = new String(storeName.getBytes("UTF-8"), "ISO-8859-1");
		}
		// 设置文件输出类型
		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename="+ storeName);
		// 设置输出长度
		response.setHeader("Content-Length", String.valueOf(fileLength));
		// 获取输入流
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		// 输出流
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		// 关闭流
		bis.close();
		bos.close();
	}
	
	// AJAX输出，返回null
		public String ajax(String content, String type,HttpServletResponse response) {
			try {
				//HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType(type + ";charset=UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().write(content);
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	    protected String listTojson(List<?> list,HttpServletResponse response){
			
			//String name = list.get(0).getClass().getSimpleName();
			Map<String, List> map = new HashMap<String, List>();
			map.put("rows", list);
			//map.put("page", p.getPageNumber());
			
			JsonConfig jsonConfig = new JsonConfig();
	        jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor(){
	 
	            @Override
	            public Object processArrayValue(Object arg0, JsonConfig arg1) {
	                return null;
	            }
	 
	            @Override
	            public Object processObjectValue(String arg0, Object arg1,
	                    JsonConfig arg2) {
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                String date ="";
	                if(arg1!=null){
	                	date = sdf.format(((Date)arg1));
	                }
	                
	                return date;
	            }
	             
	        });
			
			JSONObject jsonObject = JSONObject.fromObject(map,jsonConfig);
			return ajax(jsonObject.toString(), "text/html",response);
		}
	    public String mapAjaxJson(Map jsonMap,HttpServletResponse response) {
	    	JSONObject jsonObject = JSONObject.fromObject(jsonMap);
	    	return ajax(jsonObject.toString(), "text/html",response);
	    	}
	    

	    protected Map<String, Object> getValueMap(Object obj) {  
	    	  
	        Map<String, Object> map = new HashMap<String, Object>();  
	        // System.out.println(obj.getClass());  
	        // 获取f对象对应类中的所有属性域  
	        Field[] fields = obj.getClass().getDeclaredFields();  
	        for (int i = 0, len = fields.length; i < len; i++) {  
	            String varName = fields[i].getName();  
	            try {  
	            	if(!(varName.equals("roles")||varName.equals("funcs")||varName.equals("users"))){
		                // 获取原来的访问控制权限  
		                boolean accessFlag = fields[i].isAccessible();  
		                // 修改访问控制权限  
		                fields[i].setAccessible(true);  
		                // 获取在对象f中属性fields[i]对应的对象中的变量  
		                Object o = fields[i].get(obj);  
						if (o != null) {
							if(o instanceof Date){
								map.put(varName, sdfs.format(o));
							}else{
								map.put(varName, o.toString());
							}
						} else {
							map.put(varName, "");
						}
		                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
		                // 恢复访问控制权限  
		                fields[i].setAccessible(accessFlag);  
	                }
	            } catch (IllegalArgumentException ex) {  
	                ex.printStackTrace();  
	            } catch (IllegalAccessException ex) {  
	                ex.printStackTrace();  
	            }  
	        }  
	        return map;  
	  
	    }  
	
}
