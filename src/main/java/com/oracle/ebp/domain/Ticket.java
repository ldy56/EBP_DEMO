package com.oracle.ebp.domain;

import java.io.Serializable;
import java.sql.Date;

public class Ticket implements Serializable{
	private static final long serialVersionUID = 1L;
	private int tid;
	private String descs;
	private Date startTime;
	private int amount;
	private int balance;
	private Double price;
	private int status=1;	//默认为启用
	
	//购票张数
	private int number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Ticket [tid=" + tid + ", descinfo=" + descs + ", startTime="
				+ startTime + ", amount=" + amount + ", balance=" + balance
				+ ", price=" + price + ", status=" + status + ",number="+number+"]";
	}
	public Ticket() {
		super();
	}
	
	public Ticket(String descinfo, Date startTime, int amount, int balance,
			double price, int status) {
		super();
		this.descs = descinfo;
		this.startTime = startTime;
		this.amount = amount;
		this.balance = balance;
		this.price = price;
		this.status = status;
	}
	public Ticket(int tid, String descinfo, Date startTime, int amount,
			int balance, double price, int status) {
		super();
		this.tid = tid;
		this.descs = descinfo;
		this.startTime = startTime;
		this.amount = amount;
		this.balance = balance;
		this.price = price;
		this.status = status;
	}
}
