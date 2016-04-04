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

import com.atayun.hgs.wuliu.po.CompleteOrderDetail;
import com.atayun.hgs.wuliu.po.Orderlist;
import com.atayun.hgs.wuliu.po.RentOrderView;
import com.atayun.hgs.wuliu.service.OrderInfoService;
import com.atayun.hgs.wuliu.service.RentInfoService;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;

@Controller
@RequestMapping("/order.do")
public class OrderController {
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private RentInfoService rentInfoService;

	// 订单列表
	@RequestMapping(params = "method=getOrderList", method = { RequestMethod.POST })
	public void getOrders(int userid, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		ArrayList<Orderlist> orders = orderInfoService
				.listSampleorderInfo(userid);// 简单用户订单列表
		if (!orders.isEmpty()) {
			jsonObject.put("getOrderListStatus", "200");// 获取订单列表成功
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONArray carlist = JSONArray.fromObject(orders, jsonConfig);
			jsonObject.put("orderlist", carlist); // 车辆信息

		} else {
			jsonObject.put("getOrderListStatus", "20");// 失败;
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	// 订单详情
	@RequestMapping(params = "method=showOrderDetail", method = { RequestMethod.POST })
	public void showOrderDetail(int orderid, int orderStatus,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println(orderid + "---------"+orderStatus);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		if (orderStatus == 3) {
			CompleteOrderDetail CompleteOrderDetail = orderInfoService
					.getCompleteOrder(orderid);

			if (CompleteOrderDetail != null) {

				JSONObject CompleteOrder = JSONObject.fromObject(
						CompleteOrderDetail, jsonConfig);
				jsonObject.put("showOrderDetailState", "200");
				jsonObject.put("CompleteOrderDetail", CompleteOrder);

			} else {
				jsonObject.put("showOrderDetailState", "10");
			}
		}else if(orderStatus==1){
			RentOrderView tv=orderInfoService.getOrderInfo(orderid);
			if (tv != null) {

				JSONObject rentOrder = JSONObject.fromObject(
						tv, jsonConfig);
				jsonObject.put("showOrderDetailState", "200");
				jsonObject.put("CompleteOrderDetail", rentOrder);

			} else {
				jsonObject.put("showOrderDetailState", "10");
			}
			
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
}
