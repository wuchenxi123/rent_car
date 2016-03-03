package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CargoOwner;

public class CargoOwnerRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		
		CargoOwner cargoOwner = new CargoOwner();
		cargoOwner.setUserId(rs.getInt("USER_ID"));
		cargoOwner.setCagoCity(rs.getString("CAGO_CITY"));
		cargoOwner.setCagoCountry(rs.getString("CAGO_COUNTRY"));
		cargoOwner.setCagoProvince(rs.getString("CAGO_PROVINCE"));
		cargoOwner.setCagoStreet(rs.getString("CAGO_STREET"));
		cargoOwner.setInFoFlag(rs.getInt("INFOFLAG"));
		cargoOwner.setCagoCPName(rs.getString("CAGO_CPNAME"));
		cargoOwner.setCagoCPPicURL(rs.getString("CAGO_CPPICURL"));
		cargoOwner.setCagoCPPicFlag(rs.getInt("CAGO_CPPICFLAG"));
		cargoOwner.setCagoMobile(rs.getString("CAGO_MOBILE"));//new  add to here
		cargoOwner.setUpdateTime(rs.getDate("UPDATETIME"));
		return cargoOwner;
	}

}
