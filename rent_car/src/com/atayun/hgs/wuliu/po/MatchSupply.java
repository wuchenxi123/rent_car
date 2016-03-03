package com.atayun.hgs.wuliu.po;

import java.util.Date;

/**
 * 匹配货源
 * @author HWJ
 *
 */
public class MatchSupply {

	private Integer masuId;//自增id
	private Integer mareID;//TB_MARE表中的MARE_ID(外键)
	private Integer cainID;//TB_CAIN表中的CAIN_ID(外键)
	private Date updateTime;//记录更新时间
	public Integer getMasuId() {
		return masuId;
	}
	public void setMasuId(Integer masuId) {
		this.masuId = masuId;
	}
	public Integer getMareID() {
		return mareID;
	}
	public void setMareID(Integer mareID) {
		this.mareID = mareID;
	}
	public Integer getCainID() {
		return cainID;
	}
	public void setCainID(Integer cainID) {
		this.cainID = cainID;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
