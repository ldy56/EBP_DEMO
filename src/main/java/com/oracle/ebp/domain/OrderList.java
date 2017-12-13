package com.oracle.ebp.domain;

import java.io.Serializable;

public class OrderList implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int lid;
	
	private String descs;
	private double price;
	private int quantity;
	private double amount;	//总数
	
	private int oid;		//订单主键
	
	public OrderList(String descs, double price, int quantity, double amount,
			int oid) {
		super();
		this.descs = descs;
		this.price = price;
		this.quantity = quantity;
		this.amount = amount;
		this.oid = oid;
	}
	
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	@Override
	public String toString() {
		return "OrderList [lid=" + lid + ", descs=" + descs + ", price=" + price
				+ ", quantity=" + quantity + ", amount=" + amount + "]";
	}
	public OrderList() {
		super();
	}
}
