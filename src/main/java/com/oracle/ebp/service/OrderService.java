package com.oracle.ebp.service;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oracle.ebp.dao.OrderMapper;
import com.oracle.ebp.domain.Orders;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Service
public class OrderService {

	@Resource
	private OrderMapper mapper;
	
	/**
	 * 添加订单
	 * @param order
	 */
	public void addOrders(Orders order){
		mapper.addOrders(order);
	}
	
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Orders> queryOrdersByUid(int uid){
		return mapper.queryOrdersByUid(uid);
	}
	
//	================================================管理员方法
	
	/**
	 * 根据订单时间分页查询订单
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Orders> queryOrdersByDatePage(Date begin,Date end,int index,int pageSize){
		return mapper.queryOrdersByDatePage(begin, end, index, pageSize);
	}
	
	/**
	 * 根据订单时间查询订单数量
	 * @param begin
	 * @param end
	 * @return int
	 */
	public int queryOrderCountByDate(Date begin,Date end){
		return mapper.queryOrderCountByDate(begin, end);
	}
	
	/**
	 * 根据条件分页查询订单
	 * @param oid		订单编号
	 * @param name		用户名和姓名
	 * @param idCard	身份证号
	 * @param index		查询起始位置
	 * @param pageSize	查询条数
	 * @return
	 */
	public List<Orders> queryOrdersByCondition(int oid,String name,String idCard,int index,int pageSize){
		return mapper.queryOrdersByCondition(oid, name, idCard, index, pageSize);
	}
	/**
	 * 根据条件查询订单数量
	 * @param oid		订单编号
	 * @param name		用户名和姓名
	 * @param idCard	身份证号
	 * @return
	 */
	public int queryOrdersCountByCondition(int oid,String name,String idCard){
		return mapper.queryOrdersCountByCondition(oid, name, idCard);
	}
	
}
