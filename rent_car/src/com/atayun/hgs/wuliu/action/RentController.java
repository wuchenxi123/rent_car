package com.atayun.hgs.wuliu.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.po.OrderInfo;
import com.atayun.hgs.wuliu.po.RentInfo;
import com.atayun.hgs.wuliu.po.RentOrderView;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.service.CarInfoService;
import com.atayun.hgs.wuliu.service.OrderInfoService;
import com.atayun.hgs.wuliu.service.RentInfoService;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;

@Controller
@RequestMapping("/rent.do")
public class RentController {
	@Resource
	private RentInfoService rentInfoService = new RentInfoService();
	@Resource
	private CarInfoService carInfoService = new CarInfoService();
	@Resource
	private OrderInfoService orderInfoService = new OrderInfoService();

	// 选车 同时创建租车信息
	@RequestMapping(params = "method=selectCar", method = { RequestMethod.POST })
	public void selectCar(HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();

		/*
		 * //创建租车信息 存入租车地点 时间 天数 用户 返回信息ID int
		 * rentid=rentInfoService.CreateRentInfo(rentinfo); if(rentid!=-1){
		 * jsonObject.put("rentid",rentid);
		 * jsonObject.put("savRentState","200"); }else{
		 * jsonObject.put("savRentState","10"); }
		 */

		// 获取所有可用车辆信息
		ArrayList<CarInfo> carlist = new ArrayList<CarInfo>();
		carlist = carInfoService.getAllCarInfo();

		if (!carlist.isEmpty()) {// 判断查询结果是否为空
			// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			// 将List转换为JSONArray数据
			JSONArray jo = JSONArray.fromObject(carlist, jsonConfig);
			jsonObject.put("carlist", jo);
			// 查询成功标识
			jsonObject.put("carState", "200");
		} else {
			// 查询失败标识
			jsonObject.put("carState", "30");
		}

		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	// 提交
	@RequestMapping(params = "method=rentCar", method = { RequestMethod.POST })
	public void rentCar(RentInfo rentinfo, String takeTime, int carId,
			int userId, int carTaketype, float orderPrice,
			HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		System.out.println(rentinfo.getRentPlace() + rentinfo.getRentDays());
		System.out.println(carId + userId + carTaketype + orderPrice);
		// 修改被选车辆的租用状态 1租用
		carInfoService.changeCarStatus(carId, 1);

		// 添加租车信息 返回rentid
		CarInfo car = carInfoService.getAllCarInfo(carId);
		rentinfo.setRentCtype(car.getCarBand());
		rentinfo.setRentLpnum(car.getCarLpnum());
		rentinfo.setUserId(car.getUserId()); // 送车司机

		// 转换取车时间的数据类型
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			rentinfo.setRentTakeTime(format.parse(takeTime));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// int rentid = rentInfoService.CreateRentInfo(rentinfo);

		// jsonObject.put("rentCarState", "200");

		// 订单创建
		// 标示是否要送车费 修改相应字段
		if (carTaketype == 0) { // 0 （默认）1 公司地点取车
			carInfoService.changeCarTaketype(carId, 0);
		}
		OrderInfo orderinfo = new OrderInfo();
		orderinfo.setUserId(userId);
		orderinfo.setOrderPrice(orderPrice);
		// orderinfo.setRentId(rentid);
		int orderid = orderInfoService.createOrderDetail(rentinfo, orderinfo);
		if (orderid != -1) {
			orderInfoService.changeOrderStatus(1, orderid); // 0
															// 新建；1订单执行中；2订单已确认/支付；3订单已结束
			jsonObject.put("orderid", orderid);
			jsonObject.put("rentCarSata", "200");
		} else {
			jsonObject.put("rentCarSata", "10"); // 订单创建失败
		}

		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	// 支付 修改用户余额
	@RequestMapping(params = "method=payOrder", method = { RequestMethod.POST })
	public void payOrder(int orderid, int payflag, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		int changeOrderStatus = 0;
		System.out.println(orderid + "--------" + payflag);

		if (payflag == 1) {
			RentOrderView rentOrderView = orderInfoService
					.getOrderInfo(orderid);
			float userRemainder = rentOrderView.getUserRemainder();
			float orderPrice = rentOrderView.getOrderPrice();
			float reMoney = userRemainder - orderPrice;
			// 判断付款后余额 >=0可付订单
			if (reMoney >= 0) {
				changeOrderStatus = orderInfoService.changeOrderStatus(
						rentOrderView.getUserId(), reMoney, orderid, 2); // 0新建；1订单执行中；2订单已确认/支付；3订单已结束
				if (changeOrderStatus == 2) {
					jsonObject.put("payOrderState", "200");
				} else if (changeOrderStatus == 1) {
					jsonObject.put("payOrderState", "10"); // 余额不足 支付失败
				} else if (changeOrderStatus == 0) {
					jsonObject.put("payOrderState", "10"); // 支付失败
				}
			} else {
				jsonObject.put("payOrderState", "20");// 余额不足
			}
		}
		// else{
		// jsonObject.put("payOrderState", "30");//未选择账号余额支付
		// }
		jsonObject.put("orderid", orderid);
		System.out.println(orderid);
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	// 订单支付后 订单情况
	@RequestMapping(params = "method=showRentOrder", method = { RequestMethod.POST })
	public void showRentOrder(int orderid, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		RentOrderView rentOrderView = new RentOrderView();
		rentOrderView = orderInfoService.getOrderDetail(orderid);

		if (rentOrderView != null) {
			jsonObject.put("getOrderState", "200");
			jsonObject.put("rentPlace", rentOrderView.getRentPlace());
			jsonObject.put("rentTakeTime", rentOrderView.getRentTakeTime()
					.toString());

			CarInfo carinfo = carInfoService.getCarBybandAndLpnum(
					rentOrderView.getRentCtype(), rentOrderView.getRentLpnum());
			int rentdays = rentOrderView.getRentDays();
			jsonObject
					.put("totalrentFee", (carinfo.getCarRentPri()) * rentdays);// 租车费用
			jsonObject.put("insurePrice", carinfo.getInsurePrice() * rentdays);// 保险费
			if (carinfo.getCarTaketype() == 1) {
				jsonObject.put("scsmPrice", carinfo.getScsmPrice());
			} else {
				jsonObject.put("scsmPrice", 0);
			}
			jsonObject.put("carPlnum", carinfo.getCarLpnum());// 车牌号

			User driver = rentInfoService.getDriverInfo(rentOrderView
					.getSenddriverId()); // Column 'rent_id' not found.
			jsonObject.put("driverMobile", driver.getUserMobile());// 司机手机号
			jsonObject.put("carPhotoUrl", carinfo.getCarPictUrl());// 车辆图片地址
		} else {
			jsonObject.put("getOrderState", "10");
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

}
