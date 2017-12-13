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
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
@Service
public class TicketService {
	
	@Resource
	private TicketMapper mapper;
	
	@Resource
	private MongoTemplate template;
	
	/**
	 * ����ID����Ʊ�ݷ���ͼƬ
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
	 * ����Id��ȡƱ�ݷ�����Ϣ
	 * @param tid
	 * @return
	 */
	public GridFSDBFile getPhotoById(int tid){
		GridFS fs=new GridFS(template.getDb());
		return fs.findOne(Constants.TICKTE_PHOTO+tid);
	}
	
	/**
	 * ����IDɾ��Ʊ��ͼƬ��Ϣ
	 * @param tid
	 */
	public void removePhotById(int tid){
		GridFS fs=new GridFS(template.getDb());
		fs.remove(Constants.TICKTE_PHOTO+tid);
	}
	
	/**
	 * ����ID��ѯƱ����Ϣ
	 * @param tid
	 * @return
	 */
	public Ticket queryTicketById(int tid){
		return mapper.queryTicketById(tid);
	}
	
	/**
	 * ��ѯָ��ʱ��7���ڵ�Ʊ��
	 * @param date
	 * @return
	 */
	public List<Ticket> queryTicketsByDate(Date begin, Date end) {
		return mapper.queryTicketsByDate(begin, end);
	}
	
	/**
	 * ����Ʊ���ݳ����ڷ�ҳ��ѯƱ��
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Ticket> queryTicketsByDatePage(Date begin,Date end,int index,int pageSize){
		return mapper.queryTicketsByDatePage(begin, end, index, pageSize);
	}
	
	
	/**
	 * ���Ʊ��
	 * @param ticket
	 */
	public void addTicket(Ticket ticket){
		mapper.addTicket(ticket);
	}
	
	/**
	 * ����Id�޸�Ʊ��
	 * @param id
	 */
	public void updateTicketById(Ticket ticket){
		mapper.updateTicketById(ticket);
	}

	/**
	 * ����ID�޸�Ʊ��״̬
	 * @param id
	 */
	public void updateStatusById(Integer status,Integer id){
		mapper.updateStatusById(status, id);
	}

	
	
	/**
	 * ����Ʊ�����ڷ�ҳ��ѯƱ������
	 * @param begin
	 * @param end
	 * @return int
	 */
	public int queryTicketByDateCount(Date begin, Date end) {
		return mapper.queryTicketByDateCount(begin, end);
	}

	/**
	 * ����Ʊ�����ڷ�ҳ��ѯƱ��
	 * @param begin
	 * @param end
	 * @return List
	 */
	public List<Ticket> queryTicketByDatePage(Date begin, Date end, int index,
			int pageSize) {
		return mapper.queryTicketByDatePage(begin, end, index, pageSize);
	}
	
}
