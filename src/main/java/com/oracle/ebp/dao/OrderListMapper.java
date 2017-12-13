package com.oracle.ebp.dao;

import java.util.List;

import com.oracle.ebp.domain.OrderList;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
public interface OrderListMapper {
	
	/**
	 * 添加订单详细信息
	 * @param ordersList
	 */
	public void addOrderList(OrderList ordersList);
	
	/**
	 * 批量添加订单详细信息
	 * @param ordersList
	 */
	public void addOrderLists(List<OrderList> list);
	
	/**
	 * 根据订单ID查询所有明细
	 * @return
	 */
	public List<OrderList> queryOrderListByOid(Integer oid);
	
	
	
}
