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
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
@Controller
public class OrderController {

	@Resource
	private OrderService service;
	
	@Resource
	private OrderListService orderListService;
	
	
	/**
	 * ���ݶ���ID��ѯ��Ӧ�Ķ�����ϸ
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
	 * �����û�ID��ѯ���еĶ���
	 * @return
	 */
	@RequestMapping("/user/queryAllOrders")
	public String queryAllOrders(HttpSession session,Model model){
		//��ѯ��ǰ�û����еĶ����Լ�������ϸ
		User user=WebTool.getUserBySession(session);
		
		List<Orders> orderList = service.queryOrdersByUid(user.getUid());
		//���������浽request��������
		model.addAttribute("orderList", orderList);
		
		return "myshopping";
	}
	
//	================================================����Ա����
	private int pageSize=5;		//ҳ����ʾ����
	/**
	 * ����ʱ���ҳ��ѯ����
	 * @return
	 */
	@RequestMapping("admin/queryOrderByDate")
	public String queryOrderByDate(Date begin,Date end,Integer currentPage,Model model,String isAjax){
		
		if(end == null){//Ĭ�ϲ�ѯ��ʼʱ��
			end=WebTool.getCurrentDate();
		}
		if(begin ==null){//Ĭ�ϲ�ѯ��������
			begin=WebTool.getBeforeDate(end);
		}
		
		if(currentPage==null || currentPage<1){//Ĭ�ϲ�ѯ��һҳ
			currentPage=1;
		}
		//��ѯ������
		int allCount=service.queryOrderCountByDate(begin, end);
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//��ȡ��ѯ�±�
		int index=(currentPage-1)*pageSize;
		
		List<Orders> list = service.queryOrdersByDatePage(begin, end, index, pageSize);
		
		model.addAttribute("list", list);
		WebTool.addPageAttribute(model, pageCount, currentPage,begin,end);
		
		model.addAttribute("isDate", 1);//���������ڲ�ѯ���� ������ѯ

		if(isAjax!=null){//ajax����
			return "WEB-INF/jspf/admin/ShowAllOrderAjax";
		}
		return "admin/ShowAllOrder";
	}
	
}
