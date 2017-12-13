package com.oracle.ebp.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class Orders implements Serializable{

	private static final long serialVersionUID = 1L;
	private int oid;
	
	private Date commitTime;	//成交时间
	private double amount;		//提交总价格
	
	private String orderNumber;
	
	private int uid;
	
	//两个临时的属性，用于三表查询的
	private User user;
	private OrderList orderList;	//订单的明细项
	
	private List<OrderList> list;	//订单的所有明细项
	
	public List<OrderList> getList() {
		return list;
	}
	public void setList(List<OrderList> list) {
		this.list = list;
	}
	public OrderList getOrderList() {
		return orderList;
	}
	public void setOrderList(OrderList orderList) {
		this.orderList = orderList;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public Date getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", commitTime=" + commitTime
				+ ", amount=" + amount + ", orderNumber=" + orderNumber
				+ ", uid=" + uid + ", user=" + user + "]";
	}
	public Orders() {
		super();
	}
	public Orders(Date commitTime, double amount, String orderNumber, int uid,
			User user) {
		super();
		this.commitTime = commitTime;
		this.amount = amount;
		this.orderNumber = orderNumber;
		this.uid = uid;
		this.user = user;
	}
	public Orders(Date commitTime, double amount, int uid) {
		super();
		this.commitTime = commitTime;
		this.amount = amount;
		this.uid = uid;
	}
	
	
}
