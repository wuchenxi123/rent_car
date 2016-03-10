package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.RentOrderView;

public class RentOrderViewRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		RentOrderView rentorderview=new RentOrderView();
		rentorderview.setOrderId(rs.getInt("order_id"));
		rentorderview.setOrderPrice(rs.getFloat("order_price"));
		rentorderview.setOrderStatus(rs.getInt("order_status"));
		rentorderview.setUserId(rs.getInt("user_id"));
		rentorderview.setUserIdcard(rs.getString("user_idcard"));
		rentorderview.setUserMobile(rs.getString("user_mobile"));
		rentorderview.setUserRelname(rs.getString("user_relname"));
		rentorderview.setUserRemainder(rs.getFloat("user_remainder"));
		rentorderview.setUserType(rs.getInt("user_type"));
		rentorderview.setRentId(rs.getInt("rent_id"));
		rentorderview.setRentCtype(rs.getString("rent_ctype"));
		rentorderview.setRentDays(rs.getInt("rent_days"));
		rentorderview.setRentLpnum(rs.getString("rent_lpnum"));
		rentorderview.setRentPlace(rs.getString("rent_place"));
		rentorderview.setRentTakeTime(rs.getDate("rent_takeTime"));
		rentorderview.setSenddriverId(rs.getInt("senddriver_id"));
		rentorderview.setReturnId(rs.getInt("return_id"));
		return rentorderview;
	}

}
