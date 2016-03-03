package com.atayun.hgs.wuliu.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.atayun.hgs.wuliu.dao.MessageDao;
import com.atayun.hgs.wuliu.dao.rowmapper.MessageCarRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.MessageCargoRowMapper;
import com.atayun.hgs.wuliu.po.MessageCar;
import com.atayun.hgs.wuliu.po.MessageCargo;
import com.atayun.hgs.wuliu.utils.sql.MessageSQL;

@Repository
public class MessageDaoImpl implements MessageDao{
	
	/** 这个参数是为了通过Spring的注解方式，得到Spring的jdbc模板，前提条件是在Spring的配置文件中，配置模板 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 根据货主的ID查询我的消息
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<MessageCargo> getMyMessagesCargo(Integer cagoId) {
		
		ArrayList<MessageCargo> messages = new ArrayList<MessageCargo>();
		
		messages = (ArrayList<MessageCargo>) jdbcTemplate.query(MessageSQL.getMyMessagesCargoSQL,
									  				new Object[]{cagoId}, 
									  				new int[]{java.sql.Types.INTEGER}, 
									  				new MessageCargoRowMapper());	   
		return messages;
	}

	/**
	 *  根据车主的ID查询我的消息
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<MessageCar> getMyMessagesCar(Integer caroId) {

		ArrayList<MessageCar> messages = new ArrayList<MessageCar>();
		
		messages = (ArrayList<MessageCar>) jdbcTemplate.query(MessageSQL.getMyMessagesCarSQL,
									  				new Object[]{caroId}, 
									  				new int[]{java.sql.Types.INTEGER}, 
									  				new MessageCarRowMapper());	   
		return messages;
	}

}
