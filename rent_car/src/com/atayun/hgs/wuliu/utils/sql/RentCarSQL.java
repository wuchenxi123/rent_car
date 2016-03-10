package com.atayun.hgs.wuliu.utils.sql;

public interface RentCarSQL {
	//插入租车信息
     public String insertCarRentSQL="insert into tb_rentinfo(rent_place,rent_days,rent_takeTime,rent_ctype,rent_lpnum,user_id) values(?,?,?,?,?,?)";
   //插入部分租车信息
     public String insertPartCarRentSQL="insert into tb_rentinfo(rent_place,rent_days,rent_takeTime) values(?,?,?)";
   //完善租车信息
     public String improveCarRentSQL="update tb_rentinfo set rent_ctype=?and rent_lpnum=? where rent_id=? ";
   //获取所有未被租用的车辆信息
     public String getAllCarInfoSQL="select * from car_database where and car_flag=0";
     
     
     //获取被租用车辆的司机信息
     public String getDriverByUserId="select * from tb_user where user_id=?";
}
