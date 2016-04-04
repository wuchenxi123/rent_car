package com.atayun.hgs.wuliu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atayun.hgs.wuliu.dao.CarInfoDao;
import com.atayun.hgs.wuliu.dao.CompleteOrderDetailDao;
import com.atayun.hgs.wuliu.dao.OrderInfoDao;
import com.atayun.hgs.wuliu.dao.RentInfoDao;
import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.po.CompleteOrderDetail;
import com.atayun.hgs.wuliu.po.OrderInfo;
import com.atayun.hgs.wuliu.po.OrderView;
import com.atayun.hgs.wuliu.po.Orderlist;
import com.atayun.hgs.wuliu.po.RentInfo;
import com.atayun.hgs.wuliu.po.RentOrderView;

@Transactional
@Service
public class OrderInfoService {
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private RentInfoDao rentinfodao;
	@Autowired
	private CarInfoDao carInfoDao;
	@Autowired
	private CompleteOrderDetailDao completeOrderDetailDao;
	// 创建订单 添加信息
	public int createOrderDetail(RentInfo rentinfo, OrderInfo orderinfo) {
		int rentid = rentinfodao.creatRentInfo(rentinfo.getRentPlace(),
				rentinfo.getRentDays(), rentinfo.getRentTakeTime(),
				rentinfo.getRentLpnum(), rentinfo.getRentCtype(),
				rentinfo.getUserId());
		orderinfo.setRentId(rentid);
		return orderInfoDao.createOrderDetail(orderinfo.getUserId(),
				orderinfo.getOrderPrice(), orderinfo.getRentId());
	}

	// 修改订单状态
	public boolean changeOrderStatus(int status, int orderId) {
		return orderInfoDao.changeOrderStatus(status, orderId);
	}

	// 修改订单状态
	public int changeOrderStatus(int userid, float remainder, int status,
			int orderId) {
		int flag = 0;
		boolean changeRemainder = orderInfoDao.changeRemainder(userid,
				remainder);
		if (changeRemainder) {
			flag = 1;
			boolean changeOrderStatus = orderInfoDao.changeOrderStatus(status,
					orderId);
			if (changeOrderStatus) {
				flag = 2;
			}
		}
		return flag;
	}

	// 获取订单信息根据id
	public RentOrderView getOrderInfo(int orderid) {
		return orderInfoDao.getOrderDetalilByID(orderid);
	}

	// 修改用户余额
	public boolean changeOrderRemainder(int userid, float remainder) {
		return orderInfoDao.changeRemainder(userid, remainder);
	}

	// 获取订单详情 不带还车信息
	public RentOrderView getOrderDetail(int orderid) {
		return orderInfoDao.getOrderDetalilByID(orderid);
	}

	// 获取订单详情 带还车信息
	public OrderView getOrderDetails(int orderid) {
		return orderInfoDao.getCompleteOrderDetalilByID(orderid);
	}

	// 获取用户特定的订单状态下的订单 orderStatus=2
	public ArrayList<RentOrderView> getOrderListBystatus(int userid,
			int orderFlag) {
		return orderInfoDao.getOrderListByID(userid, orderFlag);
	}

	// 判断用户订单状态（租车 /还车）
	public ArrayList<RentOrderView> getAllOrders(int userid) {

		return orderInfoDao.getOrderListByID(userid);
	}

	// 添加还车信息
	public int addReturninfo(int orderid, int returnid) {
		return orderInfoDao.addReturnInfo(orderid, returnid);
	}

	// 修改订单超支

	public boolean changeoverSpend(int orderid, float overSpend) {
		return orderInfoDao.changeOverSpend(orderid, overSpend);
	}

	/*
	 * 根据订单号获取车辆司机信息id
	 */
	public int getCarByOrderid(int orderid) {
		RentOrderView rentorder = orderInfoDao.getOrderDetalilByID(orderid);
		
		int userid = rentorder.getUserId();
		return userid;

	}

	// 事务 修改订单价格和清零超支
	public int changeOrderPriceAndOverSpend(int orderid, float orderPrice,
			float overSpend,int rentDays) {
		int state = 0;
		if (overSpend > 0) {
			// RentOrderView order = orderInfoDao.getOrderDetalilByID(orderid);
			boolean flag = orderInfoDao.changeOrderPrice(orderid, orderPrice
					+ overSpend, rentDays,0, 3);
			if (flag) {
				RentOrderView rentorder = orderInfoDao
						.getOrderDetalilByID(orderid);
				CarInfo car = carInfoDao.getCarBybandAndLpnum(
						rentorder.getRentCtype(), rentorder.getRentLpnum());
				boolean flag3 = carInfoDao.setCarBusy(car.getCarId(), 0);// 车辆改为未租用
				if (flag3) {
					state = 3;// 订单和车辆状态都修改成功
				}
			}
		} else {
			RentOrderView rentorder = orderInfoDao.getOrderDetalilByID(orderid);
			CarInfo car = carInfoDao.getCarBybandAndLpnum(
					rentorder.getRentCtype(), rentorder.getRentLpnum());
			boolean flag3 = carInfoDao.setCarBusy(car.getCarId(), 0);// 车辆改为未租用
			if (flag3) {
				state = 3;// 订单和车辆状态都修改成功
			}
		}

		return state;
	}
	
	//列出用户简单的订单信息
	 public ArrayList<Orderlist> listSampleorderInfo(int userid){
		 ArrayList<Orderlist> orderlist=new ArrayList<Orderlist>();
		 ArrayList<RentOrderView> orders=orderInfoDao.getOrderListByID(userid);
		 if(!orders.isEmpty()){
			 for(RentOrderView order:orders){
				 Orderlist sampleorder=new Orderlist();
				 sampleorder.setRentTime(order.getRentTakeTime());
				 sampleorder.setOrderid(order.getOrderId());
				 sampleorder.setOrderPrice(order.getOrderPrice());
				 sampleorder.setRentdays(order.getRentDays());
				 sampleorder.setOrderStatus(order.getOrderStatus());
				 orderlist.add(sampleorder);
			 }
		 }
		 
		 return orderlist;
	 }
	 
	//列出用户简单的订单信息
		 public ArrayList<Orderlist> listorderInfo(int orderId){
			 ArrayList<Orderlist> orderlist=new ArrayList<Orderlist>();
			 ArrayList<RentOrderView> orders=orderInfoDao.getOrderListByID(orderId);
			 if(!orders.isEmpty()){
				 for(RentOrderView order:orders){
					 Orderlist sampleorder=new Orderlist();
					 sampleorder.setRentTime(order.getRentTakeTime());
					 sampleorder.setOrderid(order.getOrderId());
					 sampleorder.setOrderPrice(order.getOrderPrice());
					 sampleorder.setRentdays(order.getRentDays());
					 sampleorder.setOrderStatus(order.getOrderStatus());
					 orderlist.add(sampleorder);
				 }
			 }
			 
			 return orderlist;
		 }
	 //获取订单详尽信息
	 public CompleteOrderDetail getCompleteOrder(int orderid) {
		 return completeOrderDetailDao.getCompleteOrder(orderid);
	 }
}
