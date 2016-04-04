package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CompleteOrderDetail;
import com.atayun.hgs.wuliu.po.User;

public class CompleteOrderRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CompleteOrderDetail completeorder  = new CompleteOrderDetail();

		completeorder.setOrderId(rs.getInt("order_id"));
		completeorder.setUserId(rs.getInt("user_id"));
		completeorder.setCreateDate(rs.getTimestamp("createDate"));
		completeorder.setOrderPrice(rs.getFloat("order_price"));
		completeorder.setOrderStatus(rs.getInt("order_status"));
		
		completeorder.setCarBand(rs.getString("car_band"));
		completeorder.setCarColor(rs.getString("car_color"));
		completeorder.setCarLpnum(rs.getString("car_lpnum"));
		completeorder.setCarPictUrl(rs.getString("car_pictUrl"));
		completeorder.setCarRentPri(rs.getFloat("car_rentPri"));
		completeorder.setCarTakeType(rs.getInt("car_takeType"));
		
		completeorder.setDriverIDcard(rs.getString("driverIDcard"));
		completeorder.setDriverLicense(rs.getString("driverLicense"));
		completeorder.setDriverMobie(rs.getString("driverMobile"));
		completeorder.setDrivername(rs.getString("drivername"));

		completeorder.setRentDays(rs.getInt("rent_days"));
		completeorder.setRentPlace(rs.getString("rent_place"));
		completeorder.setRentTakeTime(rs.getDate("rent_takeTime"));
		completeorder.setInsurePrice(rs.getFloat("insure_price"));
		
		completeorder.setReturnPlace(rs.getString("return_place"));
		completeorder.setReturnReTime(rs.getDate("return_reTime"));
		completeorder.setReturnType(rs.getInt("returnType"));
		completeorder.setScsmPrice(rs.getFloat("scsm_price"));
		
		return completeorder;
	}

}
