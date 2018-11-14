package com.cn.mybatis.mapper;

import java.util.List;

import com.cn.mybatis.pojo.User;
import com.cn.mybatis.pojo.UserCustom;
import com.cn.mybatis.pojo.UserQueryVo;

public interface UserMapper {
	public User findUserByIdResultMap(int id);
	
	public User selectUserById(int id);
	/**
	 * 用户信息综合查询
	 * 输入参数类型：UserQueryVo
	 * @param userQueryVo
	 * @return
	 */
	public List<UserCustom> findUserList(UserQueryVo userQueryVo);
	/**
	 * 用户信息综合总数查询
	 * @param userQueryVo
	 * @return
	 */
	public int findUserCount(UserQueryVo userQueryVo);
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	public User findUserById(int id);
	/**
	 * 根据用户名查询列表
	 * @param userQueryVo
	 * @return
	 */
	public List<User> findUserByName(UserQueryVo userQueryVo);
	
	void updateUser(User user);

}
