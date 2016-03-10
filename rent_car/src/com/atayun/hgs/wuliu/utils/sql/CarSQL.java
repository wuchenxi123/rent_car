package com.atayun.hgs.wuliu.utils.sql;

public interface CarSQL {
	//根据车辆id获取租用的车辆信息
     public String getAllCarInfoByIdSQL="select * from tb_car where car_id=?";
   //根据车辆类型和车牌号获取车辆信息
     public String getCarInfoByBandAndLpnumSQL="select * from tb_car where car_band=? and car_lpnum=? and sp_flag=1";
   //获取所有未被租用的车辆信息
     public String getAllCarInfoSQL="select * from tb_car where car_flag=0 and sp_flag=1";
    //设置车辆租用状态
     public String changeCarStatusSQL="update tb_car set car_flag=? where car_id=?";
   //设置车辆取送车方式
     public String changeCarTakeTypeSQL="update tb_car set car_taketype=? where car_id=?";
}
