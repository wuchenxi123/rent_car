package com.atayun.hgs.wuliu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atayun.hgs.wuliu.dao.OrderInfoDao;
import com.atayun.hgs.wuliu.dao.RentInfoDao;
import com.atayun.hgs.wuliu.po.OrderInfo;
import com.atayun.hgs.wuliu.po.RentInfo;
import com.atayun.hgs.wuliu.po.RentOrderView;


@Transactional
@Service
public class OrderInfoService {
@Autowired
private OrderInfoDao orderInfoDao;
@Autowired
private RentInfoDao rentinfodao;

//创建订单 添加信息
public int createOrderDetail(RentInfo rentinfo,OrderInfo orderinfo){
	int rentid=rentinfodao.creatRentInfo(rentinfo.getRentPlace(), rentinfo.getRentDays(), rentinfo.getRentTakeTime(),
			rentinfo.getRentLpnum(),rentinfo.getRentCtype(), rentinfo.getUserId());
	orderinfo.setRentId(rentid);
	return orderInfoDao.createOrderDetail(orderinfo.getUserId(), orderinfo.getOrderPrice(), orderinfo.getRentId());
}
//修改订单状态
public boolean changeOrderStatus(int status,int orderId){
	return orderInfoDao.changeOrderStatus(status, orderId);
}
//修改订单状态
public int changeOrderStatus(int userid,float remainder,int status,int orderId){
	int flag=0;
	boolean changeRemainder=orderInfoDao.changeRemainder(userid, remainder);
	if(changeRemainder){
		 flag=1;
		boolean changeOrderStatus=orderInfoDao.changeOrderStatus(status, orderId);
		if(changeOrderStatus){
			flag=2;
		}
	}
	return flag;
}
//获取订单信息根据id
public RentOrderView getOrderInfo(int orderid){
	 return orderInfoDao.getOrderDetalilByID(orderid);
}
//修改用户余额
public boolean changeOrderRemainder(int userid,float remainder){
	return orderInfoDao.changeRemainder(userid, remainder);
}
//获取订单详情 不带还车信息
public RentOrderView getOrderDetail(int orderid){
	return orderInfoDao.getOrderDetalilByID(orderid);
}
}
