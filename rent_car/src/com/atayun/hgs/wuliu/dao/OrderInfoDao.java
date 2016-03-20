package com.atayun.hgs.wuliu.dao;

import java.util.ArrayList;


import com.atayun.hgs.wuliu.po.OrderView;
import com.atayun.hgs.wuliu.po.RentOrderView;


public interface OrderInfoDao {
	//设置订单状态
	public boolean changeOrderStatus(int status,int orderid) ;	
	//获取用户所有订单信息
	public ArrayList<RentOrderView> getOrderListByID(Integer userId) ;
	
	//根据用户ID和订单状态获取订单列表
		public ArrayList<RentOrderView> getOrderListByID(Integer userId,Integer orderFlag) ;

	//根据订单号获取订单详情
		public RentOrderView getOrderDetalilByID(Integer orderId) ;
	//根据订单号获取订单详情 有送车信息的
		public OrderView getCompleteOrderDetalilByID(Integer orderId) ;
    //修改用户余额
		public boolean changeRemainder(int id,float remainder);
		
	
		//添加订单详情 返回订单ID
		public int createOrderDetail(int userid ,float orderPrice,int rentId);
		
		//完善订单详情
		public int improveOrderDetail(int orderid ,String orderPrice,int returnId);
        //添加还车信息
		public int addReturnInfo(int orderid, int returnid);
		//修改订单价格
		public boolean changeOrderPrice(int orderid, float orderPrice,int rentDays,float overSpend,int orderStatus);
		//修改超支
		public boolean changeOverSpend(int orderid, float overSpend);
		
}
