package com.atayun.hgs.wuliu.dao;

import java.util.Date;

import com.atayun.hgs.wuliu.po.OperateActivity;


public interface OperateActivityDao {
	//得到最后一条优惠活动
	public OperateActivity lastActivity();
	//添加优惠活动
	public boolean addActivity(Date activityStart,Date activityEnd,float recharge1,float give1,float recharge2,float give2,float recharge3,float give3,float recharge4,float give4  );
}
