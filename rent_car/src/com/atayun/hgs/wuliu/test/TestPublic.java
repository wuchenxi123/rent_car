package com.atayun.hgs.wuliu.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atayun.hgs.wuliu.po.CarInformation;
import com.atayun.hgs.wuliu.po.CarOwner;
import com.atayun.hgs.wuliu.po.CarRoute;
import com.atayun.hgs.wuliu.po.CargoInformation;
import com.atayun.hgs.wuliu.service.CarInformationService;
import com.atayun.hgs.wuliu.service.CargoInformationService;
import com.atayun.hgs.wuliu.utils.CommonUtils;
import com.atayun.hgs.wuliu.utils.FloatEditor;
import com.atayun.hgs.wuliu.utils.IntegerEditor;
import com.atayun.hgs.wuliu.utils.JsonDateValueProcessor;
import com.atayun.hgs.wuliu.utils.OutJsonUtils;

@Controller
@RequestMapping("/public.do")
public class TestPublic {

	@Resource
	private CarInformationService carInformationService;
	@Resource
	private CargoInformationService cargoInformationService;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(float.class, new FloatEditor());
	}

	
	/**
	 * publishCarInfo： 功能：测试发布车辆信息的信息 （车主联系方式,出发地，目的地，超长，超宽，超高，超重）
	 * @param carOwner
	 * @param carInfo
	 * @param userId
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(params = "method=testPublishCarInfo", method = { RequestMethod.POST })
	public void testPublishCarInfo(String phone,CarOwner carOwner,
			CarInformation carInfo, int infoFlag, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int userId=carInformationService.getUserId(phone).getUserId();
		CarRoute carRoute=new CarRoute();
		CarInformation car=carInformationService.getCariId(userId);
		System.out.println(car+"------");
		int cariId=0;
		if(car!=null){
			cariId=car.getCariId();
		}
		System.out.println(car+"=======");
		String start=carInfo.getCariStart();
		int i=start.indexOf("省");
		int j=start.indexOf("市");
		String str1=start.substring(i+1,j+1);
		carInfo.setCariScity(str1);
		
		String end=carInfo.getCariEnd();
		int k=start.indexOf("省");
		int g=start.indexOf("市");
		String str2=end.substring(k+1,g+1);
		carInfo.setCariEcity(str2);
		
		carRoute.setCariId(cariId);
		carRoute.setCarrStart(carInfo.getCariStart());
		carRoute.setCarrEnd(carInfo.getCariEnd());
		
		if(userId>0){
			System.out.println("----------");
			boolean result;
			// 判断车主的信息是否已经完善
				// 1 已完善；2已认证；3正在审核都可发布车源信息
				// 0未完善，不可以发布车源
				// 调用service服务层的publishCarInfo方法--更新车辆相关信息
				if (CommonUtils.isMobileNO(carOwner.getCaroMobile())) {
					result = carInformationService.publishCarInfo(userId, carOwner,
							carInfo,carRoute);
					System.out.println(result+"_-------");
					if (result) {// 判断更新结果
						request.setAttribute("result","发布成功！");
						// 发布成功
					} else {
						request.setAttribute("result","发布失败！");
						// 发布失败
					}
				}
		}
			request.getRequestDispatcher("/publicCars.jsp").forward(request,response);
		
	}
	@RequestMapping(params="method=testPublishCargoInfo",method={RequestMethod.POST})
	public void testPublishCargoInfo(CargoInformation cargoInformation,String mobile,String deliTime,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//判断个人信息和货主的信息是否已经认证
		int userId=carInformationService.getUserId(mobile).getUserId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
		java.util.Date date = null;
		String start=cargoInformation.getCargoInfoStart();
		int i=start.indexOf("省");
		int j=start.indexOf("市");
		String str1=start.substring(i+1,j+1);
		cargoInformation.setCargoInfoSCity(str1);
		
		String end=cargoInformation.getCargoInfoEnd();
		int k=start.indexOf("省");
		int g=start.indexOf("市");
		String str2=end.substring(k+1,g+1);
		cargoInformation.setCargoInfoECity(str2);
		try {
			date = sdf.parse(deliTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cargoInformation.setCargoInfoDeliTime(date);
		{
			boolean flag = cargoInformationService.publishCargoInfo(cargoInformation, userId);
			if (flag) {// 判断更新结果
				request.setAttribute("result","发布成功！");
				// 发布成功
			} else {
				request.setAttribute("result","发布失败！");
				// 发布失败
			}
		}
		
		request.getRequestDispatcher("/publicCargos.jsp").forward(request,response);
	}
	/**
	 * changeCarOwnerInfo： 功能：完善/注册/修改车主用户及车辆信息
	 * @param userId
	 * @param CarOwner
	 * @param CarInformation
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(params = "method=testChangeCarOwnerInfo", method = { RequestMethod.POST })
	public void testChangeCarOwnerInfo(String phone, CarOwner carOwner,
			CarInformation carInfo, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean result;
		boolean lpNum;
		int rs=0;
		String msg="";
		int userId=carInformationService.getUserId(phone).getUserId();
		if(carOwner.getCaroMobile().isEmpty()){
			carOwner.setCaroMobile(phone);
		}
		float lenght=carInfo.getCariLength();
		float width=carInfo.getCariWidth();
		float height=carInfo.getCariHeight();
		float tj=lenght*width*height;
		carInfo.setCariVolume(tj);
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
							rs=1;
						} else {
							msg="发布失败！";
						}
					} else {
						carOwner.setInfoFlag(0);// 车主信息未完善
						carInfo.setCariRvolumn(carInfo.getCariVolume());
						carInfo.setCariRload(carInfo.getCariLoad());
						result = carInformationService.updateCarOwner(userId,
								carOwner, carInfo);// 将车主及车主对应的车辆信息插入/更新数据库中
						if (result) {// 判断插入/更新结果
							// JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
							rs=1;
						} else {
							msg="发布失败！";
						}
					}
				} else {
					msg="所给车牌号已被注册！";
				}
			} else {
				msg="所给车牌号格式错误!";
			}
		} else if ((carInfo.getCariLpnum().trim() != ""&& (carInfo.getCariLpnum().trim().equals("湘A12345")))||carInfo.getCariLpnum().trim() == "") {
			
				carOwner.setInfoFlag(0);// 车主信息未完善
				carInfo.setCariRvolumn(carInfo.getCariVolume());
				carInfo.setCariRload(carInfo.getCariLoad());
				carInfo.setCariLpnum("湘A12345");
				result = carInformationService.updateCarOwner(userId, carOwner,
						carInfo);// 将车主及车主对应的车辆信息插入/更新数据库中
				if (result) {// 判断插入/更新结果
					rs=1;
				} else {
					msg="发布失败！";
					
			}
		} 
		// 根据自定义的工具类返回json数据
		response.setCharacterEncoding("gbk");
		PrintWriter out=response.getWriter();
		out.print("<script  type='text/javascript' charset='utf-8'>");
		if(rs>0){
		out.print("window.parent.location.href='/wuLiuServer/publicCars.jsp';");
		}else{
			
			out.print("window.history.back(-1);");
			out.print("window.alert('"+msg+"');");
		}
		out.print("</script>");
		out.flush();
		out.close();

	}

}
