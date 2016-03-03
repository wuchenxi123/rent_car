package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.MessageCar;

public class MessageCarRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		MessageCar messageCar = new MessageCar();
		messageCar.setCompName(rs.getString("COMP_NAME"));
		messageCar.setCompWorkPhone(rs.getString("COMP_WORKPHONE"));
		messageCar.setCompProvice(rs.getString("COMP_PROVINCE"));
		messageCar.setCompCity(rs.getString("COMP_CITY"));
		messageCar.setCompStreet(rs.getString("COMP_STREET"));
		messageCar.setCompFlag(rs.getInt("compFlag"));//修改过
		
		messageCar.setCagoMobile(rs.getString("CAGO_MOBILE"));//new  add to here
		messageCar.setCagoFlag(rs.getInt("cagoFlag"));//修改过
		
		messageCar.setCargoInfoId(rs.getInt("CAIN_ID"));
		messageCar.setCargoInfoFlag(rs.getInt("CAIN_FLAG"));
		messageCar.setCargoInfoDesc(rs.getString("CAIN_DESC"));
		
		//询价信息
		messageCar.setPricPrice(rs.getFloat("PRIC_PRICE"));
		messageCar.setPricMark(rs.getString("PRIC_MARK"));
		messageCar.setPricUpdateTime(rs.getTimestamp("UPDATETIME"));
		
		return messageCar;
	}

}
