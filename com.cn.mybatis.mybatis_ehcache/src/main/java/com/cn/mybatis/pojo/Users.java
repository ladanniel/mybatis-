package com.cn.mybatis.pojo;

import java.io.Serializable;

public class Users implements Serializable{
	/**
	 * 为了将缓存数据取出执行反序列化操作，因为二级缓存的存储介质不一定在内存中或者多样
	 */
	private static final long serialVersionUID = 1857055540620337345L;
	private Integer id;
	private String username;
	private String sex;
	private String address;
	private String age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	

}
