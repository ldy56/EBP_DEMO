package com.oracle.ebp.dao;

import java.sql.Date;
import java.util.List;

import com.oracle.ebp.domain.Ticket;
import com.oracle.ebp.domain.User;

/**   
 * @Description:�û�DAO
 * @author :֣
 * @date :2017��1��4��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
public interface UserMapper {

	/**
	 * �û���¼
	 * @param username
	 * @param password
	 * @return user
	 */
	public User loginUser(String username,String password);
	
	/**
	 * �û���ֵ
	 * @param money
	 */
	public void topUpMoney(Integer money,int id);
	
	/**
	 * �û�ע��
	 * @param user
	 */
	public void registerUser(User user);
	
	/**
	 * �û�����
	 * @param money
	 * @param id
	 */
	public void summaryMoney(Double money,int id);
	
	/**
	 * ����ID�޸ĸ�����Ϣ
	 * @param user
	 */
	public void updateUser(User user);
	
//	================================================����Ա����
	
	/**
	 * �����û�ע�����ڷ�ҳ��ѯ�û�
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<User> queryUsersByDatePage(Date begin,Date end,int index,int pageSize);
	
	/**
	 * �����û�ע�����ڲ�ѯ�û�����
	 * @param begin
	 * @param end
	 * @return
	 */
	public int queryUsersByDateCount(Date begin,Date end);
	
	
	/**
	 * ������������ģ����ѯ�����з�ҳ
	 * @param name		����/�û���	
	 * @param idCard	���֤��
	 * @param telno		�绰��
	 * @param index		��ҳ�±�
	 * @param pageSize	��ʾ����
	 * @return
	 */
	public List<User> queryUsersByCondition(String name,String idCard,String telno,int index,int pageSize);
	
	/**
	 * ������������ģ����ѯ����
	 * @param name		����/�û���	
	 * @param idCard	���֤��
	 * @param telno		�绰��
	 * @return
	 */
	public int queryUsersByCount(String name,String idCard,String telno);
	
	
	/**
	 * �����û�ID�޸�״̬
	 * @param status
	 * @param id
	 */
	public void updateUserStatusById(Integer status,int id);
	
	
}
