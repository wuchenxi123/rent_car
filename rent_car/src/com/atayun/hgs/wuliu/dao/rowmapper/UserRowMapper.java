package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.User;

public class UserRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		User user  = new User();
		user.setUserId(rs.getInt("USER_ID"));
		user.setUserName(rs.getString("user_name"));
		user.setUserMobile(rs.getString("user_mobile"));
		user.setUserPassword(rs.getString("user_password"));
		user.setUserIDCard(rs.getString("user_idcard"));
		user.setUserRelname(rs.getString("user_relname"));
		user.setUserLicense(rs.getString("user_license"));
		user.setUserType(rs.getInt("user_type"));
		user.setUserRemainder(rs.getFloat("USER_REMAINDER"));
		user.setUserVerifyCode(rs.getString("user_VerifyCode"));
		java.util.Date userRegtime = rs.getTimestamp("user_regtime");
		
		return user;
	}

}
