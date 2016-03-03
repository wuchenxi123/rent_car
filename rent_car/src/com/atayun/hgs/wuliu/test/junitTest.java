package com.atayun.hgs.wuliu.test;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atayun.hgs.wuliu.action.UserController;
import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.po.CargoInformation;
import com.atayun.hgs.wuliu.po.MessageCar;
import com.atayun.hgs.wuliu.po.MessageCargo;
import com.atayun.hgs.wuliu.po.Order;
import com.atayun.hgs.wuliu.po.OrderDetail;
import com.atayun.hgs.wuliu.po.Pricing;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.service.CarInfoService;
import com.atayun.hgs.wuliu.service.CargoInformationService;
import com.atayun.hgs.wuliu.service.MessageService;
import com.atayun.hgs.wuliu.service.OrderService;
import com.atayun.hgs.wuliu.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebRoot/WEB-INF/springmvc-servlet.xml"}) 
public class junitTest {
	
	@Resource
	private CargoInformationService cargoInformationService;
	@Resource
	private UserService userService;
	@Resource
	private CarInfoService carInfoService;
	@Resource
	private OrderService orderService;
	@Resource
	private UserController userController;
	@Resource
	private MessageService messageService;
	@Test
	public void test() {
		int result = -1;
		//result = userService.validateCagoFlag(10000);
		//result = userService.validateCagoCompFlag(222);
		//result = userService.validateCarUserFlagSQL(15);
		/*List list  = new ArrayList<String>();
		list = cargoInformationService.getUserFlag(60);
		for (int i = 0; i < list.size(); i++) {
			System.out.print("--->");
			System.out.println(list.get(i));
		}*/
		//System.out.println(result);
		
//		boolean flag = false;
//		flag = cargoInformationService.deleteCargoInfo(42);
//		if(flag){
//			System.out.println("删除成功III");
//		}else{
//			System.out.println("删除失败");
//		}
//		String code = (int)((Math.random()*9+1)*100000)+"";
//		boolean flag = userService.sendSMSRequest("13875966342", code);
//		User u=userService.loginUserByMobile("13875966342", "123456");
		List<CarInfo> all=carInfoService.getAllCarInfo("宝马");
		System.out.println(all.size()+"-----------");
		for (CarInfo carInfo : all) {
			System.out.println(carInfo.getCarColor()+"------------");
		}
	}
	
	@Test
	public void test2() {
	
		CargoInformation cargoInformation = new CargoInformation();
		cargoInformation.setCargoInfoContacts("hwj");
		cargoInformation.setCargoInfoContactWay("12345678900");
		cargoInformation.setCargoInfoPicturl("tupian");
		cargoInformation.setCargoTypeId(2);
		cargoInformation.setTransportTypeId(16);
		cargoInformation.setCargoInfoStart("中南大学铁道学院");
		cargoInformation.setCargoInfoEnd("中南大学新校区");
		cargoInformation.setCargoInfoId(52);
		
		String deliTime = "2015-06-04 12:58:12.0";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
		java.util.Date date = null;
		try {
			date = sdf.parse(deliTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cargoInformation.setCargoInfoDeliTime(date);
		boolean flag = cargoInformationService.modifyCargoInfo(cargoInformation);
		if(flag){
			System.out.println("ok");
		}else{
			System.out.println("fail");
		}
	}
	
	@Test
	public void Test3(){
		
		/*ArrayList<Order> orders = new ArrayList<Order>();
		orders = orderService.getOrderListByID(23, 1);
		for (int i = 0; i < orders.size(); i++) {
			System.out.println(orders.get(i).getOrderNo()+":");
		}*/
		
		ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		orderDetails = orderService.getOrderDetalilByID(23);
		if(orderDetails.isEmpty()){
			System.out.println("没有数据");
		}else{
			System.out.println("ok");
		}
		for (int i = 0; i < orderDetails.size(); i++) {
			System.out.println("");
			System.out.println(orderDetails.get(i).getOrderNo()+"_________________________");
		}
	}
	
	@Test
	public void Test4(){
		
		Order order = new Order();
		order.setUserId(17);
		order.setOrderNo("8051415238222");
		order.setOrderFlag(0);
		order.setOrderPrice(120);
		
		Pricing pricing = new Pricing();
		pricing.setCainId(10);
		pricing.setCaroId(17);
		pricing.setCagoId(12);
		pricing.setPricPrice(28.5f);
		pricing.setPricDirection(0);
		pricing.setPricMark("mark");
		
		OrderDetail detail = new OrderDetail();
		detail.setCariId(4);
		detail.setCargoInfoId(10);
		detail.setOrddFlag(0);
		detail.setOrddprice(28.5f);
		detail.setOrddCSUBSPRICE(500);
		detail.setOrddHSUBSPRICE(600);
		
		boolean flag = orderService.CreateOrderOK(detail, pricing, order);
		if(flag){
			System.out.println("ok");
		}else{
			System.out.println("fail");
			
			
		}
	}
	
	@Test
	public void Test5(){
		ArrayList<MessageCargo> messages = new ArrayList<MessageCargo>();
		
		messages = messageService.getMyMessagesCargo(12);
		
		for (int i = 0; i < messages.size(); i++) {
			System.out.println(messages.get(i).getPricMark());
		}
	}
	

	@Test
	public void Test6(){
		ArrayList<MessageCar> messages = new ArrayList<MessageCar>();
		
		messages = messageService.getMyMessagesCar(23);
		
		for (int i = 0; i < messages.size(); i++) {
			System.out.println(messages.get(i).getPricMark());
		}
	}
}
