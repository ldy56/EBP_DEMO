package com.oracle.ebp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mongodb.gridfs.GridFSDBFile;
import com.oracle.ebp.domain.User;
import com.oracle.ebp.service.UserService;
import com.oracle.ebp.util.Constants;
import com.oracle.ebp.util.MD5Util;
import com.oracle.ebp.util.WebTool;

/**   
 * @Description:�û�������
 * @author :֣
 * @date :2017��1��4��
 * @version V1.0
 * ��ѵ��	������� , ��������   
 */
@Controller
public class UserController {
	
	@Resource
	private UserService service;
	
	
	/**
	 * �û���¼
	 * @param username
	 * @param password
	 * @return user
	 */
	@RequestMapping("/user/login")
	public String login(String username,String password,HttpSession session,Model model){
		User user = service.loginUser(username, MD5Util.getMD5(password));
//		User user=mongoDAO.login(username, MD5Util.getMD5(password));
		
		if(user==null){
			model.addAttribute("errors", "�û������������");
			return "login";
		}
		if(user.getStatus()==0){
			model.addAttribute("errors", "���û��Ѿ������ã�");
			return "login";
		}
		
		//��user������session��
		session.setAttribute(Constants.SESSION_USER,user);
		return "forward:queryTicketsByDate";
	}
	
	/**
	 * �û��˳�
	 * @return
	 */
	@RequestMapping("/user/logout")
	public String logout(HttpSession session){
		//���û���session��ɾ��
		session.removeAttribute(Constants.SESSION_USER);

		return "login";
	}
	
	/**
	 * �û���ֵ
	 * @return
	 */
	@RequestMapping("/user/topUpMoney")
	public String topUpMoney(String type,String money,Model model,HttpSession session){
		int balance=0;
		try {
			balance=Integer.valueOf(money);
		} catch (NumberFormatException e) {
			//��ʽ����ȷ
			model.addAttribute("errors", "��������ȷ�Ľ�");
			return "user/TopUpAccount";
		}
		
		User user=WebTool.getUserBySession(session);
		
		//�������
		service.topUpMoney(balance,user.getUid());
		
		//��������session�е��û�
		user.setBalance(user.getBalance()+balance);
		session.setAttribute(Constants.SESSION_USER,user);
		model.addAttribute("type", type);
		model.addAttribute("money", money);
		
		return "chongzhisucceed";
	}
	
	/**
	 * �û�ע��
	 * @return
	 */
	@RequestMapping(value="/user/registerUser",method=RequestMethod.POST)
	public String registerUser(User user,HttpServletRequest request){
		
		//MD5����ժҪ
		String str=user.getPassword();
		user.setPassword(MD5Util.getMD5(str));
		
		service.registerUser(user);	//���ɵ�ID��user��
		
		//�����û����浽Session��
		request.getSession().setAttribute(Constants.SESSION_USER,user);
		
		return "forward:queryTicketsByDate";
	}
	
	/**
	 * �����û�ͷ��MongoDB��
	 * @return
	 */
	@RequestMapping("/uploadPhoto")
	public String uploadPhoto(HttpServletRequest request){
		//��session�л�ȡ�û���ID
		User user=WebTool.getUserBySession(request.getSession());
		
		//0�����û�֮ǰ��ͷ��ɾ����
		service.deleteFileByUid(user.getUid());
		
		//1��������������ת����CommonsMultipartResolver
		CommonsMultipartResolver res=new CommonsMultipartResolver(request.getServletContext());
		
		//2�����request���Ƿ���� multipart����
		if(res.isMultipart(request)){
			//3��ת��request
			MultipartHttpServletRequest multipartReq=(MultipartHttpServletRequest)request;
			//4����ȡ���е��ϴ��ļ�
			Iterator<String> it = multipartReq.getFileNames();
			while(it.hasNext()){
				//5,��ȡָ�����ļ�
				MultipartFile file = multipartReq.getFile(it.next());
				
				//6,��MongoDB�б���ͼƬ
				try {
					service.uploadPhoto(file.getInputStream(), user.getUid());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "VIPmessage";
	}
	
	/**
	 * ����IDȥMongoDB��ѯ�û���ͼƬ
	 * @param id
	 * @return �����
	 */
	@RequestMapping("/getFileByUid")
	public String getFileByUid(int id,HttpServletResponse resp){
		GridFSDBFile file = service.getFileByUid(id);
		
		//��������ķ�ʽ�������ļ���ʾ��ȥ
		try {
			OutputStream out=resp.getOutputStream();
			InputStream in=file.getInputStream();
			
			byte[] buff=new byte[1024];
			int len=0;
			while((len=in.read(buff))!=-1){
				out.write(buff, 0, len);
			}
			out.flush();
			
		} catch (IOException e) {
		}
		return null;
	}
	
	
	
	/**
	 * ����ID�޸ĸ�����Ϣ
	 * @param user
	 * @return
	 */
	@RequestMapping("/user/updateUserById")
	public String updateUserById(User user,HttpSession session){
		System.out.println(user);
		User userS=WebTool.getUserBySession(session);
		
		//��������޸��ˣ�����MD5����ժҪ
		if(!userS.getPassword().equals(user.getPassword())){
			user.setPassword(MD5Util.getMD5(user.getPassword()));
		}
		
		service.updateUser(user);
		
		//�����û����浽Session��
		if(user.getName()!=null && user.getAddress()!=null){
			session.setAttribute(Constants.SESSION_USER,user);
		}
		
		return "VIPmessage";
	}

/*===============================����Ա����==============================================*/	
	private int pageSize=3;		//ҳ����ʾ����
	
	/**
	 * �����û�ע�����ڷ�ҳ��ѯ�û�
	 * @param begin
	 * @param end
	 * @return json
	 */
	@RequestMapping(value={"/admin/queryUsersByDatePage.json"})
	public String queryUsersByJson(Date begin,Date end,Integer currentPage,Model model){
		int pageSize=3;		//ҳ����ʾ����
		
		if(end == null){//Ĭ�ϲ�ѯ��ʼʱ��
			end=WebTool.getCurrentDate();
		}
		if(begin ==null){//Ĭ�ϲ�ѯ��������
			begin=WebTool.getBeforeDate(end);
		}
		if(currentPage==null || currentPage<1){//Ĭ�ϲ�ѯ��һҳ
			currentPage=1;
		}
		//��ѯ������
		int allCount=service.queryUsersByDateCount(begin, end);
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//��ȡ��ѯ�±�
		int index=(currentPage-1)*pageSize;
		
		List<User> list = service.queryUsersByDatePage(begin, end,index, pageSize);
		
		model.addAttribute("total",allCount);
		model.addAttribute("rows", list);
		
		return "admin/ShowAllUser";
	}
	
	/**
	 * �����û�ע�����ڷ�ҳ��ѯ�û�
	 * @param begin
	 * @param end
	 * @return
	 */
	@RequestMapping(value={"/admin/queryUsersByDatePage"})
	public String queryUsersByDatePage(Date begin,Date end,Integer currentPage,Model model,String isAjax){
		if(end == null){//Ĭ�ϲ�ѯ��ʼʱ��
			end=WebTool.getCurrentDate();
		}
		if(begin ==null){//Ĭ�ϲ�ѯ��������
			begin=WebTool.getBeforeDate(end);
		}
		if(currentPage==null || currentPage<1){//Ĭ�ϲ�ѯ��һҳ
			currentPage=1;
		}
		//��ѯ������
		int allCount=service.queryUsersByDateCount(begin, end);
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//��ȡ��ѯ�±�
		int index=(currentPage-1)*pageSize;
		
		List<User> list = service.queryUsersByDatePage(begin, end,index, pageSize);
		
		model.addAttribute("list", list);
		WebTool.addPageAttribute(model, pageCount, currentPage,begin,end);
		
		model.addAttribute("isDate", 1);//���������ڲ�ѯ���� ������ѯ
		
		if(isAjax!=null){//ajax����
			return "WEB-INF/jspf/admin/ShowAllUserAjax";
		}
		return "admin/ShowAllUser";
	}
	
	/**
	 * �����û�������ҳ��ѯ�û�
	 * @param begin
	 * @param end
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/queryUsersByCondition")
	public String queryUsersByCondition(String name,String idCard,String telno,
			Integer currentPage,Model model,String isAjax){
		if(currentPage==null || currentPage<1){//Ĭ�ϲ�ѯ��һҳ
			currentPage=1;
		}
		//��ѯ������
		int allCount=service.queryUsersByCount(name, idCard, telno);
		
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//��ȡ��ѯ�±�
		int index=(currentPage-1)*pageSize;
		List<User> list = service.queryUsersByCondition(name, idCard, telno, index, pageSize);
		
		model.addAttribute("list", list);
		WebTool.addPageAttribute(model, pageCount, currentPage,null,null);
		
		model.addAttribute("isCondition", 1);//���������ڲ�ѯ���� ������ѯ
		
		if(isAjax!=null){//Ajax����
			return "WEB-INF/jspf/admin/ShowAllUserAjax";
		}
		return "admin/ShowAllUser";
	}
	
	/**
	 * ����ID�޸��û�״̬
	 * @param statu
	 * @param id
	 */
	@RequestMapping("/admin/updateUserStatusById")
	public void updateUserStatusById(Integer statu,Integer uid,HttpServletResponse response){
		service.updateUserStatusById(statu, uid);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(statu.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
