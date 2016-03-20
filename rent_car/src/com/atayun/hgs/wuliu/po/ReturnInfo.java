package com.atayun.hgs.wuliu.po;

import java.util.Date;

public class ReturnInfo {
  private int returnId;
  private int returnType;//还车方式 0 上门还车  1 公司地点还车
  private String returnPlace;//还车地点
  private Date returnReTime;//还车时间
  
  public int getReturnId() {
	return returnId;
}
public void setReturnId(int returnId) {
	this.returnId = returnId;
}
public String getReturnPlace() {
	return returnPlace;
}
public void setReturnPlace(String returnPlace) {
	this.returnPlace = returnPlace;
}
public Date getReturnReTime() {
	return returnReTime;
}
public void setReturnReTime(Date returnReTime) {
	this.returnReTime = returnReTime;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getReturnType() {
	return returnType;
}
public void setReturnType(int returnType) {
	this.returnType = returnType;
}
private int userId;
}
