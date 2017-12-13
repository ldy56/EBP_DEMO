package com.oracle.ebp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年2月28日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Controller
public class MongoDBController {
	
//	@Resource(type=UserDaoImpl.class)
//	private UserDAO dao;
	
	@RequestMapping("/login")
	public void test(){
//		dao.createCollection();
		System.out.println("User连接创建成功..........");
	}
	
	
}
