package com.atayun.hgs.wuliu.utils.sql;

public interface CargoInformationSQL {
		
		//获取所有的货源
		public String getAllCargoInformationSQL ="select * from CargoAllView m where m.CAIN_FLAG =0";
		
		//根据出发地和目的地查询货源
		public String getAllCargoInfoByStartAndEndSQL ="select * from CargoAllView m where m.CAIN_FLAG =0 and 1=1";
					
		//获取最佳匹配，信息比较全,假设是组合
		public String getBestMatchAllCSQL = "select * from cargolistview c where c.mare_num>1 and c.CAIN_FLAG=0 and c.user_id=?";
		
		//获取最佳匹配，信息比较全,假设是单个
		public String getBestMatchAllSSQL = "select * from cargolistview c where c.CAIN_FLAG=0 and c.user_id=? order by c.mare_num desc limit 1";
		//获取其余匹配，全部信息
		public String getOrtherMatchAllSQL= "select * from cargolistview c where c.mare_num=1 and c.CAIN_FLAG=0 and c.user_id=?";
		//发布货源
		public String publishCargoInfoSQL= "insert into tb_cain(TRTP_ID,CATP_ID,USER_ID,CAIN_PUBLISHED,CAIN_START," +
																"CAIN_SSTREET,CAIN_END,CAIN_ESTREET,CAIN_LNG," +
																"CAIN_LAT,CAIN_ELNG,CAIN_ELAT,CAIN_DELITIME," +
																"CAIN_PRICE,CAIN_LENGTH,CAIN_WIDTH,CAIN_HEIGHT,CAIN_RLEN," +
																"CAIN_VOLUME,CAIN_VUNIT,CAIN_LOAD,CAIN_LUNIT,CAIN_DESC,CAIN_CONTACTS,CAIN_CANTACTWAY," +
																"CAIN_PICURL,CAIN_FLAG,UPDATETIME,CART_ID,CAIN_RPRICE,CAIN_RPRICEFLAG,CAIN_SCITY,CAIN_ECITY)" +
																" values(?,?,?,sysdate(),?," +
																		"?,?,?,?," +
																		"?,?,?,?," +
																		"?,?,?,?,?," +
																		"?,?,?,?,?,?,?," +
																		"?,0,sysdate(),?,?,?,?,?)";
		
		//用户使用用户ID 查找用户认证状态
		public String getUserFlagSQL ="select USER_NAME,USER_MOBILE,USER_ADDR from tb_user where USER_ID=?";
		

		//用户使用用户ID 查找用户认证状态
		public String deleteCargoInfoSQL ="delete from tb_cain where CAIN_ID=?";
		
		
		//获取其余匹配，全部信息
		public String modifyCargoInfoSQL= "update tb_cain set TRTP_ID=?,CATP_ID=?,CAIN_START=?," +
																		"CAIN_SSTREET=?,CAIN_END=?,CAIN_ESTREET=?,CAIN_LNG=?," +
																		"CAIN_LAT=?,CAIN_ELNG=?,CAIN_ELAT=?,CAIN_DELITIME=?," +
																		"CAIN_PRICE=?,CAIN_LENGTH=?,CAIN_WIDTH=?,CAIN_HEIGHT=?,CAIN_RLEN=?," +
																		"CAIN_VOLUME=?,CAIN_VUNIT=?,CAIN_LOAD=?,CAIN_LUNIT=?,CAIN_DESC=?,CAIN_CONTACTS=?,CAIN_CANTACTWAY=?," +
																		"CAIN_PICURL=?,UPDATETIME=sysdate(),CART_ID=?,CAIN_RPRICEFLAG=? where CAIN_ID=? and CAIN_FLAG=0";


		//查看发布的货源根据货主的ID
		public String getMyCargoInfoByIDSQL ="select * from CargoAllView c where c.USER_ID=?";
		
		//根据货源的ID修改最后的货源议价
		public String modifylastpriceSQL ="update tb_cain  set CAIN_RPRICE=? where CAIN_ID=?";
}
