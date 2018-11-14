package com.cn.mybatis.pojo;

public class Province {

	private Integer id;
	private String code;
	private String name;

	public Province() {
		super();
	}

	public Province(Integer id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Province [id=" + id + ", code=" + code + ", name=" + name + "]";
	}

}
