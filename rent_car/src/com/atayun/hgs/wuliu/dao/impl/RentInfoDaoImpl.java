package com.atayun.hgs.wuliu.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.RentInfoDao;
import com.atayun.hgs.wuliu.dao.rowmapper.RentInfoRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.UserRowMapper;

import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.utils.sql.RentCarSQL;

@Repository
public class RentInfoDaoImpl implements RentInfoDao{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
//添加租车信息
	public int creatRentInfo(String takePlace, int days, Date takeTime,String lpnum, String band,int userid) {
		int row=-1;
		  row= jdbcTemplate.update(RentCarSQL.insertCarRentSQL,
				            new Object[]{takePlace,days,takeTime,band,lpnum,userid},
				            new int[]{java.sql.Types.VARCHAR,java.sql.Types.INTEGER,java.sql.Types.DATE,
				                      java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types. INTEGER}
				            );
		  if(row>=0){
			  //SELECT LAST_INSERT_ID()这个语句可以获取刚刚插入的数据自增的id
					int  the_LAST_INSERT_ID=jdbcTemplate.queryForInt("SELECT LAST_INSERT_ID()");
					
					return the_LAST_INSERT_ID ;
			}else
				return -1;	
	}

	
//增加部分租车信息	返回该信息ID 操作失败则返回-1
	public int insertRentInfo(String place, int days, Date takeTime) {
		//KeyHolder keyHolder = new GeneratedKeyHolder();
		int row=-1;
		  row= jdbcTemplate.update(RentCarSQL.insertPartCarRentSQL,
				            new Object[]{place,days,takeTime},
				            new int[]{java.sql.Types.VARCHAR,java.sql.Types.INTEGER,java.sql.Types.DATE }
				            );
		  if(row>=0){
			  //SELECT LAST_INSERT_ID()这个语句可以获取刚刚插入的数据自增的id
					int  the_LAST_INSERT_ID=jdbcTemplate.queryForInt("SELECT LAST_INSERT_ID()");
					
					return the_LAST_INSERT_ID ;
			}else
				return -1;	
		  }
	
//完善租车信息 选好车辆后填入车辆类型和车牌号
	public int improveRentInfo(int rentid, String lpnum,String band) {
		int row=-1;
		  row= jdbcTemplate.update(RentCarSQL.improveCarRentSQL,
				            new Object[]{band,lpnum,rentid},
				            new int[]{java.sql.Types.VARCHAR,java.sql.Types.INTEGER,java.sql.Types.INTEGER }
				            );
		  if(row>=0){
			  //SELECT LAST_INSERT_ID()这个语句可以获取刚刚插入的数据自增的id
					int  the_LAST_INSERT_ID=jdbcTemplate.queryForInt("SELECT LAST_INSERT_ID()");
					
					return the_LAST_INSERT_ID ;
			}else
				return -1;
	}

	//获取被租车辆的司机信息
	@SuppressWarnings("unchecked")
	public User getDriverInfo(int userid) {
		User driver=new User();
		ArrayList<User> users=new ArrayList<User>();
		users=(ArrayList<User>)jdbcTemplate.query(RentCarSQL.getDriverByUserId,
				                                  new Object[]{userid},
				                                  new int[]{java.sql.Types.INTEGER},
				                                  new UserRowMapper());
		if(users.size()!=0){
			driver=users.get(0);
			return driver;
		}
		return null;
	}

	
	
	

}
