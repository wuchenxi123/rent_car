package com.atayun.hgs.wuliu.po;

import java.util.Date;

/**
 * 订单实体类
 * @author HWJ
 *
 */
public class OrderInfo {
	
	private Integer orderId;//自增id
	private Integer userId;//车主ID
	private int orderStatus;//订单状态（0 待处理订单；1未完成订单；2已完成订单；3已取消订单)
	private float orderPrice;//订单总价格
	private Date createDate;//创建时间
	private int rentId;//租车信息
	private int returnId;//还车信息
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getRentId() {
		return rentId;
	}
	public void setRentId(int rentId) {
		this.rentId = rentId;
	}
	public int getReturnId() {
		return returnId;
	}
	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}
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

	public float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	
}
