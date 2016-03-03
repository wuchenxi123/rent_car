package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.OrderDetail;

public class OrderDetailRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		OrderDetail detail = new OrderDetail();
		detail.setOrddId(rs.getInt("ORDD_ID"));
		detail.setOrderId(rs.getInt("ORDE_ID"));
		detail.setCariId(rs.getInt("CARI_ID"));
		detail.setOrddFlag(rs.getInt("ORDD_FLAG"));
		detail.setOrddprice(rs.getFloat("ORDD_PRICE"));
		detail.setOrddCSUBSPRICE(rs.getFloat("ORDD_CSUBSPRICE"));
		detail.setOrddHSUBSPRICE(rs.getFloat("ORDD_HSUBSPRICE"));
		detail.setOrddUpdateTime(rs.getTimestamp("UPDATETIME"));
		
		detail.setOrderNo(rs.getString("ORDE_NO"));
		detail.setOrderPrice(rs.getFloat("ORDE_PRICE"));
		
		detail.setCargoInfoId(rs.getInt("CAIN_ID"));
		detail.setCargoTypeId(rs.getInt("CATP_ID"));
		detail.setCargoInfoStart(rs.getString("CAIN_START"));
		detail.setCargoInfoSStreet(rs.getString("CAIN_SSTREET"));//起点详细信息
		detail.setCargoInfoEnd(rs.getString("CAIN_END"));
		detail.setCargoInfoEStreet(rs.getString("CAIN_ESTREET"));//终点详细信
		
		java.util.Date cargoInfoDeliTime = rs.getTimestamp("CAIN_DELITIME");
		detail.setCargoInfoDeliTime(cargoInfoDeliTime);
		
		detail.setCargoInfoPrice(rs.getFloat("CAIN_PRICE"));
		detail.setCargoInfoLenth(rs.getFloat("CAIN_LENGTH"));
		detail.setCargoInfoWidth(rs.getFloat("CAIN_WIDTH"));
		detail.setCargoInfoHeight(rs.getFloat("CAIN_HEIGHT"));
		detail.setCargoInfoRlen(rs.getFloat("CAIN_RLEN"));
		detail.setCargoInfoVunit(rs.getString("CAIN_VUNIT"));
		detail.setCargoInfoLunit(rs.getString("CAIN_LUNIT"));
		detail.setCargoInfoVolume(rs.getFloat("CAIN_VOLUME"));
		detail.setCargoInfoLoad(rs.getFloat("CAIN_LOAD"));
		detail.setCargoInfoDesc(rs.getString("CAIN_DESC"));
		detail.setCargoInfoContacts(rs.getString("CAIN_CONTACTS"));
		detail.setCargoInfoContactWay(rs.getString("CAIN_CANTACTWAY"));
		detail.setCargoInfoPicturl(rs.getString("CAIN_PICURL"));
		detail.setCargoInfoFlag(rs.getInt("CAIN_FLAG"));
		
		detail.setCarLpNum(rs.getString("CARI_LPNUM"));
		return detail;
	}

}
