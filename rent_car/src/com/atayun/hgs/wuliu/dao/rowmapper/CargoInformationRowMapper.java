package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CargoInformation;

public class CargoInformationRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CargoInformation cargoInformation = new CargoInformation();
		cargoInformation.setCargoInfoId(rs.getInt("CAIN_ID"));
		cargoInformation.setTransportTypeId(rs.getInt("TRTP_ID"));
		cargoInformation.setCargoTypeId(rs.getInt("CATP_ID"));
		cargoInformation.setUserId(rs.getInt("USER_ID"));
		
		java.util.Date cargoInfoPublished = rs.getTimestamp("CAIN_PUBLISHED");
		//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(cargoInfoPublished);
		cargoInformation.setCargoInfoPublished(cargoInfoPublished);
		cargoInformation.setCargoInfoStart(rs.getString("CAIN_START"));
		
		cargoInformation.setCargoInfoSStreet(rs.getString("CAIN_SSTREET"));//起点详细信息
		
		cargoInformation.setCargoInfoEnd(rs.getString("CAIN_END"));
		
		cargoInformation.setCargoInfoEStreet(rs.getString("CAIN_ESTREET"));//终点详细信息
		
		cargoInformation.setCargoInfoLng(rs.getString("CAIN_LNG"));
		cargoInformation.setCargoInfoLat(rs.getString("CAIN_LAT"));
		cargoInformation.setCargoInfoELng(rs.getString("CAIN_ELAT"));
		cargoInformation.setCargoInfoELat(rs.getString("CAIN_ELAT"));
		
		java.util.Date cargoInfoDeliTime = rs.getTimestamp("CAIN_DELITIME");
		
		cargoInformation.setCargoInfoDeliTime(cargoInfoDeliTime);
		cargoInformation.setCargoInfoPrice(rs.getFloat("CAIN_PRICE"));
		cargoInformation.setCargoInfoLenth(rs.getFloat("CAIN_LENGTH"));
		cargoInformation.setCargoInfoWidth(rs.getFloat("CAIN_WIDTH"));
		cargoInformation.setCargoInfoHeight(rs.getFloat("CAIN_HEIGHT"));
		cargoInformation.setCargoInfoRlen(rs.getFloat("CAIN_RLEN"));
		cargoInformation.setCargoInfoVunit(rs.getString("CAIN_VUNIT"));
		cargoInformation.setCargoInfoLunit(rs.getString("CAIN_LUNIT"));
		cargoInformation.setCargoInfoVolume(rs.getFloat("CAIN_VOLUME"));
		cargoInformation.setCargoInfoLoad(rs.getFloat("CAIN_LOAD"));
		cargoInformation.setCargoInfoDesc(rs.getString("CAIN_DESC"));
		cargoInformation.setCargoInfoContacts(rs.getString("CAIN_CONTACTS"));
		cargoInformation.setCargoInfoContactWay(rs.getString("CAIN_CANTACTWAY"));
		cargoInformation.setCargoInfoPicturl(rs.getString("CAIN_PICURL"));
		cargoInformation.setCargoInfoFlag(rs.getInt("CAIN_FLAG"));
		
		cargoInformation.setCartId(rs.getInt("CART_ID"));
		cargoInformation.setCargoRPrice(rs.getFloat("CAIN_RPRICE"));
		cargoInformation.setCargoRPriceFlag(rs.getInt("CAIN_RPRICEFLAG"));
		java.util.Date cargoInfoUpdateTime = rs.getTimestamp("UPDATETIME");//为了能够显示时分秒 不适用rs.getDate
		
		//2015-6-20 新增 hwj
		cargoInformation.setCargoInfoSCity(rs.getString("CAIN_SCITY"));
		cargoInformation.setCargoInfoECity(rs.getString("CAIN_ECITY"));
		
		cargoInformation.setCargoInfoUpdateTime(cargoInfoUpdateTime);
		return cargoInformation;
	}
	
	

}
