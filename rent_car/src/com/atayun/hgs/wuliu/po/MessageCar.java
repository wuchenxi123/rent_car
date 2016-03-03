package com.atayun.hgs.wuliu.po;

import java.util.Date;
/**
 * 车主看到的消息
 * @author HWJ
 *
 */
public class MessageCar {
	
	private String compName;//公司名称
	private String compWorkPhone;//公司的工作电话
	private String compProvice;//省份
	private String compCity;//城市
	private String compStreet;//详细地址
	private Integer compFlag;//信息状态(0 未完善；1 已完善；2已认证)
	
	private Integer cagoFlag;//信息状态信息状态(0 未完善；1 已完善；2已认证；3正在审核)
	private String cagoMobile;//用户联系电话
	
	private Integer cargoInfoId;//货源信息ID
	private int cargoInfoFlag;//货物的状态（0 未被运输 ； 1 已运输 已运输货物不在编辑）
	private String cargoInfoDesc;//货物描述
	
	private float   pricPrice;//价格  
	private String pricMark;//备注
	private Date pricUpdateTime;//记录更新时间
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompWorkPhone() {
		return compWorkPhone;
	}
	public void setCompWorkPhone(String compWorkPhone) {
		this.compWorkPhone = compWorkPhone;
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
	public Integer getCompFlag() {
		return compFlag;
	}
	public void setCompFlag(Integer compFlag) {
		this.compFlag = compFlag;
	}
	public Integer getCagoFlag() {
		return cagoFlag;
	}
	public void setCagoFlag(Integer cagoFlag) {
		this.cagoFlag = cagoFlag;
	}
	public String getCagoMobile() {
		return cagoMobile;
	}
	public void setCagoMobile(String cagoMobile) {
		this.cagoMobile = cagoMobile;
	}
	public Integer getCargoInfoId() {
		return cargoInfoId;
	}
	public void setCargoInfoId(Integer cargoInfoId) {
		this.cargoInfoId = cargoInfoId;
	}
	public int getCargoInfoFlag() {
		return cargoInfoFlag;
	}
	public void setCargoInfoFlag(int cargoInfoFlag) {
		this.cargoInfoFlag = cargoInfoFlag;
	}
	public String getCargoInfoDesc() {
		return cargoInfoDesc;
	}
	public void setCargoInfoDesc(String cargoInfoDesc) {
		this.cargoInfoDesc = cargoInfoDesc;
	}
	public float getPricPrice() {
		return pricPrice;
	}
	public void setPricPrice(float pricPrice) {
		this.pricPrice = pricPrice;
	}
	public String getPricMark() {
		return pricMark;
	}
	public void setPricMark(String pricMark) {
		this.pricMark = pricMark;
	}
	public Date getPricUpdateTime() {
		return pricUpdateTime;
	}
	public void setPricUpdateTime(Date pricUpdateTime) {
		this.pricUpdateTime = pricUpdateTime;
	}
}
