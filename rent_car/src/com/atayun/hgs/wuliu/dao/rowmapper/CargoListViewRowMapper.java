package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.CargoListView;

/**
 * 匹配第一条系统查询的视图
 * @author HWJ
 *
 */
public class CargoListViewRowMapper implements RowMapper<CargoListView>{

	public CargoListView mapRow(ResultSet rs, int index) throws SQLException {
		
		CargoListView cargoListView = new CargoListView();
		//cargoListView.setCagoIdecardFlag(rs.getInt("CAGO_IDCARDFLAG"));
		
		cargoListView.setCargoInfoId(rs.getInt("CAIN_ID"));
		cargoListView.setCagoCpName(rs.getString("CAGO_CPNAME"));
		cargoListView.setCagoCpPicFlag(rs.getInt("CAGO_CPPICFLAG"));
		cargoListView.setTransportType(rs.getString("TRTP_TYPE"));
		cargoListView.setCargoType(rs.getString("CATP_TYPE"));
		
		java.util.Date cargoInfoPublished = rs.getTimestamp("CAIN_PUBLISHED");
		//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(cargoInfoPublished);
		cargoListView.setCargoInfoPublished(cargoInfoPublished);
		cargoListView.setCargoInfoStart(rs.getString("CAIN_START"));
		cargoListView.setCargoInfoSStreet(rs.getString("CAIN_SSTREET"));
		cargoListView.setCargoInfoEnd(rs.getString("CAIN_END"));
		cargoListView.setCargoInfoEStreet(rs.getString("CAIN_ESTREET"));
		cargoListView.setCargoInfoLng(rs.getString("CAIN_LNG"));
		cargoListView.setCargoInfoLat(rs.getString("CAIN_LAT"));
		cargoListView.setCargoInfoELng(rs.getString("CAIN_ELNG"));
		cargoListView.setCargoInfoELat(rs.getString("CAIN_ELAT"));
		
		java.util.Date cargoInfoDeliTime = rs.getTimestamp("CAIN_DELITIME");
		
		cargoListView.setCargoInfoDeliTime(cargoInfoDeliTime);
		cargoListView.setCargoInfoPrice(rs.getFloat("CAIN_PRICE"));
		cargoListView.setCargoInfoLenth(rs.getFloat("CAIN_LENGTH"));
		cargoListView.setCargoInfoWidth(rs.getFloat("CAIN_WIDTH"));
		cargoListView.setCargoInfoHeight(rs.getFloat("CAIN_HEIGHT"));
		
		cargoListView.setCargoInfoRlen(rs.getFloat("CAIN_RLEN"));
		cargoListView.setCargoInfoVunit(rs.getString("CAIN_VUNIT"));
		cargoListView.setCargoInfoLunit(rs.getString("CAIN_LUNIT"));
		cargoListView.setCargoInfoVolume(rs.getFloat("CAIN_VOLUME"));
		cargoListView.setCargoInfoLoad(rs.getFloat("CAIN_LOAD"));
		cargoListView.setCargoInfoDesc(rs.getString("CAIN_DESC"));
		cargoListView.setCargoInfoContacts(rs.getString("CAIN_CONTACTS"));
		cargoListView.setCargoInfoContactWay(rs.getString("CAIN_CANTACTWAY"));
		cargoListView.setCargoInfoPicturl(rs.getString("CAIN_PICURL"));
		cargoListView.setCargoInfoFlag(rs.getInt("CAIN_FLAG"));
		cargoListView.setMareId(rs.getInt("mare_id"));
		cargoListView.setMareNum(rs.getInt("mare_num"));
		
		//2015-6-20 新增 hwj
		cargoListView.setCargoInfoSCity(rs.getString("CAIN_SCITY"));
		cargoListView.setCargoInfoECity(rs.getString("CAIN_ECITY"));
		//cargoListView.setMareNum(rs.getInt(""));
		return cargoListView;
	}

	
}
