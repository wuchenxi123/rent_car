package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.OrderInfo;

public class OrderInfoRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {

		OrderInfo order = new OrderInfo();
		order.setOrderId(rs.getInt("order_id"));
		order.setOrderStatus(rs.getInt("order_status"));
		order.setUserId(rs.getInt("user_id"));
		order.setOrderPrice(rs.getFloat("order_price"));
		order.setCreateDate(rs.getTimestamp("craeteTime"));
		order.setRentId(rs.getInt("rent_id"));
		order.setReturnId(rs.getInt("return_id"));
		order.setOverSpend(rs.getFloat("overSpend"));
		return order;
	}

}
