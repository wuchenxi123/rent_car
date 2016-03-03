package com.atayun.hgs.wuliu.utils.sql;
/**
 * 跟订单有关的SQL语句
 * @author HWJ
 *
 */
public interface OrderSQL {

	//根据车主ID和订单的状态查找订单
	public String getOrderListByIDSQL = "select ORDE_ID,USER_ID,ORDE_NO,ORDE_FLAG,ORDE_PRICE,UPDATETIME " +
										"from tb_orde where USER_ID=? and ORDE_FLAG=?";
	
	//根据订单的ID查找订单的详细信息
	public String getOrderDetalilByIDSQL ="select * from OrderdetailView o where o.orde_id=?";
	
	//添加价格查询
	public String addPricingSQL = "insert into tb_pric(CAIN_ID,CARO_ID,CAGO_ID,PRIC_PRICE,PRIC_DIRECTION,PRIC_MARK,UPDATETIME) values(?,?,?,?,?,?,sysdate())";
	
	//添加订单表
	public String addOrderSQL = "insert into tb_orde(USER_ID,ORDE_FLAG,ORDE_PRICE,UPDATETIME) values(?,?,?,sysdate())";
	
	//添加订单详情
	public String addOrderDetailSQL = "insert into tb_ordd(ORDE_ID,CARI_ID,CAIN_ID,ORDD_FLAG,ORDD_PRICE,ORDD_CSUBSPRICE,ORDD_HSUBSPRICE,UPDATETIME) values(?,?,?,?,?,?,?,sysdate())";
	
	//用户使用用户订单Id 删除订单的详情 第一步
	public String deleteOrderDetailByCarUserSQL ="delete from tb_ordd where ORDE_ID=?";
	
	//用户使用用户订单Id 删除订单 第二步
	public String deleteOrderByCarUserSQL ="delete from tb_orde where ORDE_ID=?";
	
	//货主拒绝订单的请求,步骤1  更新 ordd中的标志
	public String refuseOrderUpdateDDSQL ="update tb_ordd  set ORDD_FLAG=? where ORDE_ID=?";
	
	//货主拒绝订单的请求,步骤2  更新 orde中的标志s
	public String refuseOrderUpdateDESQL ="update tb_orde set ORDE_FLAG=? where ORDE_ID=?";
	
	//查看订单的ID
	public String getOrderIDSQL = "select orde.ORDE_ID  from tb_orde orde,tb_ordd ordd where orde.ORDE_ID=ordd.ORDE_ID and orde.USER_ID=? and ordd.CAIN_ID=?";
}
