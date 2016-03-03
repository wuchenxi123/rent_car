package com.atayun.hgs.wuliu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.atayun.hgs.wuliu.dao.CargoInformationDao;
import com.atayun.hgs.wuliu.po.CargoAllViewPo;
import com.atayun.hgs.wuliu.po.CargoInformation;
import com.atayun.hgs.wuliu.po.CargoListView;
import com.atayun.hgs.wuliu.po.User;
@Transactional
@Service
public class CargoInformationService {
	
	@Autowired
	private CargoInformationDao cargoInformationDao;
	/**
	 * 查询所有的货源信息
	 * @return ArrayList<CargoInformation>
	 */
	public ArrayList<CargoAllViewPo> getAllCargoInformations(){
		return cargoInformationDao.getAllCargoInformations();
	}
	/**
	 * 查询最佳匹配的信息,将全部的信息返回到客户端
	 * @param userId
	 * @return
	 */
	public ArrayList<CargoListView> getBestMatchAll(Integer userId) {

		ArrayList<CargoListView> cargoListViews = new ArrayList<CargoListView>();
		cargoListViews = cargoInformationDao.getBestMatchAllC(userId);
		if(cargoListViews.isEmpty()){
			cargoListViews = cargoInformationDao.getBestMatchAllS(userId);
		}
		return cargoListViews;
	}
	
	/**
	 * 发布货源信息
	 * @param cargoInformation
	 * @param userId
	 */
	public boolean publishCargoInfo(CargoInformation cargoInformation,int userId) {
		boolean flag = false;
		int result = cargoInformationDao.publishCargoInfo(cargoInformation, userId);
		if(result>0){
			flag = true;
		}
		//System.out.println(1/0);
		return flag;
	}
	/**
	 * 获取其余匹配的全部信息
	 * @param userId
	 * @return
	 */
	public ArrayList<CargoListView> getOrtherMatchALL(Integer userId) {
		
		return cargoInformationDao.getOrtherMatchALL(userId);
	}
	/**
	 * 获取用户状态
	 * @param userId
	 * @return
	 */
	public List getUserFlag(Integer userId) {
		
		return cargoInformationDao.getUserFlag(userId);
		
	}
	/**
	 * 删除指定货源
	 * @param cargoInfoId
	 * @return
	 */
	public boolean deleteCargoInfo(Integer cargoInfoId) {
		
		return cargoInformationDao.deleteCargoInfo(cargoInfoId);
		
	}
	
	public boolean modifyCargoInfo(CargoInformation cargoInformation) {
		
		return cargoInformationDao.modifyCargoInfo(cargoInformation);
	}
	
	/**
	 * 查看自己发布的货源 根据货主ID
	 * @param userId
	 * @return
	 */
	public ArrayList<CargoAllViewPo> getMyCargoInfoByID(Integer userId) {
		
		return cargoInformationDao.getMyCargoInfoByID(userId);
		
	}
	/**
	 * 修改最后的议价
	 * @param cainId
	 * @param cargoRPrice
	 * @return
	 */
	public boolean modifylastprice(Integer cainId,float cargoRPrice) {
		
		return cargoInformationDao.modifylastprice(cainId, cargoRPrice);
	}
	
	/**
	 * 根据起点和终点，条件查询货源
	 * @param start
	 * @param end
	 * @return
	 */
	public ArrayList<CargoAllViewPo> getAllCargoInfoByStartAndEnd(String start,String end) {
		
		return cargoInformationDao.getAllCargoInfoByStartAndEnd(start, end);
	}

}
