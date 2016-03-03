package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CarOwner;

public class CarOwnerRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		
		CarOwner carOwner = new CarOwner();
		carOwner.setUserId(rs.getInt("USER_ID"));
		carOwner.setCaroMobile(rs.getString("CARO_MOBILE"));
		carOwner.setCaroDlicpicurl(rs.getString("CARO_DLICPICURL"));
		carOwner.setCaroDlicpicflag(rs.getInt("CARO_DLICPICFLAG"));
		carOwner.setCaroCpName(rs.getString("CARO_CPNAME"));
		carOwner.setInfoFlag(rs.getInt("InfoFlag"));
		carOwner.setUpdateTime(rs.getTimestamp("UPDATETIME"));
		return carOwner;
	}

}
