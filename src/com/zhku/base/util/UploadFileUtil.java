package com.zhku.base.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {
	
	
	public static String uploadImg(MultipartFile file, HttpServletRequest request){
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
		String dir = request.getSession().getServletContext().getRealPath("/") + "upload"+java.io.File.separator+""; 
        String uuid = UUID.randomUUID().toString();
		String filePath = dir+uuid+"."+ext;     
		String [] arrType= {"jpg","jpeg","gif","png","JPG","JPEG","GIF","PNG"};
			if(file!=null){
			 if(!file.isEmpty()) {
				     	boolean isRealType = false;  
				        for (String str : arrType) {  
				            if(ext.equals(str)){  
				                isRealType = true;  
				                break;  
				            }  
				        }
				        if(!isRealType){  
	                        //提示错误信息:文件格式不正�? 
	                        return "2";  
	                    } 
				        if(file.getSize()>2097152){
				        	//提示错误信息:图片大于2m
	                        return "3"; 
				        }

		            try {  
		                // 文件保存路径  
		                File newFile = new File(filePath);
		                File fileDir = new File(dir);
		                if(!newFile.exists()&&!newFile.isDirectory()){
		                	fileDir .mkdir();  
		                }
		                // 转存文件  
		                file.transferTo(new File(filePath));      
		            } catch (Exception e) {  
		                e.printStackTrace();  
		            }  
		        }  
			}
		return uuid;
	}
	
	/**
	 * 上传ZIP格式图片
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static String uploadZip(MultipartFile file, HttpServletRequest request) throws Exception{
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
		String dir = request.getSession().getServletContext().getRealPath("/") + "upload"+java.io.File.separator+"zipfile"+java.io.File.separator+""; 
        String filePath = dir+UUID.randomUUID().toString()+"."+ext;
		String [] arrType= {"zip"};
		if(file!=null){
		 if(!file.isEmpty()) {
			        if(!ext.equals("zip")){  
                        //提示错误信息:文件格式不正�? 
                        return "2";  
                    } 

	            try {  
	                // 文件保存路径  
	                File newFile = new File(filePath);
	                File fileDir = new File(dir);
	                if(!newFile.exists()&&!newFile.isDirectory()){
	                	fileDir .mkdir();  
	                }
	                // 转存文件  
	                file.transferTo(new File(filePath));      
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
		}
		
		ZIPUtil.unzip(filePath, request.getRealPath("/")+"upload");
		return "1";
	}
	
	/**
	 * 上传Excel文件
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String uploadExcel(MultipartFile file, HttpServletRequest request) throws Exception{
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
		String dir = request.getSession().getServletContext().getRealPath("/") + "upload"+java.io.File.separator+"excelfile"+java.io.File.separator+""; 
        String filePath = dir+UUID.randomUUID().toString()+"."+ext;
		String [] arrType= {"xls","xlsx"};
		if(file!=null){
		 if(!file.isEmpty()) {
		 		boolean isRealType = false;  
		        for (String str : arrType) {  
		            if(ext.equals(str.toLowerCase())){  
		                isRealType = true;  
		                break;  
		            }  
		        }   
		        if(!isRealType){  
                    //提示错误信息:文件格式不正�? 
                    return "2";  
                }
	            try {  
	                // 文件保存路径  
	                File newFile = new File(filePath);
	                File fileDir = new File(dir);
	                if(!newFile.exists()&&!newFile.isDirectory()){
	                	fileDir .mkdir();  
	                }
	                // 转存文件  
	                file.transferTo(new File(filePath));      
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
		}
		return filePath;
	}
	
	/**
	 * 上传TXT文件
	 * @param file
	 * @param request
	 * @return
	 */
	public static String uploadTxt(MultipartFile file, HttpServletRequest request){
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
		String dir = request.getSession().getServletContext().getRealPath("/") + "upload"+java.io.File.separator+"zipfile"+java.io.File.separator+""; 
        String filePath = dir+UUID.randomUUID().toString()+"."+ext;
		if(file!=null){
			if(!file.isEmpty()) {
		        if(!ext.equals("txt")){  
                    //提示错误信息:文件格式不正�? 
                    return "2";  
                } 
	            try {  
	                // 文件保存路径  
	                File newFile = new File(filePath);
	                File fileDir = new File(dir);
	                if(!newFile.exists()&&!newFile.isDirectory()){
	                	fileDir .mkdir();  
	                }
	                // 转存文件  
	                file.transferTo(new File(filePath));      
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
		}	
		return filePath;
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @param request
	 * @return
	 */
	public static String uploadFile(MultipartFile file, HttpServletRequest request){
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
		String dir = request.getSession().getServletContext().getRealPath("/") + "upload"+java.io.File.separator+"file"+java.io.File.separator+""; 
        
		String uuid = UUID.randomUUID().toString();
		
		String filePath = dir+uuid+"."+ext;     
		if(file!=null){
			 if(!file.isEmpty()) {
		        if(file.getSize()>2097152){
		        	//提示错误信息:文件大于2m
                    return "3"; 
		        }
	            try {  
	                // 文件保存路径  
	                File newFile = new File(filePath);
	                File fileDir = new File(dir);
	                if(!newFile.exists()&&!newFile.isDirectory()){
	                	fileDir .mkdir();  
	                }
	                // 转存文件  
	                file.transferTo(new File(filePath));      
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
		}
		return uuid+"."+ext;
	}
	/**
	 * 上传文件APK
	 * @param file
	 * @param request
	 * @return
	 */
	public static String uploadAPKFile(MultipartFile file, HttpServletRequest request,String fileName){
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
		String dir = request.getSession().getServletContext().getRealPath("/") + "upload"+File.separator+"apk"+File.separator+""; 
        String uuid = UUID.randomUUID().toString();
		
		String filePath = dir+uuid+"."+ext;   
		if(file!=null){
			 if(!file.isEmpty()) {
		        if(file.getSize()>31457280){
		        	//提示错误信息:文件大于30m
                    return "3";
		        }
	            try {  
	                // 文件保存路径  
	                File newFile = new File(filePath);
	                File fileDir = new File(dir);
	                if(!newFile.exists()&&!newFile.isDirectory()){
	                	fileDir .mkdir();  
	                }
	                
	                if(newFile.exists()){
	                	newFile.delete();
	                }
	                // 转存文件  
	                file.transferTo(new File(filePath));      
	            } catch (Exception e) {  
	                e.printStackTrace();  
	                return "4";
	            }  
	        }  
		}
		return uuid+"."+ext;
	}
}
