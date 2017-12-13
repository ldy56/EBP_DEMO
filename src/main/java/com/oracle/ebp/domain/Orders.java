package com.oracle.ebp.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class Orders implements Serializable{

	private static final long serialVersionUID = 1L;
	private int oid;
	
	private Date commitTime;	//�ɽ�ʱ��
	private double amount;		//�ύ�ܼ۸�
	
	private String orderNumber;
	
	private int uid;
	
	//������ʱ�����ԣ����������ѯ��
	private User user;
	private OrderList orderList;	//��������ϸ��
	
	private List<OrderList> list;	//������������ϸ��
	
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
