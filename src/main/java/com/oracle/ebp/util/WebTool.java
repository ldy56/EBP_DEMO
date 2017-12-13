package com.oracle.ebp.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.oracle.ebp.domain.User;

/**   
 * @Description:������
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
public class WebTool {
	
	public static void main(String[] args) {
		java.util.Date date=new java.util.Date();
		String dateStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		
		Date d2=Date.valueOf("2012-12-12");
		Date dateS=new Date(date.getTime());
		
		Timestamp time=new Timestamp(dateS.getTime());
		
		System.out.println(time);
		
		
	}
	
	/**
	 * ������������ҳ���С����ȡ��ҳ��
	 * @param allCount ��ҳ��
	 * @param pageSize ҳ�泤��
	 * @return
	 */
	public static int getPageCount(int allCount,int pageSize){
		if(allCount==0){
			return 1;
		}
		return allCount%pageSize==0?allCount/pageSize:allCount/pageSize+1;
	}
	
	/**
	 * ��Model����ӵ�ǰҳ����ҳ������
	 * @param model
	 * @param pageCount	ҳ������
	 * @param currentPage ��ǰҳ
	 */
	public static void addPageAttribute(Model model,int pageCount,int currentPage
			,Date begin,Date end){
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
	}
	
	/**
	 * ��ȡ��ǰʱ��ǰ����ʱ�䡣
	 * @param date
	 * @return
	 */
	public static Date getBeforeDate(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -137);
		Date beforeDate=new Date(cal.getTime().getTime());
		
		return beforeDate;
	}
	
	/**
	 * ��ȡ��ǰʱ��
	 * @return sql.Date
	 */
	public static Date getCurrentDate() {
		return new Date(new java.util.Date().getTime());
	}
	
	/**
	 * ��Session�л�ȡUser����
	 * @param session
	 * @return
	 */
	public static User getUserBySession(HttpSession session){
		return (User)session.getAttribute(Constants.SESSION_USER);
	}
	
	/**
	 * ��Session������User����
	 * @param session
	 * @return
	 */
	public static void setUserBySession(HttpSession session,User user){
		session.setAttribute(Constants.SESSION_USER,user);
	}
	

}
