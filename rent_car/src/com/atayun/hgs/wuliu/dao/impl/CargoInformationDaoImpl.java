package com.atayun.hgs.wuliu.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.atayun.hgs.wuliu.dao.CargoInformationDao;
import com.atayun.hgs.wuliu.dao.rowmapper.CargoAllViewPoRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.CargoInformationRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.CargoListViewRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.UserRowMapper;
import com.atayun.hgs.wuliu.po.CargoAllViewPo;
import com.atayun.hgs.wuliu.po.CargoInformation;
import com.atayun.hgs.wuliu.po.CargoListView;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.utils.sql.CargoInformationSQL;
import com.atayun.hgs.wuliu.utils.sql.OrderSQL;
import com.atayun.hgs.wuliu.utils.sql.UserSQL;
@Repository
public class CargoInformationDaoImpl implements CargoInformationDao{

	/** 这个参数是为了通过Spring的注解方式，得到Spring的jdbc模板，前提条件是在Spring的配置文件中，配置模板 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	/**
	 * 获取所有的货源信息
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CargoAllViewPo> getAllCargoInformations() {
		
		ArrayList<CargoAllViewPo> arrayList = new ArrayList<CargoAllViewPo>();
		arrayList = (ArrayList<CargoAllViewPo>) jdbcTemplate.query( CargoInformationSQL.getAllCargoInformationSQL,
																		new Object[]{}, 
																		new int[]{}, 
																		new CargoAllViewPoRowMapper());
		
		/*if(arrayList.isEmpty()){
			arrayList = null;
		}*/
		return arrayList;
	}


	/**
	 * 根据用户的ID获取最佳匹配的货源
	 * 已修改
	 */
	/*@SuppressWarnings("unchecked")
	public BestMatch getBestMatch(Integer userId) {
		//查询的时候主要通过对order by mare.mare_num desc对mare.mare_num进行降序的排列，选取第一条即为最佳的匹配
		BestMatch bestMatch = new BestMatch();
		ArrayList<BestMatch> bestMatchs = null;
		bestMatchs = (ArrayList<BestMatch>) jdbcTemplate.query( CargoInformationSQL.getBestMatchSQL,
											new Object[]{userId}, 
											new int[]{java.sql.Types.INTEGER}, 
											new BestMatchRowMapper());
		if(!bestMatchs.isEmpty()){
			bestMatch = bestMatchs.get(0);
		}
		return bestMatch;
	}*/

	/**
	 * 根据用户的ID获取除了最佳匹配外的所有其他的匹配
	 * 已经修改
	 */
	/*@SuppressWarnings("unchecked")
	public ArrayList<BestMatch> getOrtherMatch(Integer userId) {
		
		ArrayList<BestMatch> bestMatchs = null;
		bestMatchs = (ArrayList<BestMatch>) jdbcTemplate.query( CargoInformationSQL.getOrtherMatchSQL,
											new Object[]{userId}, 
											new int[]{java.sql.Types.INTEGER}, 
											new BestMatchRowMapper());
		
		return bestMatchs;
	}*/


	/**
	 * 发布货源信息
	 */
	public int publishCargoInfo(CargoInformation cargoInformation,int userId) {
		
		/*new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER,java.sql.Types.INTEGER,java.sql.Types.VARCHAR,
				  java.sql.Types.VARCHAR,
				  java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.CHAR,java.sql.Types.CHAR,java.sql.Types.CHAR,
				  java.sql.Types.CHAR,java.sql.Types.DATE,java.sql.Types.FLOAT,
				  java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT,
				  java.sql.Types.FLOAT,java.sql.Types.VARCHAR,
				  java.sql.Types.FLOAT,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,
				  java.sql.Types.VARCHAR}*/
		int result = -1;
		System.out.println(cargoInformation.getTransportTypeId()+cargoInformation.getCargoTypeId()+userId+cargoInformation.getCargoInfoStart()+
				 cargoInformation.getCargoInfoSStreet()+
				 cargoInformation.getCargoInfoEnd()+cargoInformation.getCargoInfoEStreet()+cargoInformation.getCargoInfoLng()+cargoInformation.getCargoInfoLat()+cargoInformation.getCargoInfoELng()+
				 cargoInformation.getCargoInfoELat()+cargoInformation.getCargoInfoDeliTime()+cargoInformation.getCargoInfoPrice()+
				 cargoInformation.getCargoInfoLenth()+cargoInformation.getCargoInfoWidth()+cargoInformation.getCargoInfoHeight()+cargoInformation.getCargoInfoRlen()+
				 cargoInformation.getCargoInfoVolume()+cargoInformation.getCargoInfoVunit()+
				 cargoInformation.getCargoInfoLoad()+cargoInformation.getCargoInfoLunit()+cargoInformation.getCargoInfoDesc()+cargoInformation.getCargoInfoContacts()+ cargoInformation.getCargoInfoContactWay()+
				 cargoInformation.getCargoInfoPicturl()+cargoInformation.getCartId()+cargoInformation.getCargoInfoPrice()+cargoInformation.getCargoRPriceFlag()+
				 cargoInformation.getCargoInfoSCity()+cargoInformation.getCargoInfoECity());
		
		result =jdbcTemplate.update(CargoInformationSQL.publishCargoInfoSQL,
							new Object[]{cargoInformation.getTransportTypeId(),cargoInformation.getCargoTypeId(),userId,cargoInformation.getCargoInfoStart(),
										 cargoInformation.getCargoInfoSStreet(),
										 cargoInformation.getCargoInfoEnd(),cargoInformation.getCargoInfoEStreet(),cargoInformation.getCargoInfoLng(),cargoInformation.getCargoInfoLat(),cargoInformation.getCargoInfoELng(),
										 cargoInformation.getCargoInfoELat(),cargoInformation.getCargoInfoDeliTime(),cargoInformation.getCargoInfoPrice(),
										 cargoInformation.getCargoInfoLenth(),cargoInformation.getCargoInfoWidth(),cargoInformation.getCargoInfoHeight(),cargoInformation.getCargoInfoRlen(),
										 cargoInformation.getCargoInfoVolume(),cargoInformation.getCargoInfoVunit(),
										 cargoInformation.getCargoInfoLoad(),cargoInformation.getCargoInfoLunit(),cargoInformation.getCargoInfoDesc(),cargoInformation.getCargoInfoContacts(), cargoInformation.getCargoInfoContactWay(),
										 cargoInformation.getCargoInfoPicturl(),cargoInformation.getCartId(),cargoInformation.getCargoInfoPrice(),cargoInformation.getCargoRPriceFlag(),
										 cargoInformation.getCargoInfoSCity(),cargoInformation.getCargoInfoECity()});
		
		return result;
	}

	/**
	 * 获取最佳匹配的所有信息,组合最佳
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CargoListView> getBestMatchAllC(Integer userId) {
		
		ArrayList<CargoListView> cargoListViews = new ArrayList<CargoListView>();
		cargoListViews = (ArrayList<CargoListView>) jdbcTemplate.query( CargoInformationSQL.getBestMatchAllCSQL,
											new Object[]{userId}, 
											new int[]{java.sql.Types.INTEGER}, 
											new CargoListViewRowMapper());
		/*if(cargoListViews.isEmpty()){
			cargoListViews = null;
		}*/

		return cargoListViews;
	}

	/**
	 *  获取最佳匹配的所有信息,单个最佳
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CargoListView> getBestMatchAllS(Integer userId) {
		
		ArrayList<CargoListView> cargoListViews = new ArrayList<CargoListView>();
		cargoListViews = (ArrayList<CargoListView>) jdbcTemplate.query( CargoInformationSQL.getBestMatchAllSSQL,
											new Object[]{userId}, 
											new int[]{java.sql.Types.INTEGER}, 
											new CargoListViewRowMapper());
		/*if(cargoListViews.isEmpty()){
			cargoListViews = null;
		}*/
		return cargoListViews;
	}


	@SuppressWarnings("unchecked")
	public ArrayList<CargoListView> getOrtherMatchALL(Integer userId) {
		
		ArrayList<CargoListView> orthseMatchs = new ArrayList<CargoListView>();
		orthseMatchs = (ArrayList<CargoListView>) jdbcTemplate.query( CargoInformationSQL.getOrtherMatchAllSQL,
											new Object[]{userId}, 
											new int[]{java.sql.Types.INTEGER}, 
											new CargoListViewRowMapper());
		
		return orthseMatchs;
	}
	/**
	 * 获取货主用户是否被认证的标志，进而是否需要提示完善信息
	 */
	@SuppressWarnings("unchecked")
	public List getUserFlag(Integer userId) {
		
		int result = -1;
		List userList = new ArrayList();
		userList = jdbcTemplate.query(CargoInformationSQL.getUserFlagSQL,
														new Object[]{userId}, 
														new int[]{java.sql.Types.INTEGER}, 
														new RowMapper() {

															public Object mapRow(
																	ResultSet rs,
																	int arg1)
																	throws SQLException {
																
																Map<String,String> map = new HashMap<String, String>();
																map.put("userName", (rs.getString("USER_NAME")));
																map.put("userMobile", (rs.getString("USER_MOBILE")));
																map.put("userAddr", (rs.getString("USER_ADDR")));
																return map;
															}
														});
	/*	 if (!userList.isEmpty()) {
			  user = userList.get(0);
		    }*/
		return userList;
	}


	/**
	 * 删除发布的一条货源根据货源ID
	 */
	public boolean deleteCargoInfo(Integer cargoInfoId) {
		
		boolean flag = false;
		int result = -1;
		result = jdbcTemplate.update(CargoInformationSQL.deleteCargoInfoSQL,  
                			new Object[] {cargoInfoId});
		
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 * 修改货源信息
	 */
	public boolean modifyCargoInfo(CargoInformation cargoInformation) {
		
		boolean flag = false;
		int result = -1;
		result =jdbcTemplate.update(CargoInformationSQL.modifyCargoInfoSQL,
				new Object[]{cargoInformation.getTransportTypeId(),cargoInformation.getCargoTypeId(),cargoInformation.getCargoInfoStart(),
							 cargoInformation.getCargoInfoSStreet(),
							 cargoInformation.getCargoInfoEnd(),cargoInformation.getCargoInfoEStreet(),cargoInformation.getCargoInfoLng(),cargoInformation.getCargoInfoLat(),cargoInformation.getCargoInfoELng(),
							 cargoInformation.getCargoInfoELat(),cargoInformation.getCargoInfoDeliTime(),cargoInformation.getCargoInfoPrice(),
							 cargoInformation.getCargoInfoLenth(),cargoInformation.getCargoInfoWidth(),cargoInformation.getCargoInfoHeight(),cargoInformation.getCargoInfoRlen(),
							 cargoInformation.getCargoInfoVolume(),cargoInformation.getCargoInfoVunit(),
							 cargoInformation.getCargoInfoLoad(),cargoInformation.getCargoInfoLunit(),cargoInformation.getCargoInfoDesc(),cargoInformation.getCargoInfoContacts(), cargoInformation.getCargoInfoContactWay(),
							 cargoInformation.getCargoInfoPicturl(),cargoInformation.getCartId(),cargoInformation.getCargoRPriceFlag(),cargoInformation.getCargoInfoId(),cargoInformation.getCargoInfoSCity(),cargoInformation.getCargoInfoECity()},
 			   new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER,java.sql.Types.VARCHAR,
	  					 java.sql.Types.VARCHAR,
	  					 java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.CHAR,java.sql.Types.CHAR,java.sql.Types.CHAR,
	  					 java.sql.Types.CHAR,java.sql.Types.DATE,java.sql.Types.FLOAT,
	  					 java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT,java.sql.Types.FLOAT,
	  					 java.sql.Types.FLOAT,java.sql.Types.VARCHAR,
	  					 java.sql.Types.FLOAT,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,
	  					 java.sql.Types.VARCHAR,java.sql.Types.INTEGER,java.sql.Types.FLOAT,java.sql.Types.INTEGER,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR});
		
		if(result>0){
			flag = true;
		}
		
		return flag;
	}

	/**
	 * 查看我的发布货源信息
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CargoAllViewPo> getMyCargoInfoByID(Integer userId) {
		
		ArrayList<CargoAllViewPo> arrayList = new ArrayList<CargoAllViewPo>();
		arrayList = (ArrayList<CargoAllViewPo>) jdbcTemplate.query( CargoInformationSQL.getMyCargoInfoByIDSQL,
																		new Object[]{userId}, 
																		new int[]{java.sql.Types.INTEGER}, 
																		new CargoAllViewPoRowMapper());
		
		/*if(arrayList.isEmpty()){
			arrayList = null;
		}*/
		return arrayList;	
	}


	/**
	 * 修改最后的议价
	 */
	public boolean modifylastprice(Integer cainId,float cargoRPrice) {
		
		boolean flag = false;
		int result = -1;
		result = jdbcTemplate.update(CargoInformationSQL.modifylastpriceSQL,  
    			new Object[] {cargoRPrice,cainId},
    			new int[]{java.sql.Types.FLOAT,java.sql.Types.INTEGER});
		
		if(result>0){
			flag = true;
		}
		return flag;
	}


	/**
	 * 根据出发地和目的地查询货源
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CargoAllViewPo> getAllCargoInfoByStartAndEnd(String start,String end) {
		
		String sql = CargoInformationSQL.getAllCargoInfoByStartAndEndSQL;
		ArrayList<CargoAllViewPo> arrayList = new ArrayList<CargoAllViewPo>();
		if(null != start && !"".equals(start)){
			sql = sql+" and m.CAIN_START like ?";
			
			if(null != end && !"".equals(end)){//起点和终点都有
				
				sql = sql+" and m.CAIN_END like ?";
				
				arrayList = (ArrayList<CargoAllViewPo>) jdbcTemplate.query( sql,
						new Object[]{"%"+start+"%","%"+end+"%"}, 
						new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR}, 
						new CargoAllViewPoRowMapper());
			}else{//只有起点，没有终点
				
				arrayList = (ArrayList<CargoAllViewPo>) jdbcTemplate.query( sql,
						new Object[]{"%"+start.trim()+"%"}, 
						new int[]{java.sql.Types.CHAR}, 
						new CargoAllViewPoRowMapper());
			}			
		}else if(null != end && !"".equals(end)){//没有起点，有终点
			
			sql = sql+" and m.CAIN_END like ?";
			arrayList = (ArrayList<CargoAllViewPo>) jdbcTemplate.query( sql,
					new Object[]{"%"+end+"%"}, 
					new int[]{java.sql.Types.VARCHAR}, 
					new CargoAllViewPoRowMapper());
		}else{//起点和终点都没有输入
			
			arrayList = (ArrayList<CargoAllViewPo>) jdbcTemplate.query( sql,
					new Object[]{}, 
					new int[]{}, 
					new CargoAllViewPoRowMapper());
		}
			
		return arrayList;
	}

}
