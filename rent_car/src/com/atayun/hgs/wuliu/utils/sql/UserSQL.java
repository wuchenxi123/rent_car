package com.atayun.hgs.wuliu.utils.sql;

public interface UserSQL {
	
	public String registertUserSQL = "insert into tb_user(user_mobile,user_password,user_idcard,user_type,user_license,user_verifycode,user_regtime) " +
								"values(?,?,?,?,?,?,sysdate())";
	
	//登陆成功够根据手机号获取ID号
	public String lastInsertUserIDSQL = "select * from tb_user where USER_MOBILE=?";
	//用户使用手机号和密码登陆的SQL
	public String loginByMobileAndPswSQL ="select * from tb_user where USER_MOBILE=? and USER_PASSWORD=?";
	
	//根据手机号码查询用户SQL
	public String queryUserByMobileSQL ="select * from tb_user where USER_MOBILE=?";
	
	//根据用户名查询用户SQL
	public String queryUserByNameSQL ="select * from tb_user where USER_NAME=?";
	
	//根据手机号码查询用户密码验证码SQL
	public String queryUserVerifyCodeByMobileSQL ="select * from tb_user where USER_MOBILE=?";
		
	//用户使用用户名和密码登陆的SQL
	public String loginByNameAndPswSQL ="select * from tb_user where USER_NAME=? and USER_PASSWORD=?";
	
	//用户重新设置密码
	public String resetPswByMobileSQL = "update tb_user set USER_PASSWORD=? where USER_MOBILE=?";
	
	public String getRemainderSQL ="select * from tb_user where USER_Id=?";
	//完善用户的基本个人信息第一步，完善用户名和用户地址
	public String improveCargoUser1SQL = "update tb_user set USER_NAME=?,USER_IDCARDURLP=?,USER_IDCARDURLN=?,USER_IDCARDFLAG=?," +
										 "USER_IDCARD=?,USER_ADDR=?,INFOFLAG=? where USER_ID=?";
	
	/*//完善用户的基本个人信息第二步，货主的身份证等信息
	public String improveCargoUser2SQL = "insert into tb_cago(USER_ID,CAGO_COUNTRY," +
										 "CAGO_PROVINCE,CAGO_CITY,CAGO_STREET,INFOFLAG,CAGO_CPPICFLAG,UPDATETIME)" +
										 "values(?,'中国',?,?,?,?,0,sysdate())";*/
	
	//完善用户的基本个人信息第二步，货主的身份证等信息
	public String improveCargoUser2SQL = "update tb_cago set CAGO_PROVINCE=?,CAGO_CITY=?,CAGO_STREET=?,INFOFLAG=?,CAGO_MOBILE=?,UPDATETIME=sysdate() where USER_ID=?";
	
	//用户登陆记录	
	public String userLoginRecordSQL = "insert into tb_ulre(USER_ID,ULRE_LANDTIME,ULRE_IP,ULRE_IMEI,ULRE_FLAG) values(?,sysdate(),?,?,?)";	
	
	/*//用户认证为企业货主
	public String accreDTTCargoOwnerSQL = "insert into tb_comp(USER_ID,COMP_NAME,COMP_TAXNO,COMP_WORKPHONE,COMP_CPPICURL,COMP_SWDJFBURL," +
																"COMP_KHXKZURL,COMP_XQSWMBURL,COMP_COUNTRY,COMP_PROVINCE,COMP_CITY,COMP_STREET," +
																"INFOFLAG,UPDATETIME)" +
																"values(?,?,?,?,?,?,?,?,'中国',?,?,?,?,sysdate())";*/
	
	//用户认证为企业货主
	public String accreDTTCargoOwnerSQL = "update tb_comp set COMP_NAME=?,COMP_TAXNO=?,COMP_WORKPHONE=?,COMP_CPPICURL=?,COMP_SWDJFBURL=?," +
																	"COMP_KHXKZURL=?,COMP_XQSWMBURL=?,COMP_PROVINCE=?,COMP_CITY=?,COMP_STREET=?," +
																	"INFOFLAG=?,UPDATETIME=sysdate() where USER_ID=?";

	
	// 查看货主的个人基本信息是否完善（车主和货主共用）
	public String validateUserBaseFlagSQL = "select INFOFLAG from tb_user where USER_ID = ?";
	// 查看货主的个人信息是否完善
	public String validateCagoFlagSQL = "select * from tb_cago where USER_ID = ?";
	//  查看企业货主信息是否完善
	public String validateCagoCompFlagSQL = "select INFOFLAG from tb_comp where USER_ID = ?";
	
	 // 查看货主的个人信息是否完善
	public String validateCarUserFlagSQL = "select INFOFLAG from tb_caro where USER_ID = ?";
	
	//注册成功后或者从切换的位置进来将货主表进行初始化
	public String initCargoUserSQl = "insert into tb_cago(USER_ID,CAGO_COUNTRY,INFOFLAG,CAGO_CPPICFLAG,UPDATETIME)" +
											 "values(?,'中国',0,0,sysdate())";
	
	//注册成功后或者从切换的位置进来将企业货主表进行初始化
	public String initCompUserSQL =  "insert into tb_comp(USER_ID,COMP_COUNTRY,INFOFLAG,UPDATETIME)" +
										 "values(?,'中国',0,sysdate())";
	
	//注册成功后或者从切换的位置进来将车主表进行初始化
	public String initCarUserSQL ="insert into tb_caro(USER_ID,CARO_DLICPICFLAG,INFOFLAG,UPDATETIME) values(?,0,0,sysdate())";
	
	//查询用户身份证号是否已经存在
	public String validateUserIDCardSQL = "select * from tb_user where USER_IDCARD=?";
	
	//完善车主个人信息第一步
	public String improveCarUserSQL1 = "update tb_user set USER_NAME=?,USER_IDCARDURLP=?,USER_IDCARDURLN=?,USER_IDCARDFLAG=?," +
											 "USER_IDCARD=?,USER_ADDR=?,INFOFLAG=? where USER_ID=?";
	
	//完善车主的联系方式
	public String improveCarUserSQL2 = "update tb_caro set CARO_MOBILE=?,UPDATETIME=sysdate() where USER_ID=?";
	
	//由货主--->车主
	public String cargo2carSQL = "update tb_user set USER_FLAG=0,UPDATETIME=sysdate() where USER_ID=?";
	
	//由货主--->车主
	public String car2cargoSQL = "update tb_user set USER_FLAG=1,UPDATETIME=sysdate() where USER_ID=?";
	
	//查看个人信息
	public String getUserBaseMegSQL = "select u.USER_NAME,u.USER_MOBILE,u.USER_IDCARDURLP,u.USER_IDCARDURLN,u.USER_IDCARDFLAG,u.USER_IDCARD,u.USER_ADDR,u.USER_PICURL,u.USER_FLAG," +
											"u.USER_INTEGRAL,u.USER_REMAINDER,u.USER_MFLAG,u.INFOFLAG,u.UPDATETIME from tb_user u where u.USER_ID = ?";	
	//查看货主个人信息
	public String getCargoUserBaseMegSQL = "select * from tb_cago where USER_ID = ?";
	
	//查看企业货主信息
	public String getCompanyBaseMegSQL = "select * from tb_comp where USER_ID = ?";
	
	//update picture
	public String uploadPICSQL = "update tb_user set USER_PICURL=?,UPDATETIME=sysdate() where USER_ID=?";
}
