package com.cn.mybatis.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;





import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.lf5.util.Resource;
import org.junit.Test;

import com.cn.mybatis.mapper.OrderMapperCustom;
import com.cn.mybatis.mapper.UserMapper;
import com.cn.mybatis.pojo.OrderCustom;
import com.cn.mybatis.pojo.Orders;
import com.cn.mybatis.pojo.User;
import com.cn.mybatis.pojo.UserCustom;
import com.cn.mybatis.pojo.UserQueryVo;
import com.cn.mybatis.pojo.Users;


public class mybatisTest{
	//测试一级缓存 存在于sqlsession中
	@Test
	public void testcache(){
		String config = "sqlMapConfig.xml";
		//获取配置文件流
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		//创建回话工厂，传入mybatis配置文件信息
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		//通过工厂得到session
		SqlSession session =sql.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		//第一次发起请求 查询id=1的用户
		User user1 = mapper.findUserByIdResultMap(1);
		System.out.println("测试结果"+user1);
		//如果sqlsession去执行commit操作（执行insert，delete，update），清空sql session中的一级缓存，目的保证缓中是最新数据，避免脏数据
		
		//创建更新对象 一级缓存已经被更新（清空缓存）第二次查询不能显示数据 一级缓存，mybatis自动调用
		mapper.updateUser(user1);
		//发起第二次请求，查询id=1的用户
		User user2 = mapper.findUserByIdResultMap(1);
		System.out.println("测试结果"+user2);
		session.close();
		
	}
	/**
	 * 一级缓存的应用：正式开发，是将mybatis和spring整合开发，事物控制在service中 一个service方法中 有很多mapper调用
	 * 
	 * service[开始执行时，开启事务，创建sqlsession对象，第一次。。。第二次。。执行完，sqlsession关闭]
	 * 
	 * 
	 */
	
	//二级缓存：
	@Test
	public void testsecondcache(){
		String config = "sqlMapConfig.xml";
		//获取配置文件流
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		//创建回话工厂，传入mybatis配置文件信息
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		//通过工厂得到session
		SqlSession session =sql.openSession();
		SqlSession session2 =sql.openSession();
		SqlSession session3 =sql.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		//第一次发起请求 查询id=1的用户
		User user1 = mapper.findUserByIdResultMap(1);
		System.out.println("测试结果"+user1);
		//如果sqlsession去执行commit操作（执行insert，delete，update），清空sql session中的一级缓存，目的保证缓中是最新数据，避免脏数据
//		UserMapper mapper3 = session3.getMapper(UserMapper.class);
//		UserQueryVo userQueryVo = new UserQueryVo();
//		UserCustom userCustom = new UserCustom();
//		userCustom.setSex(2);
//		userCustom.setUsername("江一阳");
//		userQueryVo.setUserCustom(userCustom);
//		int count = mapper3.findUserCount(userQueryVo);
//		System.out.println("测试："+count);
//		session.close();
		//创建更新对象 一级缓存已经被更新（清空缓存）第二次查询不能显示数据 一级缓存，mybatis自动调用
		//mapper.updateUser(user1);
		//session2
		UserMapper mapper2 = session2.getMapper(UserMapper.class);
		User user2 = mapper2.findUserByIdResultMap(1);
		System.out.println("测试结果"+user2);
		session.close();
		
		//session3
		
		
		
		
	}
	@Test
	public void findOrderUserLazyLoading(){
		String config = "sqlMapConfig.xml";
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		SqlSession session =sql.openSession();
		OrderMapperCustom mapper = session.getMapper(OrderMapperCustom.class);
		List<Orders> oc = mapper.findOrderUserLazyLoading();
		for(Orders order:oc){
			Users users = order.getUsers();
			System.out.println(users);
		}
	}
	
	@Test
	public void findOrderUserAndOrderDetail(){
		String config = "sqlMapConfig.xml";
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		SqlSession session =sql.openSession();
		OrderMapperCustom mapper = session.getMapper(OrderMapperCustom.class);
		List<Orders> oc = mapper.findOrderUserAndOrderDetail();
		for(Orders order:oc){
			System.out.println("测试："+order);
		}
	}
	@Test
	public void findOrderUserResultMap(){
		String config = "sqlMapConfig.xml";
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		SqlSession session =sql.openSession();
		OrderMapperCustom mapper = session.getMapper(OrderMapperCustom.class);
		List<Orders> oc = mapper.findOrderUserResultMap();
		for(Orders order:oc){
			System.out.println("测试："+order);
		}
		
		session.close();
	}
	@Test
	public void findOrderUser(){
		String config = "sqlMapConfig.xml";
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		SqlSession session =sql.openSession();
		OrderMapperCustom mapper = session.getMapper(OrderMapperCustom.class);
		List<OrderCustom> oc = mapper.findOrderUser();
		for(OrderCustom order:oc){
			System.out.println("测试："+order);
		}
		
		session.close();
	}
	@Test
	public void findUserByIdResultMap(){
		String config = "sqlMapConfig.xml";
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		SqlSession session =sql.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User count = mapper.findUserByIdResultMap(1);
		System.out.println("测试："+count);
		session.close();
	}
	/**
	 * 输入映射 用户信息综合查询sql片段 动态sql
	 */
	@Test
	public void findUserList(){
		String config = "sqlMapConfig.xml";
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		SqlSession session =sql.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		//因为使用了动态sql ,如果不设置值，条件不会拼接到sql中
		//userCustom.setSex(2);
//		userCustom.setUsername("江一阳");
//		userQueryVo.setUserCustom(userCustom);
		
		//传入多个id
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(10);
		ids.add(12);
		userQueryVo.setIds(ids);
		userQueryVo.setUserCustom(userCustom);
		List<UserCustom> list = mapper.findUserList(userQueryVo);
		for(UserCustom str :list){
			System.out.println("sql foreach拼接："+str);
		}
		
//		System.out.println("测试："+list);
		session.close();
	}
	/**用户信息总数列表查询  通过和上面的用户总数查询列表才可以实现分页
	 * 简单类型:结果集只有一行且一列
	 * resutlType 输出，要求查询出来的列名和pojo中的属性名一致才可以映射成功
	 */
	@Test
	public void ResultTypefindUserCount(){
		String config = "sqlMapConfig.xml";
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		SqlSession session =sql.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex(2);
		userCustom.setUsername("江一阳");
		userQueryVo.setUserCustom(userCustom);
		int count = mapper.findUserCount(userQueryVo);
		System.out.println("测试："+count);
		session.close();
	}
	/**
	 * 输出单个或多个列表，在mapper.xml中resultType指定的类型是一样的
	 * 在mapper.java中指定的方法返回值类型不同
	 * 1、出处单个，返回单个对象类型
	 * 2、输出多个 返回list
	 */
	@Test
	public void ResultTypefindUserByName(){
		String config = "sqlMapConfig.xml";
		InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
		SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
		SqlSession session =sql.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setUsername("江");
		userQueryVo.setUserCustom(userCustom);
		List<User> list = mapper.findUserByName(userQueryVo);
		for(User str :list){
			System.out.println("测试："+str);
		}
		
		session.close();
	}
	@Test
	public void selectById()throws IOException {
		//mybatis 配置文件
				String config = "sqlMapConfig.xml";
				//获取配置文件流
				InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
				//创建回话工厂，传入mybatis配置文件信息
				SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
				//通过工厂得到session
				SqlSession session =sql.openSession();
				
				//通过sqlsession操作数据库 statement = namespace.statement的id paramenterType
				User user = session.selectOne("com.cn.mybatis.mapper.UserMapper.selectUserById", 1);
				System.out.println(user);
				session.close();
				System.out.println(user.getId());
	}
	
		//模糊测试查询用户列表
	@Test
	public void selectByName()throws IOException {
		//mybatis 配置文件
				String config = "sqlMapConfig.xml";
				//获取配置文件流
				InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
				//创建回话工厂，传入mybatis配置文件信息
				SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
				//通过工厂得到session
				SqlSession session =sql.openSession();
				
				//通过sqlsession操作数据库 statement = namespace.statement的id paramenterType
				User user = session.selectOne("test.selectByName", "%江一阳%");
				System.out.println(user);
				session.close();
				
		
	}	
	
	@Test
	public void insertByUserName()throws IOException {
		//mybatis 配置文件
				String config = "sqlMapConfig.xml";
				//获取配置文件流
				InputStream ips = Resource.class.getClassLoader().getResourceAsStream(config);
				//创建回话工厂，传入mybatis配置文件信息
				SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(ips);
				//通过工厂得到session
				SqlSession session =sql.openSession();
				
				User user = new User();
				user.setId(31);
				user.setUsername("张巧");
				user.setPassword("654321");
				user.setSex(1);
				user.setBirthday(new Date());
				user.setIsAdmin(1);
				session.insert("test.insertByUserName",user);
				session.commit();
				//主键返回
				Integer id = user.getId();
				System.out.println(id);
				session.close();
				
		
	}
	
	
	
	

}