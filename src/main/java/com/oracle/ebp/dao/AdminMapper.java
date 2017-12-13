package com.oracle.ebp.dao;

import com.oracle.ebp.domain.AdminUser;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
public interface AdminMapper {
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminUser loginAdmin(String username,String password);
	
}
