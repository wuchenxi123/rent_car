package com.atayun.hgs.wuliu.po;

import java.util.Date;

/**
 * 我的消息实体
 * @author HWJ
 *
 */
public class MessageCargo {

	private int caroDlicpicflag;//车主证件认证状态（0：未认证；1：已认证）
	private String caroCpName;//公司名称
	private String caroMobile;//车主联系号码（默认把TB_USER的手机号存入）
	private int infoFlag;//信息状态(0 未完善；1 已完善；2已认证；3正在审核)
	
	private String cariLpnum;//车牌号
	private float cariLength;//车长
	private float cariWidth;//车宽
	private float cariHeight;//车高
	private float cariVolume;//车辆体积
	private String cariVunit;//车辆体积单位
	private float cariLoad;//载重
	private String cariLunit;//载重单位
	private int cariDlicFlag;//车辆行驶证认证状态（0：未认证；1：已认证）
	private int cariPicFlag;//车辆图片认证状态（0：空载；3：部分空；7：满载）
	private String cariPicUrl;//车辆图片地址
	private int cariFlag;//车辆状态（0：空载；3：部分空；7：满载）
	private String cariStart;//车辆现在的起始地址
	private String cariEnd;//车辆现在的结束地址
	private int cariOlength;//是否超长的标志（0 否；1 是）
	private int cariOwidth;//是否超宽的标志（0 否；1 是）
	private int cariOheight;//是否超高的标志（0 否；1 是）
	private int cariOload;//是否超重的标志（0 否；1 是）
	
	private float   pricPrice;//价格  
	private String pricMark;//备注
	private Date pricUpdateTime;//记录更新时间
	
	private Integer pricCainId;//货物的ID
	private Integer pricCaroId;//车主的ID
	
	
	public Integer getPricCaroId() {
		return pricCaroId;
	}
	public void setPricCaroId(Integer pricCaroId) {
		this.pricCaroId = pricCaroId;
	}
	public Integer getPricCainId() {
		return pricCainId;
	}
	public void setPricCainId(Integer pricCainId) {
		this.pricCainId = pricCainId;
	}
	public String getCariPicUrl() {
		return cariPicUrl;
	}
	public void setCariPicUrl(String cariPicUrl) {
		this.cariPicUrl = cariPicUrl;
	}
	public String getCaroMobile() {
		return caroMobile;
	}
	public void setCaroMobile(String caroMobile) {
		this.caroMobile = caroMobile;
	}
	public int getInfoFlag() {
		return infoFlag;
	}
	public void setInfoFlag(int infoFlag) {
		this.infoFlag = infoFlag;
	}
	public int getCaroDlicpicflag() {
		return caroDlicpicflag;
	}
	public void setCaroDlicpicflag(int caroDlicpicflag) {
		this.caroDlicpicflag = caroDlicpicflag;
	}
	public String getCaroCpName() {
		return caroCpName;
	}
	public void setCaroCpName(String caroCpName) {
		this.caroCpName = caroCpName;
	}
	public String getCariLpnum() {
		return cariLpnum;
	}
	public void setCariLpnum(String cariLpnum) {
		this.cariLpnum = cariLpnum;
	}
	public float getCariLength() {
		return cariLength;
	}
	public void setCariLength(float cariLength) {
		this.cariLength = cariLength;
	}
	public float getCariWidth() {
		return cariWidth;
	}
	public void setCariWidth(float cariWidth) {
		this.cariWidth = cariWidth;
	}
	public float getCariHeight() {
		return cariHeight;
	}
	public void setCariHeight(float cariHeight) {
		this.cariHeight = cariHeight;
	}
	public float getCariVolume() {
		return cariVolume;
	}
	public void setCariVolume(float cariVolume) {
		this.cariVolume = cariVolume;
	}
	public String getCariVunit() {
		return cariVunit;
	}
	public void setCariVunit(String cariVunit) {
		this.cariVunit = cariVunit;
	}
	public float getCariLoad() {
		return cariLoad;
	}
	public void setCariLoad(float cariLoad) {
		this.cariLoad = cariLoad;
	}
	public String getCariLunit() {
		return cariLunit;
	}
	public void setCariLunit(String cariLunit) {
		this.cariLunit = cariLunit;
	}
	public int getCariDlicFlag() {
		return cariDlicFlag;
	}
	public void setCariDlicFlag(int cariDlicFlag) {
		this.cariDlicFlag = cariDlicFlag;
	}
	public int getCariPicFlag() {
		return cariPicFlag;
	}
	public void setCariPicFlag(int cariPicFlag) {
		this.cariPicFlag = cariPicFlag;
	}
	public int getCariFlag() {
		return cariFlag;
	}
	public void setCariFlag(int cariFlag) {
		this.cariFlag = cariFlag;
	}
	public String getCariStart() {
		return cariStart;
	}
	public void setCariStart(String cariStart) {
		this.cariStart = cariStart;
	}
	public String getCariEnd() {
		return cariEnd;
	}
	public void setCariEnd(String cariEnd) {
		this.cariEnd = cariEnd;
	}
	public int getCariOlength() {
		return cariOlength;
	}
	public void setCariOlength(int cariOlength) {
		this.cariOlength = cariOlength;
	}
	public int getCariOwidth() {
		return cariOwidth;
	}
	public void setCariOwidth(int cariOwidth) {
		this.cariOwidth = cariOwidth;
	}
	public int getCariOheight() {
		return cariOheight;
	}
	public void setCariOheight(int cariOheight) {
		this.cariOheight = cariOheight;
	}
	public int getCariOload() {
		return cariOload;
	}
	public void setCariOload(int cariOload) {
		this.cariOload = cariOload;
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
