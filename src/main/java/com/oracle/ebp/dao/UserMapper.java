package com.oracle.ebp.dao;

import java.sql.Date;
import java.util.List;

import com.oracle.ebp.domain.Ticket;
import com.oracle.ebp.domain.User;

/**   
 * @Description:用户DAO
 * @author :郑
 * @date :2017年1月4日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
public interface UserMapper {

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return user
	 */
	public User loginUser(String username,String password);
	
	/**
	 * 用户充值
	 * @param money
	 */
	public void topUpMoney(Integer money,int id);
	
	/**
	 * 用户注册
	 * @param user
	 */
	public void registerUser(User user);
	
	/**
	 * 用户结账
	 * @param money
	 * @param id
	 */
	public void summaryMoney(Double money,int id);
	
	/**
	 * 根据ID修改个人信息
	 * @param user
	 */
	public void updateUser(User user);
	
//	================================================管理员方法
	
	/**
	 * 根据用户注册日期分页查询用户
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<User> queryUsersByDatePage(Date begin,Date end,int index,int pageSize);
	
	/**
	 * 根据用户注册日期查询用户数量
	 * @param begin
	 * @param end
	 * @return
	 */
	public int queryUsersByDateCount(Date begin,Date end);
	
	
	/**
	 * 根据条件进行模糊查询并进行分页
	 * @param name		姓名/用户名	
	 * @param idCard	身份证号
	 * @param telno		电话号
	 * @param index		分页下标
	 * @param pageSize	显示条数
	 * @return
	 */
	public List<User> queryUsersByCondition(String name,String idCard,String telno,int index,int pageSize);
	
	/**
	 * 根据条件进行模糊查询数量
	 * @param name		姓名/用户名	
	 * @param idCard	身份证号
	 * @param telno		电话号
	 * @return
	 */
	public int queryUsersByCount(String name,String idCard,String telno);
	
	
	/**
	 * 根据用户ID修改状态
	 * @param status
	 * @param id
	 */
	public void updateUserStatusById(Integer status,int id);
	
	
}
