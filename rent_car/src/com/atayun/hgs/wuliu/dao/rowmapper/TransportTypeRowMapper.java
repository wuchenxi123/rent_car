package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.TransportType;

public class TransportTypeRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		TransportType transportType = new TransportType();
		transportType.setTransportTypeId(rs.getInt("TRTP_ID"));
		transportType.setTransportType(rs.getString("TRTP_TYPE"));
		transportType.setTransportTypeUpdateTime(rs.getDate("UPDATETIME"));
		return transportType;
	}

}
