package com.atayun.hgs.wuliu.po;

import java.util.Date;

/**
 * 系统匹配表
 * @author HWJ
 *
 */
public class MatchRecord {

	private Integer mareID;//自增id
	private Integer cariId;//TB_CARI表中的CARI_ID(外键)
	private float marePrice;//匹配的总价格
	private float mareVolumn;//匹配的总体积（单位：方）
	private float mareLoad;//匹配的总重量（单位：顿）
	private float mareRVolumn;//车辆剩余空间（单位：方）
	private float mareRLoad;//车辆剩余载重（单位：顿）
	private float mareRate;//匹配的比率
	private Integer mareNum;//匹配的货源数
	private Integer mareFlag;//匹配种类标识（0：整体匹配；1：单个货源）
	private Date updateTime;//记录更新时间
	
	public Integer getMareID() {
		return mareID;
	}
	public void setMareID(Integer mareID) {
		this.mareID = mareID;
	}
	public Integer getCariId() {
		return cariId;
	}
	public void setCariId(Integer cariId) {
		this.cariId = cariId;
	}
	public float getMarePrice() {
		return marePrice;
	}
	public void setMarePrice(float marePrice) {
		this.marePrice = marePrice;
	}
	public float getMareVolumn() {
		return mareVolumn;
	}
	public void setMareVolumn(float mareVolumn) {
		this.mareVolumn = mareVolumn;
	}
	public float getMareLoad() {
		return mareLoad;
	}
	public void setMareLoad(float mareLoad) {
		this.mareLoad = mareLoad;
	}
	public float getMareRVolumn() {
		return mareRVolumn;
	}
	public void setMareRVolumn(float mareRVolumn) {
		this.mareRVolumn = mareRVolumn;
	}
	public float getMareRLoad() {
		return mareRLoad;
	}
	public void setMareRLoad(float mareRLoad) {
		this.mareRLoad = mareRLoad;
	}
	public float getMareRate() {
		return mareRate;
	}
	public void setMareRate(float mareRate) {
		this.mareRate = mareRate;
	}
	public Integer getMareNum() {
		return mareNum;
	}
	public void setMareNum(Integer mareNum) {
		this.mareNum = mareNum;
	}
	public Integer getMareFlag() {
		return mareFlag;
	}
	public void setMareFlag(Integer mareFlag) {
		this.mareFlag = mareFlag;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
