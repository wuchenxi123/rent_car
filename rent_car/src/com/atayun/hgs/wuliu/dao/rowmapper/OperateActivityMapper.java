package com.atayun.hgs.wuliu.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atayun.hgs.wuliu.po.OperateActivity;


@SuppressWarnings("rawtypes")
public class OperateActivityMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		OperateActivity operateActivity=new OperateActivity();
		operateActivity.setActivityId(rs.getInt("activity_id"));
		operateActivity.setActivityStart(rs.getDate("activity_start"));
		operateActivity.setActivityEnd(rs.getDate("activity_end"));
		operateActivity.setActivityRecharge1(rs.getFloat("activity_recharge1"));
		operateActivity.setActivityGive1(rs.getFloat("activity_give1"));
		operateActivity.setActivityRecharge2(rs.getFloat("activity_recharge2"));
		operateActivity.setActivityGive2(rs.getFloat("activity_give2"));
		operateActivity.setActivityRecharge3(rs.getFloat("activity_recharge3"));
		operateActivity.setActivityGive3(rs.getFloat("activity_give3"));
		operateActivity.setActivityRecharge4(rs.getFloat("activity_recharge4"));
		operateActivity.setActivityGive4(rs.getFloat("activity_give4"));
		return operateActivity;
	}

}
