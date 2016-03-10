package com.atayun.hgs.wuliu.po;

import java.util.Date;

public class ReturnInfo {
  private int returnId;
  private String returnPlace;
  private Date returnReTime;
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
private int userId;
}
