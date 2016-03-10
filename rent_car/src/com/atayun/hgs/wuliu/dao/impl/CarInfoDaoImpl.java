package com.atayun.hgs.wuliu.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.CarInfoDao;
import com.atayun.hgs.wuliu.dao.rowmapper.CarInfoRowMapper;
import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.utils.sql.CarSQL;
@Repository
public class CarInfoDaoImpl implements CarInfoDao{

	/** 这个参数是为了通过Spring的注解方式，得到Spring的jdbc模板，前提条件是在Spring的配置文件中，配置模板 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	/**
	 * 获取car信息
	 */
	@SuppressWarnings("unchecked")
	public CarInfo getAllCarInfo(int carid) {
		CarInfo car=new CarInfo();
		ArrayList<CarInfo> carlist=new ArrayList<CarInfo>();
		carlist=(ArrayList<CarInfo>)jdbcTemplate.query(CarSQL.getAllCarInfoByIdSQL,
				                                        new Object[]{carid}, 
				                                        new int[]{java.sql.Types.INTEGER},
				                                        new CarInfoRowMapper());
		if(!carlist.isEmpty()){
			car=carlist.get(0);
		}
		return car;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<CarInfo> getAllCarInfo() {
		ArrayList<CarInfo> carlist=new ArrayList<CarInfo>();
		carlist=(ArrayList<CarInfo>)jdbcTemplate.query(CarSQL.getAllCarInfoSQL,
				                                        new Object[]{}, 
				                                        new int[]{},
				                                        new CarInfoRowMapper());
		return carlist;
	}
	@SuppressWarnings("unchecked")
	public boolean setCarBusy(int carid,int flag) {
		int rowchange=-1;
		rowchange=jdbcTemplate.update(CarSQL.changeCarStatusSQL,new  Object[]{flag,carid}, 
				                new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER}
				                );
		if(rowchange>=0){
			return true;  //设置成功
		}else
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public CarInfo getCarBybandAndLpnum(String band, String lpnum) {
		CarInfo carinfo=new CarInfo();
		ArrayList<CarInfo> carlist=new ArrayList<CarInfo>();
		carlist=(ArrayList<CarInfo>)jdbcTemplate.query(CarSQL.getCarInfoByBandAndLpnumSQL,
				                                        new Object[]{band,lpnum}, 
				                                        new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR},
				                                        new CarInfoRowMapper());
		if(!carlist.isEmpty()){
			carinfo=carlist.get(0);
		}
		return carinfo;
	}
	public boolean changeCarTakeType(int carid, int taketype) {
		int rowchange=-1;
		rowchange=jdbcTemplate.update(CarSQL.changeCarTakeTypeSQL,new  Object[]{taketype,carid}, 
				                new int[]{java.sql.Types.VARCHAR,java.sql.Types.INTEGER}
				                );
		if(rowchange>=0){
			return true;  //设置成功
		}else
		return false;
		
	}

}
