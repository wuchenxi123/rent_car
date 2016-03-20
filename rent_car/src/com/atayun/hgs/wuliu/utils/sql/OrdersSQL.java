package com.atayun.hgs.wuliu.utils.sql;
/**
 * 跟订单有关的SQL语句
 * @author HWJ
 *
 */
public interface OrdersSQL {
	  //添加订单详情
		public String addRentOrderSQL = "insert into tb_order(user_id,order_price,rent_id) values(?,?,?)";
		//添加订单详情还车信息
		public String addReturnOrderSQL = "update tb_order set return_id=? where order_id=?";
		
		//更改订单状态
		public String modifyOrderStatusSQL="update tb_order set order_status=? where order_id=?";
		//修改订单价格
		public String changeOrderPriceSQL="update tb_rentinfo r, tb_order o set o.order_price=?,r.rent_days=?,o.overSpend=?,o.order_status=? where  o.order_id=? and r.rent_id=o.rent_id ";
		//更改超支费用
	    public String changeOverSpendSQL="update tb_order set overSpend=? where order_id=?";
		
		
        //修改用户余额
		public String changeUserRemainderSQL="update tb_user set user_remainder=? where user_id=?";
		
		
		/*
		 * view_rentorder表 没有还车详情
		 */
		//获取用户所有订单信息
		public String getAllOrderByUseridSQL="select * from view_rentorder where user_id=?";
		//获取用户所有某状态下的订单信息
		public String getAllOrderByFlagSQL="select * from view_rentorder where user_id=? and order_status between 1 and ?";
		//根据订单号获取订单详情
		public String getOrderInfoByOrderidSQL="select * from view_rentorder where order_id=?";
		
		
		/*
		 * view_order表 有还车详情
		 */
		//获取用户所有完成的订单信息
		public String getAllCompleteOrderByUseridSQL="select * from view_rentorder where user_id=?";
		//根据订单号获取完成的订单详情
		public String getCompleteOrderInfoByOrderidSQL="select * from view_order where order_id=?";

		/*
		 * 查询订单相关详细信息
		 */
		public String getOrderRelatedInfoByOrderidSQL="select * from view_orderDetail where order_id=? ";
		
/*	//根据车主ID和订单的状态查找订单
	public String getOrderListByIDSQL = "select ORDE_ID,USER_ID,ORDE_NO,ORDE_FLAG,ORDE_PRICE,UPDATETIME " +
										"from tb_orde where USER_ID=? and ORDE_FLAG=?";
	
	//根据订单的ID查找订单的详细信息
	public String getOrderDetalilByIDSQL ="select * from OrderdetailView o where o.orde_id=?";
	
	//添加价格查询
	public String addPricingSQL = "insert into tb_pric(CAIN_ID,CARO_ID,CAGO_ID,PRIC_PRICE,PRIC_DIRECTION,PRIC_MARK,UPDATETIME) values(?,?,?,?,?,?,sysdate())";
	
	//添加订单表
	public String addOrderSQL = "insert into tb_orde(USER_ID,ORDE_FLAG,ORDE_PRICE,UPDATETIME) values(?,?,?,sysdate())";
	
	
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
*/
		}
