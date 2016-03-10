package com.atayun.hgs.wuliu.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.OrderInfoDao;
import com.atayun.hgs.wuliu.dao.rowmapper.RentOrderViewRowMapper;
import com.atayun.hgs.wuliu.po.OrderView;
import com.atayun.hgs.wuliu.po.RentOrderView;
import com.atayun.hgs.wuliu.utils.sql.OrdersSQL;

@Repository
public class OrderInfoDaoImpl implements OrderInfoDao{
  @Autowired
  JdbcTemplate jdbcTemplate =new JdbcTemplate();
  
//获取用户所有订单信息
  @SuppressWarnings("unchecked")
  public ArrayList<RentOrderView> getOrderListByID(Integer userId) {
		ArrayList<RentOrderView> allorder=new ArrayList<RentOrderView>();
		allorder=(ArrayList<RentOrderView>)jdbcTemplate.query(OrdersSQL.getAllOrderByUseridSQL, 
				                     new Object[]{userId},
				                     new int[]{java.sql.Types.INTEGER},
				                     new RentOrderViewRowMapper());
		return allorder;
	}
  
  //根据用户ID和订单状态获取订单列表
  @SuppressWarnings("unchecked")
	public ArrayList<RentOrderView> getOrderListByID(Integer userId,Integer orderFlag) {
	  ArrayList<RentOrderView> allorder=new ArrayList<RentOrderView>();
		allorder=(ArrayList<RentOrderView>)jdbcTemplate.query(OrdersSQL.getAllOrderByFlagSQL, 
				                     new Object[]{userId,orderFlag},
				                     new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER},
				                     new RentOrderViewRowMapper());
		return allorder;
	}
//根据订单号获取订单详情
  @SuppressWarnings("unchecked")
	public RentOrderView getOrderDetalilByID(Integer orderId) {
	  ArrayList<RentOrderView> orderlist=new ArrayList<RentOrderView>();
	  RentOrderView order=new RentOrderView();
	  orderlist=( ArrayList<RentOrderView>)jdbcTemplate.query(OrdersSQL.getOrderInfoByOrderidSQL,
				            new Object[]{orderId},
				            new int[]{java.sql.Types.INTEGER},
				            new RentOrderViewRowMapper());
		if(!orderlist.isEmpty()){
			order=orderlist.get(0);
		}
		return order;
	}
//根据订单号获取订单详情 有送车信息的
  @SuppressWarnings("unchecked")
	public OrderView getCompleteOrderDetalilByID(Integer orderId){
		ArrayList<OrderView> orderlist=new ArrayList<OrderView>();
		  OrderView order=new OrderView();
		  orderlist=( ArrayList<OrderView>)jdbcTemplate.query(OrdersSQL.getCompleteOrderInfoByOrderidSQL,
					            new Object[]{orderId},
					            new int[]{java.sql.Types.INTEGER},
					            new RentOrderViewRowMapper());
			if(!orderlist.isEmpty()){
				order=orderlist.get(0);
			}
			return order;
	}

	

	
	//添加订单详情 返回订单ID 操作失败则返回-1
	public int createOrderDetail(int userid, float orderPrice,int rentId) {
		int row=-1;
		row=jdbcTemplate.update(OrdersSQL.addRentOrderSQL,
				              new Object[]{userid,orderPrice,rentId},
				              new int[]{java.sql.Types.INTEGER,java.sql.Types.FLOAT,java.sql.Types.INTEGER}
				              );
		if(row>=0){
		  //SELECT LAST_INSERT_ID()这个语句可以获取刚刚插入的数据自增的id
				int  the_LAST_INSERT_ID=jdbcTemplate.queryForInt("SELECT LAST_INSERT_ID()");
				
				return the_LAST_INSERT_ID ;
		}else
			return -1;
	}

	//添加订单详情增加还车信息 返回订单ID 操作失败则返回-1
	public int improveOrderDetail(int orderid, String orderPrice, int returnId) {
		int row=-1;
		row=jdbcTemplate.update(OrdersSQL.addReturnOrderSQL,
				              new Object[]{orderid,orderPrice,returnId},
				              new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER,java.sql.Types.FLOAT,java.sql.Types.INTEGER}
				               );
		if(row>=0){
		  //SELECT LAST_INSERT_ID()这个语句可以获取刚刚插入的数据自增的id
				int  the_LAST_INSERT_ID=jdbcTemplate.queryForInt("SELECT LAST_INSERT_ID()");
				
				return the_LAST_INSERT_ID ;
		}else
			return -1;
	}
	//修改订单状态
	public boolean changeOrderStatus(int status,int orderId) {

           int row=-1;
        		 row=  jdbcTemplate.update(OrdersSQL.modifyOrderStatusSQL,
        				               new Object[]{status,orderId}, 
                                        new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER});
        		 if(row>=0){
        			 return true;
        		 }
		return false;
	}
//修改用户余额
	public boolean changeRemainder(int id, float remainder) {
		int row=-1;
		 row=jdbcTemplate.update(OrdersSQL.changeUserRemainderSQL,
	               new Object[]{remainder,id}, 
                  new int[]{java.sql.Types.FLOAT,java.sql.Types.INTEGER});
		 
		 if(row>=0){
			 return true;
		 }else{
			 return false;
		 }
	}
	

}
