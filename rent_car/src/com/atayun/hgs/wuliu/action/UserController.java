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

import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.po.Orderlist;
import com.atayun.hgs.wuliu.po.RentOrderView;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.service.CarInfoService;
import com.atayun.hgs.wuliu.service.OrderInfoService;
import com.atayun.hgs.wuliu.service.RentInfoService;
import com.atayun.hgs.wuliu.service.UserService;
import com.atayun.hgs.wuliu.utils.CommonUtils;
import com.atayun.hgs.wuliu.utils.IpUtils;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.MD5;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;

/**
 * UserController 概述： user的控制器，进行业务逻辑的调用，返回相应的视图
 * <p>
 * 1.用户发送短信 2.用户手机号码登陆 3.用户使用用户名登陆 4.验证手机号是否注册 5.验证用户名是否已存在 6.用户注册
 * </p>
 * 
 * @author HWJ
 * @version 0.5, 2015/05/11
 */
@Controller
@RequestMapping("/user.do")
public class UserController {
	/**	 */
	@Resource
	private UserService userService;
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private RentInfoService rentInfoService;
	@Resource
	private CarInfoService carInfoService;

	/**
	 * validateMobile： 功能：注册新用户 验证手机号是否有效
	 * 
	 * @param userMobile
	 *            手机号码
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=validateMobile", method = { RequestMethod.POST })
	public void validateMobile(String userMobile, HttpServletRequest request,
			HttpServletResponse response) {

		boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		// 判断该号码是否已经注册
		User user = userService.validateMobile(userMobile);
		if (user != null) {// 说明该手机号码已经注册
			jsonObject.put("sendSMS", "20");// 手机号已经注册
		} else {// 手机号未注册
				// 生成六位数字的验证码
			String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
			// 调用业务逻辑发送验证码
			flag = userService.sendSMSRequest(userMobile, code);
			if (flag) {
				jsonObject.put("codeSMS", code);// 客户端接收code
				jsonObject.put("sendSMS", "200");// 短信发送成功
			} else {
				jsonObject.put("sendSMS", "10");// 短信发送失败，手机号码不符合规则
			}
		}
		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);

	}

	/**
	 * 在找回密码的时候进行的手机验证
	 * 
	 * @param userMobile
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=validateMobileAtFindPsw", method = { RequestMethod.POST })
	public void validateMobileAtFindPsw(String userMobile,
			HttpServletRequest request, HttpServletResponse response) {

		boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		// 判断该号码是否已经注册
		User user = userService.validateMobile(userMobile);
		if (user == null) {// 说明该手机号码未注册
			jsonObject.put("sendSMS", "20");// 手机号未注册，不能使用重新设置密码
		} else {// 手机号已经注册
				// 生成六位数字的验证码
			String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
			// 调用业务逻辑发送验证码
			flag = userService.sendSMSRequest(userMobile, code);
			if (flag) {
				jsonObject.put("codeSMS", code);// 短信发送验证码到客户端
				jsonObject.put("sendSMS", "200");// 短信发送成功
			} else {
				jsonObject.put("sendSMS", "10");// 短信发送失败
			}
		}
		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	/**
	 * 重新发布短信验证的请求
	 * 
	 * @param userMobile
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=reValidateMobile", method = { RequestMethod.POST })
	public void reValidateMobile(String userMobile, HttpServletRequest request,
			HttpServletResponse response) {

		boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		// String userPhone = (String)
		// request.getSession().getAttribute("userPhone");
		// 生成六位数字的验证码
		String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
		// 调用业务逻辑发送验证码
		if (userMobile != null) {
			flag = userService.sendSMSRequest(userMobile, code);
		}
		if (flag) {
			jsonObject.put("codeSMS", code);// 客户端接收code
			jsonObject.put("sendSMS", "200");// 短信发送成功
		} else {
			jsonObject.put("sendSMS", "10");// 短信发送失败，手机号码不符合规则
		}
		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	/**
	 * registerUser： 功能：用户默认注册 逻辑判断过程： 1.判断用户输入的验证码 if（正确） {}
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	/*
	 * @RequestMapping(params="method=register",method={RequestMethod.POST})
	 * public void registerUser(User user,HttpServletRequest
	 * request,HttpServletResponse response){
	 * 
	 * JSONObject jsonObject = new JSONObject(); //测试使用start
	 * //如果是从网页进行测试那么将再次先对密码进行MD5加密
	 * if(user.getUserPassword().length()!=32&&CommonUtils
	 * .isPassword(user.getUserPassword())){
	 * if(user.getUserPassword().length()<=18){
	 * System.out.println("密码================原来的是==="+user.getUserPassword());
	 * user.setUserPassword(MD5.getMD5(user.getUserPassword())); } } //暂时有个问题
	 * //测试使用end String userMobile = user.getUserMobile(); //3.加密密码验证码存进数据库
	 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String
	 * time = df.format(new Date());
	 * System.out.println("***************time*"+time); String v =
	 * MD5.getMD5(time); System.out.println("加密后的时间："+v); String userVerifycode
	 * = MD5.getMD5(userMobile+v);//密码验证码 //4.加密密码 String MD5Pwd =
	 * MD5.getMD5(userVerifycode+user.getUserPassword()); if
	 * (user.getUserPassword().length() == 32) {// 符合密码要求，传过来的是32位加密后的密码数据 //
	 * 6.获取的手机号码不为空时，设置手机号码为注册的号码 if (userMobile != null) {// 获取到手机号
	 * user.setUserMobile(userMobile); user.setUserVerifyCode(userVerifycode);
	 * user.setUserPassword(MD5Pwd); // 调用业务方法，注册用户 user =
	 * userService.registerUser(user); //初始化表
	 * userService.initTable(user.getUserId(),user.getUserFlag()); //
	 * 返回客户端json数据 jsonObject.put("registerState", "200");// 注册成功
	 * jsonObject.put("userId", user.getUserId());//用户唯一ID
	 * jsonObject.put("userFlag", user.getUserFlag());// 用户的状态，是车主还是货主
	 * 
	 * } else { jsonObject.put("registerState", "30");// 返回的用户的手机号出错 } } else
	 * {// 密码不符合规则 jsonObject.put("registerState", "10");// 返回10 表示密码不符合规则 }
	 * OutJsonUtils.putOutJson(jsonObject, request, response); }
	 */

	@RequestMapping(params = "method=register", method = { RequestMethod.POST })
	public void registerUser(User user, String userMobile,
			HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		// 测试使用start

		// //如果是从网页进行测试那么将再次先对密码进行MD5加密
		// if(user.getUserPassword().length()!=32&&CommonUtils.isPassword(user.getUserPassword())){
		// if(user.getUserPassword().length()<=18){
		// System.out.println("密码================原来的是==="+user.getUserPassword());
		// user.setUserPassword(MD5.getMD5(user.getUserPassword()));
		// }
		// }
		// 4.加密密码
		String MD5Pwd = MD5.getMD5(user.getUserVerifyCode()
				+ user.getUserPassword());
		System.out.println(user.getUserVerifyCode()+"---"
				+ user.getUserPassword());
		System.out.println(MD5Pwd+"========");
		if (MD5Pwd.length() == 32) {// 符合密码要求，传过来的是32位加密后的密码数据
			// 6.获取的手机号码不为空时，设置手机号码为注册的号码
			if (user.getUserMobile() != null) {// 获取到手机号
				user.setUserMobile(user.getUserMobile());
				user.setUserPassword(MD5Pwd);
				user.setUserIDCard(user.getUserIDCard());
				user.setUserLicense(user.getUserLicense());
				user.setUserType(3);
				user.setUserVerifyCode(user.getUserVerifyCode());
				// 调用业务方法，注册用户
				user = userService.registerUser(user);
				// 返回客户端json数据
				jsonObject.put("registerState", "200");// 注册成功
				jsonObject.put("userId", user.getUserId());// 用户唯一ID

			} else {
				jsonObject.put("registerState", "30");// 返回的用户的手机号出错
			}
		} else {// 密码不符合规则
			jsonObject.put("registerState", "10");// 返回10 表示密码不符合规则
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	/*
	 * @RequestMapping(params="method=register",method={RequestMethod.POST})
	 * public void registerUser(String userPhone,String userVcode,String
	 * userPassword, String idcard,String userLicense, HttpServletRequest
	 * request,HttpServletResponse response){
	 * 
	 * JSONObject jsonObject = new JSONObject(); //测试使用start
	 * 
	 * // //如果是从网页进行测试那么将再次先对密码进行MD5加密 //
	 * if(user.getUserPassword().length()!=32&&
	 * CommonUtils.isPassword(user.getUserPassword())){ //
	 * if(user.getUserPassword().length()<=18){ //
	 * System.out.println("密码================原来的是==="+user.getUserPassword());
	 * // user.setUserPassword(MD5.getMD5(user.getUserPassword())); // } // }
	 * User user=new User(); //4.加密密码 String MD5Pwd =
	 * MD5.getMD5(userVcode+userPassword); if (MD5Pwd.length() == 32) {//
	 * 符合密码要求，传过来的是32位加密后的密码数据 // 6.获取的手机号码不为空时，设置手机号码为注册的号码 if (userPhone !=
	 * null) {// 获取到手机号 user.setUserMobile(userPhone);
	 * user.setUserPassword(MD5Pwd); user.setUserIDCard(idcard);
	 * user.setUserLicense(userLicense); user.setUserType(3);
	 * user.setUserVerifyCode(userVcode); // 调用业务方法，注册用户 user =
	 * userService.registerUser(user); // 返回客户端json数据
	 * jsonObject.put("registerState", "200");// 注册成功 jsonObject.put("userId",
	 * user.getUserId());//用户唯一ID
	 * 
	 * } else { jsonObject.put("registerState", "30");// 返回的用户的手机号出错 } } else
	 * {// 密码不符合规则 jsonObject.put("registerState", "10");// 返回10 表示密码不符合规则 }
	 * OutJsonUtils.putOutJson(jsonObject, request, response); }
	 */

	/**
	 * loginUser： 功能：根据用户输入的手机号或用户名和密码进行登陆 修改：取消用户名登陆
	 * 
	 * @param userMobile
	 * @param userPassword
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=login", method = { RequestMethod.POST })
	public void loginUser(String userAccount, String userPassword,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
		User user = new User();
		int userId = -1;
		String resultState = "";
		String fitState = "";// 是否符合规范手机号和密码
		fitState = CommonUtils.fitRequirement(userAccount, userPassword);
		int orderStatus=0;
		
		if ("200".equals(fitState)) {
			// 判断用户输入的手机号是否已经注册
			if (userService.validateMobile(userAccount) == null) {
				resultState = "60";// 账号不存在，手机号未注册
			} else {
				user = userService.loginUserByMobile(userAccount, userPassword);
				if (user != null) {
					resultState = "200";// 登陆成功
					userId = user.getUserId();
					
						ArrayList<RentOrderView> list=orderInfoService.getAllOrders(userId);
						ArrayList<Orderlist> orders=orderInfoService.listSampleorderInfo(userId);//简单用户订单列表
						if(list!=null){
							  for(RentOrderView order:list){
								 if( order.getOrderStatus()==1){
									 orderStatus=1;
									 RentOrderView rentOrderView = orderInfoService.getOrderInfo(order.getOrderId());
									 JSONObject rentOrder = JSONObject.fromObject(rentOrderView, jsonConfig);
									 jsonObject.put("rentOrderView", rentOrder);
									 CarInfo carinfo=carInfoService.getCarBybandAndLpnum(rentOrderView.getRentCtype(), rentOrderView.getRentLpnum());
									 System.out.println(carinfo+"----"+rentOrderView.getRentCtype()+rentOrderView.getRentLpnum());
									 JSONObject carinfojs = JSONObject.fromObject(carinfo, jsonConfig);
									 jsonObject.put("carInfo", carinfojs);//车辆信息
//									 jsonObject.put("loginState", "10");   //用户有订单处在租车状态
									 break;
								 }else
								 if((order.getOrderStatus()==2)){
									 orderStatus=1;
//									 jsonObject.put("loginState", "20");   //用户有订单处在还车状态
									 RentOrderView rentOrderView = orderInfoService.getOrderInfo(order.getOrderId());
									 JSONObject rentOrder = JSONObject.fromObject(rentOrderView, jsonConfig);
									 jsonObject.put("rentOrderView", rentOrder);
									 CarInfo carinfo=carInfoService.getCarBybandAndLpnum(rentOrderView.getRentCtype(), rentOrderView.getRentLpnum());
									 System.out.println(carinfo+"----"+rentOrderView.getRentCtype()+rentOrderView.getRentLpnum());
									 JSONObject carinfojs = JSONObject.fromObject(carinfo, jsonConfig);
									 jsonObject.put("carInfo", carinfojs);//车辆信息
									
									 User driver=rentInfoService.getDriverInfo(rentOrderView.getSenddriverId());
									 JSONObject drive = JSONObject.fromObject(driver, jsonConfig);
								     jsonObject.put("driver", drive);//司机信息
										
									 break;
								 }
//								 jsonObject.put("loginState", "30");   //用户有订单都已结束
							  }
						
						}else{
//							 jsonObject.put("loginState", "40");   //用户没有订单信息
						}

					jsonObject.put("userId", userId);// 返回用户的唯一标识
					jsonObject.put("orderStatus", orderStatus);// 返回用户的唯一标识
					jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
					JSONArray orderlist = JSONArray.fromObject(orders, jsonConfig);
				    jsonObject.put("orderlist", orderlist);   //车辆信息
				} else {
					resultState = "50";// 密码错误
				}
			}

		} else {
			resultState = fitState;
		}

		// 登陆记录?是需要用户的ID号？
		String loginIp = IpUtils.getIpAddr(request);
	
		jsonObject.put("loginState", resultState);
		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	/**
	 * 重新设置密码
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=resetUserPassword", method = { RequestMethod.POST })
	public void resetUserPassword(User user, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();

		// 测试使用start
		// 如果是从网页进行测试那么将再次先对密码进行MD5加密
		/*
		 * if(user.getUserPassword().length()!=32&&CommonUtils.isPassword(user.
		 * getUserPassword())){ if(user.getUserPassword().length()<=18){
		 * System.out
		 * .println("修改密码================原来的是==="+user.getUserPassword());
		 * user.setUserPassword(MD5.getMD5(user.getUserPassword())); } }
		 */
		// 暂时有个问题
		// 测试使用end
		// if(LoginUtils.isPassword(user.getUserPassword())){//符合密码的规则
		String userMobile = user.getUserMobile();
		System.out.println(userMobile + "----------" + user.getUserPassword());
		if (userMobile != null) {
			// 调用业务方法，更新用户的密码
			userService
					.resetUserPswByMObile(userMobile, user.getUserPassword());
			// 返回客户端json数据
			jsonObject.put("resetPswState", "200");// 密码重置成功
		} else {
			jsonObject.put("resetPswState", "30");// 密码重置失败，未找到相应手机号码
		}
		// }else{//密码不符合规则
		// jsonObject.put("resetPswState", "10");//返回10 表示密码不符合规则
		// }

		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
//获取用户账户余额
	@RequestMapping(params = "method=getRemainder", method = { RequestMethod.POST })
	public void getRemainder(int userId, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();

		System.out.println(userId + "----------");
		if (userId >0) {
			// 调用业务方法，更新用户的密码
			User user_re=userService.getRemainder(userId);
			jsonObject.put("userRemainder", user_re.getUserRemainder());
			// 返回客户端json数据
			jsonObject.put("getRemainderStatus", "200");// 获取用户账户余额成功
		} else {
			jsonObject.put("getRemainderStatus", "30");// 获取用户账户余额失败
		}

		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	/**
	 * 完善用户个人信息信息
	 * 
	 * @param user
	 *            用户基本信息
	 * @param userId
	 *            用户ID
	 * @param request
	 * @param response
	 */
	/*
	 * @RequestMapping(params="method=improveCargoUser",method={RequestMethod.POST
	 * }) public void improveCargoUser(CargoOwner owner,User user,Integer
	 * userId,HttpServletRequest request,HttpServletResponse response){
	 * 
	 * JSONObject jsonObject = new JSONObject(); //判断身份号是否已经存在
	 * if(user.getUserIDCard().trim().length()==18){ boolean iDCardIsOK =
	 * userService.validateUserIDCard(user.getUserIDCard()); if(iDCardIsOK){
	 * 
	 * user.setUserAddr(owner.getCagoProvince()+owner.getCagoCity()+owner.
	 * getCagoStreet()); boolean flag = userService.improveUserMessage(owner,
	 * user, userId); if(flag){ jsonObject.put("improveUserState",
	 * "200");//用户信息完善成功 } else{ jsonObject.put("improveUserState",
	 * "10");///用户信息完善失败 } }else{ jsonObject.put("improveUserState",
	 * "20");///用户信息完善失败,身份证已使用 } }else{ jsonObject.put("improveUserState",
	 * "30");///用户信息完善失败，身份证不符合规范 }
	 * 
	 * //根据自定义的工具类返回json数据 OutJsonUtils.putOutJson(jsonObject, request,
	 * response); }
	 */
	/**
	 * 完善车主基本个人信息
	 * 
	 * @param owner
	 *            车主信息
	 * @param user
	 *            用户基本信息
	 * @param userId
	 *            用户ID
	 * @param userProvince
	 *            省
	 * @param userCity
	 *            市
	 * @param request
	 * @param response
	 */
	/*
	 * @RequestMapping(params="method=improveCarUser",method={RequestMethod.POST}
	 * ) public void improveCarUser(CarOwner owner,User user,Integer
	 * userId,String userProvince,String userCity,HttpServletRequest
	 * request,HttpServletResponse response){
	 * 
	 * JSONObject jsonObject = new JSONObject();
	 * 
	 * if(user.getUserIDCard().trim().length()==18){ boolean iDCardIsOK =
	 * userService.validateUserIDCard(user.getUserIDCard()); if(iDCardIsOK){
	 * user.setUserAddr(userProvince+userCity+user.getUserAddr()); boolean flag
	 * = userService.improveCarUser(user, owner, userId); if(flag){
	 * jsonObject.put("improveUserState", "200");//用户信息完善成功 } else{
	 * jsonObject.put("improveUserState", "10");//用户信息完善失败 } }else{
	 * jsonObject.put("improveUserState", "20");//用户信息完善失败,身份证已使用 } }else{
	 * jsonObject.put("improveUserState", "30");//用户信息完善失败，身份证不符合规范 }
	 * //根据自定义的工具类返回json数据 OutJsonUtils.putOutJson(jsonObject, request,
	 * response); }
	 */

}
