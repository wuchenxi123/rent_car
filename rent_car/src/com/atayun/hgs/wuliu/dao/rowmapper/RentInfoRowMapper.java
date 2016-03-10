package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.ReturnInfo;

@SuppressWarnings("rawtypes")
public class RentInfoRowMapper implements RowMapper {
    

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		ReturnInfo returninfo=new ReturnInfo();
		returninfo.setReturnId(rs.getInt("return_id"));
		returninfo.setReturnPlace(rs.getString("return_place"));
		returninfo.setReturnReTime(rs.getDate("return_reTime"));
		returninfo.setUserId(rs.getInt("user_id"));
		 
		return returninfo;
	}
    
     
}
