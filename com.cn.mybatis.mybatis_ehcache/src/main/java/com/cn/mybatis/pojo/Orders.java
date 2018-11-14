package com.cn.mybatis.pojo;

import java.util.Date;
import java.util.List;

public class Orders {
	private Integer id;
	private Integer userId;
	private String number;
	private Date createtime;
	private String note;
	//用户信息
	private Users users;
	
	List<OrderDetails> orderDetail;
	
	
	public List<OrderDetails> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<OrderDetails> orderDetail) {
		this.orderDetail = orderDetail;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	

}
