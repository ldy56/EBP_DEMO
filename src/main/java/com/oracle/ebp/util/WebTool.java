package com.oracle.ebp.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.oracle.ebp.domain.User;

/**   
 * @Description:工具类
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
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
	 * 根据总数量和页面大小，获取总页数
	 * @param allCount 总页数
	 * @param pageSize 页面长度
	 * @return
	 */
	public static int getPageCount(int allCount,int pageSize){
		if(allCount==0){
			return 1;
		}
		return allCount%pageSize==0?allCount/pageSize:allCount/pageSize+1;
	}
	
	/**
	 * 向Model中添加当前页，和页面总数
	 * @param model
	 * @param pageCount	页面总数
	 * @param currentPage 当前页
	 */
	public static void addPageAttribute(Model model,int pageCount,int currentPage
			,Date begin,Date end){
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
	}
	
	/**
	 * 获取当前时间前七天时间。
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
	 * 获取当前时间
	 * @return sql.Date
	 */
	public static Date getCurrentDate() {
		return new Date(new java.util.Date().getTime());
	}
	
	/**
	 * 从Session中获取User对象
	 * @param session
	 * @return
	 */
	public static User getUserBySession(HttpSession session){
		return (User)session.getAttribute(Constants.SESSION_USER);
	}
	
	/**
	 * 从Session中设置User对象
	 * @param session
	 * @return
	 */
	public static void setUserBySession(HttpSession session,User user){
		session.setAttribute(Constants.SESSION_USER,user);
	}
	

}
