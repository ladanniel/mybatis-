package com.cn.mybatis.pojo;

import java.io.Serializable;

public class Users implements Serializable{
	/**
	 * Ϊ�˽���������ȡ��ִ�з����л���������Ϊ��������Ĵ洢���ʲ�һ�����ڴ��л��߶���
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
