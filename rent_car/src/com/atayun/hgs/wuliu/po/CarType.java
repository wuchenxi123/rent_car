package com.atayun.hgs.wuliu.po;

import java.util.Date;

/**
 * 车辆类型表
 * @author chenlei
 *
 */
public class CarType {

	private int cartId;//主键
	private String cartType;//车辆类型
	private Date updateTime;//记录更新时间
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getCartType() {
		return cartType;
	}
	public void setCartType(String cartType) {
		this.cartType = cartType;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
