package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.Order;

public class OrderRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {

		Order order = new Order();
		order.setOrderId(rs.getInt("ORDE_ID"));
		order.setUserId(rs.getInt("USER_ID"));
		order.setOrderNo(rs.getString("ORDE_NO"));
		order.setOrderFlag(rs.getInt("ORDE_FLAG"));
		order.setOrderPrice(rs.getFloat("ORDE_PRICE"));
		order.setUpdateTime(rs.getTimestamp("UPDATETIME"));
		return order;
	}

}
