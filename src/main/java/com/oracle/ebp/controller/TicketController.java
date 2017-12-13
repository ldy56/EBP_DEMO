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
 * @Description:(��һ�仰�������ļ���ʲô) 
 * @author :֣
 * @date :2017��1��9��
 * @version V1.0
 * ��ѵ��	������� , ��������   
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
	 * ��ajax��ʽ�����Ӱ������Ϣ
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
//					System.out.println("����ɹ���");
					resp.getWriter().write("ok");
				} catch (Exception e) {
					System.out.println(tid+"����ʧ�ܣ�");
				}
			}
		}
		return null;
	}
	
	/**
	 * ���ڴ����ķ�ʽ��ʾͼƬ
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
	 * ��ѯָ��ʱ��7���ڵ�Ʊ��:10��
	 * @param date
	 * @return ��ҳ��
	 */
	@RequestMapping("/user/queryTicketsByDate")
	public String queryTicketsByDate(Date date,Model model){
		queryTicketByDate(null, date, null,10, model);
		
		return "index";
	}
	
	/**
	 * ��ѯָ��ʱ��7���ڵ�Ʊ�ݣ�4��
	 * @param date
	 * @return ʵʱ��Ʊҳ��
	 */
	@RequestMapping("/user/queryTicketsBy6")
	public String queryTicketsByDate(Model model,Integer currentPage,Date endDate){
		queryTicketByDate(null, endDate, currentPage,4, model);
		return "ticket";
	}
	
	
	/**
	 * Ʊ�ݽ���
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
			//�û���������ת��ֵҳ�棡
			session.setAttribute("errors_balance", "������������ֵ��");
			return "chongzhi";
		}else{
			//�û�����
			userService.summaryMoney(money, user.getUid());
			//��������session�е��û�
			user.setBalance(user.getBalance()-money);
			WebTool.setUserBySession(session, user);
			
		}
		
		Date commitTime=WebTool.getCurrentDate();
		
		//���ɶ�����Ϣ
		Orders order=new Orders(commitTime, money, user.getUid());
		orderService.addOrders(order);
		
		List<OrderList> list=new ArrayList<OrderList>();
		for(Ticket t:map.values()){
			//���ɶ�����ϸ��Ϣ�б�
			OrderList olist=new OrderList(t.getDescs(), t.getPrice(), 
					t.getNumber(), t.getNumber()*t.getPrice(), order.getOid());
			
			//��������
			//orderListService.addOrderList(olist);
			list.add(olist);
		}
		//��������
		orderListService.addOrderLists(list);

		//�������Լ���ϸ��ŵ�request��������
		model.addAttribute(Constants.SESSION_SHOPPING, map);
		model.addAttribute("order", order);
		model.addAttribute("sumMoney", session.getAttribute("sumMoney"));
		model.addAttribute("sumTicket", session.getAttribute("sumTicket"));
		
		//���session�й��ﳵ����Ϣ,�����ܼۣ�Ʊ��
		session.removeAttribute(Constants.SESSION_SHOPPING);
		session.removeAttribute("sumMoney");
		session.removeAttribute("sumTicket");
		
		
		return "jiezhang";
	}
	
	/**
	 * ��ѯ���ﳵ��ͳ�����е�Ʊ�����Լ��ܼ�
	 * @return
	 */
	@RequestMapping("/user/showCart")
	public String showCart(HttpSession session){
		Map<Integer,Ticket> map=(Map<Integer,Ticket>)session.getAttribute(Constants.SESSION_SHOPPING);
		if(map==null){
			return "gouwuche";
		}
		Double sumMoney=0.0;	//�ܼ�
		int sumTicket=0;	//������
		
		for(Ticket t:map.values()){
			sumMoney+=t.getNumber()*t.getPrice();
			sumTicket+=t.getNumber();
		}
		
		session.setAttribute("sumMoney", sumMoney);
		session.setAttribute("sumTicket", sumTicket);
		
		return "gouwuche";
	}
	
	/**
	 * ����keyɾ�����ﳵ�е�Ʊ��
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
	 * AJAX��ʽ��session����ӹ��ﳵ��Ϣ
	 * @param tid
	 * @param number
	 * @return
	 */
	@RequestMapping("/user/addShoppingCart")
	public void addShoppingCart(Integer tid,Integer number,
			HttpSession session,HttpServletResponse response){
		Ticket ticket = service.queryTicketById(tid);
		//����û����������
		ticket.setNumber(number);
		
		//��session�л�ȡ���ﳵ��Ϣ
		Map<Integer,Ticket> map=(Map<Integer,Ticket>)session.getAttribute(Constants.SESSION_SHOPPING);
		if(map==null || map.isEmpty()){
			//������session�еĹ��ﳵ����
			map=new TreeMap<Integer, Ticket>();
			//��ӹ��ﳵ
			map.put(1,ticket);
		}else{
			int key=((TreeMap<Integer, Ticket>)map).lastKey();
			map.put(key+1,ticket);
		}
		
		session.setAttribute(Constants.SESSION_SHOPPING, map);
		
		//��ͻ��˷��ؽ��
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
	 * ����ID��ѯƱ��
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
	
	/*===============================����Ա�ķ���==============================================*/	
	/**
	 * ��ѯָ��ʱ��7���ڵ�Ʊ�ݣ�4��
	 * @param date
	 * @return ʵʱ��Ʊҳ��
	 */
	@RequestMapping("/admin/queryTickets")
	public String queryTickets(Model model,Integer currentPage,Date end,Date begin){
		queryTicketByDate(begin, end, currentPage,4, model);
		return "admin/ticket";
	}

	/**
	 * ����Id�޸�Ʊ��
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
	 * ����ID�޸�Ʊ��״̬
	 * @param id
	 */
	@RequestMapping("/admin/updateStatusById")
	public String updateStatusById(Integer status,Integer id,HttpServletResponse resp){
		try {
			service.updateStatusById(status, id);
			resp.getWriter().write("ok");
//			System.out.println("״̬�޸ĳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
/*===============================ͳһ��ҳ�ķ���==============================================*/	
	/**
	 * ����Ʊ�����ڷ�ҳ��ѯƱ��
	 * @param begin
	 * @param end
	 * @param currentPage
	 * @param model
	 * @return
	 */
	public void queryTicketByDate(Date begin,Date end,Integer currentPage,int pageSize,Model model){
		if(pageSize<1){
			pageSize=10;		//ҳ����ʾ����
		}
		
		if(end == null){//Ĭ�ϲ�ѯ����ʱ��
			end=WebTool.getCurrentDate();
		}
		if(begin ==null){//Ĭ�ϲ�ѯ��ʼʱ��
			begin=WebTool.getBeforeDate(end);
		}
		if(currentPage==null || currentPage<1){//Ĭ�ϲ�ѯ��һҳ
			currentPage=1;
		}
		//��ѯ������
		int allCount=service.queryTicketByDateCount(begin, end);
		int pageCount=WebTool.getPageCount(allCount, pageSize);
		if(currentPage>pageCount){
			currentPage=pageCount;
		}
		
		//��ȡ��ѯ�±�
		int index=(currentPage-1)*pageSize;
		
		List<Ticket> list = service.queryTicketByDatePage(begin, end,index, pageSize);
		
		model.addAttribute("begin",begin);
		model.addAttribute("end",end);
		model.addAttribute("Tickets", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageCount", pageCount);
	}
	
	
}
