package com.atayun.hgs.wuliu.po;

import java.util.Date;
/**
 * 个人货主信息
 * @author HWJ
 *
 */
public class CargoOwner {

	private Integer userId;//主键，同时TB_USER表中的USER_ID（外键）
	private String cagoCountry;//国家
	private String cagoProvince;//省份
	private String cagoCity;//城市
	private String cagoStreet;//详细地址
	private Integer inFoFlag;//信息状态信息状态(0 未完善；1 已完善；2已认证；3正在审核)
	private String cagoCPName;//公司名称
	private String cagoCPPicURL;//公司营业执照图片地址
	private Integer cagoCPPicFlag;//公司营业执照认证状态（0 未认证；1已认证）
	private String cagoMobile;//用户联系电话
	private Date updateTime;//记录更新时间
	
	
	public String getCagoMobile() {
		return cagoMobile;
	}
	public void setCagoMobile(String cagoMobile) {
		this.cagoMobile = cagoMobile;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCagoCountry() {
		return cagoCountry;
	}
	public void setCagoCountry(String cagoCountry) {
		this.cagoCountry = cagoCountry;
	}
	public String getCagoProvince() {
		return cagoProvince;
	}
	public void setCagoProvince(String cagoProvince) {
		this.cagoProvince = cagoProvince;
	}
	public String getCagoCity() {
		return cagoCity;
	}
	public void setCagoCity(String cagoCity) {
		this.cagoCity = cagoCity;
	}
	public String getCagoStreet() {
		return cagoStreet;
	}
	public void setCagoStreet(String cagoStreet) {
		this.cagoStreet = cagoStreet;
	}
	public Integer getInFoFlag() {
		return inFoFlag;
	}
	public void setInFoFlag(Integer inFoFlag) {
		this.inFoFlag = inFoFlag;
	}
	public String getCagoCPName() {
		return cagoCPName;
	}
	public void setCagoCPName(String cagoCPName) {
		this.cagoCPName = cagoCPName;
	}
	public String getCagoCPPicURL() {
		return cagoCPPicURL;
	}
	public void setCagoCPPicURL(String cagoCPPicURL) {
		this.cagoCPPicURL = cagoCPPicURL;
	}
	public Integer getCagoCPPicFlag() {
		return cagoCPPicFlag;
	}
	public void setCagoCPPicFlag(Integer cagoCPPicFlag) {
		this.cagoCPPicFlag = cagoCPPicFlag;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
