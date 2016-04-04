package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.RentInfo;
import com.atayun.hgs.wuliu.po.ReturnInfo;

public class ReturnInfoRowMapper implements RowMapper {
    

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		ReturnInfo returnInfo =new ReturnInfo();
		
		returnInfo.setReturnId(rs.getInt("return_id"));
		returnInfo.setReturnType(rs.getInt("returnType"));
		returnInfo.setReturnPlace(rs.getString("return_place"));
		returnInfo.setReturnReTime(rs.getDate("return_reTime"));
		
		
		return returnInfo;
	}
    
     
}
