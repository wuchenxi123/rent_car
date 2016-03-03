package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CarInformation;

public class CarInformationRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		CarInformation carInformation = new CarInformation();
		carInformation.setCariId(rs.getInt("CARI_ID"));
		carInformation.setCartId(rs.getInt("CART_ID"));
		carInformation.setUserId(rs.getInt("USER_ID"));
		carInformation.setCariLpnum(rs.getString("CARI_LPNUM"));
		carInformation.setCariLength(rs.getFloat("CARI_LENGTH"));
		carInformation.setCariWidth(rs.getFloat("CARI_WIDTH"));
		carInformation.setCariHeight(rs.getFloat("CARI_HEIGHT"));
		carInformation.setCariVolume(rs.getFloat("CARI_VOLUME"));
		carInformation.setCariVunit(rs.getString("CARI_VUNIT"));
		carInformation.setCariRvolumn(rs.getFloat("CARI_RVOLUMN"));
		carInformation.setCariLoad(rs.getFloat("CARI_LOAD"));
		carInformation.setCariLunit(rs.getString("CARI_LUNIT"));
		carInformation.setCariRload(rs.getFloat("CARI_RLOAD"));
		carInformation.setCariDlicUrl(rs.getString("CARI_DLICURL"));
		carInformation.setCariDlicFlag(rs.getInt("CARI_DLICFLAG"));
		carInformation.setCariPicUrl(rs.getString("CARI_PICURL"));
		carInformation.setCariPicFlag(rs.getInt("CARI_PICFLAG"));
		carInformation.setCariFlag(rs.getInt("CARI_FLAG"));
		carInformation.setCariStart(rs.getString("CARI_START"));
		carInformation.setCariScity(rs.getString("CARI_SCITY"));
		carInformation.setCariSstreet(rs.getString("CARI_SSTREET"));
		carInformation.setCariEnd(rs.getString("CARI_END"));
		carInformation.setCariEcity(rs.getString("CARI_ECITY"));
		carInformation.setCariEstreet(rs.getString("CARI_ESTREET"));
		carInformation.setCariLng(rs.getString("CARI_LNG"));
		carInformation.setCariLat(rs.getString("CARI_LAT"));
		carInformation.setCariElng(rs.getString("CARI_ELNG"));
		carInformation.setCariElat(rs.getString("CARI_ELAT"));
		carInformation.setCariOlength(rs.getInt("CARI_OLENGTH"));
		carInformation.setCariOwidth(rs.getInt("CARI_OWIDTH"));
		carInformation.setCariOheight(rs.getInt("CARI_OHEIGHT"));
		carInformation.setCariOload(rs.getInt("CARI_OLOAD"));
		carInformation.setCariRouteNum(rs.getInt("CARI_ROUTENUM"));
		java.util.Date carInfoUpdateTime = rs.getTimestamp("UPDATETIME");//为了能够显示时分秒 不适用rs.getDate
		carInformation.setUpdateTime(carInfoUpdateTime);
		return carInformation;
	}
	
	

}
