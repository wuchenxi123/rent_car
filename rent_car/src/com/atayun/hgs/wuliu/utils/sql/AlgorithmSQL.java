package com.atayun.hgs.wuliu.utils.sql;

public interface AlgorithmSQL {

	//添加系统匹配表
	public String addToMareSQL="insert into tb_mare(CARI_ID,MARE_PRICE,MARE_VOLUMN,MARE_LOAD,MARE_RVOLUMN,MARE_RLOAD,MARE_RATE,MARE_NUM,MARE_FLAG,UPDATETIME)" +
								"values(?,?,?,?,?,?,?,?,?,sysdate())";
	//添加系统匹配货源
	public String addToMasuSQL="insert into tb_masu(MARE_ID,CAIN_ID,UPDATETIME) values(?,?,sysdate())";
}
