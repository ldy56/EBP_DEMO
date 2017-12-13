package com.oracle.ebp.domain;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.oracle.ebp.util.WebTool;

@Document(collection="user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int uid;
	
	private String username;
	private String password;
	private String name;
	private int gender;
	private int age;
	private String idCard;
	private String address;
	private String telno;
	
//	private Date regTime=WebTool.getCurrentDate();
	private String regTime=WebTool.getCurrentDate().toString();
	
	private double balance=0;		//’Àªß”‡∂Ó
	private int status=1;			//ƒ¨»œ1
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password="
				+ password + ", name=" + name + ", gender=" + gender + ", age="
				+ age + ", idCard=" + idCard + ", address=" + address
				+ ", telno=" + telno + ", regTime=" + regTime + ", balance="
				+ balance + ", status=" + status + "]";
	}
	public User(String username, String idCard, String telno) {
		super();
		this.username = username;
		this.idCard = idCard;
		this.telno = telno;
	}
	public User() {
		super();
	}
	public User(int uid, String username, String password, String name,
			int gender, int age, String idCard, String address, String telno,
			String regTime, double balance, int status) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.idCard = idCard;
		this.address = address;
		this.telno = telno;
		this.regTime = regTime;
		this.balance = balance;
		this.status = status;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
