package com.atayun.hgs.wuliu.po;

import java.util.Date;
/**
 * 货源类型
 * @author HWJ
 *
 */
public class CargoType {

	private Integer cargoTypeId;//货源类型ID
	private String  cargoType;//货源类型
	private Date    cargoTypeUpdateTime;//货源类型更新时间
	public Integer getCargoTypeId() {
		return cargoTypeId;
	}
	public void setCargoTypeId(Integer cargoTypeId) {
		this.cargoTypeId = cargoTypeId;
	}
	public String getCargoType() {
		return cargoType;
	}
	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}
	public Date getCargoTypeUpdateTime() {
		return cargoTypeUpdateTime;
	}
	public void setCargoTypeUpdateTime(Date cargoTypeUpdateTime) {
		this.cargoTypeUpdateTime = cargoTypeUpdateTime;
	}
}
