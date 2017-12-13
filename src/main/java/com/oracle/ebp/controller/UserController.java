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
 * @Description:用户控制器
 * @author :郑
 * @date :2017年1月4日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Controller
public class UserController {
	
	@Resource
	private UserService service;
	
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return user
	 */
	@RequestMapping("/user/login")
	public String login(String username,String password,HttpSession session,Model model){
		User user = service.loginUser(username, MD5Util.getMD5(password));
//		User user=mongoDAO.login(username, MD5Util.getMD5(password));
		
		if(user==null){
			model.addAttribute("errors", "用户名或密码错误！");
			return "login";
		}
		if(user.getStatus()==0){
			model.addAttribute("errors", "该用户已经被禁用！");
			return "login";
		}
		
		//将user保存在session中
		session.setAttribute(Constants.SESSION_USER,user);
		return "forward:queryTicketsByDate";
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	@RequestMapping("/user/logout")
	public String logout(HttpSession session){
		//将用户从session中删除
		session.removeAttribute(Constants.SESSION_USER);

		return "login";
	}
	
	/**
	 * 用户充值
	 * @return
	 */
	@RequestMapping("/user/topUpMoney")
	public String topUpMoney(String type,String money,Model model,HttpSession session){
		int balance=0;
		try {
			balance=Integer.valueOf(money);
		} catch (NumberFormatException e) {
			//格式不正确
			model.addAttribute("errors", "请输入正确的金额！");
			return "user/TopUpAccount";
		}
		
		User user=WebTool.getUserBySession(session);
		
		//保存操作
		service.topUpMoney(balance,user.getUid());
		
		//重新设置session中的用户
		user.setBalance(user.getBalance()+balance);
		session.setAttribute(Constants.SESSION_USER,user);
		model.addAttribute("type", type);
		model.addAttribute("money", money);
		
		return "chongzhisucceed";
	}
	
	/**
	 * 用户注册
	 * @return
	 */
	@RequestMapping(value="/user/registerUser",method=RequestMethod.POST)
	public String registerUser(User user,HttpServletRequest request){
		
		//MD5进行摘要
		String str=user.getPassword();
		user.setPassword(MD5Util.getMD5(str));
		
		service.registerUser(user);	//生成的ID在user中
		
		//将该用户保存到Session中
		request.getSession().setAttribute(Constants.SESSION_USER,user);
		
		return "forward:queryTicketsByDate";
	}
	
	/**
	 * 保存用户头像到MongoDB中
	 * @return
	 */
	@RequestMapping("/uploadPhoto")
	public String uploadPhoto(HttpServletRequest request){
		//从session中获取用户的ID
		User user=WebTool.getUserBySession(request.getSession());
		
		//0，将用户之前的头像删除！
		service.deleteFileByUid(user.getUid());
		
		//1，将上下文容器转换成CommonsMultipartResolver
		CommonsMultipartResolver res=new CommonsMultipartResolver(request.getServletContext());
		
		//2，检查request中是否包含 multipart数据
		if(res.isMultipart(request)){
			//3，转换request
			MultipartHttpServletRequest multipartReq=(MultipartHttpServletRequest)request;
			//4，获取所有的上传文件
			Iterator<String> it = multipartReq.getFileNames();
			while(it.hasNext()){
				//5,获取指定的文件
				MultipartFile file = multipartReq.getFile(it.next());
				
				//6,向MongoDB中保存图片
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
	 * 根据ID去MongoDB查询用户的图片
	 * @param id
	 * @return 输出流
	 */
	@RequestMapping("/getFileByUid")
	public String getFileByUid(int id,HttpServletResponse resp){
		GridFSDBFile file = service.getFileByUid(id);
		
		//以输出流的方式，将该文件显示出去
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
	 * 根据ID修改个人信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/user/updateUserById")
	public String updateUserById(User user,HttpSession session){
		System.out.println(user);
		User userS=WebTool.getUserBySession(session);
		
		//如果密码修改了，进行MD5进行摘要
		if(!userS.getPassword().equals(user.getPassword())){
			user.setPassword(MD5Util.getMD5(user.getPassword()));
		}
		
		service.updateUser(user);
		
		//将该用户保存到Session中
		if(user.getName()!=null && user.getAddress()!=null){
			session.setAttribute(Constants.SESSION_USER,user);
		}
		
		return "VIPmessage";
	}

/*===============================管理员方法==============================================*/	
	private int pageSize=3;		//页面显示条数
	
	/**
	 * 根据用户注册日期分页查询用户
	 * @param begin
	 * @param end
	 * @return json
	 */
	@RequestMapping(value={"/admin/queryUsersByDatePage.json"})
	public String queryUsersByJson(Date begin,Date end,Integer currentPage,Model model){
		int pageSize=3;		//页面显示条数
		
		if(end == null){//默认查询起始时间
			end=WebTool.getCurrentDate();
		}
		if(begin ==null){//默认查询结束数据
			begin=WebTool.getBeforeDate(end);
		}
		if(currentPage==null || currentPage<1){//默认查询第一页
			currentPage=1;
		}
		//查询总条数
		int allCount=service.queryUsersByDateCount(begin, end);
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//获取查询下标
		int index=(currentPage-1)*pageSize;
		
		List<User> list = service.queryUsersByDatePage(begin, end,index, pageSize);
		
		model.addAttribute("total",allCount);
		model.addAttribute("rows", list);
		
		return "admin/ShowAllUser";
	}
	
	/**
	 * 根据用户注册日期分页查询用户
	 * @param begin
	 * @param end
	 * @return
	 */
	@RequestMapping(value={"/admin/queryUsersByDatePage"})
	public String queryUsersByDatePage(Date begin,Date end,Integer currentPage,Model model,String isAjax){
		if(end == null){//默认查询起始时间
			end=WebTool.getCurrentDate();
		}
		if(begin ==null){//默认查询结束数据
			begin=WebTool.getBeforeDate(end);
		}
		if(currentPage==null || currentPage<1){//默认查询第一页
			currentPage=1;
		}
		//查询总条数
		int allCount=service.queryUsersByDateCount(begin, end);
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//获取查询下标
		int index=(currentPage-1)*pageSize;
		
		List<User> list = service.queryUsersByDatePage(begin, end,index, pageSize);
		
		model.addAttribute("list", list);
		WebTool.addPageAttribute(model, pageCount, currentPage,begin,end);
		
		model.addAttribute("isDate", 1);//设置是日期查询还是 条件查询
		
		if(isAjax!=null){//ajax请求
			return "WEB-INF/jspf/admin/ShowAllUserAjax";
		}
		return "admin/ShowAllUser";
	}
	
	/**
	 * 根据用户条件分页查询用户
	 * @param begin
	 * @param end
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/queryUsersByCondition")
	public String queryUsersByCondition(String name,String idCard,String telno,
			Integer currentPage,Model model,String isAjax){
		if(currentPage==null || currentPage<1){//默认查询第一页
			currentPage=1;
		}
		//查询总条数
		int allCount=service.queryUsersByCount(name, idCard, telno);
		
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//获取查询下标
		int index=(currentPage-1)*pageSize;
		List<User> list = service.queryUsersByCondition(name, idCard, telno, index, pageSize);
		
		model.addAttribute("list", list);
		WebTool.addPageAttribute(model, pageCount, currentPage,null,null);
		
		model.addAttribute("isCondition", 1);//设置是日期查询还是 条件查询
		
		if(isAjax!=null){//Ajax请求
			return "WEB-INF/jspf/admin/ShowAllUserAjax";
		}
		return "admin/ShowAllUser";
	}
	
	/**
	 * 根据ID修改用户状态
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
