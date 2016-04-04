package com.atayun.hgs.wuliu.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.CarInfoDao;
import com.atayun.hgs.wuliu.dao.CompleteOrderDetailDao;
import com.atayun.hgs.wuliu.dao.rowmapper.CarInfoRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.CompleteOrderRowMapper;
import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.po.CompleteOrderDetail;
import com.atayun.hgs.wuliu.utils.sql.CarSQL;
import com.atayun.hgs.wuliu.utils.sql.OrdersSQL;
@Repository
public class CompleteOrderDetailDaoImpl implements CompleteOrderDetailDao{

	/** 这个参数是为了通过Spring的注解方式，得到Spring的jdbc模板，前提条件是在Spring的配置文件中，配置模板 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	/**
	 * 获取详尽订单信息
	 */
	@SuppressWarnings("unchecked")
	public CompleteOrderDetail getCompleteOrder(int orderid) {
		CompleteOrderDetail orderdetail=new CompleteOrderDetail();
		ArrayList<CompleteOrderDetail> orderlist=new ArrayList<CompleteOrderDetail>();
		orderlist=(ArrayList<CompleteOrderDetail>)jdbcTemplate.query(OrdersSQL.getOrderRelatedInfoByOrderidSQL,
				                                        new Object[]{orderid}, 
				                                        new int[]{java.sql.Types.INTEGER},
				                                        new CompleteOrderRowMapper());
		if(!orderlist.isEmpty()){
			orderdetail=orderlist.get(0);
		}
		return orderdetail;

	}
	
}
