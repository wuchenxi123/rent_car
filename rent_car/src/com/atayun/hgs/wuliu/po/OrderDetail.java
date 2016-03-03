package com.atayun.hgs.wuliu.po;

import java.util.Date;

public class OrderDetail {

	private Integer orddId;//订单详情的唯一标识ID
	private Integer orderId;//订单的唯一标识ID
	private Integer cariId;//车辆的唯一标识
	private Integer orddFlag;//订单的状态标识
	private float orddprice;//订单条目的价格
	private float orddCSUBSPRICE;//车主此货物的保证金
	private float orddHSUBSPRICE;//货主此货物的保证金
	private Date orddUpdateTime;//订单详情的更新时间
	
	private String orderNo;//订单号
	private float orderPrice;//订单总价格
	
	private Integer cargoInfoId;//货源信息ID
	private Integer cargoTypeId;//货源类型ID
	private String cargoInfoStart;//起点
	private String cargoInfoSStreet;//起点详细地址
	private String cargoInfoEnd;//终点
	private String cargoInfoEStreet;//终点详细地址
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
	
	private String carLpNum;//车辆的车牌
	
	
	public String getCarLpNum() {
		return carLpNum;
	}
	public void setCarLpNum(String carLpNum) {
		this.carLpNum = carLpNum;
	}
	public Integer getOrddId() {
		return orddId;
	}
	public void setOrddId(Integer orddId) {
		this.orddId = orddId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCariId() {
		return cariId;
	}
	public void setCariId(Integer cariId) {
		this.cariId = cariId;
	}
	public Integer getOrddFlag() {
		return orddFlag;
	}
	public void setOrddFlag(Integer orddFlag) {
		this.orddFlag = orddFlag;
	}
	public float getOrddprice() {
		return orddprice;
	}
	public void setOrddprice(float orddprice) {
		this.orddprice = orddprice;
	}
	public float getOrddCSUBSPRICE() {
		return orddCSUBSPRICE;
	}
	public void setOrddCSUBSPRICE(float orddCSUBSPRICE) {
		this.orddCSUBSPRICE = orddCSUBSPRICE;
	}
	public float getOrddHSUBSPRICE() {
		return orddHSUBSPRICE;
	}
	public void setOrddHSUBSPRICE(float orddHSUBSPRICE) {
		this.orddHSUBSPRICE = orddHSUBSPRICE;
	}
	public Date getOrddUpdateTime() {
		return orddUpdateTime;
	}
	public void setOrddUpdateTime(Date orddUpdateTime) {
		this.orddUpdateTime = orddUpdateTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Integer getCargoInfoId() {
		return cargoInfoId;
	}
	public void setCargoInfoId(Integer cargoInfoId) {
		this.cargoInfoId = cargoInfoId;
	}
	public Integer getCargoTypeId() {
		return cargoTypeId;
	}
	public void setCargoTypeId(Integer cargoTypeId) {
		this.cargoTypeId = cargoTypeId;
	}
	public String getCargoInfoStart() {
		return cargoInfoStart;
	}
	public void setCargoInfoStart(String cargoInfoStart) {
		this.cargoInfoStart = cargoInfoStart;
	}
	public String getCargoInfoSStreet() {
		return cargoInfoSStreet;
	}
	public void setCargoInfoSStreet(String cargoInfoSStreet) {
		this.cargoInfoSStreet = cargoInfoSStreet;
	}
	public String getCargoInfoEnd() {
		return cargoInfoEnd;
	}
	public void setCargoInfoEnd(String cargoInfoEnd) {
		this.cargoInfoEnd = cargoInfoEnd;
	}
	public String getCargoInfoEStreet() {
		return cargoInfoEStreet;
	}
	public void setCargoInfoEStreet(String cargoInfoEStreet) {
		this.cargoInfoEStreet = cargoInfoEStreet;
	}
	public java.util.Date getCargoInfoDeliTime() {
		return cargoInfoDeliTime;
	}
	public void setCargoInfoDeliTime(java.util.Date cargoInfoDeliTime) {
		this.cargoInfoDeliTime = cargoInfoDeliTime;
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
}
