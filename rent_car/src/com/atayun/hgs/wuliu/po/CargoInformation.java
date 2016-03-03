package com.atayun.hgs.wuliu.po;

import java.util.Date;

/**
 * 货源信息
 * @author HWJ
 *
 */
public class CargoInformation {

	
	private Integer cargoInfoId;//货源信息ID
	private Integer transportTypeId;//运输类型ID
	private Integer cargoTypeId;//货源类型ID
	private Integer userId;//用户id
	private java.util.Date cargoInfoPublished;//货源信息发布时间
	private String cargoInfoStart;//起点
	private String cargoInfoSStreet;//起点详细地址
	private String cargoInfoEnd;//终点
	private String cargoInfoEStreet;//终点详细地址
	private String cargoInfoLng;//起点货源经度
	private String cargoInfoLat;//起点货源纬度
	private String cargoInfoELng;//终点货源经度
	private String cargoInfoELat;//终点货源纬度
	private java.util.Date cargoInfoDeliTime;//发货时间
	private float cargoInfoPrice;//运价
	private float cargoInfoLenth;//货长
	private float cargoInfoWidth;//货宽
	private float cargoInfoHeight;//货高
	private float cargoInfoRlen;//需要的车长
	private String cargoInfoVunit;//车辆体积单位
	private String cargoInfoLunit;//车辆载重单位
	private float cargoInfoVolume;//货物体积
	private float cargoInfoLoad;//载重（单位：不定）
	private String cargoInfoDesc;//货物描述
	private String cargoInfoContacts;//收货联系人
	private String cargoInfoContactWay;//收货联系方式
	private String cargoInfoPicturl;//货物图片
	private int cargoInfoFlag;//货物的状态（0 未被运输 ； 1 已运输 已运输货物不在编辑）
	private java.util.Date cargoInfoUpdateTime;//货物记录更新时间
	
	
	//新增
	private Integer cartId;//需求车型的ID
	private float cargoRPrice;//最后的议价
	private Integer cargoRPriceFlag;//是否接收议价
	
	//2015-6-20 新增 hwj
	private String cargoInfoSCity;//起点城市
	private String cargoInfoECity;//结束城市
	
	
	public String getCargoInfoSCity() {
		return cargoInfoSCity;
	}
	public void setCargoInfoSCity(String cargoInfoSCity) {
		this.cargoInfoSCity = cargoInfoSCity;
	}
	public String getCargoInfoECity() {
		return cargoInfoECity;
	}
	public void setCargoInfoECity(String cargoInfoECity) {
		this.cargoInfoECity = cargoInfoECity;
	}
	public Integer getCargoRPriceFlag() {
		return cargoRPriceFlag;
	}
	public void setCargoRPriceFlag(Integer cargoRPriceFlag) {
		this.cargoRPriceFlag = cargoRPriceFlag;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public float getCargoRPrice() {
		return cargoRPrice;
	}
	public void setCargoRPrice(float cargoRPrice) {
		this.cargoRPrice = cargoRPrice;
	}
	public float getCargoInfoRlen() {
		return cargoInfoRlen;
	}
	public void setCargoInfoRlen(float cargoInfoRlen) {
		this.cargoInfoRlen = cargoInfoRlen;
	}
	public String getCargoInfoVunit() {
		return cargoInfoVunit;
	}
	public void setCargoInfoVunit(String cargoInfoVunit) {
		this.cargoInfoVunit = cargoInfoVunit;
	}
	public String getCargoInfoLunit() {
		return cargoInfoLunit;
	}
	public void setCargoInfoLunit(String cargoInfoLunit) {
		this.cargoInfoLunit = cargoInfoLunit;
	}
	public String getCargoInfoSStreet() {
		return cargoInfoSStreet;
	}
	public void setCargoInfoSStreet(String cargoInfoSStreet) {
		this.cargoInfoSStreet = cargoInfoSStreet;
	}
	public String getCargoInfoEStreet() {
		return cargoInfoEStreet;
	}
	public void setCargoInfoEStreet(String cargoInfoEStreet) {
		this.cargoInfoEStreet = cargoInfoEStreet;
	}
	public String getCargoInfoELng() {
		return cargoInfoELng;
	}
	public void setCargoInfoELng(String cargoInfoELng) {
		this.cargoInfoELng = cargoInfoELng;
	}
	public String getCargoInfoELat() {
		return cargoInfoELat;
	}
	public void setCargoInfoELat(String cargoInfoELat) {
		this.cargoInfoELat = cargoInfoELat;
	}
	public Integer getCargoInfoId() {
		return cargoInfoId;
	}
	public void setCargoInfoId(Integer cargoInfoId) {
		this.cargoInfoId = cargoInfoId;
	}
	public Integer getTransportTypeId() {
		return transportTypeId;
	}
	public void setTransportTypeId(Integer transportTypeId) {
		this.transportTypeId = transportTypeId;
	}
	public Integer getCargoTypeId() {
		return cargoTypeId;
	}
	public void setCargoTypeId(Integer cargoTypeId) {
		this.cargoTypeId = cargoTypeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getCargoInfoStart() {
		return cargoInfoStart;
	}
	public void setCargoInfoStart(String cargoInfoStart) {
		this.cargoInfoStart = cargoInfoStart;
	}
	public String getCargoInfoEnd() {
		return cargoInfoEnd;
	}
	public void setCargoInfoEnd(String cargoInfoEnd) {
		this.cargoInfoEnd = cargoInfoEnd;
	}
	public String getCargoInfoLng() {
		return cargoInfoLng;
	}
	public void setCargoInfoLng(String cargoInfoLng) {
		this.cargoInfoLng = cargoInfoLng;
	}
	public String getCargoInfoLat() {
		return cargoInfoLat;
	}
	public void setCargoInfoLat(String cargoInfoLat) {
		this.cargoInfoLat = cargoInfoLat;
	}
	
	public float getCargoInfoPrice() {
		return cargoInfoPrice;
	}
	public void setCargoInfoPrice(float cargoInfoPrice) {
		this.cargoInfoPrice = cargoInfoPrice;
	}
	public float getCargoInfoLenth() {
		return cargoInfoLenth;
	}
	public void setCargoInfoLenth(float cargoInfoLenth) {
		this.cargoInfoLenth = cargoInfoLenth;
	}
	public float getCargoInfoWidth() {
		return cargoInfoWidth;
	}
	public void setCargoInfoWidth(float cargoInfoWidth) {
		this.cargoInfoWidth = cargoInfoWidth;
	}
	public float getCargoInfoHeight() {
		return cargoInfoHeight;
	}
	public void setCargoInfoHeight(float cargoInfoHeight) {
		this.cargoInfoHeight = cargoInfoHeight;
	}
	public float getCargoInfoVolume() {
		return cargoInfoVolume;
	}
	public void setCargoInfoVolume(float cargoInfoVolume) {
		this.cargoInfoVolume = cargoInfoVolume;
	}
	public float getCargoInfoLoad() {
		return cargoInfoLoad;
	}
	public void setCargoInfoLoad(float cargoInfoLoad) {
		this.cargoInfoLoad = cargoInfoLoad;
	}
	public String getCargoInfoDesc() {
		return cargoInfoDesc;
	}
	public void setCargoInfoDesc(String cargoInfoDesc) {
		this.cargoInfoDesc = cargoInfoDesc;
	}
	public String getCargoInfoContacts() {
		return cargoInfoContacts;
	}
	public void setCargoInfoContacts(String cargoInfoContacts) {
		this.cargoInfoContacts = cargoInfoContacts;
	}
	public String getCargoInfoContactWay() {
		return cargoInfoContactWay;
	}
	public void setCargoInfoContactWay(String cargoInfoContactWay) {
		this.cargoInfoContactWay = cargoInfoContactWay;
	}
	public String getCargoInfoPicturl() {
		return cargoInfoPicturl;
	}
	public void setCargoInfoPicturl(String cargoInfoPicturl) {
		this.cargoInfoPicturl = cargoInfoPicturl;
	}
	public int getCargoInfoFlag() {
		return cargoInfoFlag;
	}
	public void setCargoInfoFlag(int cargoInfoFlag) {
		this.cargoInfoFlag = cargoInfoFlag;
	}
	public java.util.Date getCargoInfoPublished() {
		return cargoInfoPublished;
	}
	public void setCargoInfoPublished(java.util.Date cargoInfoPublished) {
		this.cargoInfoPublished = cargoInfoPublished;
	}
	public java.util.Date getCargoInfoDeliTime() {
		return cargoInfoDeliTime;
	}
	public void setCargoInfoDeliTime(java.util.Date cargoInfoDeliTime) {
		this.cargoInfoDeliTime = cargoInfoDeliTime;
	}
	public java.util.Date getCargoInfoUpdateTime() {
		return cargoInfoUpdateTime;
	}
	public void setCargoInfoUpdateTime(java.util.Date cargoInfoUpdateTime) {
		this.cargoInfoUpdateTime = cargoInfoUpdateTime;
	}
	
	
	
}
