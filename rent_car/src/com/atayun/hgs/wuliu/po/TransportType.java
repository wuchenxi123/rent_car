package com.atayun.hgs.wuliu.po;

import java.util.Date;

/**
 * 运输类型
 * @author HWJ
 *
 */
public class TransportType {

	private Integer transportTypeId;//运输类型ID
	private String  transportType;//运输类型
	private Date transportTypeUpdateTime;//运输类型更新时间
	public Integer getTransportTypeId() {
		return transportTypeId;
	}
	public void setTransportTypeId(Integer transportTypeId) {
		this.transportTypeId = transportTypeId;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public Date getTransportTypeUpdateTime() {
		return transportTypeUpdateTime;
	}
	public void setTransportTypeUpdateTime(Date transportTypeUpdateTime) {
		this.transportTypeUpdateTime = transportTypeUpdateTime;
	}
}
