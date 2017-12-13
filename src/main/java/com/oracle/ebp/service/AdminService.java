package com.oracle.ebp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oracle.ebp.dao.AdminMapper;
import com.oracle.ebp.domain.AdminUser;

/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
@Service
public class AdminService {

	@Resource
	private AdminMapper mapper;
	
	/**
	 * ����Ա��¼
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminUser loginAdmin(String username,String password){
		return mapper.loginAdmin(username, password);
	}
	
}
