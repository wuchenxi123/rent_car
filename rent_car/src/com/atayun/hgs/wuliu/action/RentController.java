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
import com.atayun.hgs.wuliu.utils.CountDays;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;

@Controller
@RequestMapping("/rent.do")
public class RentController {
	@Resource
	private RentInfoService rentInfoService;
	@Resource
	private CarInfoService carInfoService;
	@Resource
	private OrderInfoService orderInfoService;

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
		JsonConfig jsonConfig = new JsonConfig();
		
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		

		 
		int changeOrderStatus = 0;
		System.out.println(orderid + "--------" + payflag);
		int showRentOrder=0;
		RentOrderView rentOrderView=null;
		rentOrderView = orderInfoService
				.getOrderInfo(orderid);
		JSONObject rentOrder = JSONObject.fromObject(rentOrderView, jsonConfig);
		jsonObject.put("rentOrderView", rentOrder);
		float userRemainder = rentOrderView.getUserRemainder();
		if (payflag == 1) {
			
			float orderPrice = rentOrderView.getOrderPrice();
			float reMoney = userRemainder - orderPrice;
			// 判断付款后余额 >=0可付订单
			if (reMoney >= 0) {
				changeOrderStatus = orderInfoService.changeOrderStatus(
						rentOrderView.getUserId(), reMoney, 2, orderid); // 0新建；1订单执行中；2订单已确认/支付；3订单已结束
				if (changeOrderStatus == 2) {
					jsonObject.put("payOrderState", "200");
					showRentOrder=1;
				} else if (changeOrderStatus == 1) {
					jsonObject.put("payOrderState", "10"); // 支付失败
				} else if (changeOrderStatus == 0) {
					jsonObject.put("payOrderState", "10"); // 支付失败
				}
			} else {
				boolean flag1 = orderInfoService.changeOrderRemainder(
						rentOrderView.getUserId(), 0);
				if (flag1) {
					orderInfoService.changeOrderStatus(2, orderid); // 0新建；1订单执行中；2订单已确认/支付；3订单已结束
					jsonObject.put("payOrdertate", "200");// 支付成功状态码
					showRentOrder=1;
				} else {
					jsonObject.put("payOrderState", "10");// 支付失败
				}

			}
		} else {
			changeOrderStatus = orderInfoService.changeOrderStatus(
					rentOrderView.getUserId(), userRemainder, 2, orderid); // 0新建；1订单执行中；2订单已确认/支付；3订单已结束。
			if (changeOrderStatus == 2) {
				jsonObject.put("payOrderState", "200");
				showRentOrder=1;
			} else if (changeOrderStatus == 1) {
				jsonObject.put("payOrderState", "10"); // 支付失败
			} else if (changeOrderStatus == 0) {
				jsonObject.put("payOrderState", "10"); // 支付失败
			}
			//jsonObject.put("payOrderState", "30");// 未选择账号余额支付
		}
		jsonObject.put("orderid", orderid);
		
		if(showRentOrder==1){
			//查订单信息
			CarInfo carinfo=carInfoService.getCarBybandAndLpnum(rentOrderView.getRentCtype(), rentOrderView.getRentLpnum());
			User driver=rentInfoService.getDriverInfo(rentOrderView.getSenddriverId());
			JSONObject carJson=JSONObject.fromObject(carinfo, jsonConfig); 
			JSONObject userJson=JSONObject.fromObject(driver, jsonConfig); 
			jsonObject.put("carinfo", carJson);
			jsonObject.put("driver", userJson);

		}
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

	//获取当前用户所有未还车订单的信息       到使用中页面1 所有车辆信息
	@RequestMapping(params = "method=getOrders", method = { RequestMethod.POST })
	public void getOrders(int userid,HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject=new JSONObject();
		//查询当前用户的未完成所有订单     0 新建；1订单执行中；2订单已确认/支付；3订单已结束
		ArrayList<RentOrderView> orders=orderInfoService.getOrderListBystatus(userid,2);
		
		if (!orders.isEmpty()) {// 判断查询结果是否为空
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONArray jo = JSONArray.fromObject(orders, jsonConfig);
			jsonObject.put("orders", jo);
			// 查询成功标识
			jsonObject.put("getOrdersState", "200");
		} else {
			// 查询失败标识
			jsonObject.put("getOrdersState", "30");
		}
		// 根据自定义的工具类返回json数据
				OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	
    //    使用中页面1——使用中页面2  自己传递信息
	//    使用中页面——还车页面   选定订单后 获取订单 ：车辆信息   当前产生费用   （预计产生省费用 为 订单费用）
	@RequestMapping(params = "method=carOnUse", method = { RequestMethod.POST })
	public void carOnUse(int orderid,HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject=new JSONObject();
        
		//获取当前车辆信息
		RentOrderView rentOrderView=orderInfoService.getOrderInfo(orderid);
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//jsonObject.put("takecarTime", sdf.format(rentOrderView.getRentTakeTime()));   
		CarInfo carinfo=carInfoService.getCarBybandAndLpnum(rentOrderView.getRentCtype() , rentOrderView.getRentLpnum());
		if(carinfo!=null){
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			JSONArray car = JSONArray.fromObject(carinfo, jsonConfig);
		       jsonObject.put("CarInfo", car);   //车辆信息
		       
		       jsonObject.put("carOnUseState", "200");    //获取车辆信息成功
		       Date startDay=rentOrderView.getRentTakeTime();
		       CountDays countdays=new CountDays();

				Date end=new Date();  //系统日期
		       long days=countdays.countDays(startDay,end);  //计算当前租车天数
		       float presentPrice=days*carinfo.getCarRentPri();
		       jsonObject.put("presentPrice", presentPrice);   //当前产生费用
		       
//传出数据   租车时间    预计还车时间   =租车时间+租车天数
           	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		       String rentTime=sdf.format(rentOrderView.getRentTakeTime());
		       jsonObject.put("rentTime", rentTime);    //租车时间
		       
		       Date predictReturnTime=new  Date((rentOrderView.getRentTakeTime()).getTime()+(rentOrderView.getRentDays())*(24*60*60*1000));
		       jsonObject.put("predictReturnTime", sdf.format(predictReturnTime));    //预计还车时间
		
		}else{
		       jsonObject.put("carOnUseState", "20");    //获取车辆信息失败

		}
		// 根据自定义的工具类返回json数据
				OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	

}
