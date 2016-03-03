package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CarRoute;

public class CarRouteRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int 	index) throws SQLException {
		CarRoute carRoute=new CarRoute();
//		carRoute.setCariId(rs.getInt("CARI_ID"));
//		carRoute.setCarrId(rs.getInt("CARR_ID"));
		carRoute.setCarrStart(rs.getString("CARR_START"));
		carRoute.setCarrEnd(rs.getString("CARR_END"));
//		carRoute.setUpdateTime(rs.getDate("UPDATETIME"));
		return carRoute;
	}

}
