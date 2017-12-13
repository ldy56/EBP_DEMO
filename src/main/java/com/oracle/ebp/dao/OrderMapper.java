package com.oracle.ebp.dao;

import java.sql.Date;
import java.util.List;

import com.oracle.ebp.domain.Orders;
import com.oracle.ebp.domain.User;

/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
public interface OrderMapper {

	/**
	 * ��Ӷ���
	 * @param order
	 */
	public void addOrders(Orders order);

	/**
	 * ��ѯָ���û������ж���
	 * @return
	 */
	public List<Orders> queryOrdersByUid(int uid);
	
	
//	================================================����Ա����
	
	/**
	 * ���ݶ���ʱ���ҳ��ѯ����
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Orders> queryOrdersByDatePage(Date begin,Date end,int index,int pageSize);
	
	/**
	 * ���ݶ���ʱ���ѯ��������
	 * @param begin
	 * @param end
	 * @return int
	 */
	public int queryOrderCountByDate(Date begin,Date end);
	
	/**
	 * ����������ҳ��ѯ����
	 * @param oid		�������
	 * @param name		�û���������
	 * @param idCard	���֤��
	 * @param index		��ѯ��ʼλ��
	 * @param pageSize	��ѯ����
	 * @return
	 */
	public List<Orders> queryOrdersByCondition(int oid,String name,String idCard,int index,int pageSize);
	
	/**
	 * ����������ѯ��������
	 * @param oid		�������
	 * @param name		�û���������
	 * @param idCard	���֤��
	 * @return
	 */
	public int queryOrdersCountByCondition(int oid,String name,String idCard);
	
}
