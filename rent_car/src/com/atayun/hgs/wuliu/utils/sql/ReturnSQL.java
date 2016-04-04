package com.atayun.hgs.wuliu.utils.sql;

public interface ReturnSQL {
	//根据车辆id获取还车信息
     public String getAllReturnCarInfoSQL="select * from tb_returninfo where return_id=?";
  //插入还车信息
     public String insertReturnCarInfoSQL="insert into tb_returninfo(return_place,return_reTime,user_id) values(?,?,?)";
   //修改还车方式状态
     public String changeReturnTypeSQL="update tb_returninfo set returnType=? where return_id=?";
}
