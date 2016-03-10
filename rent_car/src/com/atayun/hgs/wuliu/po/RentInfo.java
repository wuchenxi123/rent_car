package com.atayun.hgs.wuliu.po;

import java.util.Date;

public class RentInfo {
  private int rentId;
  private String rentLpnum;//车牌号
  private String rentCtype;//车辆类型
  private String rentPlace;//取车地点
  private Date rentTakeTime;//取车时间
  private int userId;//送车司机
  private int rentDays;//租车天数
public int getRentId() {
	return rentId;
}
public void setRentId(int rentId) {
	this.rentId = rentId;
}
public String getRentLpnum() {
	return rentLpnum;
}
public void setRentLpnum(String rentLpnum) {
	this.rentLpnum = rentLpnum;
}
public String getRentCtype() {
	return rentCtype;
}
public void setRentCtype(String rentCtype) {
	this.rentCtype = rentCtype;
}
public String getRentPlace() {
	return rentPlace;
}
public void setRentPlace(String rentPlace) {
	this.rentPlace = rentPlace;
}
public Date getRentTakeTime() {
	return rentTakeTime;
}
public void setRentTakeTime(Date rentTakeTime) {
	this.rentTakeTime = rentTakeTime;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}

public int getRentDays() {
	return rentDays;
}
public void setRentDays(int rentDays) {
	this.rentDays = rentDays;
}

}
