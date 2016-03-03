package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.MessageCargo;

public class MessageCargoRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		MessageCargo message = new MessageCargo();
		
		//车主
		message.setCaroMobile(rs.getString("CARO_MOBILE"));
		message.setCaroDlicpicflag(rs.getInt("CARO_DLICPICFLAG"));
		message.setCaroCpName(rs.getString("CARO_CPNAME"));
		message.setInfoFlag(rs.getInt("InfoFlag"));
		
		//车辆信息
		message.setCariLpnum(rs.getString("CARI_LPNUM"));
		message.setCariLength(rs.getFloat("CARI_LENGTH"));
		message.setCariWidth(rs.getFloat("CARI_WIDTH"));
		message.setCariHeight(rs.getFloat("CARI_HEIGHT"));
		message.setCariVolume(rs.getFloat("CARI_VOLUME"));
		message.setCariVunit(rs.getString("CARI_VUNIT"));
		message.setCariLoad(rs.getFloat("CARI_LOAD"));
		message.setCariLunit(rs.getString("CARI_LUNIT"));
		message.setCariDlicFlag(rs.getInt("CARI_DLICFLAG"));
		message.setCariPicUrl(rs.getString("CARI_PICURL"));
		message.setCariPicFlag(rs.getInt("CARI_PICFLAG"));
		message.setCariFlag(rs.getInt("CARI_FLAG"));
		message.setCariStart(rs.getString("CARI_START"));
		message.setCariEnd(rs.getString("CARI_END"));
		message.setCariOlength(rs.getInt("CARI_OLENGTH"));
		message.setCariOwidth(rs.getInt("CARI_OWIDTH"));
		message.setCariOheight(rs.getInt("CARI_OHEIGHT"));
		message.setCariOload(rs.getInt("CARI_OLOAD"));
		
		//询价信息
		message.setPricPrice(rs.getFloat("PRIC_PRICE"));
		message.setPricMark(rs.getString("PRIC_MARK"));
		message.setPricUpdateTime(rs.getTimestamp("UPDATETIME"));
		
		//新增
		message.setPricCainId(rs.getInt("CAIN_ID"));
		message.setPricCaroId(rs.getInt("CARO_ID"));
		return message;
	}

}
