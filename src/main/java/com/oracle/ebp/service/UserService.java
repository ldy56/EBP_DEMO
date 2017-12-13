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
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Service
public class UserService {
	
	@Resource
	private UserMapper mapper;
	
	@Resource
	private MongoTemplate template;
	
	/**
	 * 向mongo中保存文件
	 * @param in 文件输入流
	 * @param id 主键
	 */
	public void uploadPhoto(InputStream in,int id){
		GridFS fs=new GridFS(template.getDb());
		GridFSInputFile file = fs.createFile(in);
		file.setFilename(Constants.USER_PHOTO+id);
		file.save();
	}
	/**
	 * 根据ID从MongoDB中查询文件
	 * @param id
	 * @return
	 */
	public GridFSDBFile getFileByUid(int id){
		GridFS fs=new GridFS(template.getDb());
		return fs.findOne(Constants.USER_PHOTO+id);
	}
	
	/**
	 * 根据ID从MongoDB中查询文件
	 * @param id
	 * @return
	 */
	public void deleteFileByUid(int id){
		GridFS fs=new GridFS(template.getDb());
		fs.remove(Constants.USER_PHOTO+id);
	}

	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return user
	 */
	public User loginUser(String username, String password) {
		return mapper.loginUser(username, password);
	}

	/**
	 * 根据ID对用户充值
	 * @return
	 */
	public void topUpMoney(int money,int id) {
		mapper.topUpMoney(money,id);
	}
	
	/**
	 * 用户结账
	 * @param money
	 * @param id
	 */
	public void summaryMoney(Double money,int id){
		mapper.summaryMoney(money, id);
	}

	/**
	 * 用户注册
	 * @param user
	 */
	public void registerUser(User user){
		mapper.registerUser(user);
	}
	
	/**
	 * 根据ID修改个人信息
	 * @param user
	 */
	public void updateUser(User user){
		mapper.updateUser(user);
	}
	
//	================================================管理员方法
	/**
	 * 根据用户注册日期分页查询用户
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<User> queryUsersByDatePage(Date begin,Date end,int index,int pageSize){
		return mapper.queryUsersByDatePage(begin, end, index, pageSize);
	}
	
	/**
	 * 根据用户注册日期查询用户数量
	 * @param begin
	 * @param end
	 * @return
	 */
	public int queryUsersByDateCount(Date begin,Date end){
		return mapper.queryUsersByDateCount(begin, end);
	}
	
	/**
	 * 根据条件进行模糊查询并进行分页
	 * @param name		姓名	
	 * @param idCard	身份证号
	 * @param telno		电话号
	 * @param index		分页下标
	 * @param pageSize	显示条数
	 * @return
	 */
	public List<User> queryUsersByCondition(String name,String idCard,String telno,int index,int pageSize){
		return mapper.queryUsersByCondition(name, idCard, telno, index, pageSize);				
	}
	
	/**
	 * 根据条件进行模糊查询数量
	 * @param name		姓名/用户名	
	 * @param idCard	身份证号
	 * @param telno		电话号
	 * @return
	 */
	public int queryUsersByCount(String name,String idCard,String telno){
//		System.out.println(name);
		return mapper.queryUsersByCount(name, idCard, telno);
	}
	
	/**
	 * 根据用户ID修改状态
	 * @param status
	 * @param id
	 */
	public void updateUserStatusById(Integer status,int id){
		mapper.updateUserStatusById(status, id);
	}
	
}
