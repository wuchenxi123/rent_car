package com.atayun.hgs.wuliu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atayun.hgs.wuliu.dao.CarInformationDao;
import com.atayun.hgs.wuliu.po.CarInformation;
import com.atayun.hgs.wuliu.po.CarListView;
import com.atayun.hgs.wuliu.po.CarOwner;
import com.atayun.hgs.wuliu.po.CarRoute;
import com.atayun.hgs.wuliu.po.User;

/**
 * CarInformationService 概述： 车辆的业务逻辑，调用CarInformationDaoImpl的持久层进行业务逻辑的实现
 * <p>
 * 1.查询所有车辆信息 2.根据条件查询车辆列表 3.根据车辆carrId查询具体车辆信息
 * </p>
 * 
 * @author ChenLei
 * @version 0.5, 2015/05/20
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CarInformationService {
	/** spring 注入方式 将CarInformationDao注入 */
	@Autowired
	private CarInformationDao carInformationDao;

	/**
	 * getAllCarInformations：查询所有车辆信息 无参数
	 * 
	 * @return ArrayList<CarInformation>
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ArrayList<CarInformation> getAllCarInformations() {
		return carInformationDao.getAllCarInformations();
	}

	/**
	 * getAllCarInformations：根据查询条件（起点-终点）查询车辆信息列表
	 * 
	 * @param start
	 *            --起点
	 * @param end
	 *            --终点
	 * @return ArrayList<CarInformation>
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ArrayList<CarListView> getAllCarListInformations(String start,
			String end) {
		return carInformationDao.getAllCarListInformations(start, end);
	}

	/**
	 * getCarInformations：根据查询条件用户ID查询具体车主、车辆相关信息详情
	 * 
	 * @param user_Id
	 *            --用户ID
	 * @return CarListView
	 * 			--车辆视图
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public CarListView getCarInformations(int userId) {
		return carInformationDao.getCarInformations(userId);
	}

	/**
	 * 车主发布车主、车辆信息，同时更新车主行车路线记录
	 * @param userId
	 * @param carOwner
	 * @param carInfo
	 * @param carRoute
	 * @return
	 */
	public boolean publishCarInfo(int userId,CarOwner carOwner,CarInformation carInfo,CarRoute carRoute) {
		
		boolean route=false;
		boolean publicCar=false;
		route=carInformationDao.addCarRoute(carRoute);
		int rs=carInformationDao.countRoute(carRoute.getCariId());
		if(rs>=0){
			carInfo.setCariRouteNum(rs);
			publicCar=carInformationDao.publicCarOwner(userId, carOwner, carInfo);
		}
		return route&&publicCar;
	}
	/**
	 * 根据车牌、用户id，检查数据库是否有相同的车牌号
	 * @param lpNum
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean checkCar(String lpNum,int userId) {
		
		return carInformationDao.checkCar(lpNum,userId);
	}
	/**
	 * 根据用户id查询车主信息
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public CarOwner getCarOwner(int userId) {
		
		return carInformationDao.getCarOwner(userId);
	}
	/**
	 * 根据用户id得到车辆信息
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public CarInformation getCarInfo(int userId) {
		
		return carInformationDao.getCarInfo(userId);
	}
	/**
	 * 根据用户id查询车辆id
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public CarInformation getCariId(int userId) {
		return carInformationDao.getCariId(userId);
	}
	/**
	 * 受理车主修改信息申请
	 * @param userId
	 * @param carOwner
	 * @param carInfo
	 * @return
	 */
	public boolean applyUpdateCarOwner(int userId, CarOwner carOwner,
			CarInformation carInfo) {
		
		return carInformationDao.applyUpdateCarOwner(userId, carOwner, carInfo);
	}
	/**
	 * 更新车主信息    
	 * @param userId
	 * @param carOwner
	 * @param carInfo
	 * @return
	 */
	public boolean updateCarOwner(int userId, CarOwner carOwner,
			CarInformation carInfo) {
		
		return carInformationDao.updateCarOwner(userId, carOwner, carInfo);
	}
	/**
	 * 根据手机号得到用户id
	 * @param phone
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public User getUserId(String phone) {
		return carInformationDao.getUserId(phone);
	}
	/**
	 * 查询车主的行车路线记录
	 * @param cariId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<CarRoute> getRoute(int cariId) {
		return carInformationDao.getRoute(cariId);
	}
}
