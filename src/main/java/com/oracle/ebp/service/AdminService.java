package com.oracle.ebp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oracle.ebp.dao.AdminMapper;
import com.oracle.ebp.domain.AdminUser;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Service
public class AdminService {

	@Resource
	private AdminMapper mapper;
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminUser loginAdmin(String username,String password){
		return mapper.loginAdmin(username, password);
	}
	
}
