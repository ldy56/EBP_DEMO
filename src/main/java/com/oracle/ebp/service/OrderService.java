package com.oracle.ebp.service;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oracle.ebp.dao.OrderMapper;
import com.oracle.ebp.domain.Orders;

/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
@Service
public class OrderService {

	@Resource
	private OrderMapper mapper;
	
	/**
	 * ��Ӷ���
	 * @param order
	 */
	public void addOrders(Orders order){
		mapper.addOrders(order);
	}
	
	/**
	 * ��ѯ���ж���
	 * @return
	 */
	public List<Orders> queryOrdersByUid(int uid){
		return mapper.queryOrdersByUid(uid);
	}
	
//	================================================����Ա����
	
	/**
	 * ���ݶ���ʱ���ҳ��ѯ����
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Orders> queryOrdersByDatePage(Date begin,Date end,int index,int pageSize){
		return mapper.queryOrdersByDatePage(begin, end, index, pageSize);
	}
	
	/**
	 * ���ݶ���ʱ���ѯ��������
	 * @param begin
	 * @param end
	 * @return int
	 */
	public int queryOrderCountByDate(Date begin,Date end){
		return mapper.queryOrderCountByDate(begin, end);
	}
	
	/**
	 * ����������ҳ��ѯ����
	 * @param oid		�������
	 * @param name		�û���������
	 * @param idCard	���֤��
	 * @param index		��ѯ��ʼλ��
	 * @param pageSize	��ѯ����
	 * @return
	 */
	public List<Orders> queryOrdersByCondition(int oid,String name,String idCard,int index,int pageSize){
		return mapper.queryOrdersByCondition(oid, name, idCard, index, pageSize);
	}
	/**
	 * ����������ѯ��������
	 * @param oid		�������
	 * @param name		�û���������
	 * @param idCard	���֤��
	 * @return
	 */
	public int queryOrdersCountByCondition(int oid,String name,String idCard){
		return mapper.queryOrdersCountByCondition(oid, name, idCard);
	}
	
}
