package com.atayun.hgs.wuliu.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atayun.hgs.wuliu.po.OperateActivity;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.service.OperateActivityService;
import com.atayun.hgs.wuliu.service.OrderInfoService;
import com.atayun.hgs.wuliu.service.UserService;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;


@Controller
@RequestMapping("/setting.do")
public class SettingController {
	
@Resource
private UserService uservice;
@Resource
public OperateActivityService  operateActivityService;
@Resource
private OrderInfoService orderInfoService ;

//当前用户余额
@RequestMapping(params="method=getUserRemainder",method={RequestMethod.POST})
public void getUserRemainder(int userid,HttpServletRequest request,HttpServletResponse response){
	JSONObject jsonObject=new JSONObject();
	User user=uservice.getRemainder(userid);
	
	jsonObject.put("userRemainder", user.getUserRemainder());
	OutJsonUtils.putOutJson(jsonObject, request, response);
}
//优惠活动
@RequestMapping(params="method=getActivity",method={RequestMethod.POST})
public void getActivity(HttpServletRequest request,HttpServletResponse response){
	JSONObject jsonObject=new JSONObject();
	OperateActivity activitys=operateActivityService.lastActivity();
	
	jsonObject.put("activitys",activitys);
	
	OutJsonUtils.putOutJson(jsonObject, request, response);
}


//充值
@RequestMapping(params="method=recharge",method={RequestMethod.POST})
public void recharge(int userid,float chargePrice,float sendPrice,HttpServletRequest request,HttpServletResponse response){
	JSONObject jsonObject=new JSONObject();
	User user=uservice.getRemainder(userid);
	float rechargeFee=user.getUserRemainder()+chargePrice+sendPrice;  //充值后用户余额
	boolean flag=orderInfoService.changeOrderRemainder(userid, rechargeFee);
	if(flag){
	jsonObject.put("rechargeState", "200");//充值成功
	}
	jsonObject.put("rechargeState", "10");//充值失败
	OutJsonUtils.putOutJson(jsonObject, request, response);
}
}
