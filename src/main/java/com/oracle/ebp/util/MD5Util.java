package com.oracle.ebp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��2��27��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
public class MD5Util {
	
	public static void main(String[] args) {
		String str=MD5Util.getMD5("123456");
		System.out.println(str);
	}
	
	/**
	 * ����MD5����
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
			
			if(digital<0){	//������ת��Ϊ����
				digital+=256;
			}
			if(digital<16){ //ʮ������ռ��λ��׷��һ��0
				md5Str.append("0");
			}
			md5Str.append(Integer.toHexString(digital));
		}
		return md5Str.toString().toUpperCase();
	}
	
	
}