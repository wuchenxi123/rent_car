package com.atayun.hgs.wuliu.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atayun.hgs.wuliu.po.CargoAllViewPo;
import com.atayun.hgs.wuliu.po.CargoInformation;
import com.atayun.hgs.wuliu.po.CargoListView;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.service.CargoInformationService;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;
@Controller
@RequestMapping("/cargoInfo.do")
public class CargoInformationAction {

	@Resource
	private CargoInformationService cargoInformationService;
	/**
	 * 获取所有的货源（没有任何其他要求的条件下）
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getAllCargoInfo",method={RequestMethod.POST})
	public void getAllCargoInfo(HttpServletRequest request,HttpServletResponse response){
		
		//boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		ArrayList<CargoAllViewPo> arrayList = cargoInformationService.getAllCargoInformations();
		if(!arrayList.isEmpty()){
			jsonObject.put("getCarInfoState", "200");
			JsonConfig jsonConfig = new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
			JSONArray jsonArray = JSONArray.fromObject(arrayList,jsonConfig);
			jsonObject.put("Cargo", jsonArray);
			
		}else{
			jsonObject.put("getCarInfoState", "10");
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 根据起点和终点查询货源
	 * @param start  起点
	 * @param end    终点
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getAllCargoInfoByStartAndEnd",method={RequestMethod.POST})
	public void getAllCargoInfoByStartAndEnd(String start,String end,HttpServletRequest request,HttpServletResponse response){
		
		//boolean flag = false;
		JSONObject jsonObject = new JSONObject();
		ArrayList<CargoAllViewPo> arrayList = new ArrayList<CargoAllViewPo>();
		arrayList = cargoInformationService.getAllCargoInfoByStartAndEnd(start, end);
		if(!arrayList.isEmpty()){
			jsonObject.put("getCarInfoState", "200");//获取成功有数据
			JsonConfig jsonConfig = new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
			JSONArray jsonArray = JSONArray.fromObject(arrayList,jsonConfig);
			jsonObject.put("Cargo", jsonArray);
			
		}else{
			jsonObject.put("getCarInfoState", "10");//获取失败没数据
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 发布货源信息
	 * @param cargoInformation 发布有关货源的信息
	 * @param userId           发布者的ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=publishCargoInfo",method={RequestMethod.POST})
	public void publishCargoInfo(CargoInformation cargoInformation,int userId,String deliTime,HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		//判断个人信息和货主的信息是否已经认证
		//int userFlag = -1;
		//userFlag = cargoInformationService.getUserFlag(userId).getUserFlag();
		//if (userFlag == 7) 
			// 已认证，可以发布货源
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
			java.util.Date date = null;
			try {
				date = sdf.parse(deliTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cargoInformation.setCargoInfoDeliTime(date);

			boolean flag = cargoInformationService.publishCargoInfo(
					cargoInformation, userId);
			if (flag) {
				jsonObject.put("publishCargoState", "200");// 发布成功
			} else {
				jsonObject.put("publishCargoState", "10");// 发布失败
			}
		//else{
		//	jsonObject.put("publishCargoState", "30");//请先完善个人信息
		//}
		//未被认证，前去完善信息
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 根据用户的信息匹配货源
	 * @param userId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getCargoInfoByUserAll",method={RequestMethod.POST})
	public void getCargoInfoByUserCarAll(Integer userId,HttpServletRequest request,HttpServletResponse response){
		
	    JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		//获取最佳货源的匹配
	    ArrayList<CargoListView> cargoListViews = new ArrayList<CargoListView>();
	    cargoListViews = cargoInformationService.getBestMatchAll(userId);
		if(!cargoListViews.isEmpty()){
			jsonObject.put("bestCargoState", "200");
			JSONArray bestMatchArray = JSONArray.fromObject(cargoListViews,jsonConfig);
			jsonObject.put("BestMatch", bestMatchArray);
		}else{
			//没有匹配，返回10状态码
			jsonObject.put("bestCargoState", "10");
		}
		//获取其他货源的匹配
		 ArrayList<CargoListView> ortherMatchs = new  ArrayList<CargoListView>();
		//调用业务逻辑方法返回结果集
		 ortherMatchs = cargoInformationService.getOrtherMatchALL(userId);
		//说明除了第一条匹配外还有其他匹配
		if(!ortherMatchs.isEmpty()){
			jsonObject.put("ortherCargoState", "200");
			JSONArray ortherMatchArray = JSONArray.fromObject(ortherMatchs,jsonConfig);
			jsonObject.put("OrtherMatch", ortherMatchArray);
		}else{
			//没有匹配，返回10状态码
			jsonObject.put("ortherCargoState", "10");
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	/**
	 * 获得用户的基本信息
	 * @param userId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getUserBaseMeg",method={RequestMethod.POST})
	public void getUserBaseMeg(int userId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject = new JSONObject();
		List list  = new ArrayList<String>();
		list = cargoInformationService.getUserFlag(userId);
		if(list.isEmpty()){
			jsonObject.put("getUserBaseMegState", "10");//获取失败，没有该信息
		}else{
			
			jsonObject.put("getUserBaseMegState", "200");//获取成功
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
			JSONArray userBaseArray = JSONArray.fromObject(list,jsonConfig);
			jsonObject.put("UserBaseMeg", userBaseArray);
			
		}
		
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 根据cain的cargoInfoId删除货主发布的货源
	 * @param cargoInfoId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=deleteCargoByCainId",method={RequestMethod.POST})
	public void deleteCargoByCainId(int cargoInfoId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject = new JSONObject();
		boolean flag = false;
		flag = cargoInformationService.deleteCargoInfo(cargoInfoId);
		if(flag){
			jsonObject.put("deleteCargoState", "200");//删除成功
		}else{
			jsonObject.put("deleteCargoState", "10");//删除失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * 根据货物的ID对货物进行修改
	 * @param cargoInformation
	 * @param deliTime
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=modifyCargoInfoByCainId",method={RequestMethod.POST})
	public void modifyCargoInfoByCainId(CargoInformation cargoInformation,String deliTime,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject = new JSONObject();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
		java.util.Date date = null;
		try {
			date = sdf.parse(deliTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cargoInformation.setCargoInfoDeliTime(date);
		boolean flag = false;
		flag = cargoInformationService.modifyCargoInfo(cargoInformation);
		if(flag){
			jsonObject.put("modifyCargoState", "200");//修改成功
		}else{
			jsonObject.put("modifyCargoState", "10");//修改失败
		}
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	

	/**
	 * 获取到我的货源
	 * @param userId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="method=getMyCargoInfoById",method={RequestMethod.POST})
	public void getMyCargoInfoById(int userId,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject jsonObject = new JSONObject();
		ArrayList<CargoAllViewPo> arrayList = cargoInformationService.getMyCargoInfoByID(userId);
		if(!arrayList.isEmpty()){
			jsonObject.put("getMyCarInfoState", "200");//获取我的货源成功
			JsonConfig jsonConfig = new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
			JSONArray jsonArray = JSONArray.fromObject(arrayList,jsonConfig);
			jsonObject.put("Cargo", jsonArray);
			
		}else{
			jsonObject.put("getMyCarInfoState", "10");//获取我的货源失败
		}
		//根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
		
	}
	
	
	
	
}
