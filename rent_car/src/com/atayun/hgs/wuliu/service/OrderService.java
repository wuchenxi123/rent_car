package com.atayun.hgs.wuliu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atayun.hgs.wuliu.dao.OrderDao;
import com.atayun.hgs.wuliu.po.Order;
import com.atayun.hgs.wuliu.po.OrderDetail;
import com.atayun.hgs.wuliu.po.Pricing;

/**
 * 订单的业务逻辑
 * @author HWJ
 *
 */
@Transactional
@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	/**
	 * 根据车主ID和订单的状态查找订单
	 * @param userId
	 * @param orderFlag
	 * @return
	 */
	public ArrayList<Order> getOrderListByID(Integer userId, Integer orderFlag) {
		
		return orderDao.getOrderListByID(userId, orderFlag);
	}
	/**
	 * 根据订单的ID查看订单的详情
	 * @param orderId
	 * @return
	 */
	public ArrayList<OrderDetail> getOrderDetalilByID(Integer orderId) {
		
		return orderDao.getOrderDetalilByID(orderId);
	}
	
	/**
	 * 生成订单
	 * @param detail
	 * @param pricing
	 * @param order
	 * @return
	 */
	public boolean CreateOrderOK(OrderDetail detail,Pricing pricing,Order order){
		
		boolean flag = false;
		boolean flagPric = false;
		boolean flagOrderDetail = false;
		int ordeId = orderDao.createOrder(order);
		detail.setOrddId(ordeId);
		flagPric = orderDao.createPricing(pricing);
		flagOrderDetail = orderDao.createOrderDetail(detail);
		if(flagPric&&flagOrderDetail){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 车主删除订单
	 * @param orderId
	 * @return
	 */
	public boolean deleteOrderByCarUser(Integer orderId) {
		
		boolean flag = false;
		boolean deleteDetail = false;
		boolean deleteOrder = false;
		deleteDetail = orderDao.deleteOrderDetailByCarUser(orderId);
		if(deleteDetail){
			deleteOrder = orderDao.deleteOrderByCarUser(orderId);
		}
		if(deleteDetail&&deleteOrder){
			flag = true;
		}
		return flag;
		
	}
	
	/**
	 * 货主拒绝车主的订单请求
	 * @param cainId
	 * @param pricCaroId
	 * @return
	 */
	public boolean refuseOrderByCargo(Integer cainId, Integer pricCaroId,Integer setFlag) {
		
		boolean flag = false;
		boolean flagDD = false;
		boolean flagDE = false;
		int orderId  = orderDao.getOrderID(cainId, pricCaroId);
		if(orderId>0){
			
			flagDD = orderDao.refuseOrderUpdateDD(orderId,setFlag);
			flagDE = orderDao.refuseOrderUpdateDE(orderId,setFlag);
		}
		if(flagDD&&flagDE){
			flag = true;
		}
		return flag;
	}
	
	//车主取消订单,只能取消未处理订单
	public boolean cancelOrderByCarUser(Integer orderId,Integer setFlag){
		
		boolean flag = false;
		boolean flagDD = false;
		boolean flagDE = false;
		if(orderId>0){
			flagDD = orderDao.refuseOrderUpdateDD(orderId,setFlag);
			flagDE = orderDao.refuseOrderUpdateDE(orderId,setFlag);
		}
		if(flagDD&&flagDE){
			flag = true;
		}
		return flag;
	}
	
}
