package com.atayun.hgs.wuliu.po;

import java.util.Date;

/**
 * 询价，议价
 * @author HWJ
 *
 */
public class Pricing {

	private Integer pricId;//自增id
	private Integer cainId;//TB_CAIN表中的CAIN_ID(货物信息)
	private Integer caroId;//TB_CARO表中的USER_ID(车主)
	private Integer cagoId;//TB_CAGO表中的USER_ID(货主)
	private float   pricPrice;//价格  
	private int     pricDirection;//询价方向（0 车主向货主；1货主向车主）
	private String pricMark;//备注
	private Date pricUpdateTime;//记录更新时间
	public Integer getPricId() {
		return pricId;
	}
	public void setPricId(Integer pricId) {
		this.pricId = pricId;
	}
	public Integer getCainId() {
		return cainId;
	}
	public void setCainId(Integer cainId) {
		this.cainId = cainId;
	}
	public Integer getCaroId() {
		return caroId;
	}
	public void setCaroId(Integer caroId) {
		this.caroId = caroId;
	}
	public Integer getCagoId() {
		return cagoId;
	}
	public void setCagoId(Integer cagoId) {
		this.cagoId = cagoId;
	}
	public float getPricPrice() {
		return pricPrice;
	}
	public void setPricPrice(float pricPrice) {
		this.pricPrice = pricPrice;
	}
	public int getPricDirection() {
		return pricDirection;
	}
	public void setPricDirection(int pricDirection) {
		this.pricDirection = pricDirection;
	}
	public String getPricMark() {
		return pricMark;
	}
	public void setPricMark(String pricMark) {
		this.pricMark = pricMark;
	}
	public Date getPricUpdateTime() {
		return pricUpdateTime;
	}
	public void setPricUpdateTime(Date pricUpdateTime) {
		this.pricUpdateTime = pricUpdateTime;
	}
}
