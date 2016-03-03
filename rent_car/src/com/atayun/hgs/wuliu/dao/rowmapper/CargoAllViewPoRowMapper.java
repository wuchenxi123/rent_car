package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CargoAllViewPo;

public class CargoAllViewPoRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CargoAllViewPo allViewPo = new CargoAllViewPo();
		allViewPo.setUserId(rs.getInt("USER_ID"));
		allViewPo.setCargoInfoId(rs.getInt("CAIN_ID"));
		allViewPo.setUserName(rs.getString("USER_NAME"));
		allViewPo.setUserMobile(rs.getString("USER_MOBILE"));
		allViewPo.setUserAddr(rs.getString("USER_ADDR"));
		allViewPo.setTransportType(rs.getString("TRTP_TYPE"));
		allViewPo.setCargoType(rs.getString("CATP_TYPE"));
		
		java.util.Date cargoInfoPublished = rs.getTimestamp("CAIN_PUBLISHED");
		//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(cargoInfoPublished);
		allViewPo.setCargoInfoPublished(cargoInfoPublished);
		allViewPo.setCargoInfoStart(rs.getString("CAIN_START"));
		
		allViewPo.setCargoInfoSStreet(rs.getString("CAIN_SSTREET"));//起点详细信息
		
		allViewPo.setCargoInfoEnd(rs.getString("CAIN_END"));
		
		allViewPo.setCargoInfoEStreet(rs.getString("CAIN_ESTREET"));//终点详细信息
		
		allViewPo.setCargoInfoLng(rs.getString("CAIN_LNG"));
		allViewPo.setCargoInfoLat(rs.getString("CAIN_LAT"));
		allViewPo.setCargoInfoELng(rs.getString("CAIN_ELAT"));
		allViewPo.setCargoInfoELat(rs.getString("CAIN_ELAT"));
		
		java.util.Date cargoInfoDeliTime = rs.getTimestamp("CAIN_DELITIME");
		
		allViewPo.setCargoInfoDeliTime(cargoInfoDeliTime);
		allViewPo.setCargoInfoPrice(rs.getFloat("CAIN_PRICE"));
		allViewPo.setCargoInfoLenth(rs.getFloat("CAIN_LENGTH"));
		allViewPo.setCargoInfoWidth(rs.getFloat("CAIN_WIDTH"));
		allViewPo.setCargoInfoHeight(rs.getFloat("CAIN_HEIGHT"));
		allViewPo.setCargoInfoVolume(rs.getFloat("CAIN_VOLUME"));
		allViewPo.setCargoInfoLoad(rs.getFloat("CAIN_LOAD"));
		allViewPo.setCargoInfoDesc(rs.getString("CAIN_DESC"));
		allViewPo.setCargoInfoContacts(rs.getString("CAIN_CONTACTS"));
		allViewPo.setCargoInfoContactWay(rs.getString("CAIN_CANTACTWAY"));
		allViewPo.setCargoInfoPicturl(rs.getString("CAIN_PICURL"));
		allViewPo.setCargoInfoFlag(rs.getInt("CAIN_FLAG"));
		
		//2015-6-20 新增 hwj
		allViewPo.setCargoInfoSCity(rs.getString("CAIN_SCITY"));
		allViewPo.setCargoInfoECity(rs.getString("CAIN_ECITY"));
		
		java.util.Date cargoInfoUpdateTime = rs.getTimestamp("UPDATETIME");//为了能够显示时分秒 不适用rs.getDate
		
		allViewPo.setCargoInfoUpdateTime(cargoInfoUpdateTime);
		
		return allViewPo;
	}

}
