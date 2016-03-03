package com.atayun.hgs.wuliu.dao;

import java.util.ArrayList;

import com.atayun.hgs.wuliu.po.Order;
import com.atayun.hgs.wuliu.po.OrderDetail;
import com.atayun.hgs.wuliu.po.Pricing;

public interface OrderDao {
	
	//车主查看订单gyik
	public ArrayList<Order> getOrderListByID(Integer userId,Integer orderFlag);
	
	//车主根据orderOId来查看订单的详情
	public ArrayList<OrderDetail> getOrderDetalilByID(Integer orderId);
	
	//添加查询价格
	public boolean createPricing(Pricing pricing);
	
	//添加订单详情
	public boolean createOrderDetail(OrderDetail detail);
	
	//车主根据货物生成订单请求页面，然后输入议价后则提交生成订单，初始的时候是待处理订单
	public int createOrder(Order order);
	
	//车主删除订单,步骤一删除订单详情
	public boolean deleteOrderDetailByCarUser(Integer orderId);
	
	//车主删除订单,步骤二删除订单
	public boolean deleteOrderByCarUser(Integer orderId);
	
	//货主拒绝订单请求,根据货物的ID 步骤一
	public boolean refuseOrderUpdateDD(Integer orderId,Integer setFlag);
	
	//货主拒绝订单请求,根据货物的ID 步骤二
	public boolean refuseOrderUpdateDE(Integer orderId,Integer setFlag);
	
	//查找订单的ID
	public int getOrderID(Integer cainId,Integer pricCaroId);
	

}
