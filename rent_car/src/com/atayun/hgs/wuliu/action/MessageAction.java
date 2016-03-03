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

import com.atayun.hgs.wuliu.po.MessageCar;
import com.atayun.hgs.wuliu.po.MessageCargo;
import com.atayun.hgs.wuliu.service.MessageService;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;

@Controller
@RequestMapping("/message.do")
public class MessageAction {
	
	@Resource
	private MessageService messageService;
	
	/**
	 * 根据货主的Id查看我的消息
	 * @param cagoId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getMyMessagesCargo",method={RequestMethod.POST})
	public void getMyMessagesCargo(Integer cagoId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		
		ArrayList<MessageCargo> messages = new ArrayList<MessageCargo>();
		messages = messageService.getMyMessagesCargo(cagoId);
		if(!messages.isEmpty()){
			jsonObject.put("getMyMessageState", "200");//有消息
			JSONArray myMessagesArray = JSONArray.fromObject(messages,jsonConfig);
			jsonObject.put("MyMessages", myMessagesArray);
		}else{
			jsonObject.put("getMyMessageState", "10");//没有消息
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	
	/**
	 * 根据车主的Id查看我的消息
	 * @param caroId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getMyMessagesCar",method={RequestMethod.POST})
	public void getMyMessagesCar(Integer caroId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		
		ArrayList<MessageCar> messages = new ArrayList<MessageCar>();
		messages = messageService.getMyMessagesCar(caroId);
		if(!messages.isEmpty()){
			jsonObject.put("getMyMessageState", "200");//有消息
			JSONArray myMessagesArray = JSONArray.fromObject(messages,jsonConfig);
			jsonObject.put("MyMessages", myMessagesArray);
		}else{
			jsonObject.put("getMyMessageState", "10");//没有消息
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
	
	/**
	 * 推送消息通知
	 * @param title
	 * @param content
	 * @param alias
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=jPushMessage",method={RequestMethod.POST})
	public void jPushMessage(String title,String content,String alias,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject  = new JSONObject();
		boolean flag = messageService.jPushMessage(title, content, alias);
		if(flag){
			jsonObject.put("jPushMessageState", "200");//推送消息成功
		}else{
			jsonObject.put("jPushMessageState", "10");//推送消息失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);	
	}
}
