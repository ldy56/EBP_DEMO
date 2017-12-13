package com.oracle.ebp.service;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.oracle.ebp.dao.TicketMapper;
import com.oracle.ebp.domain.Ticket;
import com.oracle.ebp.util.Constants;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Service
public class TicketService {
	
	@Resource
	private TicketMapper mapper;
	
	@Resource
	private MongoTemplate template;
	
	/**
	 * 根据ID保存票据封面图片
	 * @param in
	 * @param tid
	 */
	public void saveTicketPhoto(InputStream in,int tid){
		GridFS fs=new GridFS(template.getDb());
		GridFSInputFile file = fs.createFile(in);
		file.setFilename(Constants.TICKTE_PHOTO+tid);
		file.save();
	}
	
	/**
	 * 根据Id获取票据封面信息
	 * @param tid
	 * @return
	 */
	public GridFSDBFile getPhotoById(int tid){
		GridFS fs=new GridFS(template.getDb());
		return fs.findOne(Constants.TICKTE_PHOTO+tid);
	}
	
	/**
	 * 根据ID删除票据图片信息
	 * @param tid
	 */
	public void removePhotById(int tid){
		GridFS fs=new GridFS(template.getDb());
		fs.remove(Constants.TICKTE_PHOTO+tid);
	}
	
	/**
	 * 根据ID查询票据信息
	 * @param tid
	 * @return
	 */
	public Ticket queryTicketById(int tid){
		return mapper.queryTicketById(tid);
	}
	
	/**
	 * 查询指定时间7天内的票据
	 * @param date
	 * @return
	 */
	public List<Ticket> queryTicketsByDate(Date begin, Date end) {
		return mapper.queryTicketsByDate(begin, end);
	}
	
	/**
	 * 根据票据演出日期分页查询票据
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Ticket> queryTicketsByDatePage(Date begin,Date end,int index,int pageSize){
		return mapper.queryTicketsByDatePage(begin, end, index, pageSize);
	}
	
	
	/**
	 * 添加票据
	 * @param ticket
	 */
	public void addTicket(Ticket ticket){
		mapper.addTicket(ticket);
	}
	
	/**
	 * 根据Id修改票据
	 * @param id
	 */
	public void updateTicketById(Ticket ticket){
		mapper.updateTicketById(ticket);
	}

	/**
	 * 根据ID修改票据状态
	 * @param id
	 */
	public void updateStatusById(Integer status,Integer id){
		mapper.updateStatusById(status, id);
	}

	
	
	/**
	 * 根据票据日期分页查询票据总数
	 * @param begin
	 * @param end
	 * @return int
	 */
	public int queryTicketByDateCount(Date begin, Date end) {
		return mapper.queryTicketByDateCount(begin, end);
	}

	/**
	 * 根据票据日期分页查询票据
	 * @param begin
	 * @param end
	 * @return List
	 */
	public List<Ticket> queryTicketByDatePage(Date begin, Date end, int index,
			int pageSize) {
		return mapper.queryTicketByDatePage(begin, end, index, pageSize);
	}
	
}
