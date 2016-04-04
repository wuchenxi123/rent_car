package com.atayun.hgs.wuliu.dao;

import java.util.Date;

import com.atayun.hgs.wuliu.po.ReturnInfo;

public interface ReturnInfoDao {
       //插入还车信息记录
	public int insertReturnInfo(int userid,String returnPlace,Date reTime);
	//根据ID查询租车信息
	public ReturnInfo selectReturnInfo(int returnid);
	//修改还车方式状态
	public boolean changeReturnType(int returnid,int returnType);
}
