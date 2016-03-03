package com.atayun.hgs.wuliu.po;

import java.util.Date;
/**
 * 车主信息视图
 * @author chenlei
 */
public class CarListView {
	//车辆
	private int cariId;// TB_CARI表中的CARI_ID（外键）
	private String cariLpnum;// 车牌号
	private float cariLength;// 车长
	private float cariWidth;// 车宽
	private float cariHeight;// 车高
	private float cariVolume;// 车辆体积
	private String cariVunit;//车辆体积单位
	private float cariRvolumn;//车辆剩余体积（单位：方）
	private float cariLoad;//载重
	private String cariLunit;//载重单位
	private float cariRload;//车辆剩余载重（单位：顿）
	private String cariDlicUrl;// 行驶证图片地址
	private int cariDlicFlag;// 车辆行驶证认证状态（0：未认证；1：已认证）
	private String cariPicUrl;// 车辆图片地址
	private int cariPicFlag;// 车辆图片认证状态（0：空载；3：部分空；7：满载）
	private int cariFlag;// 车辆状态（0：空载；3：部分空；7：满载）
	private String cariStart;//车辆现在的起始地址
	private String cariScity;//起点城市
	private String cariSstreet;//起点详细地址
	private String cariEnd;//车辆现在的结束地址
	private String cariEcity;//结束城市
	private String cariEstreet;//结束详细地址
	private String cariLng;//车辆起始经度
	private String cariLat;//车辆起始纬度
	private String cariElng;//车辆结束经度
	private String cariElat;//车辆结束纬度
	private int cariOlength;//是否超长的标志（0 否；1 是）
	private int cariOwidth;//是否超宽的标志（0 否；1 是）
	private int cariOheight;//是否超高的标志（0 否；1 是）
	private int cariOload;//是否超重的标志（0 否；1 是）
	public String getCariLng() {
		return cariLng;
	}

	public void setCariLng(String cariLng) {
		this.cariLng = cariLng;
	}

	public String getCariLat() {
		return cariLat;
	}

	public void setCariLat(String cariLat) {
		this.cariLat = cariLat;
	}

	public String getCariElng() {
		return cariElng;
	}

	public void setCariElng(String cariElng) {
		this.cariElng = cariElng;
	}

	public String getCariElat() {
		return cariElat;
	}

	public void setCariElat(String cariElat) {
		this.cariElat = cariElat;
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

	public float getCariRvolumn() {
		return cariRvolumn;
	}

	public void setCariRvolumn(float cariRvolumn) {
		this.cariRvolumn = cariRvolumn;
	}

	public float getCariRload() {
		return cariRload;
	}

	public void setCariRload(float cariRload) {
		this.cariRload = cariRload;
	}

	public String getCariScity() {
		return cariScity;
	}

	public void setCariScity(String cariScity) {
		this.cariScity = cariScity;
	}

	public String getCariSstreet() {
		return cariSstreet;
	}

	public void setCariSstreet(String cariSstreet) {
		this.cariSstreet = cariSstreet;
	}

	public String getCariEcity() {
		return cariEcity;
	}

	public void setCariEcity(String cariEcity) {
		this.cariEcity = cariEcity;
	}

	public String getCariEstreet() {
		return cariEstreet;
	}

	public void setCariEstreet(String cariEstreet) {
		this.cariEstreet = cariEstreet;
	}

	private int cariRouteNum;// 车辆行车路线记录条数
	private Date updateTime;// 车辆更新时间
	//车辆类型
	private int cartId;// TB_CART表中的CART_ID(外键)
	private String cartType;
	//车主
	private int userId;// TB_USER表中的USER_ID(外键)
	private String caroDlicpicurl;// 车主驾驶证图片地址
	private String caroCpName;// 公司名称
	private int caroDlicpicflag;// 车主证件认证状态（0：未认证；1：已认证）
	private int infoFlag;//信息状态(0 未完善；1 已完善；2已认证；3正在审核)
	private String caroMobile;//车主联系号码（默认把TB_USER的手机号存入）
	//用户
	private String userName;// 车主姓名
	private String userMobile;// 手机号
	private String userAddr;// 地址
	private String userPicurl;// 车主头像
	private Date userRegTime;// 注册时间
	private int userFlag;//用户状态标识（0：未认证；3已认证车主；7已认证货主）
	
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

	
	public int getCariId() {
		return cariId;
	}

	public void setCariId(int cariId) {
		this.cariId = cariId;
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

	public float getCariLoad() {
		return cariLoad;
	}

	public void setCariLoad(float cariLoad) {
		this.cariLoad = cariLoad;
	}

	public String getCariDlicUrl() {
		return cariDlicUrl;
	}

	public void setCariDlicUrl(String cariDlicUrl) {
		this.cariDlicUrl = cariDlicUrl;
	}

//	public int getCariDlicFlag() {
//		return cariDlicFlag;
//	}
//
//	public void setCariDlicFlag(int cariDlicFlag) {
//		this.cariDlicFlag = cariDlicFlag;
//	}

	public String getCariPicUrl() {
		return cariPicUrl;
	}

	public void setCariPicUrl(String cariPicUrl) {
		this.cariPicUrl = cariPicUrl;
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

	public int getCariRouteNum() {
		return cariRouteNum;
	}

	public void setCariRouteNum(int cariRouteNum) {
		this.cariRouteNum = cariRouteNum;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserPicurl() {
		return userPicurl;
	}

	public void setUserPicurl(String userPicurl) {
		this.userPicurl = userPicurl;
	}

	public Date getUserRegTime() {
		return userRegTime;
	}

	public void setUserRegTime(Date userRegTime) {
		this.userRegTime = userRegTime;
	}

	public int getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(int userFlag) {
		this.userFlag = userFlag;
	}

	public String getCartType() {
		return cartType;
	}

	public void setCartType(String cartType) {
		this.cartType = cartType;
	}

	public int getCariDlicFlag() {
		return cariDlicFlag;
	}

	public void setCariDlicFlag(int cariDlicFlag) {
		this.cariDlicFlag = cariDlicFlag;
	}

	public String getCaroCpName() {
		return caroCpName;
	}

	public void setCaroCpName(String caroCpName) {
		this.caroCpName = caroCpName;
	}

	public String getCariVunit() {
		return cariVunit;
	}

	public void setCariVunit(String cariVunit) {
		this.cariVunit = cariVunit;
	}

	public String getCariLunit() {
		return cariLunit;
	}

	public void setCariLunit(String cariLunit) {
		this.cariLunit = cariLunit;
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
