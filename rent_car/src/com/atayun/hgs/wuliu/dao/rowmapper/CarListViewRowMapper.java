package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import sun.security.jca.GetInstance;

import com.atayun.hgs.wuliu.po.CarListView;

public class CarListViewRowMapper implements RowMapper{

public Object mapRow(ResultSet rs, int index) throws SQLException {
		
		CarListView carListView=new CarListView();
		
		carListView.setCariId(rs.getInt("cari_id"));
		carListView.setCariLpnum(rs.getString("cari_lpnum"));
		carListView.setCariLength(rs.getFloat("cari_length"));
		carListView.setCariWidth(rs.getFloat("cari_width"));
		carListView.setCariHeight(rs.getFloat("cari_height"));
		carListView.setCariVolume(rs.getFloat("cari_volume"));
		carListView.setCariVunit(rs.getString("CARI_VUNIT"));
		carListView.setCariRvolumn(rs.getFloat("CARI_RVOLUMN"));
		carListView.setCariLoad(rs.getFloat("CARI_LOAD"));
		carListView.setCariLunit(rs.getString("CARI_LUNIT"));
		carListView.setCariRload(rs.getFloat("CARI_RLOAD"));
		carListView.setCariDlicUrl(rs.getString("cari_dlicurl"));
		carListView.setCariDlicFlag(rs.getInt("cari_dlicflag"));
		carListView.setCariPicUrl(rs.getString("cari_picurl"));
		carListView.setCariPicFlag(rs.getInt("cari_picflag"));
		carListView.setCariFlag(rs.getInt("cari_flag"));
		carListView.setCariStart(rs.getString("CARI_START"));
		carListView.setCariScity(rs.getString("CARI_SCITY"));
		carListView.setCariSstreet(rs.getString("CARI_SSTREET"));
		carListView.setCariEnd(rs.getString("CARI_END"));
		carListView.setCariEcity(rs.getString("CARI_ECITY"));
		carListView.setCariEstreet(rs.getString("CARI_ESTREET"));
		carListView.setCariLng(rs.getString("CARI_LNG"));
		carListView.setCariLat(rs.getString("CARI_LAT"));
		carListView.setCariElng(rs.getString("CARI_ELNG"));
		carListView.setCariElat(rs.getString("CARI_ELAT"));
		carListView.setCariOlength(rs.getInt("CARI_OLENGTH"));
		carListView.setCariOwidth(rs.getInt("CARI_OWIDTH"));
		carListView.setCariOheight(rs.getInt("CARI_OHEIGHT"));
		carListView.setCariOload(rs.getInt("CARI_OLOAD"));
		
		carListView.setCariRouteNum(rs.getInt("cari_routenum"));
		java.util.Date carListViewUpdatetime=rs.getTimestamp("UPDATETIME");
		carListView.setUpdateTime(carListViewUpdatetime);
		
		carListView.setCartId(rs.getInt("cart_id"));
		carListView.setCartType(rs.getString("cart_type"));
		
		carListView.setUserId(rs.getInt("user_id"));
		carListView.setCaroDlicpicurl(rs.getString("caro_dlicpicurl"));
		carListView.setCaroDlicpicflag(rs.getInt("caro_dlicpicflag"));
		carListView.setCaroCpName(rs.getString("caro_cpname"));
		carListView.setInfoFlag(rs.getInt("InfoFlag"));
		carListView.setCaroMobile(rs.getString("caro_Mobile"));
		
		carListView.setUserName(rs.getString("user_name"));
		carListView.setUserMobile(rs.getString("user_mobile"));
		carListView.setUserAddr(rs.getString("user_addr"));
		carListView.setUserPicurl(rs.getString("user_picurl"));
		carListView.setUserRegTime(rs.getTimestamp("user_regtime"));
		carListView.setUserFlag(rs.getInt("user_flag"));
		return carListView;
	}	

}
