package com.atayun.hgs.wuliu.po;

import java.util.Date;
/**
 * 行车路线
 * @author chenlei
 *
 */
public class CarRoute {

	private int carrId;//主键
	private int cariId;//TB_CARI表中的CARI_ID（外键）
	private String carrStart;//起始地址
	private String carrEnd;//结束地址
	private Date updateTime;//记录更新时间
	public int getCarrId() {
		return carrId;
	}
	public void setCarrId(int carrId) {
		this.carrId = carrId;
	}
	public int getCariId() {
		return cariId;
	}
	public void setCariId(int cariId) {
		this.cariId = cariId;
	}
	public String getCarrStart() {
		return carrStart;
	}
	public void setCarrStart(String carrStart) {
		this.carrStart = carrStart;
	}
	public String getCarrEnd() {
		return carrEnd;
	}
	public void setCarrEnd(String carrEnd) {
		this.carrEnd = carrEnd;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
