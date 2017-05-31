package com.zhku.base.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZIPUtil {

 public static void main(String[] args) throws Exception {
  unzip("E:/work/work/czmarketing/WebRoot/upload/test.zip", "E:/work/work/czmarketing/WebRoot/upload/");
 }

 static String getSuffixName(String name) {
  return name.substring(0, name.lastIndexOf("."));
 }

 public static void unzip(String zipFilePath, String unzipDirectory)
   throws Exception {
		  // 创建文件对象
		  File file = new File(zipFilePath);
		  // 创建zip文件对象
		  ZipFile zipFile = new ZipFile(file);
		  // 创建本zip文件解压目录
		  File unzipFile = new File(unzipDirectory);
		  if (unzipFile.exists())
		   unzipFile.delete();
		  unzipFile.mkdir();
		  // 得到zip文件条目枚举对象
		  Enumeration zipEnum = zipFile.entries();
		  // 定义输入输出流对�?
		  InputStream input = null;
		  OutputStream output = null;
		  // 循环读取条目
		  System.out
		    .println("name\t\t\tsize\t\t\tcompressedSize\t\t\tisDirectory");
		  while (zipEnum.hasMoreElements()) {
		   // 得到当前条目
		   ZipEntry entry = (ZipEntry) zipEnum.nextElement();
		   String entryName = new String(entry.getName().getBytes("ISO8859_1"));
		   System.out.println(entryName + "\t\t\t" + entry.getSize()
		     + "\t\t\t" + entry.getCompressedSize() + "\t\t\t\t\t\t\t"
		     + entry.isDirectory());
		
		   // 若当前条目为目录则创�?
		   if (entry.isDirectory())
		    new File(unzipFile.getAbsolutePath() + "/" + entryName).mkdir();
		   else { // 若当前条目为文件则解压到相应目录
		    input = zipFile.getInputStream(entry);
		    output = new FileOutputStream(new File(unzipFile
		      .getAbsolutePath()
		      + "/" + entryName));
		    byte[] buffer = new byte[1024 * 8];
		    int readLen = 0;
		    while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1)
		     output.write(buffer, 0, readLen);
		    input.close();
		    output.flush();
		    output.close();
		   }
		  }
		 }
}