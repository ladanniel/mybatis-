package com.cn.mybatis.dao;

import com.cn.mybatis.pojo.User;

public interface UserDao {
	//查询用户信息
	public User findUserById(int id);
	//添加用户信息
	public void insertUser(User user);
	//删除用户信息
	public void deleteUser(int id);

}
