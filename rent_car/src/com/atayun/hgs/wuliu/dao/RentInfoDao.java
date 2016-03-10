package com.atayun.hgs.wuliu.dao;

import java.util.Date;

import com.atayun.hgs.wuliu.po.User;

public interface RentInfoDao {
	//插入租车信息 返回id
    public int insertRentInfo(String takePlace,int days,Date date);
    //根据租车信息ID完善租车信息
    public int improveRentInfo(int rentid,String lpnum,String band);
  //插入租车信息 返回id
    public int creatRentInfo(String takePlace,int days,Date date,String lpnum,String band,int userid);
    //根据rentid获取被租用车辆的司机信息
    public User getDriverInfo(int userid);
}
