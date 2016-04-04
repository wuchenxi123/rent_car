package com.atayun.hgs.wuliu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atayun.hgs.wuliu.dao.OperateActivityDao;
import com.atayun.hgs.wuliu.po.OperateActivity;

@Transactional
@Service
public class OperateActivityService {
	@Autowired
	private OperateActivityDao operateActivityDao;
	//得到最后一条优惠活动
	public OperateActivity lastActivity(){
		return operateActivityDao.lastActivity();
	}
	//添加优惠活动
	public boolean addActivity(Date activityStart,Date activityEnd,float recharge1,float give1,float recharge2,float give2,float recharge3,float give3,float recharge4,float give4  ){
		return operateActivityDao.addActivity(activityStart, activityEnd, recharge1, give1, recharge2, give2, recharge3, give3, recharge4, give4);
	}

}
