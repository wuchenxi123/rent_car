package com.atayun.hgs.wuliu.po;

import java.util.Date;
/**
 * 车主信息表
 * @author chenlei
 *
 */
public class CarOwner {

	private int userId;//主键，同时TB_USER表中的user_id（外键）
	private String caroMobile;//车主联系号码（默认把TB_USER的手机号存入）
	private String caroDlicpicurl;//车主驾驶证图片地址
	private int caroDlicpicflag;//车主证件认证状态（0：未认证；1：已认证）
	private String caroCpName;//公司名称
	private int infoFlag;//信息状态(0 未完善；1 已完善；2已认证；3正在审核)
	private Date updateTime;//记录更新时间
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCaroDlicpicurl() {
		return caroDlicpicurl;
	}
	public void setCaroDlicpicurl(String caroDlicpicurl) {
		this.caroDlicpicurl = caroDlicpicurl;
	}
	public int getCaroDlicpicflag() {
		return caroDlicpicflag;
	}
	public void setCaroDlicpicflag(int caroDlicpicflag) {
		this.caroDlicpicflag = caroDlicpicflag;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCaroCpName() {
		return caroCpName;
	}
	public void setCaroCpName(String caroCpName) {
		this.caroCpName = caroCpName;
	}
	public int getInfoFlag() {
		return infoFlag;
	}
	public void setInfoFlag(int infoFlag) {
		this.infoFlag = infoFlag;
	}
	public String getCaroMobile() {
		return caroMobile;
	}
	public void setCaroMobile(String caroMobile) {
		this.caroMobile = caroMobile;
	}
	
}
