package com.cn.mybatis.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.cn.mybatis.dao.UserDao;
import com.cn.mybatis.pojo.User;

public class UserDaoImpl implements UserDao{
    // 需要向dao实现类中注入SQLSessionFactory，通过构造函数注入\
	private SqlSessionFactory sqls;
	public UserDaoImpl(SqlSessionFactory sqls) {
		this.sqls = sqls;
	}
	
	public User findUserById(int id) {
		SqlSession sql = sqls.openSession();
		User user = sql.selectOne("test.selectById",id);
		sql.close();
		return user;
	}

	public void insertUser(User user) {
		SqlSession sql = sqls.openSession();
		sql.insert("test.insertByUserName",user);
		sql.commit();
		sql.close();
		
	}

	public void deleteUser(int id) {
		SqlSession sql = sqls.openSession();
		sql.delete("test.insertById",id);
		sql.commit();
		sql.close();
		
	}

}
