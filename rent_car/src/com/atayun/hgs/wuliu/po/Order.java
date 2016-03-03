package com.atayun.hgs.wuliu.po;

import java.util.Date;

/**
 * 订单实体类
 * @author HWJ
 *
 */
public class Order {
	
	private Integer orderId;//自增id
	private Integer userId;//车主ID
	private String orderNo;//订单号
	private Integer orderFlag;//订单状态（0 待处理订单；1未完成订单；2已完成订单；3已取消订单)
	private float orderPrice;//订单总价格
	private Date updateTime;//记录更新时间
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getOrderFlag() {
		return orderFlag;
	}
	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
	}
	public float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
