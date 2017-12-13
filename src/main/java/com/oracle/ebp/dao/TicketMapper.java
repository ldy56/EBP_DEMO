package com.oracle.ebp.dao;

import java.sql.Date;
import java.util.List;

import com.oracle.ebp.domain.Ticket;

/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
public interface TicketMapper {
	
	/**
	 * ����Ʊ���ݳ����ڲ�ѯƱ��
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Ticket> queryTicketsByDate(Date begin,Date end);

	/**
	 * ����Ʊ���ݳ����ڷ�ҳ��ѯƱ��
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Ticket> queryTicketsByDatePage(Date begin,Date end,int index,int pageSize);
	
	/**
	 * ����ID��ѯƱ��
	 * @param id
	 * @return
	 */
	public Ticket queryTicketById(int id);
	
	/**
	 * ���Ʊ��
	 * @param ticket
	 */
	public void addTicket(Ticket ticket);
	
	/**
	 * ����Id�޸�Ʊ��
	 * @param id
	 */
	public void updateTicketById(Ticket ticket);
	
	/**
	 * ����ID�޸�Ʊ��״̬
	 * @param id
	 */
	public void updateStatusById(Integer status,Integer id);
	
	/**
	 * ����IDɾ��Ʊ��
	 * @param id
	 */
	public void deleteTicketById(Integer id);
	
	
	/**
	 * ����Ʊ�����ڷ�ҳ��ѯƱ������
	 * @param begin
	 * @param end
	 * @return int
	 */
	public int queryTicketByDateCount(Date begin, Date end);
	
	/**
	 * ����Ʊ�����ڷ�ҳ��ѯƱ��
	 * @param begin
	 * @param end
	 * @return List
	 */
	public List<Ticket> queryTicketByDatePage(Date begin, Date end, int index,
			int pageSize);
	
	
}
