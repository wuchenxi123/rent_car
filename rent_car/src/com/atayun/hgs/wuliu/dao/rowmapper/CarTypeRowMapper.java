package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CarType;

public class CarTypeRowMapper implements RowMapper{

public Object mapRow(ResultSet rs, int index) throws SQLException {
		
		CarType carType = new CarType();
		carType.setCartId(rs.getInt("CART_ID"));
		carType.setCartType(rs.getString("CART_TYPE"));
		carType.setUpdateTime(rs.getTimestamp("UPDATETIME"));
		return carType;
	}

}
