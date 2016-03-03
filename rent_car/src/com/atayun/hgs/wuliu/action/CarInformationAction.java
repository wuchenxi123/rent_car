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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atayun.hgs.wuliu.po.CarInformation;
import com.atayun.hgs.wuliu.po.CarListView;
import com.atayun.hgs.wuliu.po.CarOwner;
import com.atayun.hgs.wuliu.po.CarRoute;
import com.atayun.hgs.wuliu.service.CarInformationService;
import com.atayun.hgs.wuliu.utils.CommonUtils;
import com.atayun.hgs.wuliu.utils.FloatEditor;
import com.atayun.hgs.wuliu.utils.IntegerEditor;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;
import com.atayun.hgs.wuliu.utils.SubStrUtils;

@Controller
@RequestMapping("/carInfo.do")
public class CarInformationAction {

	@Resource
	private CarInformationService carInformationService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(float.class, new FloatEditor());
	}

	/**
	 * getAllCarInfo： 功能：查询tb_cari获取全部车辆信息 无参数
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */

	@RequestMapping(params = "method=getAllCarInfo", method = { RequestMethod.POST })
	public void getAllCarInfo(HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		ArrayList<CarInformation> al = carInformationService
				.getAllCarInformations();// 调用service服务层，实现查询方法
		if (!al.isEmpty()) {// 判断查询结果是否为空
			// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			// 将List转换为JSONArray数据
			JSONArray jo = JSONArray.fromObject(al, jsonConfig);
			jsonObject.put("car", jo);
			// 查询成功标识
			jsonObject.put("carState", "200");
		} else {
			// 查询失败标识
			jsonObject.put("carState", "30");
		}

		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}

	/**
	 * getAllCarListInfo： 功能：查询视图carListView（tb_user,tb_caro_,tb_cari,tb_carr）
	 * 根据行程区间，获取全部用户，车主，车辆类型，车辆，行车路线信息 
	 * 查询条件:
	 * @param start
	 *            ：出发点
	 * @param end
	 *            ：目的地
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=getAllCarListInfo", method = { RequestMethod.POST })
	public void getAllCarListInfo(String start, String end,
			HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		ArrayList<CarListView> al = new ArrayList<CarListView>();
		// 调用service服务层的方法，实现对输入参数的模糊查询或精细查询
		if (start.trim() != null || end.trim() != null) {// 三种情况：
			// 1，出发地目的地都不为空2.二者其中之一为空
			al = carInformationService.getAllCarListInformations(start, end);
			if (al.size() == 0) {// 如果区范围内的搜索结果为空时，扩大搜索市一级
				// 对以上三种情况依次二次查询
				if (start.trim() != null && end.trim() != null) {// 出发地目的地均不为空
					start = SubStrUtils.SubStr(start, "市");
					end = SubStrUtils.SubStr(end, "市");
					al = carInformationService.getAllCarListInformations(start,
							end);
				} else if (start.trim() == null && end.trim() != null) {// 出发地为空
					end = SubStrUtils.SubStr(end, "市");
					al = carInformationService.getAllCarListInformations(start,
							end);
				} else if (start.trim() == null && end.trim() != null) {// 目的地为空
					start = SubStrUtils.SubStr(start, "市");
					al = carInformationService.getAllCarListInformations(start,
							end);
				}
			}
		}
		if (!al.isEmpty()) {// 判断查询结果是否为空值
			// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			// 将List转换为JSONArray数据
			JSONArray jo = JSONArray.fromObject(al, jsonConfig);
			jsonObject.put("carListView", jo);
			jsonObject.put("carViewState", "200");// 查找成功
		} else {
			jsonObject.put("carViewState", "30");// 查找失败
		}
		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);

	}

	/**
	 * getOwnerInfo： 功能：根据查询条件即用户ID查询相应的车主、车辆信息
	 * 
	 * @param userId
	 *            ：用户ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=getOwnerInfo", method = { RequestMethod.POST })
	public void getOwnerInfo(int userId, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		CarListView car = null;

		// 调用service服务层的getCarInformations方法
		car = carInformationService.getCarInformations(userId);
		if (car != null) {// 判断查询结果是否为空
			// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONObject jo = JSONObject.fromObject(car, jsonConfig);
			jsonObject.put("infoObject", jo);
			jsonObject.put("infoState", "200");// 查找成功
		} else {
			jsonObject.put("infoState", "30");// 根据userId查询不到车辆信息
		}

		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	/**
	 * getCarOwner： 功能：根据查询条件即用户ID查询相应的车主信息
	 * 
	 * @param userId
	 *            ：用户ID
	 * @param request
	 * @param response
	 * @return carOwner
	 */
	@RequestMapping(params = "method=getCarOwner", method = { RequestMethod.POST })
	public void getCarOwner(int userId, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		CarOwner carOwner = null;

		// 调用service服务层的getCarOwner方法
		carOwner = carInformationService.getCarOwner(userId);
		if (carOwner != null) {// 判断查询结果是否为空
			// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONObject jo = JSONObject.fromObject(carOwner, jsonConfig);
			jsonObject.put("infoObject", jo);
			jsonObject.put("infoState", "200");// 查找成功
		} else {
			jsonObject.put("infoState", "30");// 根据userId查询不到车主信息
		}

		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	
	/**
	 * getCarInfo： 功能：根据查询条件即用户ID查询相应的车辆信息
	 * 
	 * @param userId
	 *            ：用户ID
	 * @param request
	 * @param response
	 * @return carOwner
	 */
	@RequestMapping(params = "method=getCarInfo", method = { RequestMethod.POST })
	public void getCarInfo(int userId, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		CarInformation car = null;

		// 调用service服务层的getCarInfo方法
		car = carInformationService.getCarInfo(userId);
		if (car != null) {// 判断查询结果是否为空
			// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONObject jo = JSONObject.fromObject(car, jsonConfig);
			jsonObject.put("carInfo", jo);
			jsonObject.put("carState", "200");// 查找成功
		} else {
			jsonObject.put("carState", "30");// 根据userId查询不到车主信息
		}

		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	/**
	 * changeCarOwnerInfo： 功能：完善/注册/修改车主用户及车辆信息
	 * @param userId
	 * @param CarOwner
	 * @param CarInformation
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=changeCarOwnerInfo", method = { RequestMethod.POST })
	public void changeCarOwnerInfo(int userId, CarOwner carOwner,
			CarInformation carInfo, HttpServletRequest request,
			HttpServletResponse response) {
		boolean result;
		boolean lpNum;
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		//车主未填车牌时，系统默认车牌号：湘A12345，插入数据库
		if (carInfo.getCariLpnum().trim() != ""&& !(carInfo.getCariLpnum().trim().equals("湘A12345"))) {
			boolean flag = CommonUtils.isLpnum(carInfo.getCariLpnum()); // 当车牌格式正确时，将返回true，否则返回false
			if (flag) {
				//检查所填车牌是否已被注册
				lpNum = carInformationService.checkCar(carInfo.getCariLpnum(),userId);
				if (!lpNum) {// 所填车牌是否已被注册
					// 只要用户信息，还未添加到车主及车辆信息表中。
					if (CommonUtils.isCompleted(carOwner, carInfo)) {
						carOwner.setInfoFlag(1);// 车主信息已完善
						carInfo.setCariRvolumn(carInfo.getCariVolume());
						carInfo.setCariRload(carInfo.getCariLoad());
						carInfo.getCariRvolumn();
						result = carInformationService.updateCarOwner(userId,
								carOwner, carInfo);// 将车主及车主对应的车辆信息更新数据库中
						if (result) {// 判断插入/更新结果
							jsonObject.put("ownerState", "200");// 插入成功
						} else {
							jsonObject.put("ownerState", "50");// 插入失败
						}
					} else {
						carOwner.setInfoFlag(0);// 车主信息未完善
						carInfo.setCariRvolumn(carInfo.getCariVolume());
						carInfo.setCariRload(carInfo.getCariLoad());
						result = carInformationService.updateCarOwner(userId,
								carOwner, carInfo);// 将车主及车主对应的车辆信息插入/更新数据库中
						if (result) {// 判断插入/更新结果
							// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法

							jsonObject.put("ownerState", "200");// 插入成功

						} else {
							jsonObject.put("ownerState", "50");// 插入失败
						}
					}
				} else {
					jsonObject.put("ownerState", "40");// 所给车牌号已被注册
				}
			} else {
				jsonObject.put("ownerState", "30");// 所给车牌号格式错误
			}
		} else if ((carInfo.getCariLpnum().trim() != ""&& (carInfo.getCariLpnum().trim().equals("湘A12345")))||carInfo.getCariLpnum().trim() == "") {
			
				carOwner.setInfoFlag(0);// 车主信息未完善
				carInfo.setCariRvolumn(carInfo.getCariVolume());
				carInfo.setCariRload(carInfo.getCariLoad());
				carInfo.setCariLpnum("湘A12345");
				result = carInformationService.updateCarOwner(userId, carOwner,
						carInfo);// 将车主及车主对应的车辆信息插入/更新数据库中
				if (result) {// 判断插入/更新结果
					jsonObject.put("ownerState", "200");// 插入成功
				} else {
					jsonObject.put("ownerState", "50");// 插入失败
			}
		} 
		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);

	}

	/**
	 * publishCarInfo： 功能：发布车辆信息（车主联系方式,出发地，目的地，超长，超宽，超高，超重）
	 * @param carOwner
	 * @param carInfo
	 * @param userId
	 * @param infoFlag
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=publishCarInfo", method = { RequestMethod.POST })
	public void publishCarInfo(int userId, CarOwner carOwner,
			CarInformation carInfo,HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		
		//根据用户id获取车辆id
		CarInformation car=carInformationService.getCariId(userId);
		int cariId=0;
		if(car!=null){
			cariId=car.getCariId();
			//完成行车路线类的赋值
			CarRoute carRoute=new CarRoute();
			carRoute.setCariId(cariId);
			carRoute.setCarrStart(carInfo.getCariStart());
			carRoute.setCarrEnd(carInfo.getCariEnd());
			boolean result;
				
				// 调用service服务层的publishCarInfo方法--更新车辆相关信息
				if (CommonUtils.isMobileNO(carOwner.getCaroMobile())) {
					result = carInformationService.publishCarInfo(userId, carOwner,
							carInfo,carRoute);
					if (result) {// 判断更新结果
						jsonObject.put("carState", "200");// 发布成功
					} else {
						jsonObject.put("carState", "40");// 发布失败
					}
				} else {
					jsonObject.put("carState", "30");//输入的货主联系号码格式错误
				}
		}else{
			jsonObject.put("carState", "20");// 用户无车辆信息
		}
		
		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	/**
	 * getRoute： 功能：查询车主的行车路线记录
	 * @param userId
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=getRoute", method = { RequestMethod.POST })
	public void getRoute(int userId, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		//根据用户id获取车辆id
		CarInformation car=carInformationService.getCariId(userId);
		int cariId=0;
		if(car!=null){
			cariId=car.getCariId();
			// 调用service服务层的getRoute方法
			ArrayList<CarRoute> carRoute = (ArrayList<CarRoute>) carInformationService.getRoute(cariId);
			
			if(!(carRoute.isEmpty())){
				jsonObject.put("routeState", 200);//查询成功
				jsonObject.put("routeResult", carRoute);//将查询结果封装到son数组中
			}else{
				jsonObject.put("routeState", 30);//查询失败或车主未发布过车源信息
			}
		}else{
			jsonObject.put("routeState", 40);//用户无车辆信息
		}	
		
		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);
	}
	/**
	 * changeCarOwnerInfo： 功能：受理车主修改车主/车辆信息的申请
	 * @param userId
	 * @param CarOwner
	 * @param CarInformation
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=applyUpdateCarOwnerInfo", method = { RequestMethod.POST })
	public void applyUpdateCarOwnerInfo(int userId, CarOwner carOwner,
			CarInformation carInfo, HttpServletRequest request,
			HttpServletResponse response) {
		boolean result;
		boolean lpNum;
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		
		if (carInfo.getCariLpnum().trim() != ""&& !(carInfo.getCariLpnum().trim().equals("湘A12345"))) {
			boolean flag = CommonUtils.isLpnum(carInfo.getCariLpnum()); // 当车牌格式正确时，将返回true，否则返回false
			if (flag) {
				lpNum = carInformationService.checkCar(carInfo.getCariLpnum(),userId);
				if (!lpNum) {// 所填车牌是否已被注册
					// 只要用户信息，还未添加到车主及车辆信息表中。
					if (CommonUtils.isCompleted(carOwner, carInfo)) {
						carOwner.setInfoFlag(1);// 车主信息已完善
						result = carInformationService.applyUpdateCarOwner(userId,
								carOwner, carInfo);// 将车主及车主对应的车辆信息更新数据库中
						if (result) {// 判断插入/更新结果
							// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法

							jsonObject.put("ownerState", "200");// 插入成功

						} else {
							jsonObject.put("ownerState", "50");// 插入失败
						}
					} else {
						carOwner.setInfoFlag(0);// 车主信息未完善
						result = carInformationService.applyUpdateCarOwner(userId,
								carOwner, carInfo);// 将车主及车主对应的车辆信息插入/更新数据库中
						if (result) {// 判断插入/更新结果
							// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法

							jsonObject.put("ownerState", "200");// 插入成功

						} else {
							jsonObject.put("ownerState", "50");// 插入失败
						}
					}
				} else {
					jsonObject.put("ownerState", "40");// 所给车牌号已被注册
				}
			} else {
				jsonObject.put("ownerState", "30");// 所给车牌号格式错误
			}
		}else if ((carInfo.getCariLpnum().trim() != ""&& (carInfo.getCariLpnum().trim().equals("湘A12345")))||carInfo.getCariLpnum().trim() == "") {
			
				carOwner.setInfoFlag(0);// 车主信息未完善
				carInfo.setCariLpnum("湘A12345");
				result = carInformationService.applyUpdateCarOwner(userId, carOwner,
						carInfo);// 将车主及车主对应的车辆信息插入/更新数据库中
				if (result) {// 判断插入/更新结果
					// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法

					jsonObject.put("ownerState", "200");// 插入成功

				} else {
					jsonObject.put("ownerState", "50");// 插入失败
			}
		} 
		// 根据自定义的工具类返回json数据
		OutJsonUtils.putOutJson(jsonObject, request, response);

	}
	
}
