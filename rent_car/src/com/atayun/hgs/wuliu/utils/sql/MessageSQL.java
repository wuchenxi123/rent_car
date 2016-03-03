package com.atayun.hgs.wuliu.utils.sql;

public interface MessageSQL {

	//根据货主的ID去查看消息
	public String getMyMessagesCargoSQL = "select * from CargoMessageView m where m.CAGO_ID=?";
	
	//根据货主的ID去查看消息
	public String getMyMessagesCarSQL = "select * from CarMessageView m where m.CARO_ID=?";
	
	
}
