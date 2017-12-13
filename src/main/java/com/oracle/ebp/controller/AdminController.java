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
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
@Controller
public class AdminController {

	@Resource
	private AdminService service;
	
	/**
	 * ����Ա��¼
	 * @param username
	 * @param password
	 * @return 
	 */
	@RequestMapping("/admin/login")
	public String login(String username,String password,HttpSession session,Model model){
		AdminUser admin = service.loginAdmin(username, MD5Util.getMD5(password));
		
		if(admin==null){
			model.addAttribute("errors", "�û������������");
			return "admin/adminLogin";
		}
		
		//��user������session��
		session.setAttribute(Constants.SESSION_ADMIN,admin);
		return "admin/adminindex";
	}
	
	/**
	 * ����Ա�˳�
	 * @return
	 */
	@RequestMapping("/admin/logout")
	public String logout(HttpSession session){
		//���û���session��ɾ��
		session.removeAttribute(Constants.SESSION_ADMIN);

		return "admin/adminLogin";
	}
	
	
}
