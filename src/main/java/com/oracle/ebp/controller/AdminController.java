package com.oracle.ebp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.ebp.domain.AdminUser;
import com.oracle.ebp.domain.User;
import com.oracle.ebp.service.AdminService;
import com.oracle.ebp.util.Constants;
import com.oracle.ebp.util.MD5Util;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Controller
public class AdminController {

	@Resource
	private AdminService service;
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @return 
	 */
	@RequestMapping("/admin/login")
	public String login(String username,String password,HttpSession session,Model model){
		AdminUser admin = service.loginAdmin(username, MD5Util.getMD5(password));
		
		if(admin==null){
			model.addAttribute("errors", "用户名或密码错误！");
			return "admin/adminLogin";
		}
		
		//将user保存在session中
		session.setAttribute(Constants.SESSION_ADMIN,admin);
		return "admin/adminindex";
	}
	
	/**
	 * 管理员退出
	 * @return
	 */
	@RequestMapping("/admin/logout")
	public String logout(HttpSession session){
		//将用户从session中删除
		session.removeAttribute(Constants.SESSION_ADMIN);

		return "admin/adminLogin";
	}
	
	
}
