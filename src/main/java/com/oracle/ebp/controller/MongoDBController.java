package com.oracle.ebp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��2��28��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
@Controller
public class MongoDBController {
	
//	@Resource(type=UserDaoImpl.class)
//	private UserDAO dao;
	
	@RequestMapping("/login")
	public void test(){
//		dao.createCollection();
		System.out.println("User���Ӵ����ɹ�..........");
	}
	
	
}
