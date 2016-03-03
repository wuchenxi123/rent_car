package com.atayun.hgs.wuliu.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.OrderDao;
import com.atayun.hgs.wuliu.dao.rowmapper.OrderDetailRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.OrderRowMapper;
import com.atayun.hgs.wuliu.po.Order;
import com.atayun.hgs.wuliu.po.OrderDetail;
import com.atayun.hgs.wuliu.po.Pricing;
import com.atayun.hgs.wuliu.utils.sql.CargoInformationSQL;
import com.atayun.hgs.wuliu.utils.sql.OrderSQL;
import com.atayun.hgs.wuliu.utils.sql.UserSQL;
import com.mysql.jdbc.Statement;
@Repository
public class OrderDaoImpl implements OrderDao{
	
	
	 /** 这个参数是为了通过Spring的注解方式，得到Spring的jdbc模板，前提条件是在Spring的配置文件中，配置模板 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 车主获取自己的订单信息
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Order> getOrderListByID(Integer userId, Integer orderFlag) {
		
	    ArrayList<Order> orders = new ArrayList<Order>();
	    orders = (ArrayList<Order>) jdbcTemplate.query(OrderSQL.getOrderListByIDSQL,
									new Object[]{userId,orderFlag}, 
									new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER}, 
									new OrderRowMapper());	    
		return orders;
	}
	
	/**
	 * 根据订单的ID查看订单的详情
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<OrderDetail> getOrderDetalilByID(Integer orderId) {
	
		ArrayList<OrderDetail> orderdetails = new ArrayList<OrderDetail>();
		orderdetails = (ArrayList<OrderDetail>) jdbcTemplate.query(OrderSQL.getOrderDetalilByIDSQL,
						   						new Object[]{orderId}, 
						   						new int[]{java.sql.Types.INTEGER}, 
						   						new OrderDetailRowMapper());
		return orderdetails;
	}

	/**
	 * 确认生成订单，请求货主反馈
	 */
	public boolean createOrder(OrderDetail detail, Order order, Pricing pricing) {
		
		boolean flag = false;
		int result=-1;
		result = jdbcTemplate.update(OrderSQL.getOrderDetalilByIDSQL,
							new Object[]{});
		if(result>0){
			
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 根据插入的订单返回订单的ID
	 */
	public int createOrder(final Order order) {  
		int key = 0;  
        try  
        {  
            KeyHolder holder = new GeneratedKeyHolder();  
              
            jdbcTemplate.update(new PreparedStatementCreator(){  
  
                public PreparedStatement createPreparedStatement(Connection con)  
                        throws SQLException  
                {  
                    int i = 1;  
                    PreparedStatement ps = con.prepareStatement(OrderSQL.addOrderSQL, Statement.RETURN_GENERATED_KEYS); // 主键字段 new String[]{"ADVERTID"}  
                    ps.setInt(i++, order.getUserId());  
                    ps.setInt(i++, order.getOrderFlag()); 
                    ps.setFloat(i++, order.getOrderPrice());
                    return ps;  
                }

            },holder);  
            key = holder.getKey().intValue();  
        } catch (DataAccessException e)  
        {  
        	e.printStackTrace();
        }  
        return key;  
	}

	/**
	 * 插入询价
	 */
	public boolean createPricing(Pricing pricing) {

		boolean flag = false;
		int result = -1;
		result = jdbcTemplate.update(OrderSQL.addPricingSQL,
				                     new Object[]{pricing.getCainId(),pricing.getCaroId(),pricing.getCagoId(),pricing.getPricPrice(),
												  pricing.getPricDirection(),pricing.getPricMark()});
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 * 插入订单详情
	 */
	public boolean createOrderDetail(OrderDetail detail) {

		boolean flag = false;
		int result = -1;
		result = jdbcTemplate.update(OrderSQL.addOrderDetailSQL,
				                     new Object[]{detail.getOrddId(),detail.getCariId(),detail.getCargoInfoId(),detail.getOrddFlag(),
												  detail.getOrddprice(),detail.getOrddCSUBSPRICE(),detail.getOrddHSUBSPRICE()});	
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 * 车主删除订单,步骤一删除订单详情
	 */
	public boolean deleteOrderDetailByCarUser(Integer orderId) {
		
		boolean flag = false;
		int result = -1;
		result = jdbcTemplate.update(OrderSQL.deleteOrderDetailByCarUserSQL,  
                			new Object[] {orderId});
		
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 * 车主删除订单,步骤二删除订单
	 */
	public boolean deleteOrderByCarUser(Integer orderId) {
		
		boolean flag = false;
		int result = -1;
		result = jdbcTemplate.update(OrderSQL.deleteOrderByCarUserSQL,  
                			new Object[] {orderId});
		
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 *货主拒绝订单的请求,步骤一 更新 ordd中的标志
	 */
	public boolean refuseOrderUpdateDD(Integer orderId,Integer setFlag) {

		boolean flag = false;
		int result = -1;
		result = jdbcTemplate.update(OrderSQL.refuseOrderUpdateDDSQL,  
    			new Object[] {setFlag,orderId},
    			new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER});
		
		if(result>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 *货主拒绝订单的请求,步骤二 更新 orde中的标志
	 */
	public boolean refuseOrderUpdateDE(Integer orderId,Integer setFlag) {
		
		boolean flag = false;
		int result = -1;
		result = jdbcTemplate.update(OrderSQL.refuseOrderUpdateDESQL,  
    			new Object[]{setFlag,orderId},
    			new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER});
		
		if(result>0){
			flag = true;
		}
		return flag;
	}

	//查找订单的ID
	@SuppressWarnings("unchecked")
	public int getOrderID(Integer cainId, Integer pricCaroId) {
		int result = -1;
		List list = null;
		list =jdbcTemplate.query(UserSQL.validateUserBaseFlagSQL, 
											new Object[] {cainId,pricCaroId},
											new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER},
											new RowMapper() {
												public Object mapRow(
														ResultSet rs, int arg1)
														throws SQLException {
													int r = rs.getInt("ORDE_ID");
													return r;
												}
											});
		
		if(!list.isEmpty()){	
			result = (Integer) list.get(0);
		}
		
		return result;
	}
	
}
