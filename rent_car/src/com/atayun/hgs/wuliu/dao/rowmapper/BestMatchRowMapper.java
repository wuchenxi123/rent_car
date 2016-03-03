package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.BestMatch;

public class BestMatchRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		
		BestMatch bestMatch = new BestMatch();
		bestMatch.setCainEnd(rs.getString("cain_end"));
		bestMatch.setCainPublished(rs.getTimestamp("cain_published"));
		bestMatch.setCainStart(rs.getString("cain_start"));
		bestMatch.setCatpType(rs.getString("catp_type"));
		bestMatch.setMareNum(rs.getInt("mare_num"));
		bestMatch.setMareId(rs.getInt("mare_id"));
		bestMatch.setMinCainDelitime(rs.getTimestamp("CAIN_DELITIME"));
		bestMatch.setTrtpType(rs.getString("trtp_type"));
		bestMatch.setUserId(rs.getInt("user_id"));
		
		return bestMatch;
	}

}
