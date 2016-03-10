package com.atayun.hgs.wuliu.test;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atayun.hgs.wuliu.action.UserController;
import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.service.CarInfoService;
import com.atayun.hgs.wuliu.service.OrderInfoService;
import com.atayun.hgs.wuliu.service.UserService;
import com.atayun.hgs.wuliu.utils.MD5;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebRoot/WEB-INF/springmvc-servlet.xml"}) 
public class junitTest {
	
	@Resource
	private UserService userService;
	@Resource
	private CarInfoService carInfoService;
	
	@Resource
	private UserController userController;
	@Resource
	private OrderInfoService orderInfoService;
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
		/*
		User u=userService.loginUserByMobile("13875966342", "123456");
//		System.out.println(u.getUserName()+"-----------");
		List<CarInfo> all=carInfoService.getAllCarInfo("宝马");
		System.out.println(all.size()+"-----------");
		for (CarInfo carInfo : all) {
			System.out.println(carInfo.getCarColor()+"------------");
		}
		*/
		ArrayList<CarInfo> al=carInfoService.getAllCarInfo();
		for (CarInfo carInfo : al) {
			System.out.println(carInfo.getCarBand()+"----");
		}
		System.out.println(MD5.getMD5("1212123456")+"---------");
		System.out.println(orderInfoService.getOrderDetail(1));
	}
	
	
}
