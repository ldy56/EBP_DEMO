package com.oracle.ebp.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.ebp.domain.OrderList;
import com.oracle.ebp.domain.Orders;
import com.oracle.ebp.domain.User;
import com.oracle.ebp.service.OrderListService;
import com.oracle.ebp.service.OrderService;
import com.oracle.ebp.util.WebTool;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Controller
public class OrderController {

	@Resource
	private OrderService service;
	
	@Resource
	private OrderListService orderListService;
	
	
	/**
	 * 根据订单ID查询对应的订单明细
	 * @param model
	 * @param oid
	 * @return
	 */
	@RequestMapping("/queryAllOrderList")
	public String queryAllOrderList(Model model,Integer oid){
		List<OrderList> list = orderListService.queryOrderListByOid(oid);
		model.addAttribute("orderList", list);
		return "user/ShowOrdersList";
	}
	
	/**
	 * 根据用户ID查询所有的订单
	 * @return
	 */
	@RequestMapping("/user/queryAllOrders")
	public String queryAllOrders(HttpSession session,Model model){
		//查询当前用户所有的订单以及订单明细
		User user=WebTool.getUserBySession(session);
		
		List<Orders> orderList = service.queryOrdersByUid(user.getUid());
		//将订单保存到request作用域中
		model.addAttribute("orderList", orderList);
		
		return "myshopping";
	}
	
//	================================================管理员方法
	private int pageSize=5;		//页面显示条数
	/**
	 * 根据时间分页查询订单
	 * @return
	 */
	@RequestMapping("admin/queryOrderByDate")
	public String queryOrderByDate(Date begin,Date end,Integer currentPage,Model model,String isAjax){
		
		if(end == null){//默认查询起始时间
			end=WebTool.getCurrentDate();
		}
		if(begin ==null){//默认查询结束数据
			begin=WebTool.getBeforeDate(end);
		}
		
		if(currentPage==null || currentPage<1){//默认查询第一页
			currentPage=1;
		}
		//查询总条数
		int allCount=service.queryOrderCountByDate(begin, end);
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//获取查询下标
		int index=(currentPage-1)*pageSize;
		
		List<Orders> list = service.queryOrdersByDatePage(begin, end, index, pageSize);
		
		model.addAttribute("list", list);
		WebTool.addPageAttribute(model, pageCount, currentPage,begin,end);
		
		model.addAttribute("isDate", 1);//设置是日期查询还是 条件查询

		if(isAjax!=null){//ajax请求
			return "WEB-INF/jspf/admin/ShowAllOrderAjax";
		}
		return "admin/ShowAllOrder";
	}
	
}
