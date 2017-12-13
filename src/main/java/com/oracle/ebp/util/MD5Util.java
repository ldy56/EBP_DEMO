package com.oracle.ebp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年2月27日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
public class MD5Util {
	
	public static void main(String[] args) {
		String str=MD5Util.getMD5("123456");
		System.out.println(str);
	}
	
	/**
	 * 进行MD5加密
	 * @param message
	 * @return String
	 */
	public static String getMD5(String message){
		if(message==null){
			return null;
		}
		String MD5Str="";
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] input = message.getBytes();
			
			byte[] digest = md.digest(input);
			
			MD5Str=bytesToHex(digest);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MD5Str;
	}
	
	public static String bytesToHex(byte[] bytes){
		StringBuffer md5Str=new StringBuffer();
		
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital=bytes[i];
			
			if(digital<0){	//将负数转换为正数
				digital+=256;
			}
			if(digital<16){ //十六进制占两位，追加一个0
				md5Str.append("0");
			}
			md5Str.append(Integer.toHexString(digital));
		}
		return md5Str.toString().toUpperCase();
	}
	
	
}