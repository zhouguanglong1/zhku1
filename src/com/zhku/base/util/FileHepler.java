package com.zhku.base.util;

/**
 * 文件帮助工具
 * @create 2016�?�?1�?下午8:59:53
 * @author lbd
 * @version V1.0
 */
public class FileHepler {
	
	/** Web根目�?*/
	private static String webRootPath = "";
	
	static{
		
		String classRootFile = FileHepler.class.getClassLoader().getResource("/").getPath();
		webRootPath = classRootFile.substring(0, classRootFile.indexOf("WEB-INF/classes/"));
		
	}
	
	public static String getWebRootPath(){
		return webRootPath;
	}

}
