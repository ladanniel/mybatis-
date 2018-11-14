package com.cn.mybatis.pojo;
/**
 * 订单拓展类，通过该类映射订单和用户查询的结果，让该类继承包括字段较多的pojo类
 * @author Administrator
 *映射结果的po接收映射
 */
public class OrderCustom extends Orders{
	//添加用户属性
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
