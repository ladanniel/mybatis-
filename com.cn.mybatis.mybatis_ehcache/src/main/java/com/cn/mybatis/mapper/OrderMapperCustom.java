package com.cn.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.cn.mybatis.pojo.OrderCustom;
import com.cn.mybatis.pojo.Orders;
import com.cn.mybatis.pojo.User;
public interface OrderMapperCustom {
	/**
	 * ��ѯ���������û���Ϣ
	 * @return
	 */
	List<OrderCustom> findOrderUser();
    List<Orders>findOrderUserResultMap();
    List<Orders>findOrderUserAndOrderDetail();
    List<Orders>findOrderUserLazyLoading();
    void updateUser(User user);
}
