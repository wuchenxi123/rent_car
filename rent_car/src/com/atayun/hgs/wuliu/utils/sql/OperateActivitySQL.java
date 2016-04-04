package com.atayun.hgs.wuliu.utils.sql;

public interface OperateActivitySQL {
	//显示最后一条
	public String lastActivity="select * from tb_activity where activity_id=(select max(activity_id) from tb_activity)";
	//插入优惠活动
	public String addActivity="insert into tb_activity (activity_start,activity_end,activity_recharge1,activity_give1,activity_recharge2,activity_give2,activity_recharge3,activity_give3,activity_recharge4,activity_give4)" +" "
	                           +"VALUES(?,?,?,?,?,?,?,?,?,?)";
}
