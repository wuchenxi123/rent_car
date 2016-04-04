package com.atayun.hgs.wuliu.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.OperateActivityDao;
import com.atayun.hgs.wuliu.dao.rowmapper.OperateActivityMapper;
import com.atayun.hgs.wuliu.po.OperateActivity;
import com.atayun.hgs.wuliu.utils.sql.OperateActivitySQL;


@Repository
public class OperateActivityImpl implements OperateActivityDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//得到最后一条优惠活动
	@SuppressWarnings("unchecked")
	public OperateActivity lastActivity() {
		// TODO Auto-generated method stub
		ArrayList<OperateActivity> operateActivityList=new ArrayList<OperateActivity>();
		OperateActivity operateActivity=new OperateActivity();
		operateActivityList=(ArrayList<OperateActivity>) jdbcTemplate.query(OperateActivitySQL.lastActivity,
															new Object[]{},
															new int[]{},
															new OperateActivityMapper());
		if(!operateActivityList.isEmpty()){
			operateActivity=operateActivityList.get(0);
		}
		return operateActivity;
	}
	
	//添加优惠活动
	public boolean addActivity(Date activityStart, Date activityEnd,
			float recharge1, float give1, float recharge2, float give2,
			float recharge3, float give3, float recharge4, float give4) {
		// TODO Auto-generated method stub
		int bool=-1;
		bool=jdbcTemplate.update(OperateActivitySQL.addActivity,
								new Object[]{activityStart,activityEnd,recharge1,give1,recharge2,give2,recharge3,give3,recharge4,give4},
								new int[]{java.sql.Types.DATE,java.sql.Types.DATE,java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT}
								);
		if(bool!=-1){
			return true;
		}
		return false;
	}

}
