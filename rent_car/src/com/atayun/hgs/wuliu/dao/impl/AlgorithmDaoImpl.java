package com.atayun.hgs.wuliu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.AlgorithmDao;
import com.atayun.hgs.wuliu.po.MatchRecord;
import com.atayun.hgs.wuliu.po.MatchSupply;
import com.atayun.hgs.wuliu.po.Order;
import com.atayun.hgs.wuliu.utils.sql.AlgorithmSQL;
import com.atayun.hgs.wuliu.utils.sql.CargoInformationSQL;
import com.atayun.hgs.wuliu.utils.sql.OrderSQL;
import com.mysql.jdbc.Statement;
@Repository
public class AlgorithmDaoImpl implements AlgorithmDao{
	
	/** 这个参数是为了通过Spring的注解方式，得到Spring的jdbc模板，前提条件是在Spring的配置文件中，配置模板 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/*public boolean addToMare(MatchRecord matchRecord) {
		
		boolean flag = false;
		int result = -1;
		result =jdbcTemplate.update(AlgorithmSQL.addToMareSQL,
							new Object[]{matchRecord.getCariId(),matchRecord.getMarePrice(),matchRecord.getMareVolumn(),matchRecord.getMareLoad(),
										 matchRecord.getMareRVolumn(),matchRecord.getMareRLoad(),matchRecord.getMareRate(),matchRecord.getMareNum(),
										 matchRecord.getMareFlag()});
		
		if(result>0){
			flag = true;
		}
		
		return flag;
	}*/

	/**
	 * 添加到系统匹配的匹配货源表中
	 */
	public boolean addToMasu(MatchSupply matchSupply,MatchRecord matchRecord) {
		
		boolean flag = false;
		matchSupply.setMareID(addToMareReturnKey(matchRecord));
		int result = -1;
		result =jdbcTemplate.update(AlgorithmSQL.addToMasuSQL,
							new Object[]{matchSupply.getMareID(),matchSupply.getCainID()});
		
		if(result>0){
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * 根据系统匹配添加到系统匹配表中
	 */
	public int addToMareReturnKey(final MatchRecord matchRecord) {  
		int key = 0;  
        try  
        {  
            KeyHolder holder = new GeneratedKeyHolder();  
              
            jdbcTemplate.update(new PreparedStatementCreator(){  
  
                public PreparedStatement createPreparedStatement(Connection con)  
                        throws SQLException  
                {  
                    int i = 1;  
                    PreparedStatement ps = con.prepareStatement(AlgorithmSQL.addToMareSQL, Statement.RETURN_GENERATED_KEYS); // 主键字段 new String[]{"ADVERTID"}  
                    ps.setInt(i++, matchRecord.getCariId());  
                    ps.setFloat(i++, matchRecord.getMarePrice()); 
                    ps.setFloat(i++,matchRecord.getMareVolumn());
                    ps.setFloat(i++,matchRecord.getMareLoad());
                    ps.setFloat(i++,matchRecord.getMareRVolumn());
                    ps.setFloat(i++,matchRecord.getMareRLoad());
                    ps.setFloat(i++,matchRecord.getMareRate());
                    ps.setInt(i++,matchRecord.getMareNum());
                    ps.setInt(i++, matchRecord.getMareFlag());
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

}
