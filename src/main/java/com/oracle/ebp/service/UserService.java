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
import com.oracle.ebp.dao.UserMapper;
import com.oracle.ebp.domain.User;
import com.oracle.ebp.util.Constants;

/**   
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
@Service
public class UserService {
	
	@Resource
	private UserMapper mapper;
	
	@Resource
	private MongoTemplate template;
	
	/**
	 * ��mongo�б����ļ�
	 * @param in �ļ�������
	 * @param id ����
	 */
	public void uploadPhoto(InputStream in,int id){
		GridFS fs=new GridFS(template.getDb());
		GridFSInputFile file = fs.createFile(in);
		file.setFilename(Constants.USER_PHOTO+id);
		file.save();
	}
	/**
	 * ����ID��MongoDB�в�ѯ�ļ�
	 * @param id
	 * @return
	 */
	public GridFSDBFile getFileByUid(int id){
		GridFS fs=new GridFS(template.getDb());
		return fs.findOne(Constants.USER_PHOTO+id);
	}
	
	/**
	 * ����ID��MongoDB�в�ѯ�ļ�
	 * @param id
	 * @return
	 */
	public void deleteFileByUid(int id){
		GridFS fs=new GridFS(template.getDb());
		fs.remove(Constants.USER_PHOTO+id);
	}

	
	/**
	 * �û���¼
	 * @param username
	 * @param password
	 * @return user
	 */
	public User loginUser(String username, String password) {
		return mapper.loginUser(username, password);
	}

	/**
	 * ����ID���û���ֵ
	 * @return
	 */
	public void topUpMoney(int money,int id) {
		mapper.topUpMoney(money,id);
	}
	
	/**
	 * �û�����
	 * @param money
	 * @param id
	 */
	public void summaryMoney(Double money,int id){
		mapper.summaryMoney(money, id);
	}

	/**
	 * �û�ע��
	 * @param user
	 */
	public void registerUser(User user){
		mapper.registerUser(user);
	}
	
	/**
	 * ����ID�޸ĸ�����Ϣ
	 * @param user
	 */
	public void updateUser(User user){
		mapper.updateUser(user);
	}
	
//	================================================����Ա����
	/**
	 * �����û�ע�����ڷ�ҳ��ѯ�û�
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<User> queryUsersByDatePage(Date begin,Date end,int index,int pageSize){
		return mapper.queryUsersByDatePage(begin, end, index, pageSize);
	}
	
	/**
	 * �����û�ע�����ڲ�ѯ�û�����
	 * @param begin
	 * @param end
	 * @return
	 */
	public int queryUsersByDateCount(Date begin,Date end){
		return mapper.queryUsersByDateCount(begin, end);
	}
	
	/**
	 * ������������ģ����ѯ�����з�ҳ
	 * @param name		����	
	 * @param idCard	���֤��
	 * @param telno		�绰��
	 * @param index		��ҳ�±�
	 * @param pageSize	��ʾ����
	 * @return
	 */
	public List<User> queryUsersByCondition(String name,String idCard,String telno,int index,int pageSize){
		return mapper.queryUsersByCondition(name, idCard, telno, index, pageSize);				
	}
	
	/**
	 * ������������ģ����ѯ����
	 * @param name		����/�û���	
	 * @param idCard	���֤��
	 * @param telno		�绰��
	 * @return
	 */
	public int queryUsersByCount(String name,String idCard,String telno){
//		System.out.println(name);
		return mapper.queryUsersByCount(name, idCard, telno);
	}
	
	/**
	 * �����û�ID�޸�״̬
	 * @param status
	 * @param id
	 */
	public void updateUserStatusById(Integer status,int id){
		mapper.updateUserStatusById(status, id);
	}
	
}
