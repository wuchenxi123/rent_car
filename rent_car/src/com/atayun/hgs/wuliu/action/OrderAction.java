package com.atayun.hgs.wuliu.action;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atayun.hgs.wuliu.po.Order;
import com.atayun.hgs.wuliu.po.OrderDetail;
import com.atayun.hgs.wuliu.po.Pricing;
import com.atayun.hgs.wuliu.service.CargoInformationService;
import com.atayun.hgs.wuliu.service.OrderService;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;
@Controller
@RequestMapping("/order.do")
public class OrderAction {
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private CargoInformationService cargoInformationService;
	/**
	 * 车主根据自己的UserId去自己的订单查看订单
	 * @param userId  车主ID
	 * @param orderFlag 订单状态（0 待处理订单；1未完成订单；2已完成订单；3已取消订单)
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getOrderListByID",method={RequestMethod.POST})
	public void getOrderListByID(Integer userId, Integer orderFlag,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = orderService.getOrderListByID(userId, orderFlag);
		if(!orders.isEmpty()){
			jsonObject.put("getOrderState", "200");//有订单
			JSONArray orderArray = JSONArray.fromObject(orders,jsonConfig);
			jsonObject.put("OrderList", orderArray);
		}else{
			jsonObject.put("getOrderState", "10");//还没有任何订单	
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	
	/**
	 * 根据订单的ID查看订单的详情
	 * @param orderId 订单的ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getOrderDetalilByID",method={RequestMethod.POST})
	public void getOrderDetalilByID(Integer orderId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		ArrayList<OrderDetail> orderdetails = new ArrayList<OrderDetail>();
		orderdetails = orderService.getOrderDetalilByID(orderId);
		if(!orderdetails.isEmpty()){
			jsonObject.put("getOrderDetailState", "200");//查看订单详情成功
			JSONArray orderDetailArray = JSONArray.fromObject(orderdetails,jsonConfig);
			jsonObject.put("OrderDetail", orderDetailArray);
		}else{
			jsonObject.put("getOrderDetailState", "10");//查看订单详情失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	
	/**
	 * 生成订单，待处理状态
	 * @param detail 订单详情
	 * @param pricing 询问价格
	 * @param order 订单列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=CreateOrderOK",method={RequestMethod.POST})
	public void CreateOrderOK(OrderDetail detail,Pricing pricing,Order order,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		boolean flag = false;
		order.setOrderFlag(0);//第一次车主请求货主确认订单的时候 ，订单默认为待处理
		detail.setOrddFlag(0);//第一次车主请求货主确认订单的时候 ，详情默认为待处理
		pricing.setCainId(detail.getCargoInfoId());
		pricing.setCaroId(order.getUserId());
		
		flag = orderService.CreateOrderOK(detail, pricing, order);
		if(flag){
			jsonObject.put("CreateOrderState", "200");//订单生成成功
		}else{
			jsonObject.put("CreateOrderState", "10");//订单生成失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	

	/**
	 * * 车主根据订单ID 删除订单
	 * @param orderId 订单ID
	 * @param orderFlag 订单状态
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=deleteOrderByCarUser",method={RequestMethod.POST})
	public void deleteOrderByCarUser(Integer orderId,Integer orderFlag,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = false;
		if(orderFlag!=1){//不是处在未完成状态的订单
			flag = orderService.deleteOrderByCarUser(orderId);
		}else{
			jsonObject.put("deleteOrderState", "20");//未完成状态的订单不可以删除
		}
		
		if(flag){
			jsonObject.put("deleteOrderState", "200");//订单删除成功
		}else{
			jsonObject.put("deleteOrderState", "10");//订单删除失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	
	/**
	 * 货主拒绝车主的订单请求
	 * @param cainId
	 * @param pricCaroId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=refuseOrderByCargo",method={RequestMethod.POST})
	public void refuseOrderByCargo(Integer cainId, Integer pricCaroId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = false;
		Integer setFlag = 3;
		flag = orderService.refuseOrderByCargo(cainId, pricCaroId,setFlag);
		if(flag){
			jsonObject.put("refuseOrderState", "200");//货主拒绝订单成功
		}else{
			jsonObject.put("refuseOrderState", "10");//货主拒绝订单失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	
	/**
	 * 车主取消 未处理的订单
	 * @param orderId
	 * @param orderFlag
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=cancelOrderByCarUser",method={RequestMethod.POST})
	public void cancelOrderByCarUser(Integer orderId,Integer orderFlag,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = false;
		Integer setFlag = 3;
		if(orderFlag==0){//未处理状态的订单
			flag = orderService.cancelOrderByCarUser(orderId,setFlag);
		}else{
			jsonObject.put("cancelOrderState", "20");//该状态的订单不可以取消，只有处于未处理状态的订单可以取消
		}
		
		if(flag){
			jsonObject.put("cancelOrderState", "200");//订单取消成功
		}else{
			jsonObject.put("cancelOrderState", "10");//订单取消失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	
	/**
	 * 货主接受订单
	 * @param cainId
	 * @param pricCaroId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=acceptOrderByCargo",method={RequestMethod.POST})
	public void acceptOrderByCargo(Integer cainId, Integer pricCaroId,float cargoRPrice,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = false;
		boolean priceFlag = false;
		Integer setFlag = 1;
		flag = orderService.refuseOrderByCargo(cainId, pricCaroId, setFlag);
		priceFlag = cargoInformationService.modifylastprice(cainId, cargoRPrice);
		if(flag&&priceFlag){
			jsonObject.put("acceptOrderState", "200");//货主接受订单成功
		}else{
			jsonObject.put("acceptOrderState", "10");//货主接受订单失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	
	/**
	 * 货主确认订单，订单状态改为已完成
	 * @param orderId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=confirmOrderByCargo",method={RequestMethod.POST})
	public void confirmOrderByCargo(Integer orderId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = false;
		Integer setFlag = 2;
		flag = orderService.cancelOrderByCarUser(orderId, setFlag);
		if(flag){
			jsonObject.put("confirmOrderState", "200");//货主确认订单成功
		}else{
			jsonObject.put("confirmOrderState", "10");//货主确认订单失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	
	
}
