package com.atayun.hgs.wuliu.po;

import java.util.Date;

public class BestMatch {

	private Integer userId;//车主的userID
	private String trtpType;//货物的运输类型
	private String catpType;//货物的类型
	private Integer mareId;//系统匹配表的ID
	private Date cainPublished;//货物发布时间
	private Date minCainDelitime;//最早发货时间
	private String cainStart;//出发地
	private String cainEnd;//目的地
	private Integer mareNum;//匹配的货源数
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTrtpType() {
		return trtpType;
	}
	public void setTrtpType(String trtpType) {
		this.trtpType = trtpType;
	}
	public String getCatpType() {
		return catpType;
	}
	public void setCatpType(String catpType) {
		this.catpType = catpType;
	}
	public Integer getMareId() {
		return mareId;
	}
	public void setMareId(Integer mareId) {
		this.mareId = mareId;
	}
	public Date getCainPublished() {
		return cainPublished;
	}
	public void setCainPublished(Date cainPublished) {
		this.cainPublished = cainPublished;
	}
	public Date getMinCainDelitime() {
		return minCainDelitime;
	}
	public void setMinCainDelitime(Date minCainDelitime) {
		this.minCainDelitime = minCainDelitime;
	}
	public String getCainStart() {
		return cainStart;
	}
	public void setCainStart(String cainStart) {
		this.cainStart = cainStart;
	}
	public String getCainEnd() {
		return cainEnd;
	}
	public void setCainEnd(String cainEnd) {
		this.cainEnd = cainEnd;
	}
	public Integer getMareNum() {
		return mareNum;
	}
	public void setMareNum(Integer mareNum) {
		this.mareNum = mareNum;
	}
}
