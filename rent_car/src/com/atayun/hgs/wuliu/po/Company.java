package com.atayun.hgs.wuliu.po;

import java.util.Date;

public class Company {
	
	private Integer userId;//主键，同时TB_USER表中的USER_ID（外键）
	private String compName;//公司名称
	private String compTaxNo;//国税号
	private String compWorkPhone;//公司的工作电话
	private String compCPPicURL;//营业执照照片地址
	private String compSWDJFBURL;//税务登记副本照片地址
	private String compKHXKZURL;//开户许可证照片地址
	private String compXQSWMBURL;//需求税务模板照片地址
	private String compCountry;//国家
	private String compProvice;//省份
	private String compCity;//城市
	private String compStreet;//详细地址
	private Integer inFoFlag;//信息状态(0 未完善；1 已完善；2已认证)
	private Date updateTime;//记录更新时间
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompTaxNo() {
		return compTaxNo;
	}
	public void setCompTaxNo(String compTaxNo) {
		this.compTaxNo = compTaxNo;
	}
	public String getCompWorkPhone() {
		return compWorkPhone;
	}
	public void setCompWorkPhone(String compWorkPhone) {
		this.compWorkPhone = compWorkPhone;
	}
	public String getCompCPPicURL() {
		return compCPPicURL;
	}
	public void setCompCPPicURL(String compCPPicURL) {
		this.compCPPicURL = compCPPicURL;
	}
	public String getCompSWDJFBURL() {
		return compSWDJFBURL;
	}
	public void setCompSWDJFBURL(String compSWDJFBURL) {
		this.compSWDJFBURL = compSWDJFBURL;
	}
	public String getCompKHXKZURL() {
		return compKHXKZURL;
	}
	public void setCompKHXKZURL(String compKHXKZURL) {
		this.compKHXKZURL = compKHXKZURL;
	}
	public String getCompXQSWMBURL() {
		return compXQSWMBURL;
	}
	public void setCompXQSWMBURL(String compXQSWMBURL) {
		this.compXQSWMBURL = compXQSWMBURL;
	}
	public String getCompCountry() {
		return compCountry;
	}
	public void setCompCountry(String compCountry) {
		this.compCountry = compCountry;
	}
	public String getCompProvice() {
		return compProvice;
	}
	public void setCompProvice(String compProvice) {
		this.compProvice = compProvice;
	}
	public String getCompCity() {
		return compCity;
	}
	public void setCompCity(String compCity) {
		this.compCity = compCity;
	}
	public String getCompStreet() {
		return compStreet;
	}
	public void setCompStreet(String compStreet) {
		this.compStreet = compStreet;
	}
	public Integer getInFoFlag() {
		return inFoFlag;
	}
	public void setInFoFlag(Integer inFoFlag) {
		this.inFoFlag = inFoFlag;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
