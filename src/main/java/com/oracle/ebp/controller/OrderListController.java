package com.oracle.ebp.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.ebp.domain.OrderList;
import com.oracle.ebp.service.OrderListService;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Controller
public class OrderListController {

	@Resource
	private OrderListService service;
	
	
	/**
	 * 根据订单id查询所有的订单明细项
	 * @param oid
	 * @return
	 */
	@RequestMapping("/queryOrderListByOid")
	public String queryOrderListByOid(Integer oid,HttpServletResponse resp,HttpServletRequest req){
		List<OrderList> list = service.queryOrderListByOid(oid);
		req.setAttribute("orderList", list);
		return "orderListAjax";
	}
	
	
}
