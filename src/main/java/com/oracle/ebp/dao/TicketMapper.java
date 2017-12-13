package com.oracle.ebp.dao;

import java.sql.Date;
import java.util.List;

import com.oracle.ebp.domain.Ticket;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
public interface TicketMapper {
	
	/**
	 * 根据票据演出日期查询票据
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Ticket> queryTicketsByDate(Date begin,Date end);

	/**
	 * 根据票据演出日期分页查询票据
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Ticket> queryTicketsByDatePage(Date begin,Date end,int index,int pageSize);
	
	/**
	 * 根据ID查询票据
	 * @param id
	 * @return
	 */
	public Ticket queryTicketById(int id);
	
	/**
	 * 添加票据
	 * @param ticket
	 */
	public void addTicket(Ticket ticket);
	
	/**
	 * 根据Id修改票据
	 * @param id
	 */
	public void updateTicketById(Ticket ticket);
	
	/**
	 * 根据ID修改票据状态
	 * @param id
	 */
	public void updateStatusById(Integer status,Integer id);
	
	/**
	 * 根据ID删除票据
	 * @param id
	 */
	public void deleteTicketById(Integer id);
	
	
	/**
	 * 根据票据日期分页查询票据总数
	 * @param begin
	 * @param end
	 * @return int
	 */
	public int queryTicketByDateCount(Date begin, Date end);
	
	/**
	 * 根据票据日期分页查询票据
	 * @param begin
	 * @param end
	 * @return List
	 */
	public List<Ticket> queryTicketByDatePage(Date begin, Date end, int index,
			int pageSize);
	
	
}
