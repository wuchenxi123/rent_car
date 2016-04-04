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
import com.atayun.hgs.wuliu.po.OrderView;
import com.atayun.hgs.wuliu.po.Orderlist;
import com.atayun.hgs.wuliu.po.RentOrderView;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.service.CarInfoService;
import com.atayun.hgs.wuliu.service.OrderInfoService;
import com.atayun.hgs.wuliu.service.ReturnInfoService;
import com.atayun.hgs.wuliu.service.UserService;
import com.atayun.hgs.wuliu.utils.CountDays;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;


@Controller
@RequestMapping("/return.do")
public class ReturnController {
	@Resource
	private UserService userService;
	@Resource
	private CarInfoService carInfoService = new CarInfoService();
	@Resource
	private OrderInfoService orderInfoService = new OrderInfoService();
    @Resource
    private ReturnInfoService returnInfoService=new ReturnInfoService();
	
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
	//    使用中页面——还车页面   选定订单后 获取订单 ：车辆信息   当前产生费用   （预计产生省费用 为 订单费用）预计还车时间 
	@RequestMapping(params = "method=carOnUse", method = { RequestMethod.POST })
	public void carOnUse(int orderid,HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject=new JSONObject();
        
		//获取当前车辆信息
		RentOrderView rentOrderView=orderInfoService.getOrderInfo(orderid);
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//jsonObject.put("takecarTime", sdf.format(rentOrderView.getRentTakeTime()));   
		CarInfo carinfo=carInfoService.getCarBybandAndLpnum(rentOrderView.getRentCtype() , rentOrderView.getRentLpnum());
		if(carinfo!=null){
//			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
//			JSONArray car = JSONArray.fromObject(carinfo, jsonConfig);
		       jsonObject.put("carLpnum", carinfo.getCarLpnum());   //车辆信息
		       jsonObject.put("carRentPrice", carinfo.getCarRentPri());   //车辆信息
//		       jsonObject.put("carInfo", carinfo);   //车辆信息
		       jsonObject.put("carOnUseState", "200");    //获取车辆信息成功
		       Date startDay=rentOrderView.getRentTakeTime();
		       CountDays countdays=new CountDays();

				Date end=new Date();  //系统日期
		       long days=countdays.countDays(startDay,end);  //计算当前租车天数
		       float presentPrice=days*carinfo.getCarRentPri();
		       jsonObject.put("presentPrice", presentPrice);   //当前产生费用
		       jsonObject.put("orderPrice", rentOrderView.getOrderPrice());
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

	
	// 点击设置 计算预计租车费用 超支费用等
	// 传入数据 选定的换车订单信息 有两个实体对象rentOrderView (orderid  rentTime) carinfo(carRentPrice)
	@RequestMapping(params = "method=returnCar", method = { RequestMethod.POST })
	public void returnCar(String carLpnum,String rentTakeTime, int orderid,float carRentPrice,
			String predictReturnTime, String returnPlace, String setReturnTime,
			int returnType, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("-------------");
		System.out.println(rentTakeTime+"==="+orderid+"===="+carRentPrice
			+"-----"+predictReturnTime+"======"+returnPlace+"----"+setReturnTime
			+"--"+returnType);
		JSONObject jsonObject = new JSONObject();
		// 超时的费用该不该存到数据库？？
		// 计算超时费用
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		CountDays countdays = new CountDays();
		String isOverspend="0";
		
		try {
			// 设置的还车时间在预计还车时间之后 重新计算预计产生费用
			if ((countdays.countDays(sdf.parse(predictReturnTime),sdf.parse(setReturnTime))) >= 0) {
				if ((countdays.countDays(sdf.parse(predictReturnTime),sdf.parse(setReturnTime))) > 0) {
					long days = countdays.countDays(sdf.parse(rentTakeTime),sdf.parse(setReturnTime));
					float predictMoney = carRentPrice * days;
					jsonObject.put("predictMoney", predictMoney); // 预计产生的费用
					
					float overspend=(countdays.countDays(sdf.parse(predictReturnTime),sdf.parse(setReturnTime)))*carRentPrice;
					jsonObject.put("overspend", overspend); // 超出定单原始价格的支出
					// 设置订单超支
					boolean flag = orderInfoService.changeoverSpend(orderid, overspend);
					if (flag) {
						isOverspend="1"; // 设置超支成功
						jsonObject.put("overSpendState", isOverspend); // 设置未超支
					}
				}

				// 提交租车信息 存入数据库
//				int userid = orderInfoService.getCarByOrderid(orderid);// 获取司机信息
				int userid=carInfoService.getCarByLpnum(carLpnum).getUserId();
				int juge = returnInfoService.putReturnInfo(userid, returnPlace,
						sdf.parse(setReturnTime), returnType);
				
				if (juge != 0) { // juge值 0 失败 ； returnid 成功
					jsonObject.put("overSpendState",isOverspend); // 设置未超支
					jsonObject.put("returnCarState", "300"); // 创建还车信息成功
					jsonObject.put("returnid", juge);
					// 添加到订单
					orderInfoService.addReturninfo(orderid, juge);
					jsonObject.put("orderid", orderid); // 订单号
				}
				else {

					jsonObject.put("returnCarState", "20"); // 出错

				}

			} else {

				jsonObject.put("returnCarState", "30"); // 设置还车时间出错

			}

		} catch (ParseException e) {
			jsonObject.put("returnCarState", "20");// 出错
			e.printStackTrace();
		}
System.out.println("=======");
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	
	//支付超出订单费用
	@RequestMapping(params="method=payReturn",method={RequestMethod.POST})
	public void payReturn(int userid,String carLpnum,int rentDays,int orderid,float orderPrice,float overSpend,HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject=new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		
		System.out.println(rentDays+"-----------------+");
		int state=orderInfoService.changeOrderPriceAndOverSpend(orderid, orderPrice,overSpend,rentDays);
		
		if(state==3){
			ArrayList<Orderlist> orders=orderInfoService.listorderInfo(userid);//简单用户订单列表
			JSONArray orderlist = JSONArray.fromObject(orders, jsonConfig);
			jsonObject.put("orderlist", orderlist);
			System.out.println(orderlist.toString()+"=----------");
			CarInfo car=carInfoService.getCarByLpnum(carLpnum);
			System.out.println(car.getUserId()+"--"+carLpnum);
			User user=userService.getRemainder(car.getUserId());
			
			jsonObject.put("payOverSpendState", "200"); // 支付失败
			jsonObject.put("driverName", user.getUserName());
			jsonObject.put("driverMobile", user.getUserMobile());
		}else{
			jsonObject.put("payOverSpendState", "20"); // 支付失败
		}
		
    	   OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	

	
	
	//确认还车信息  查询订单详情
		@RequestMapping(params = "method=showOrder", method = { RequestMethod.POST })
		public void showOrder(int orderid,HttpServletRequest request,HttpServletResponse response){
			JSONObject jsonObject=new JSONObject();
			//获取订单详情
			OrderView orderView= orderInfoService.getOrderDetails(orderid);
			CarInfo carinfo=carInfoService.getCarBybandAndLpnum(orderView.getRentCtype() , orderView.getRentLpnum());
			
			//车辆信息
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			JSONArray car = JSONArray.fromObject(carinfo, jsonConfig);
     		jsonObject.put("carinfo", car); //车辆信息
     		
     		
    		Date startDay=orderView.getRentTakeTime();
    		
//    		CountDays countdays=new CountDays();
//    		
//    	    long days=countdays.countDays(startDay,new Date());  //计算当前租车天数
//    	    System.out.println("**********************"+days);
//    		float presentPrice=days*(carinfo.getCarRentPri());
//
//    		jsonObject.put("presentPrice", presentPrice);   //当前产生费用
//            jsonObject.put("total", orderView.getOrderPrice());   //预计产生费用
//            jsonObject.put("returnPlace", orderView.getReturnPlace());//还车地点
//            
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            jsonObject.put("returntime", sdf.format(orderView.getReturnReTime()));  //还车时间  json只能接受String类型
//            
            jsonObject.put("drivername", orderView.getUserRelname());//司机姓名
     		jsonObject.put("drivermobile", orderView.getUserMobile());//司机电话

     		jsonObject.put("orderid", orderid); //订单ID
            
        	OutJsonUtils.putOutJson(jsonObject, request, response);
		}
		
		

		
		
	//已付款  修改订单状态 修改车辆出租状态
	@RequestMapping(params="method=afterPaied",method={RequestMethod.POST})
	public void afterPaied(int orderid,HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject=new JSONObject();
		
	//	事务int  changeOrderAndCarState（int orderid）
       int changeFlag=returnInfoService.changeOrderAndCarState(orderid);
    	   if(changeFlag==2){  
    		   jsonObject.put("afterPaiedState", "200"); //订单和车辆租用状态都修改成功
       }else{
    	   jsonObject.put("afterPaiedState", "20"); //订单和车辆租用状态修改失败
       }
    	   OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
}
