package com.zhku.base.util;

import java.util.Date;
import java.util.Random;


/**
 * UUID生成器帮助类
 * @create 2016�?�?1�?上午10:41:47
 * @author lbd
 * @version V1.0
 */
public class UUIDHelper {
	
	/**
	 * 生成17位UUID字符
	 * 格式�?3位时间戳 ＋４位随机数
	 */
	public static String generateUUID(){
		StringBuffer uuidString = new StringBuffer();
		uuidString.append(DateUtil.formatDate(new Date(), "yyyyMMddHHmmsss"));
		uuidString.append(String.format("%04d", new Random().nextInt(10000)));
		return uuidString.toString();
	}

}
