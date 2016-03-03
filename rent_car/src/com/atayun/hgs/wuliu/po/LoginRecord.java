package com.atayun.hgs.wuliu.po;

import java.util.Date;

public class LoginRecord {

	private Integer uLREId;//登录记录表ID
	private Integer userId;//tb_user中的外键
	private Date uLRELandTime;//用户登陆时间
	private String uLREIp;//用户登陆IP
	private String uLREIIMEI;//用户手机的IMEI码
	private Integer uLREFlag;//用户登陆的标志
	public Integer getuLREId() {
		return uLREId;
	}
	public void setuLREId(Integer uLREId) {
		this.uLREId = uLREId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getuLRELandTime() {
		return uLRELandTime;
	}
	public void setuLRELandTime(Date uLRELandTime) {
		this.uLRELandTime = uLRELandTime;
	}
	public String getuLREIp() {
		return uLREIp;
	}
	public void setuLREIp(String uLREIp) {
		this.uLREIp = uLREIp;
	}
	public String getuLREIIMEI() {
		return uLREIIMEI;
	}
	public void setuLREIIMEI(String uLREIIMEI) {
		this.uLREIIMEI = uLREIIMEI;
	}
	public Integer getuLREFlag() {
		return uLREFlag;
	}
	public void setuLREFlag(Integer uLREFlag) {
		this.uLREFlag = uLREFlag;
	}
}
