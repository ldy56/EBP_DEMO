package com.oracle.ebp.dao;

import java.util.List;

import com.oracle.ebp.domain.OrderList;

/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
public interface OrderListMapper {
	
	/**
	 * ��Ӷ�����ϸ��Ϣ
	 * @param ordersList
	 */
	public void addOrderList(OrderList ordersList);
	
	/**
	 * ������Ӷ�����ϸ��Ϣ
	 * @param ordersList
	 */
	public void addOrderLists(List<OrderList> list);
	
	/**
	 * ���ݶ���ID��ѯ������ϸ
	 * @return
	 */
	public List<OrderList> queryOrderListByOid(Integer oid);
	
	
	
}
