package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.po.User;

public class CarInfoRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CarInfo carInfo=new CarInfo();
		carInfo.setCarId(rs.getInt("car_id"));
		carInfo.setUpdateTime(rs.getTimestamp("updatetime"));
		carInfo.setCarBand(rs.getString("car_band"));
		carInfo.setCarColor(rs.getString("car_color"));
		carInfo.setCarPrice(rs.getFloat("car_price"));
		carInfo.setUserId(rs.getInt("user_id"));
		carInfo.setCarLpnum(rs.getString("car_lpnum"));
		carInfo.setCarPictUrl(rs.getString("car_pictUrl"));
		carInfo.setCarContent(rs.getString("car_content"));
		carInfo.setCarPurTime(rs.getDate("car_purTime"));
		carInfo.setCarRentPri(rs.getFloat("car_rentPri"));
		carInfo.setCarFlag(rs.getInt("car_flag"));
		carInfo.setInsurePrice(rs.getFloat("insure_price"));
		carInfo.setScsmPrice(rs.getFloat("scsm_price"));
		carInfo.setSpFlag(rs.getInt("sp_flag"));
		carInfo.setCarTaketype(rs.getInt("car_takeType"));
		return carInfo;
	}

}
