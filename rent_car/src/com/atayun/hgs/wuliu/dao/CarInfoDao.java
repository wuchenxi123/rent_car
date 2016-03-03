package com.atayun.hgs.wuliu.dao;

import java.util.ArrayList;
import java.util.List;

import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.po.CarInformation;
import com.atayun.hgs.wuliu.po.CarListView;
import com.atayun.hgs.wuliu.po.CarOwner;
import com.atayun.hgs.wuliu.po.CarRoute;
import com.atayun.hgs.wuliu.po.User;

/**
 * CarInfoDao CarInfoDao(tb_cari与carListView）的持久层接口定义
 * 
 * @author ChenLei
 * @date 2015-05-20
 */
public interface CarInfoDao {

	/**
	 * 查询全部车辆信息
	 * 
	 * @return
	 */
	public ArrayList<CarInfo> getAllCarInfo(String carBand);

//	/**
//	 * 根据区间查询车源信息列表
//	 * 
//	 * @param start
//	 * @param end
//	 * @return
//	 */
//	public ArrayList<CarListView> getAllCarListInformations(String start,
//			String end);
//
//	/**
//	 * getCarInformations：根据查询条件用户ID查询具体车主、车辆相关信息详情
//	 * 
//	 * @param user_Id
//	 *            --用户ID
//	 * @return CarListView
//	 */
//	public CarListView getCarInformations(int userId);
//
//	
//
//	/**
//	 * 查询车主信息
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	public CarOwner getCarOwner(int userId);
//
//	/**
//	 * 给已完善信息的车主表infoFlag字段打完善标记
//	 * 
//	 * @param userId
//	 * @param flag
//	 * @return
//	 */
//	/**
//	 * 完善车主信息（更新）
//	 * 
//	 * @param userId
//	 * @param flag
//	 * @return
//	 */
//	public boolean updateCarOwner(int userId, int flag);
//
//	/**
//	 * 查询所给用户是否已在车辆信息中注册
//	 * 
//	 * @param lpNum
//	 * @param userId
//	 * @return
//	 */
//	/**
//	 * 根据车牌号，用户id检查车牌是否重复注册过
//	 * 
//	 * @param lpNum
//	 * @param userId
//	 * @return
//	 */
//	public boolean checkCar(String lpNum, int userId);
//
//	/**
//	 * 查询车辆信息
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	public CarInformation getCarInfo(int userId);
//
//	/**
//	 * 申请修改车主信息（更新）
//	 * 
//	 * @param userId
//	 * @param carOwner
//	 * @param carInfo
//	 * @return
//	 */
//	public boolean applyUpdateCarOwner(int userId, CarOwner carOwner,
//			CarInformation carInfo);
//
//	/**
//	 * 完善车主信息（更新）
//	 * 
//	 * @param userId
//	 * @param carOwner
//	 * @param carInfo
//	 * @return
//	 */
//	public boolean updateCarOwner(int userId, CarOwner carOwner,
//			CarInformation carInfo);
//
//	/**
//	 * 初始化车辆信息表
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	public int initCarInfoMation(Integer userId);
//
//	/**
//	 * 车主发布车主、车辆信息，同时更新行车路线记录
//	 * 
//	 * @param userId
//	 * @param carOwner
//	 * @param carInfo
//	 * @return
//	 */
//	public boolean publicCarOwner(int userId, CarOwner carOwner,
//			CarInformation carInfo);
//
//	/**
//	 * 根据手机号查询用户id
//	 * 
//	 * @param phone
//	 * @return
//	 */
//	public User getUserId(String phone);
//
//	/**
//	 * 查询车主行车路线条数
//	 * 
//	 * @param cariId
//	 * @return
//	 */
//	public int countRoute(int cariId);
//	
//	/**
//	 * 查询车主行车路线记录
//	 * 
//	 * @param cariId
//	 * @return
//	 */
//	public List<CarRoute> getRoute(int cariId);
//	/**
//	 * 添加车主的行车路线
//	 * 
//	 * @param carRoute
//	 * @return
//	 */
//	public boolean addCarRoute(CarRoute carRoute);
//
//	/**
//	 * 根据用户id查询车辆id
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	public CarInformation getCariId(int userId);
}
