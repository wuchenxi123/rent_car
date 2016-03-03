package com.atayun.hgs.wuliu.dao;

import com.atayun.hgs.wuliu.po.MatchRecord;
import com.atayun.hgs.wuliu.po.MatchSupply;

public interface AlgorithmDao {
	
	//根据系统的算法匹配 插入数据库mare
	public int addToMareReturnKey(MatchRecord matchRecord);

	//根据系统的算法匹配 插入数据库masu
	public boolean addToMasu(MatchSupply matchSupply,MatchRecord matchRecord);
}
