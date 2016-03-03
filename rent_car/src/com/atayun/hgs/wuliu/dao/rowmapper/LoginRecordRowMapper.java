package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.LoginRecord;

public class LoginRecordRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		
		LoginRecord loginRecord = new LoginRecord();
		
		loginRecord.setuLREId(rs.getInt("ULRE_ID"));
		loginRecord.setUserId(rs.getInt("USER_ID"));
		loginRecord.setuLRELandTime(rs.getDate("ULRE_LANDTIME"));
		loginRecord.setuLREIp(rs.getString("ULRE_IP"));
		loginRecord.setuLREIIMEI(rs.getString("ULRE_IMEI"));
		loginRecord.setuLREFlag(rs.getInt("ULRE_FLAG"));
		
		return loginRecord;
	}
	
	

}
