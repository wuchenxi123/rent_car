package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.OrderView;

public class OrderViewRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		OrderView orderview=new OrderView();
		orderview.setOrderId(rs.getInt("order_id"));
		orderview.setOrderPrice(rs.getFloat("order_price"));
		orderview.setOrderStatus(rs.getInt("order_status"));
		orderview.setUserId(rs.getInt("user_id"));
		orderview.setUserIdcard(rs.getString("user_idcard"));
		orderview.setUserMobile(rs.getString("user_mobile"));
		orderview.setUserRelname(rs.getString("user_relname"));
		orderview.setUserRemainder(rs.getFloat("user_remainder"));
		orderview.setUserType(rs.getInt("user_type"));
		orderview.setRentId(rs.getInt("rent_id"));
		orderview.setRentCtype(rs.getString("rent_ctype"));
		orderview.setRentDays(rs.getInt("rent_days"));
		orderview.setRentLpnum(rs.getString("rent_lpnum"));
		orderview.setRentPlace(rs.getString("rent_place"));
		orderview.setRentTakeTime(rs.getDate("rent_takeTime"));
		orderview.setSenddriverId(rs.getInt("senddriver_id"));
		orderview.setReturnId(rs.getInt("return_id"));
		orderview.setReturnPlace(rs.getString("return_place"));
		orderview.setReturnReTime(rs.getDate("return_reTime"));
		orderview.setTakedriverId(rs.getInt("takedriver_id"));
		orderview.setOverSpend(rs.getFloat("overSpend"));
		return orderview;
	}

}
