package com.oracle.ebp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oracle.ebp.dao.OrderListMapper;
import com.oracle.ebp.domain.OrderList;

/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
@Service
public class OrderListService {

	@Resource
	private OrderListMapper mapper;
	
	/**
	 * ��Ӷ�����ϸ��Ϣ
	 * @param ordersList
	 */
	public void addOrderList(OrderList ordersList){
		mapper.addOrderList(ordersList);
	}
	
	/**
	 * ������Ӷ�����ϸ��Ϣ
	 * @param ordersList
	 */
	public void addOrderLists(List<OrderList> list){
		mapper.addOrderLists(list);
	}
	
	/**
	 * ���ݶ���ID��ѯ������ϸ
	 * @return
	 */
	public List<OrderList> queryOrderListByOid(int oid){
		return mapper.queryOrderListByOid(oid);
	}
	
}
