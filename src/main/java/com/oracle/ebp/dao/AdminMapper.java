package com.oracle.ebp.dao;

import com.oracle.ebp.domain.AdminUser;

/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
public interface AdminMapper {
	
	/**
	 * ����Ա��¼
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminUser loginAdmin(String username,String password);
	
}
