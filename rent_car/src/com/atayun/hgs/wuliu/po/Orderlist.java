package com.atayun.hgs.wuliu.po;

import java.util.Date;

public class Orderlist {
  private int orderid;
  private Date rentTime;
  private int rentdays;
  private float orderPrice;
  private int orderStatus;
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public Date getRentTime() {
	return rentTime;
}
public void setRentTime(Date rentTime) {
	this.rentTime = rentTime;
}
public int getRentdays() {
	return rentdays;
}
public void setRentdays(int rentdays) {
	this.rentdays = rentdays;
}
public float getOrderPrice() {
	return orderPrice;
}
public void setOrderPrice(float orderPrice) {
	this.orderPrice = orderPrice;
}
public int getOrderStatus() {
	return orderStatus;
}
public void setOrderStatus(int orderStatus) {
	this.orderStatus = orderStatus;
}
  
}
