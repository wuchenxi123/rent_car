package com.atayun.hgs.wuliu.utils.sql;

public interface CarInfoSQL {

	// 获取所有的车源
	public String getAllCarInfoSQL = "select * from tb_car where car_flag=0 and sp_flag=1";
}
