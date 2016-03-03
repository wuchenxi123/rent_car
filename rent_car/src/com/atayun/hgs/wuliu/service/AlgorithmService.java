package com.atayun.hgs.wuliu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atayun.hgs.wuliu.dao.AlgorithmDao;
import com.atayun.hgs.wuliu.po.MatchRecord;
import com.atayun.hgs.wuliu.po.MatchSupply;

@Service
public class AlgorithmService {

	@Autowired
	private AlgorithmDao algorithmDao;
	
	/**
	 * 添加匹配系统表
	 * @param matchRecord
	 * @param matchSupply
	 * @return
	 */
	public boolean addToMareAndMasu(MatchRecord matchRecord,MatchSupply matchSupply) {
		boolean flag = false;
		boolean addMasuFlag = false;
		addMasuFlag = algorithmDao.addToMasu(matchSupply, matchRecord);
		if(addMasuFlag){
			flag = true;
		}
		return flag;
	}
}
