package com.oracle.ebp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mongodb.gridfs.GridFSDBFile;
import com.oracle.ebp.domain.OrderList;
import com.oracle.ebp.domain.Orders;
import com.oracle.ebp.domain.Ticket;
import com.oracle.ebp.domain.User;
import com.oracle.ebp.service.OrderListService;
import com.oracle.ebp.service.OrderService;
import com.oracle.ebp.service.TicketService;
import com.oracle.ebp.service.UserService;
import com.oracle.ebp.util.Constants;
import com.oracle.ebp.util.WebTool;

/**   
 * @Description:(用一句话描述该文件做什么) 
 * @author :郑
 * @date :2017年1月9日
 * @version V1.0
 * 班训：	天道酬勤 , 不忘初衷   
 */
@Controller
public class TicketController {

	@Resource
	private TicketService service;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderListService orderListService;
	
	@Resource
	private UserService userService;
	
	/**
	 * 以ajax方式保存电影封面信息
	 * @param req
	 * @param tid
	 * @return
	 */
	@RequestMapping("/admin/saveTicketPhoto")
	public String saveTicketPhoto(HttpServletRequest req,int tid,HttpServletResponse resp){
		service.removePhotById(tid);
		
		CommonsMultipartResolver res=new CommonsMultipartResolver(req.getServletContext());
		if(res.isMultipart(req)){
			MultipartHttpServletRequest request=(MultipartHttpServletRequest)req;
			Iterator<String> iterator = request.getFileNames();
			while(iterator.hasNext()){
				try {
					MultipartFile file = request.getFile(iterator.next());
					service.saveTicketPhoto(file.getInputStream(), tid);
//					System.out.println("保存成功！");
					resp.getWriter().write("ok");
				} catch (Exception e) {
					System.out.println(tid+"保存失败！");
				}
			}
		}
		return null;
	}
	
	/**
	 * 以内存流的方式显示图片
	 * @param tid
	 * @param resp
	 * @return
	 */
	@RequestMapping("/getPhotoByTid")
	public String getPhotoByTid(int tid,HttpServletResponse resp){
		System.out.println("111");
		GridFSDBFile file = service.getPhotoById(tid);
		
		InputStream in =null;
		OutputStream out=null;
		try {
			in = file.getInputStream();
			out=resp.getOutputStream();
			byte[] buff=new byte[1024];
			int len=0;
			while((len=in.read(buff))!=-1){
				out.write(buff, 0, len);
			}
		} catch (Exception e) {
		}finally{
			try {
				in.close();
				out.close();
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	/**
	 * 查询指定时间7天内的票据:10条
	 * @param date
	 * @return 首页面
	 */
	@RequestMapping("/user/queryTicketsByDate")
	public String queryTicketsByDate(Date date,Model model){
		queryTicketByDate(null, date, null,10, model);
		
		return "index";
	}
	
	/**
	 * 查询指定时间7天内的票据：4条
	 * @param date
	 * @return 实时订票页面
	 */
	@RequestMapping("/user/queryTicketsBy6")
	public String queryTicketsByDate(Model model,Integer currentPage,Date endDate){
		queryTicketByDate(null, endDate, currentPage,4, model);
		return "ticket";
	}
	
	
	/**
	 * 票据结账
	 * @return
	 */
	@RequestMapping("/user/summaryMoney")
	public String summaryMoney(HttpSession session,Double money,Model model){
		Map<Integer,Ticket> map=(Map<Integer,Ticket>)session.getAttribute(Constants.SESSION_SHOPPING);
		
		if(money==null || money<1){
			return "gouwuche";
		}
		
		User user=WebTool.getUserBySession(session);
		if(user.getBalance()<money){
			//用户余额不够，跳转充值页面！
			session.setAttribute("errors_balance", "余额不够，请您充值！");
			return "chongzhi";
		}else{
			//用户结账
			userService.summaryMoney(money, user.getUid());
			//重新设置session中的用户
			user.setBalance(user.getBalance()-money);
			WebTool.setUserBySession(session, user);
			
		}
		
		Date commitTime=WebTool.getCurrentDate();
		
		//生成订单信息
		Orders order=new Orders(commitTime, money, user.getUid());
		orderService.addOrders(order);
		
		List<OrderList> list=new ArrayList<OrderList>();
		for(Ticket t:map.values()){
			//生成订单详细信息列表
			OrderList olist=new OrderList(t.getDescs(), t.getPrice(), 
					t.getNumber(), t.getNumber()*t.getPrice(), order.getOid());
			
			//单个保存
			//orderListService.addOrderList(olist);
			list.add(olist);
		}
		//批量保存
		orderListService.addOrderLists(list);

		//将订单以及详细项放到request作用域中
		model.addAttribute(Constants.SESSION_SHOPPING, map);
		model.addAttribute("order", order);
		model.addAttribute("sumMoney", session.getAttribute("sumMoney"));
		model.addAttribute("sumTicket", session.getAttribute("sumTicket"));
		
		//清除session中购物车的信息,包括总价，票数
		session.removeAttribute(Constants.SESSION_SHOPPING);
		session.removeAttribute("sumMoney");
		session.removeAttribute("sumTicket");
		
		
		return "jiezhang";
	}
	
	/**
	 * 查询购物车，统计所有的票数，以及总价
	 * @return
	 */
	@RequestMapping("/user/showCart")
	public String showCart(HttpSession session){
		Map<Integer,Ticket> map=(Map<Integer,Ticket>)session.getAttribute(Constants.SESSION_SHOPPING);
		if(map==null){
			return "gouwuche";
		}
		Double sumMoney=0.0;	//总价
		int sumTicket=0;	//总数量
		
		for(Ticket t:map.values()){
			sumMoney+=t.getNumber()*t.getPrice();
			sumTicket+=t.getNumber();
		}
		
		session.setAttribute("sumMoney", sumMoney);
		session.setAttribute("sumTicket", sumTicket);
		
		return "gouwuche";
	}
	
	/**
	 * 根据key删除购物车中的票据
	 * @param key
	 * @return
	 */
	@RequestMapping("/user/deleteCart")
	public String deleteCart(Integer key,HttpSession session){
		Map<Integer,Ticket> map=(Map<Integer,Ticket>)session.getAttribute(Constants.SESSION_SHOPPING);
		map.remove(key);
		session.setAttribute(Constants.SESSION_SHOPPING, map);
		return showCart(session);
	}
	
	/**
	 * AJAX方式向session中添加购物车信息
	 * @param tid
	 * @param number
	 * @return
	 */
	@RequestMapping("/user/addShoppingCart")
	public void addShoppingCart(Integer tid,Integer number,
			HttpSession session,HttpServletResponse response){
		Ticket ticket = service.queryTicketById(tid);
		//添加用户购买的数量
		ticket.setNumber(number);
		
		//从session中获取购物车信息
		Map<Integer,Ticket> map=(Map<Integer,Ticket>)session.getAttribute(Constants.SESSION_SHOPPING);
		if(map==null || map.isEmpty()){
			//保存在session中的购物车数据
			map=new TreeMap<Integer, Ticket>();
			//添加购物车
			map.put(1,ticket);
		}else{
			int key=((TreeMap<Integer, Ticket>)map).lastKey();
			map.put(key+1,ticket);
		}
		
		session.setAttribute(Constants.SESSION_SHOPPING, map);
		
		//向客户端返回结果
		try {
			PrintWriter out = response.getWriter();
			out.write("ok");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据ID查询票据
	 * @param tid
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryTicketById")
	public String queryTicketById(Integer tid,Model model){
		Ticket ticket = service.queryTicketById(tid);
		model.addAttribute("ticket", ticket);
		return "MOVEmessage";
	}
	
	/*===============================管理员的方法==============================================*/	
	/**
	 * 查询指定时间7天内的票据：4条
	 * @param date
	 * @return 实时订票页面
	 */
	@RequestMapping("/admin/queryTickets")
	public String queryTickets(Model model,Integer currentPage,Date end,Date begin){
		queryTicketByDate(begin, end, currentPage,4, model);
		return "admin/ticket";
	}

	/**
	 * 根据Id修改票据
	 * @param id
	 */
	@RequestMapping("/admin/updateTicketAjax")
	public String updateTicketAjax(HttpServletResponse resp,Ticket ticket){
//		System.out.println(ticket);
		try {
			service.updateTicketById(ticket);
			resp.getWriter().write("ok");;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据ID修改票据状态
	 * @param id
	 */
	@RequestMapping("/admin/updateStatusById")
	public String updateStatusById(Integer status,Integer id,HttpServletResponse resp){
		try {
			service.updateStatusById(status, id);
			resp.getWriter().write("ok");
//			System.out.println("状态修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
/*===============================统一分页的方法==============================================*/	
	/**
	 * 根据票据日期分页查询票据
	 * @param begin
	 * @param end
	 * @param currentPage
	 * @param model
	 * @return
	 */
	public void queryTicketByDate(Date begin,Date end,Integer currentPage,int pageSize,Model model){
		if(pageSize<1){
			pageSize=10;		//页面显示条数
		}
		
		if(end == null){//默认查询结束时间
			end=WebTool.getCurrentDate();
		}
		if(begin ==null){//默认查询起始时间
			begin=WebTool.getBeforeDate(end);
		}
		if(currentPage==null || currentPage<1){//默认查询第一页
			currentPage=1;
		}
		//查询总条数
		int allCount=service.queryTicketByDateCount(begin, end);
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//获取查询下标
		int index=(currentPage-1)*pageSize;
		
		List<Ticket> list = service.queryTicketByDatePage(begin, end,index, pageSize);
		
		model.addAttribute("begin",begin);
		model.addAttribute("end",end);
		model.addAttribute("Tickets", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageCount", pageCount);
	}
	
	
}
