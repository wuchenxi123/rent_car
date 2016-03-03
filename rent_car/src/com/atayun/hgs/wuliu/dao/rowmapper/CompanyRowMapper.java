package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.Company;

public class CompanyRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Company company = new Company();
		company.setUserId(rs.getInt("USER_ID"));
		company.setCompName(rs.getString("COMP_NAME"));
		company.setCompTaxNo(rs.getString("COMP_TAXNO"));
		company.setCompWorkPhone(rs.getString("COMP_WORKPHONE"));
		company.setCompCPPicURL(rs.getString("COMP_CPPICURL"));
		company.setCompSWDJFBURL(rs.getString("COMP_SWDJFBURL"));
		company.setCompKHXKZURL(rs.getString("COMP_KHXKZURL"));
		company.setCompXQSWMBURL(rs.getString("COMP_XQSWMBURL"));
		company.setCompCountry(rs.getString("COMP_COUNTRY"));
		company.setCompProvice(rs.getString("COMP_PROVINCE"));
		company.setCompCity(rs.getString("COMP_CITY"));
		company.setCompStreet(rs.getString("COMP_STREET"));
		company.setInFoFlag(rs.getInt("INFOFLAG"));
		company.setUpdateTime(rs.getTimestamp("UPDATETIME"));
		return company;
	}

}
