package com.atayun.hgs.wuliu.po;

import java.util.Date;

public class CarInfo {
	private int carId; // 自增ID
	private Date updateTime;// 车辆信息更新时间
	private String carBand;// 车辆类型（品牌）
	private String carColor;//
	private float carPrice;// 购入价格
	private Integer userId;// 车主信息 外键
	private String carLpnum;// 车牌号
	private String carPictUrl;// 车辆图片地址
	private String carContent;// 车辆备注
	private Date carPurTime;// 购车时间
	private float carRentPri;// 租车单价
	private float insurePrice;
	private float scsmPrice;
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCarBand() {
		return carBand;
	}
	public void setCarBand(String carBand) {
		this.carBand = carBand;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public float getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(float carPrice) {
		this.carPrice = carPrice;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCarLpnum() {
		return carLpnum;
	}
	public void setCarLpnum(String carLpnum) {
		this.carLpnum = carLpnum;
	}
	public String getCarPictUrl() {
		return carPictUrl;
	}
	public void setCarPictUrl(String carPictUrl) {
		this.carPictUrl = carPictUrl;
	}
	public String getCarContent() {
		return carContent;
	}
	public void setCarContent(String carContent) {
		this.carContent = carContent;
	}
	public Date getCarPurTime() {
		return carPurTime;
	}
	public void setCarPurTime(Date carPurTime) {
		this.carPurTime = carPurTime;
	}
	public float getCarRentPri() {
		return carRentPri;
	}
	public void setCarRentPri(float carRentPri) {
		this.carRentPri = carRentPri;
	}
	public int getCarFlag() {
		return carFlag;
	}
	public void setCarFlag(int carFlag) {
		this.carFlag = carFlag;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public float getInsurePrice() {
		return insurePrice;
	}
	public void setInsurePrice(float insurePrice) {
		this.insurePrice = insurePrice;
	}
	public float getScsmPrice() {
		return scsmPrice;
	}
	public void setScsmPrice(float scsmPrice) {
		this.scsmPrice = scsmPrice;
	}
	private int carFlag;// 车辆租用标识0：未租出，1：已租出
}
