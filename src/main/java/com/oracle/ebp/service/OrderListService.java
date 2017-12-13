package com.oracle.ebp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oracle.ebp.dao.OrderListMapper;
import com.oracle.ebp.domain.OrderList;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Service
public class OrderListService {

	@Resource
	private OrderListMapper mapper;
	
	/**
	 * 添加订单详细信息
	 * @param ordersList
	 */
	public void addOrderList(OrderList ordersList){
		mapper.addOrderList(ordersList);
	}
	
	/**
	 * 批量添加订单详细信息
	 * @param ordersList
	 */
	public void addOrderLists(List<OrderList> list){
		mapper.addOrderLists(list);
	}
	
	/**
	 * 根据订单ID查询所有明细
	 * @return
	 */
	public List<OrderList> queryOrderListByOid(int oid){
		return mapper.queryOrderListByOid(oid);
	}
	
}
