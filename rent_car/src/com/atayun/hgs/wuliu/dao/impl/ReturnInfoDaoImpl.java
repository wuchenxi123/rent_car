package com.atayun.hgs.wuliu.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.ReturnInfoDao;
import com.atayun.hgs.wuliu.dao.rowmapper.ReturnInfoRowMapper;
import com.atayun.hgs.wuliu.po.ReturnInfo;
import com.atayun.hgs.wuliu.utils.sql.ReturnSQL;
@Repository
public class ReturnInfoDaoImpl implements ReturnInfoDao{
	
	@Autowired
   JdbcTemplate jdbcTemplate=new JdbcTemplate();
   
	public int insertReturnInfo(int userid, String returnPlace, Date reTime) {
		int row=-1;
		row=jdbcTemplate.update(ReturnSQL.insertReturnCarInfoSQL,
				                new Object[]{returnPlace,reTime,userid},
				                new int[]{java.sql.Types.VARCHAR,java.sql.Types.DATE,java.sql.Types.INTEGER});
		if(row>=0){
			int  the_LAST_INSERT_ID=jdbcTemplate.queryForInt("SELECT LAST_INSERT_ID()");
			
			return the_LAST_INSERT_ID ;
		}else{
		    return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public ReturnInfo selectReturnInfo(int returnid) {
		ReturnInfo returninfo=new ReturnInfo();
		ArrayList<ReturnInfo> returnlist=new ArrayList<ReturnInfo>();
		returnlist=(ArrayList<ReturnInfo>)jdbcTemplate.query(ReturnSQL.getAllReturnCarInfoSQL,
                new Object[]{returnid},
                new int[]{java.sql.Types.INTEGER},
                new ReturnInfoRowMapper());
		if(!returnlist.isEmpty()){
			 returninfo=returnlist.get(0);
		}
		return returninfo;
	}

	//修改还车方式状态
	public boolean changeReturnType(int returnid, int returnType) {
		int row=-1;
		row=jdbcTemplate.update(ReturnSQL.changeReturnTypeSQL,
				                new Object[]{returnType,returnid},
				                new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER});
		if(row>=0){
			return true;
		}else{
			return false;
		}
		
	}

}
