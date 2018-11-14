package com.cn.mybatis.pojo;

import java.util.List;

//vo：表示试图层面的vo(试图层对象)/po(持久层对象)/pojo(自定义对象)
/**
 * User的包装类
 * @author Administrator
 *
 */
public class UserQueryVo {
	//多个id查询
	private List<Integer> ids;
	
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	//包装所需要查询条件，这里包装userCustom相当于包装User,这个类建议不要动，如果要扩展可以建立一个扩展类
	private UserCustom userCustom;

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	

}
