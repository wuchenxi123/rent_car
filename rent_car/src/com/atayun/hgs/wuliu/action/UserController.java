package com.atayun.hgs.wuliu.action;
import java.text.SimpleDateFormat;
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

import com.atayun.hgs.wuliu.po.CarOwner;
import com.atayun.hgs.wuliu.po.CargoOwner;
import com.atayun.hgs.wuliu.po.Company;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.service.UserService;
import com.atayun.hgs.wuliu.utils.IpUtils;
import com.atayun.hgs.wuliu.utils.CommonUtils;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.MD5;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;

/**
 * UserController
 * 概述：
 * user的控制器，进行业务逻辑的调用，返回相应的视图
 * <p>
 * 1.用户发送短信
 * 2.用户手机号码登陆
 * 3.用户使用用户名登陆
 * 4.验证手机号是否注册
 * 5.验证用户名是否已存在
 * 6.用户注册
 * </p>
 * @author HWJ
 * @version 0.5, 2015/05/11
 */
@Controller
@RequestMapping("/user.do")
public class UserController {
	/**	 */
	@Resource
	private UserService userService;
	/**
	 * validateMobile：
     * 功能：注册新用户 验证手机号是否有效
	 * @param userMobile 手机号码
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=validateMobile",method={RequestMethod.POST})
	public void validateMobile(String userMobile,HttpServletRequest request,HttpServletResponse response){
		
		boolean flag = false;
		JSONObject jsonObject  = new JSONObject();
		//判断该号码是否已经注册
		User user = userService.validateMobile(userMobile);
		if(user!=null){//说明该手机号码已经注册
			jsonObject.put("sendSMS", "20");//手机号已经注册
		}else{//手机号未注册
			//生成六位数字的验证码
			String code = (int)((Math.random()*9+1)*100000)+"";
			//调用业务逻辑发送验证码
			flag = userService.sendSMSRequest(userMobile, code);
			if(flag){
				jsonObject.put("codeSMS", code);//客户端接收code
				jsonObject.put("sendSMS", "200");//短信发送成功
			}else{
				jsonObject.put("sendSMS", "10");//短信发送失败，手机号码不符合规则
			}
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
		
	}
	
	/**
	 * 在找回密码的时候进行的手机验证
	 * @param userMobile
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=validateMobileAtFindPsw",method={RequestMethod.POST})
	public void validateMobileAtFindPsw(String userMobile,HttpServletRequest request,HttpServletResponse response){
		
		boolean flag = false;
		JSONObject jsonObject  = new JSONObject();
		//判断该号码是否已经注册
		User user = userService.validateMobile(userMobile);
		if(user==null){//说明该手机号码未注册
			jsonObject.put("sendSMS", "20");//手机号未注册，不能使用重新设置密码
		}else{//手机号已经注册
			//生成六位数字的验证码
			String code = (int)((Math.random()*9+1)*100000)+"";
			//调用业务逻辑发送验证码
			flag = userService.sendSMSRequest(userMobile, code);
			if(flag){
				jsonObject.put("codeSMS", code);//短信发送验证码到客户端
				jsonObject.put("sendSMS", "200");//短信发送成功
			}else{
				jsonObject.put("sendSMS", "10");//短信发送失败
			}
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	/**
	 * 重新发布短信验证的请求
	 * @param userMobile
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=reValidateMobile",method={RequestMethod.POST})
	public void reValidateMobile(String userMobile,HttpServletRequest request,HttpServletResponse response){
		
		boolean flag = false;
		JSONObject jsonObject  = new JSONObject();
		//String userPhone = (String) request.getSession().getAttribute("userPhone");
		// 生成六位数字的验证码
		String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
		// 调用业务逻辑发送验证码	
		if(userMobile!=null){
			flag = userService.sendSMSRequest(userMobile, code);
		}
		if (flag) {
			jsonObject.put("codeSMS", code);//客户端接收code
			jsonObject.put("sendSMS", "200");// 短信发送成功
		} else {
			jsonObject.put("sendSMS", "10");// 短信发送失败，手机号码不符合规则
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	/**
	 * registerUser：
	 * 功能：用户默认注册
	 * 逻辑判断过程：
	 * 1.判断用户输入的验证码
	 *   if（正确） {}
	 * @param user
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=register",method={RequestMethod.POST})
	public void registerUser(User user,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();		
		//测试使用start
		//如果是从网页进行测试那么将再次先对密码进行MD5加密
		if(user.getUserPassword().length()!=32&&CommonUtils.isPassword(user.getUserPassword())){
			if(user.getUserPassword().length()<=18){
				System.out.println("修改密码================原来的是==="+user.getUserPassword());
				user.setUserPassword(MD5.getMD5(user.getUserPassword()));		
			}
		}
		//暂时有个问题
		//测试使用end
		String userMobile = user.getUserMobile();
		//3.加密密码验证码存进数据库
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		System.out.println("***************time*"+time);
		String v = MD5.getMD5(time);
		System.out.println("加密后的时间："+v);
		String userVerifycode = MD5.getMD5(userMobile+v);//密码验证码
		//4.加密密码
		String MD5Pwd = MD5.getMD5(userVerifycode+user.getUserPassword());
		if (user.getUserPassword().length() == 32) {// 符合密码要求，传过来的是32位加密后的密码数据
			// 6.获取的手机号码不为空时，设置手机号码为注册的号码
			if (userMobile != null) {// 获取到手机号
				user.setUserMobile(userMobile);
				user.setUserVerifyCode(userVerifycode);
				user.setUserPassword(MD5Pwd);
				// 调用业务方法，注册用户
				user = userService.registerUser(user);	
				//初始化表
				userService.initTable(user.getUserId(),user.getUserFlag());
				// 返回客户端json数据
				jsonObject.put("registerState", "200");// 注册成功
				jsonObject.put("userId", user.getUserId());//用户唯一ID
				jsonObject.put("userFlag", user.getUserFlag());// 用户的状态，是车主还是货主
				
			} else {
				jsonObject.put("registerState", "30");// 返回的用户的手机号出错
			}
		} else {// 密码不符合规则
			jsonObject.put("registerState", "10");// 返回10 表示密码不符合规则
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	
	/**
	 * loginUser：
	 * 功能：根据用户输入的手机号或用户名和密码进行登陆
	 * 修改：取消用户名登陆
	 * @param userMobile
	 * @param userPassword
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=login",method={RequestMethod.POST})
	public void loginUser(String userAccount, String userPassword,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject = new JSONObject();
		User user = new User();
		int userId = -1;
		String resultState ="";
		String fitState = "";//是否符合规范手机号和密码
		//测试使用start
		//如果是从网页进行测试那么将再次先对密码进行MD5加密
//		if(userPassword.length()!=32&&CommonUtils.isPassword(userPassword)){
//			if(userPassword.length()<=18){
//				userPassword = MD5.getMD5(userPassword);		
//			}
//		}
		//暂时有个问题
		//测试使用end
		fitState = CommonUtils.fitRequirement(userAccount, userPassword);
		if ("200".equals(fitState)){
			// 判断用户输入的手机号是否已经注册
			if (userService.validateMobile(userAccount) == null) {
				resultState = "60";// 账号不存在，手机号未注册
			}else{
				user = userService.loginUserByMobile(userAccount, userPassword);
				if (user != null) {
					resultState = "200";// 登陆成功
					userId = user.getUserId();
					jsonObject.put("userId", userId);//返回用户的唯一标识	
				} else {
					resultState = "20";// 密码错误
				}
			}
			
		}else{
			resultState = fitState;
		}
		
		//登陆记录?是需要用户的ID号？
		String loginIp = IpUtils.getIpAddr(request);
		if("200".equals(resultState)){
			
		}
		jsonObject.put("loginState", resultState);	
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 重新设置密码
	 * @param user
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=resetUserPassword",method={RequestMethod.POST})
	public void resetUserPassword(User user,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		
		//测试使用start
		//如果是从网页进行测试那么将再次先对密码进行MD5加密
		if(user.getUserPassword().length()!=32&&CommonUtils.isPassword(user.getUserPassword())){
			if(user.getUserPassword().length()<=18){
				System.out.println("修改密码================原来的是==="+user.getUserPassword());
				user.setUserPassword(MD5.getMD5(user.getUserPassword()));		
			}
		}
		//暂时有个问题
		//测试使用end
			//if(LoginUtils.isPassword(user.getUserPassword())){//符合密码的规则
				String userMobile = user.getUserMobile();
				if(userMobile!=null){
					//调用业务方法，更新用户的密码
					userService.resetUserPswByMObile(userMobile, user.getUserPassword());
					//返回客户端json数据
					jsonObject.put("resetPswState", "200");//密码重置成功
				}else{
					jsonObject.put("resetPswState", "30");//密码重置失败，未找到相应手机号码
				}
			//}else{//密码不符合规则
				//jsonObject.put("resetPswState", "10");//返回10 表示密码不符合规则
			//}

			//根据自定义的工具类返回json数据
			OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	/**
	 * 完善用户个人信息信息
	 * @param user 用户基本信息
	 * @param userId 用户ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=improveCargoUser",method={RequestMethod.POST})
	public void improveCargoUser(CargoOwner owner,User user,Integer userId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		//判断身份号是否已经存在
		if(user.getUserIDCard().trim().length()==18){
			boolean iDCardIsOK = userService.validateUserIDCard(user.getUserIDCard());
			if(iDCardIsOK){
				
				user.setUserAddr(owner.getCagoProvince()+owner.getCagoCity()+owner.getCagoStreet());
				boolean flag = userService.improveUserMessage(owner, user, userId);
				if(flag){
					jsonObject.put("improveUserState", "200");//用户信息完善成功
				}
				else{
					jsonObject.put("improveUserState", "10");///用户信息完善失败
				}
			}else{
				jsonObject.put("improveUserState", "20");///用户信息完善失败,身份证已使用
			}	
		}else{
			jsonObject.put("improveUserState", "30");///用户信息完善失败，身份证不符合规范
		}
		
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 *  完善车主基本个人信息
	 * @param owner 车主信息
	 * @param user  用户基本信息
	 * @param userId 用户ID
	 * @param userProvince 省
	 * @param userCity 市
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=improveCarUser",method={RequestMethod.POST})
	public void improveCarUser(CarOwner owner,User user,Integer userId,String userProvince,String userCity,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		
		if(user.getUserIDCard().trim().length()==18){
			boolean iDCardIsOK = userService.validateUserIDCard(user.getUserIDCard());
			if(iDCardIsOK){
				user.setUserAddr(userProvince+userCity+user.getUserAddr());
				boolean flag = userService.improveCarUser(user, owner, userId);
				if(flag){
					jsonObject.put("improveUserState", "200");//用户信息完善成功
				}
				else{
					jsonObject.put("improveUserState", "10");//用户信息完善失败
				}
			}else{
				jsonObject.put("improveUserState", "20");//用户信息完善失败,身份证已使用
			}
		}else{
			jsonObject.put("improveUserState", "30");//用户信息完善失败，身份证不符合规范
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}			
	/**
	 * 认证为企业车主
	 * @param cargoOwner
	 * @param userId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=accreditationCargoOwner",method={RequestMethod.POST})
	public void accreditationCargoOwner(Company company,Integer userId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = false;
		flag = userService.accreditationCargoOwner(company, userId);
		if(flag){
			jsonObject.put("accdttCargoState", "200");//企业信息货主完善操作成功
		}else{
			jsonObject.put("accdttCargoState", "10");//企业信息货主完善操作失败
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 由货主身份切换到车主的身份
	 * @param userId 用户ID
	 * @param carUserFlag  车主个人信息的完善标志
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=changeCargo2Car",method={RequestMethod.POST})
	public void changeCargo2Car(Integer userId, Integer carUserFlag,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = false;
		int cargoFlag = -1;
		int companyFlag = -1;
		int userBaseFlag = -1;
		flag = userService.changeCargo2Car(userId, carUserFlag);
		
		userBaseFlag = userService.validateUserBaseFlag(userId);
		carUserFlag = userService.validateCarUserFlagSQL(userId);
        cargoFlag = userService.validateCagoFlag(userId);
		companyFlag = userService.validateCagoCompFlag(userId);	
		
		if(flag){
			jsonObject.put("chageState", "200");//货主-->车主完善操作成功
		}else{
			jsonObject.put("chageState", "10");//货主-->车主完善操作失败
		}
		
		jsonObject.put("userBaseFlag", userBaseFlag);//个人基本信息是否完善	
		jsonObject.put("carUserFlag", carUserFlag);//返回车主个人信息是否完善	
		jsonObject.put("cargoFlag", cargoFlag);//返回货主个人信息是否完善	
		jsonObject.put("companyFlag", companyFlag);//返回企业用户是否完善	
		
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 有车主身份切换为货主身份
	 * @param userId 用户ID
	 * @param cargoFlag 货主个人信息完善标志
	 * @param companyFlag 企业货主信息完善标志
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=changeCar2Cargo",method={RequestMethod.POST})
	public void changeCar2Cargo(Integer userId, Integer cargoFlag,Integer companyFlag,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = false;
		int carUserFlag = -1;
		int userBaseFlag = -1;
		flag = userService.changeCar2Cargo(userId, cargoFlag, companyFlag);
		
		userBaseFlag = userService.validateUserBaseFlag(userId);
		carUserFlag = userService.validateCarUserFlagSQL(userId);
        cargoFlag = userService.validateCagoFlag(userId);
		companyFlag = userService.validateCagoCompFlag(userId);	
		if(flag){
			jsonObject.put("chageState", "200");//车主-->货主完善操作成功
		}else{
			jsonObject.put("chageState", "10");//车主-->货主完善操作失败
		}
		
		jsonObject.put("userBaseFlag", userBaseFlag);//个人基本信息是否完善	
		jsonObject.put("carUserFlag", carUserFlag);//返回车主个人信息是否完善	
		jsonObject.put("cargoFlag", cargoFlag);//返回货主个人信息是否完善	
		jsonObject.put("companyFlag", companyFlag);//返回企业用户是否完善	
		
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 用户进入个人中心需要获取个人信息的方法
	 * 获取个人的基本信息，详细
	 * @param userId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getUserBaseMeg",method={RequestMethod.POST})
	public void getUserBaseMeg(Integer userId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		User user = new User();
		user = userService.getUserBaseMeg(userId);
		if(user!=null){
			jsonObject.put("getUserState", "200");//个人信息获取成功
			JsonConfig jsonConfig = new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
			JSONArray jsonArray = JSONArray.fromObject(user,jsonConfig);
			jsonObject.put("UserBaseMeg", jsonArray);	
		}else{
			
			jsonObject.put("getUserState", "10");//个人信息获取失败
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 获取企业货主的信息
	 * @param userId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getCompBaseMeg",method={RequestMethod.POST})
	public void getCompBaseMeg(Integer userId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		Company company = new Company();
		company = userService.getCompBaseMeg(userId);
		if(company!=null){
			jsonObject.put("getCompanyState", "200");//个人信息获取成功
			JsonConfig jsonConfig = new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
			JSONArray jsonArray = JSONArray.fromObject(company,jsonConfig);
			jsonObject.put("CompanyBaseMeg", jsonArray);	
		}else{
			
			jsonObject.put("getCompanyState", "10");//个人信息获取失败
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 获取个人货主的信息
	 * @param userId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getCargoUserBaseMeg",method={RequestMethod.POST})
	public void getCargoUserBaseMeg(Integer userId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		CargoOwner cargoOwner = new CargoOwner();
		cargoOwner = userService.getCargoUserBaseMeg(userId);
		if(cargoOwner !=null){
			jsonObject.put("getCargoUserState", "200");//个人信息获取成功
			JsonConfig jsonConfig = new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
			JSONArray jsonArray = JSONArray.fromObject(cargoOwner,jsonConfig);
			jsonObject.put("CargoUserBaseMeg", jsonArray);	
		}else{
			
			jsonObject.put("getCargoUserState", "10");//个人信息获取失败
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 根据用户的ID 上传用户的头像
	 * @param user
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=uploadPicture",method={RequestMethod.POST})
	public void uploadPicture(User user,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = false;
		flag = userService.uploadPicture(user);
		if(flag){
			jsonObject.put("uploadState", "200");//上传成功
		}else{
			jsonObject.put("uploadState", "10");//上传失败
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
}
