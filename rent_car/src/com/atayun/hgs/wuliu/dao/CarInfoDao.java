package com.atayun.hgs.wuliu.dao;

import java.util.ArrayList;

import com.atayun.hgs.wuliu.po.CarInfo;


public interface CarInfoDao {
	//根据车辆ID获取车辆信息
	    public CarInfo getAllCarInfo(int carid); 
	//获取所有车辆信息
		public ArrayList<CarInfo> getAllCarInfo(); 
	//根据车辆信息id将车辆租用状态设为已租用 1 或是未租用0  
		public boolean setCarBusy(int carid,int flag);
	//根据车辆类型和车牌号获取车辆信息
		public CarInfo getCarBybandAndLpnum(String band ,String lpnum);
	//修改取/送车方式     0送车上门  上门还车（要交费）    1 公司地点取车  
	    public boolean changeCarTakeType(int carid,int taketype);
}
