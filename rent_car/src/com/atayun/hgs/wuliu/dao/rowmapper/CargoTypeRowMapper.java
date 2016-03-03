package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CargoType;

public class CargoTypeRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		
		CargoType cargoType = new CargoType();
		cargoType.setCargoType(rs.getString("CATP_TYPE"));
		cargoType.setCargoTypeId(rs.getInt("CATP_ID"));
		cargoType.setCargoTypeUpdateTime(rs.getDate("UPDATETIME"));
		return cargoType;
	}

}
