package com.atayun.hgs.wuliu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.CarInformationDao;
import com.atayun.hgs.wuliu.dao.rowmapper.CarInformationRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.CarListViewRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.CarOwnerRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.CarRouteRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.UserRowMapper;
import com.atayun.hgs.wuliu.po.CarInformation;
import com.atayun.hgs.wuliu.po.CarListView;
import com.atayun.hgs.wuliu.po.CarOwner;
import com.atayun.hgs.wuliu.po.CarRoute;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.utils.sql.CarInformationSQL;

@Repository
public class CarInformationDaoImpl implements CarInformationDao {

	/** 这个参数是为了通过Spring的注解方式，得到Spring的jdbc模板，前提条件是在Spring的配置文件中，配置模板 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * getAllCarInformations: 功能： 查询所有车辆列表信息 无参数
	 */

	@SuppressWarnings("unchecked")
	public ArrayList<CarInformation> getAllCarInformations() {

		ArrayList<CarInformation> arrayList = null;
		arrayList = (ArrayList<CarInformation>) jdbcTemplate.query(
				CarInformationSQL.getAllCarInformationSQL, new Object[] {},
				new int[] {}, new CarInformationRowMapper());
		return arrayList;
	}

	/**
	 * getAllCarListInformations: 功能： 根据出发点和目的地为查询条件查询车源信息列表
	 * 
	 * @param start
	 *            出发点
	 * @param end
	 *            目的地
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CarListView> getAllCarListInformations(String start,
			String end) {
		ArrayList<CarListView> arrayList = null;
		if ((!start.trim().isEmpty()) && (!end.trim().isEmpty())) {
			arrayList = (ArrayList<CarListView>) jdbcTemplate.query(
					CarInformationSQL.getAllCarListInformationSQL,
					new Object[] { start.trim() + "%", end.trim() + "%" },
					new int[] { java.sql.Types.CHAR, java.sql.Types.CHAR },
					new CarListViewRowMapper());
		} else if ((!start.trim().isEmpty()) && (end.trim().isEmpty())) {
			arrayList = (ArrayList<CarListView>) jdbcTemplate.query(
					CarInformationSQL.getAllCarStartListInformationSQL,
					new Object[] { start.trim() + "%" },
					new int[] { java.sql.Types.CHAR },
					new CarListViewRowMapper());
		} else if ((start.trim().isEmpty()) && (!end.trim().isEmpty())) {
			arrayList = (ArrayList<CarListView>) jdbcTemplate.query(
					CarInformationSQL.getAllCarEndListInformationSQL,
					new Object[] { end.trim() + "%" },
					new int[] { java.sql.Types.CHAR },
					new CarListViewRowMapper());
		} else {
			arrayList = (ArrayList<CarListView>) jdbcTemplate.query(
					CarInformationSQL.getAllCarListsInformationSQL,
					new Object[] {}, new int[] {}, new CarListViewRowMapper());
		}
		return arrayList;
	}

	/**
	 * getCarListInformations: 功能： 根据用户id为查询条件查询车辆信息
	 * 
	 * @param userId
	 *            ：用户Id
	 */
	@SuppressWarnings({ "unchecked" })
	public CarListView getCarInformations(int userId) {
		List<CarListView> cars = null;
		cars = (List<CarListView>) jdbcTemplate.query(
				CarInformationSQL.getCarInformationSQL,
				new Object[] { userId }, new int[] { java.sql.Types.INTEGER },
				new CarListViewRowMapper());
		return cars.isEmpty() ? null : cars.get(0);
	}

	/**
	 * checkCar: 功能：检查车辆信息表中是否有重复
	 * 
	 * @param userId
	 *            用户id
	 * @param lpNum
	 *            车牌号
	 */
	public boolean checkCar(String lpNum, int userId) {
		int rs = -1;
		rs = jdbcTemplate.queryForInt(
				CarInformationSQL.checkCarInformationSQL, new Object[] { lpNum,
						userId }, new int[] { java.sql.Types.CHAR,
						java.sql.Types.INTEGER });
		System.out.println(rs);
		return rs > 0 ? true : false;
	}

	/**
	 * getCarOwner: 功能：查询车主信息
	 * 
	 * @param userId
	 *            用户id
	 */
	@SuppressWarnings("unchecked")
	public CarOwner getCarOwner(int userId) {
		List<CarOwner> ownwer = null;
		ownwer = (List<CarOwner>) jdbcTemplate.query(
				CarInformationSQL.getCarOwnwerSQL, new Object[] { userId },
				new int[] { java.sql.Types.INTEGER }, new CarOwnerRowMapper());
		return ownwer.isEmpty() ? null : ownwer.get(0);
	}

	/**
	 * getCarInfo: 功能：查询车辆信息
	 * 
	 * @param userId
	 *            用户id
	 */
	@SuppressWarnings("unchecked")
	public CarInformation getCarInfo(int userId) {
		List<CarInformation> car = null;
		car = (List<CarInformation>) jdbcTemplate.query(
				CarInformationSQL.getCarInfoSQL, new Object[] { userId },
				new int[] { java.sql.Types.INTEGER },
				new CarInformationRowMapper());
		return car.isEmpty() ? null : car.get(0);
	}

	/**
	 * updateCarOwner: 功能：给完善后的车主infoFlag打完善标记
	 * 
	 * @param userId
	 *            用户id
	 * @param flag
	 *            信息状态
	 */
	public boolean updateCarOwner(int userId, int flag) {
		int ownerRow = -1;
		ownerRow = jdbcTemplate.update(CarInformationSQL.updateCarOwnerSQL,
				new Object[] { flag, userId }, new int[] {
						java.sql.Types.TINYINT, java.sql.Types.INTEGER });
		return ownerRow > 0 ? true : false;
	}

	/**
	 * updateCarOwner: 功能：更新车主信息
	 * 
	 * @param CarOwner
	 *            车主信息
	 * @param CarInformation
	 *            车辆信息
	 */
	public boolean updateCarOwner(int userId, CarOwner carOwner,
			CarInformation carInfo) {
		int ownerRow = -1;
		ownerRow = jdbcTemplate.update(
				CarInformationSQL.updateOwnerSQL,
				new Object[] { carOwner.getCaroDlicpicurl(),
						carOwner.getCaroDlicpicflag(),
						carOwner.getCaroCpName(), carOwner.getInfoFlag(),
						carOwner.getCaroMobile(), userId }, new int[] {
						java.sql.Types.VARCHAR, java.sql.Types.TINYINT,
						java.sql.Types.VARCHAR, java.sql.Types.TINYINT,
						java.sql.Types.CHAR, java.sql.Types.INTEGER });
		int carInfoRow = -1;
		carInfoRow = jdbcTemplate
				.update(CarInformationSQL.updateCarInfoSQL,
						new Object[] { carInfo.getCartId(),
								carInfo.getCariLpnum(),
								carInfo.getCariLength(),
								carInfo.getCariWidth(),
								carInfo.getCariHeight(),
								carInfo.getCariVolume(),
								carInfo.getCariVunit(),
								carInfo.getCariRvolumn(),
								carInfo.getCariLoad(), carInfo.getCariLunit(),
								carInfo.getCariRload(),
								carInfo.getCariDlicUrl(),
								carInfo.getCariDlicFlag(),
								carInfo.getCariPicUrl(),
								carInfo.getCariPicFlag(),
								carInfo.getCariFlag(), userId }, new int[] {
								java.sql.Types.INTEGER, java.sql.Types.CHAR,
								java.sql.Types.FLOAT, java.sql.Types.FLOAT,
								java.sql.Types.FLOAT, java.sql.Types.FLOAT,
								java.sql.Types.VARCHAR, java.sql.Types.FLOAT,
								java.sql.Types.FLOAT, java.sql.Types.VARCHAR,
								java.sql.Types.FLOAT, java.sql.Types.VARCHAR,
								java.sql.Types.TINYINT, java.sql.Types.VARCHAR,
								java.sql.Types.TINYINT, java.sql.Types.TINYINT,
								java.sql.Types.INTEGER });
		return (ownerRow > 0 && carInfoRow > 0) ? true : false;
	}

	/**
	 * publicCarOwner: 功能：车主发布车主、车辆信息，同时添加行车路线记录
	 * 
	 * @param userId
	 *            用户id
	 * @param CarOwner
	 *            车主信息
	 * @param CarInformation
	 *            车辆信息
	 */
	public boolean publicCarOwner(int userId, CarOwner carOwner,
			CarInformation carInfo) {
		int ownerRow=-1;
		ownerRow = jdbcTemplate.update(CarInformationSQL.publicOwnerSQL,
				new Object[] { carOwner.getCaroMobile(), userId }, new int[] {
						java.sql.Types.CHAR, java.sql.Types.INTEGER });
		int carInfoRow = -1;
		carInfoRow = jdbcTemplate
				.update(CarInformationSQL.publicCarInfoSQL,
						new Object[] { carInfo.getCariStart(),
								carInfo.getCariScity(),
								carInfo.getCariSstreet(), carInfo.getCariEnd(),
								carInfo.getCariEcity(),
								carInfo.getCariEstreet(), carInfo.getCariLng(),
								carInfo.getCariLat(), carInfo.getCariElng(),
								carInfo.getCariElat(),
								carInfo.getCariOlength(),
								carInfo.getCariOwidth(),
								carInfo.getCariOheight(),
								carInfo.getCariLoad(),carInfo.getCariRouteNum(), 
								userId }, new int[] {
								java.sql.Types.VARCHAR, java.sql.Types.VARCHAR,
								java.sql.Types.VARCHAR, java.sql.Types.VARCHAR,
								java.sql.Types.VARCHAR, java.sql.Types.VARCHAR,
								java.sql.Types.CHAR, java.sql.Types.CHAR,
								java.sql.Types.CHAR, java.sql.Types.CHAR,
								java.sql.Types.TINYINT, java.sql.Types.TINYINT,
								java.sql.Types.TINYINT, java.sql.Types.TINYINT,
								java.sql.Types.TINYINT,java.sql.Types.INTEGER });
		return (ownerRow > 0 && carInfoRow > 0) ? true : false;
	}

	/**
	 * applyUpdateCarOwner: 功能：受理车主更新相关信息申请
	 * 
	 * @param userId
	 *            用户id
	 * @param CarOwner
	 *            车主信息
	 * @param CarInformation
	 *            车辆信息
	 */
	public boolean applyUpdateCarOwner(int userId, CarOwner carOwner,
			CarInformation carInfo) {
		int ownerRow = -1;
		ownerRow = jdbcTemplate.update(
				CarInformationSQL.applyUpdateOwnerSQL,
				new Object[] { carOwner.getCaroDlicpicurl(),
						carOwner.getCaroCpName(), carOwner.getInfoFlag(),
						carOwner.getCaroMobile(), userId }, new int[] {
						java.sql.Types.VARCHAR, java.sql.Types.VARCHAR,
						java.sql.Types.TINYINT, java.sql.Types.CHAR,
						java.sql.Types.INTEGER });
		int carInfoRow = -1;
		carInfoRow = jdbcTemplate
				.update(CarInformationSQL.applyUpdateCarInfoSQL,
						new Object[] { carInfo.getCartId(),
								carInfo.getCariLpnum(),
								carInfo.getCariLength(),
								carInfo.getCariWidth(),
								carInfo.getCariHeight(),
								carInfo.getCariVolume(),
								carInfo.getCariVunit(), carInfo.getCariLoad(),
								carInfo.getCariLunit(),
								carInfo.getCariDlicUrl(),
								carInfo.getCariPicUrl(), carInfo.getCariFlag(),
								userId },
						new int[] { java.sql.Types.INTEGER,
								java.sql.Types.CHAR, java.sql.Types.FLOAT,
								java.sql.Types.FLOAT, java.sql.Types.FLOAT,
								java.sql.Types.FLOAT, java.sql.Types.VARCHAR,
								java.sql.Types.FLOAT, java.sql.Types.VARCHAR,
								java.sql.Types.VARCHAR, java.sql.Types.VARCHAR,
								java.sql.Types.TINYINT, java.sql.Types.INTEGER });
		return (ownerRow > 0 && carInfoRow > 0) ? true : false;
	}

	/**
	 * getUserId: 功能：根据手机号查询用户id
	 * 
	 * @param phone
	 *            用户id
	 * @return User
	 */
	@SuppressWarnings("unchecked")
	public User getUserId(String phone) {
		List<User> user = null;
		user = (List<User>) jdbcTemplate.query(CarInformationSQL.getUserId,
				new Object[] { phone }, new int[] { java.sql.Types.VARCHAR },
				new UserRowMapper());
		return user.size() > 0 ? user.get(0) : null;
	}

	/**
	 * getCariId: 功能：根据用户id查询车辆id
	 * 
	 * @param userId
	 *            用户id
	 * @return CarInformation
	 */
	@SuppressWarnings("unchecked")
	public CarInformation getCariId(int userId) {
		List<CarInformation> car = null;
		car = (List<CarInformation>) jdbcTemplate.query(
				CarInformationSQL.getCariIdSQL, new Object[] { userId },
				new int[] { java.sql.Types.VARCHAR },
				new CarInformationRowMapper());
		return car.size() > 0 ? car.get(0) : null;
	}

	/**
	 * getCariId: 功能：初始化车辆信息表
	 * 
	 * @param userId
	 *            用户id
	 * @return 更新结果，即是否更新成功
	 */
	public int initCarInfoMation(Integer userId) {

		int result = -1;
		result = jdbcTemplate.update(CarInformationSQL.initCarInfoMationSQL,
				new Object[] { userId });

		return result;
	}

	/**
	 * countRoute: 功能：统计行车路线条数
	 * 
	 * @param cariId
	 *            车辆id
	 * @return 查询结果
	 */
	public int countRoute(int cariId) {
		int rs=-1;
		rs = jdbcTemplate.queryForInt(CarInformationSQL.countRouteSQL,
				new Object[] { cariId }, new int[] { java.sql.Types.INTEGER });
		return rs;
	}

	/**
	 * addCarRoute: 功能：添加行车路线记录
	 * 
	 * @param carRoute
	 *            行车路线记录
	 * @return 返回添加结果
	 */
	public boolean addCarRoute(CarRoute carRoute) {
		int routeRow=-1;
		routeRow = jdbcTemplate.update(CarInformationSQL.addCarRouteSQL,
				new Object[] { carRoute.getCariId(), carRoute.getCarrStart(),
						carRoute.getCarrEnd() }, new int[] {
						java.sql.Types.INTEGER, java.sql.Types.VARCHAR,
						java.sql.Types.VARCHAR });
		return routeRow > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	public List<CarRoute> getRoute(int cariId) {
		List<CarRoute> routes = null;
		routes = (List<CarRoute>) jdbcTemplate.query(
				CarInformationSQL.getRouteSQL, new Object[] { cariId },
				new int[] { java.sql.Types.VARCHAR },
				new CarRouteRowMapper());
		return routes.size() > 0 ? routes : null;
	}

}
