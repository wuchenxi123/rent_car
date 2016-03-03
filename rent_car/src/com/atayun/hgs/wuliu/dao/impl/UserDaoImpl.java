package com.atayun.hgs.wuliu.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.atayun.hgs.wuliu.dao.UserDao;
import com.atayun.hgs.wuliu.dao.rowmapper.CargoOwnerRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.CompanyRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.UserBaseRowMapper;
import com.atayun.hgs.wuliu.dao.rowmapper.UserRowMapper;
import com.atayun.hgs.wuliu.po.CargoOwner;
import com.atayun.hgs.wuliu.po.Company;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.utils.CommonUtils;
import com.atayun.hgs.wuliu.utils.yzxSMS;
import com.atayun.hgs.wuliu.utils.sql.UserSQL;

/**
 * UserDaoImpl
 * 概述：
 * 实现UserDao接口，和数据库中的tb_user进行数据交互。
 * <p>
 * 1.用户发送短信
 * 2.用户手机号码登陆
 * 3.用户使用用户名登陆
 * 4.验证手机号是否注册
 * 5.验证用户名是否已存在
 * 6.用户注册
 * </p>
 * @author  黄威杰
 * @version 0.5, 2015/05/11
 */
@Repository
public class UserDaoImpl implements UserDao{
	
	 /** 这个参数是为了通过Spring的注解方式，得到Spring的jdbc模板，前提条件是在Spring的配置文件中，配置模板 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
     * sendSMSRequest：
     * 功能： 发送短信验证信息
     * @param  phone
     *         发送短信的目标手机号码
     * @param  content
     *         发送的短信验证码字符串       
     */
	public boolean sendSMSRequest(String phone, String content) {
		/**
		 * 修改时间2015-05-11
		 * 原因：更改第三方短信验证服务提供商
		 * 由原来的短信宝，改为云之讯
		 * 修改人:黄威杰
		 */
		/*String content1="";
		boolean flag = false;
		String username = "hwjv5";//短信宝帐户名
		String pass = new MD5().Md5("job774671");//短信宝帐户密码，md5加密后的字符串
		//String phone = "15274955704";//电话号码
		try {
			content1 = java.net.URLEncoder.encode("【长沙云端物流】您的验证码为"+content+"，在3分钟内有效。", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//发送内容
		httpSend send = new httpSend("http://www.smsbao.com/sms?u="+username+"&p="+pass+"&m="+phone+"&c="+content1);
		try {
			flag = send.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;*/
		//使用云之讯部分开始
		boolean flag = true;
		yzxSMS sms = new yzxSMS();
		boolean json=true;
		//为了配合原来的返回参数，已经事先判断手机号是否合法在进行短信的发送请求
		if(!CommonUtils.isMobileNO(phone)){
			flag = false;
		}else{ //短信验证码 
			
				String accountSid="0d65c3f8c916fe8e954f6937b3140a67";
				String token="f9cde63dd1812102b988b4f3094e04aa";
				String appId="e462aba25bc6498fa5ada7eefe1401b7";
				String templateId="1";
				String to=phone;
				String para="车随我,"+content+",3";
				sms.testTemplateSMS(json, accountSid,token,appId, templateId,to,para);
		}
		return flag;
	}
	
	 /**
     *loginUserByMobile:
     *功能：用户使用手机号和密码进行用户身份验证，登陆系统
     * @param  userMobile
     *         用户登陆时，输入所注册好的手机号码
     * @param  userPassword
     *         用户登陆时，输入所注册好的用户密码
     */
	@SuppressWarnings("unchecked")
	public User loginUserByMobile(String userMobile, String userPassword) {
		System.out.println(userMobile+"-----"+userPassword);
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		userList = (ArrayList<User>)jdbcTemplate.query( UserSQL.loginByMobileAndPswSQL,
														new Object[]{userMobile,userPassword}, 
														new int[]{java.sql.Types.CHAR,java.sql.Types.CHAR}, 
														new UserRowMapper());
		 if (!userList.isEmpty()) {
			  user = userList.get(0);
		    }
		return user;
	}

	 /**
     *validateMobile:
     *功能： 验证手机号是否已经注册使用
     * @param  userMobile
     *         输入需要验证的手机号码
     */
	@SuppressWarnings("unchecked")
	public User validateMobile(String userMobile) {
		
		ArrayList<User> userList = new ArrayList<User>();;
		User user = null;
		userList = (ArrayList<User>)jdbcTemplate.query(UserSQL.queryUserByMobileSQL,
														new Object[]{userMobile}, 
														new int[]{java.sql.Types.CHAR}, 
														new UserRowMapper());
		 if (!userList.isEmpty()) {
			  user = userList.get(0);
		    }
		return user;
	}

	/**
     *loginUserByName:
     *功能： 用户使用用户名和密码进行用户身份验证，登陆系统
     * @param  userName
     *         用户登陆时，输入所注册时的用户名
     * @param  userPassword
     *         用户登陆时，输入所注册好的用户密码
     */
	@SuppressWarnings("unchecked")
	public User loginUserByName(String userName, String userPassword) {
		
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		userList = (ArrayList<User>)jdbcTemplate.query(UserSQL.loginByNameAndPswSQL,
														new Object[]{userName,userPassword}, 
														new int[]{java.sql.Types.CHAR,java.sql.Types.CHAR}, 
														new UserRowMapper());
		 if (!userList.isEmpty()) {
			  user = userList.get(0);
		    }
		return user;
	}

	 /**
     *validateName:
     *功能：验证手机号是否已经注册使用
     * @param  userName
     *         输入需要验证的手机号码
     */
	@SuppressWarnings("unchecked")
	public User validateName(String userName) {

		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		userList = (ArrayList<User>)jdbcTemplate.query(UserSQL.queryUserByNameSQL,
														new Object[]{userName}, 
														new int[]{java.sql.Types.CHAR}, 
														new UserRowMapper());
		 if (!userList.isEmpty()) {
			  user = userList.get(0);
		    }
		return user;

	}

	/**
     *registerUser:
     *功能：默认情况下进行注册
     * @param  user
     *         由spring进行封装参数，默认情况下包含的参数主要有
     *         UserPassword
     *         UserMobile
     */
	public void registerUser(User user) {
		//修改原因:将SQL放在UserSQL中
		/*jdbcTemplate.update("insert into tb_user(USER_PASSWORD,USER_MOBILE,USER_REGTIME,USER_ROLEID,USER_ERRORTIMES,USER_FLAG,"
												+ "USER_VERIFYCODE,USER_INTEGRAL,USER_REMAINDER,USER_MFLAG,UPDATETIME) "
												+ "values(?,?,sysdate(),?,?,?,?,?,?,?,sysdate())",
												new Object[]{user.getUserPassword(),user.getUserMobile(),1,0,0,"XXXXX",0,0,1});*/
		//默认设置USER_MOBILE,USER_PASSWORD,USER_IDCARD,user_type,USER_LICENSE,user_remainder,user_regtime
		jdbcTemplate.update(UserSQL.registertUserSQL,
							new Object[]{user.getUserMobile(),user.getUserPassword(),user.getUserIDCard(),user.getUserType(),
				user.getUserLicense(),user.getUserRemainder()});

	}

	/**
     *registerUser:
     *功能：根据用户的手机号，重新设置密码
     * @param  userMobile
     *         用户手机号码，必须是已经注册的
     * @param  userPassword
     *         重新输入设置的密码
     */
	public void resetUserPassword(String userMobile, String userPassword) {
		
		jdbcTemplate.update(UserSQL.resetPswByMobileSQL, 
							new Object[]{userPassword, userMobile},
							new int[]{java.sql.Types.VARCHAR, java.sql.Types.VARCHAR});
		 
	}

	/**
     * @method getUserVerifyCode
     *功能：获取密码验证码
     * @param  userMobile
     *         用户手机号码，必须是已经注册的
     */
	@SuppressWarnings("unchecked")
	public String getUserVerifyCode(String userMobile) {
		
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		userList = (ArrayList<User>)jdbcTemplate.query(UserSQL.queryUserVerifyCodeByMobileSQL,
														new Object[]{userMobile}, 
														new int[]{java.sql.Types.VARCHAR}, 
														new UserRowMapper());
		 if (!userList.isEmpty()) {
			  user = userList.get(0);
		    }
		return user.getUserVerifyCode();
	}

	/**
	 * 完善货主用户个人信息
	 
	public boolean improveCargoUser(CargoOwner owner,User user,Integer userId) {
		
		boolean flag = false;
		//判断是否已经全部填写了个人的信息，如果全部填写了那么可以认为是已经完善，缺其中之一那么认为是未完善
		if(CommonUtils.isFit4ImproveCargo(owner, user)){
			owner.setInFoFlag(1);//个人货主信息已经完善
			user.setInfoFlag(1);//用户个人基本信息已经完善
		}else{
			owner.setInFoFlag(0);//个人货主信息未完善
			user.setInfoFlag(0);//用户个人基本信息未完善
		}
		int result1=-1,result2=-1;
		//完善用户的姓名用户的地身份证等信息
		result1 = jdbcTemplate.update(UserSQL.improveCargoUser1SQL, 
					new Object[]{user.getUserName(),user.getUserIDCardURLP(),user.getUserIDCardURLN(),user.getUserFlag(),
								 user.getUserIDCard(),user.getUserAddr(),user.getInfoFlag(),userId},
					new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.INTEGER,
							  java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.INTEGER,java.sql.Types.INTEGER});
	
		//完善用户地址
		result2 = jdbcTemplate.update(UserSQL.improveCargoUser2SQL, 
				new Object[]{owner.getCagoProvince(),owner.getCagoCity(),owner.getCagoStreet(),owner.getInFoFlag(),owner.getCagoMobile(),userId},
				new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.INTEGER,java.sql.Types.VARCHAR,java.sql.Types.INTEGER});
		
		if(result1>0||result2>0){
			flag = true;
		}
		return flag;
	}
*/
	/**
	 * 记录用户的登陆
	 
	public void userLoginRecrod(LoginRecord loginRecord) {
		
		//默认设置
		jdbcTemplate.update(UserSQL.userLoginRecordSQL,
							new Object[]{loginRecord.getUserId(),loginRecord.getuLREIp(),loginRecord.getuLREIIMEI(),loginRecord.getuLREFlag()});
	}
	
	public boolean accreditationCargoOwner(Company company, Integer userId) {
		
		boolean flag = false;
		if(CommonUtils.isFit4ImproveCompany(company)){
			company.setInFoFlag(1);
		}else{
			company.setInFoFlag(0);
		}
		int result=-1;
		result = jdbcTemplate.update(UserSQL.accreDTTCargoOwnerSQL,
				new Object[]{company.getCompName(),company.getCompTaxNo(),company.getCompWorkPhone(),company.getCompCPPicURL(),
					         company.getCompSWDJFBURL(),company.getCompKHXKZURL(),company.getCompXQSWMBURL(),company.getCompProvice(),
					         company.getCompCity(),company.getCompStreet(),company.getInFoFlag(),userId},
		        new int[]{java.sql.Types.VARCHAR,java.sql.Types.CHAR,java.sql.Types.CHAR,java.sql.Types.VARCHAR,
						  java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,
						  java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.INTEGER,java.sql.Types.INTEGER});
		if(result>0){
			flag = true;
		}
		return flag;
	}
	*/
	
	/**
	 * 查找userID
	 */
	@SuppressWarnings("unchecked")
	public User getUserId(String userMobile) {
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		userList = (ArrayList<User>)jdbcTemplate.query(UserSQL.lastInsertUserIDSQL,
														new Object[]{userMobile}, 
														new int[]{java.sql.Types.CHAR}, 
														new UserRowMapper());
		 if (!userList.isEmpty()) {
			  user = userList.get(0);
		    }
		return user;
	}
	
	/**
	 * 用户的基本信息是否完善（车主和货主都有共用）
	 
	@SuppressWarnings("unchecked")
	public int validateUserBaseFlag(Integer userId) {
		
		int result = -1;
		List list = null;
		list =jdbcTemplate.query(UserSQL.validateUserBaseFlagSQL, 
											new Object[] {userId},
											new int[]{java.sql.Types.INTEGER},
											new RowMapper() {
												public Object mapRow(
														ResultSet rs, int arg1)
														throws SQLException {
													int r = rs.getInt("INFOFLAG");
													return r;
												}
											});
		
		if(!list.isEmpty()){	
			result = (Integer) list.get(0);
		}
		return result;
	}
*/
	/**
	 *个人货主是否完善信息
	 
	@SuppressWarnings("unchecked")
	public int validateCagoFlag(Integer userId) {
		int result = -1;
		ArrayList<CargoOwner> owners= new ArrayList<CargoOwner>();
		CargoOwner cargoOwner = null;
		owners = (ArrayList<CargoOwner>)jdbcTemplate.query(UserSQL.validateCagoFlagSQL,
														new Object[]{userId}, 
														new int[]{java.sql.Types.INTEGER}, 
														new CargoOwnerRowMapper());
		 if (!owners.isEmpty()) {
			 cargoOwner = owners.get(0);
			 result = cargoOwner.getInFoFlag();
		    }
		return result;
	}
*/
	/**
	 * 企业货主是否完善信息
	 */
	@SuppressWarnings("unchecked")
	public int validateCagoCompFlag(Integer userId) {
		int result = -1;
		List list = new ArrayList();
		list =jdbcTemplate.query(UserSQL.validateCagoCompFlagSQL, 
											new Object[] {userId},
											new int[]{java.sql.Types.INTEGER},
											new RowMapper() {
												public Object mapRow(
														ResultSet rs, int arg1)
														throws SQLException {
													int r = rs.getInt("INFOFLAG");
													return r;
												}
											});
		
		if(!list.isEmpty()){	
			result = (Integer) list.get(0);
		}
		return result;
	}

	/**
	 * 车主的信息是否完善
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int validateCarUserFlagSQL(Integer userId) {
		int result = -1;
		List list = new ArrayList();
		list =jdbcTemplate.query(UserSQL.validateCarUserFlagSQL, 
											new Object[] {userId},
											new int[]{java.sql.Types.INTEGER},
											new RowMapper() {
												public Object mapRow(
														ResultSet rs, int arg1)
														throws SQLException {
													int r = rs.getInt("INFOFLAG");
													return r;
												}
											});
		
		if(!list.isEmpty()){	
			result = (Integer) list.get(0);
		}
		return result;
	}
	/**
	 * 初始化个人货主信息表
	 */
	public int initCargoUser(Integer userId) {

		int result=-1;
		result = jdbcTemplate.update(UserSQL.initCargoUserSQl,
									new Object[]{userId});
		
		return result;
	}

	/**
	 * 初始化企业货主信息表
	 
	public int initCompUser(Integer userId) {
		int result=-1;
		result = jdbcTemplate.update(UserSQL.initCompUserSQL,
									new Object[]{userId});
		
		return result;
	}
*/

	/**
	 * 初始化企业车主信息表
	 */
	public int initCarUser(Integer userId) {
		int result=-1;
		result = jdbcTemplate.update(UserSQL.initCarUserSQL,
									new Object[]{userId});
		
		return result;
	}

	/**
	 * 检查IDcard身份证是否有效
	 */
	@SuppressWarnings("unchecked")
	public boolean validateUserIDCard(String userIDCard) {
		
		boolean flag = true;
		ArrayList<User> userList = new ArrayList<User>();
		userList = (ArrayList<User>)jdbcTemplate.query(UserSQL.validateUserIDCardSQL,
														new Object[]{userIDCard}, 
														new int[]{java.sql.Types.CHAR}, 
														new UserRowMapper());
		 if (!userList.isEmpty()) {
			 flag = false;
		    }
		return flag;
	}

	/**
	 * 完善车主基本个人信息
	 
	public boolean improveCarUser(User user,CarOwner owner, Integer userId) {
		
		boolean flag = false;
		if(CommonUtils.isFit4ImproveCarUser(owner, user)){
			user.setInfoFlag(1);//用户个人信息已经完善
		}else{
			user.setInfoFlag(0);//用户个人信息未完善
		}
		int result1=-1;
		int result2=-2;
		//完善用户的姓名用户的地身份证等信息  1
		result1 = jdbcTemplate.update(UserSQL.improveCarUserSQL1, 
					new Object[]{user.getUserName(),user.getUserIDCardURLP(),user.getUserIDCardURLN(),user.getUserFlag(),
								 user.getUserIDCard(),user.getUserAddr(),user.getInfoFlag(),userId},
					new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.INTEGER,
							  java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.INTEGER});
		//完善车主的联系手机号码  2
		result2 = jdbcTemplate.update(UserSQL.improveCarUserSQL2, 
				new Object[]{owner.getCaroMobile(),userId},
				new int[]{java.sql.Types.VARCHAR,java.sql.Types.INTEGER});
		
		if(result1>0&&result2>0){
			flag = true;
		}
		return flag;
	}
*/
	/**
	 *  切换身份
	 *  由货主--->车主
	 */
	public boolean changeCargo2Car(Integer userId, Integer carUserFlag) {
		boolean flag = false;
		int result = -1;
		//已经直接将userFlag置为0
		result = jdbcTemplate.update(UserSQL.cargo2carSQL, 
				                   new Object[]{userId},
				                   new int[]{java.sql.Types.INTEGER});
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 * 切换身份
	 * 由车主--->货主
	 */
	public boolean changeCar2Cargo(Integer userId, Integer cargoFlag,Integer companyFlag) {
		
		boolean flag = false;
		int result = -1;
		//已经直接将userFlag置为0
		result = jdbcTemplate.update(UserSQL.car2cargoSQL, 
				                   new Object[]{userId},
				                   new int[]{java.sql.Types.INTEGER});
		if(result>0){
			flag = true;
		}
		return flag;
	}

	/**
	 * 查看货主信息
	 */
	@SuppressWarnings("unchecked")
	public CargoOwner getCargoUserBaseMeg(Integer userId) {

		ArrayList<CargoOwner> cargoOwnerList = new ArrayList<CargoOwner>();
		CargoOwner cargoOwner = null;
		cargoOwnerList = (ArrayList<CargoOwner>)jdbcTemplate.query( UserSQL.getCargoUserBaseMegSQL,
														new Object[]{userId}, 
														new int[]{java.sql.Types.INTEGER}, 
														new CargoOwnerRowMapper());
		
		if(!cargoOwnerList.isEmpty()){
			cargoOwner = cargoOwnerList.get(0);
		}
		
		return cargoOwner;
	}

	/**
	 * 获取个人的信息
	 */
	@SuppressWarnings("unchecked")
	public User getUserBaseMeg(Integer userId) {
		
		ArrayList<User> userList = new ArrayList<User>();
		User user = null;
		userList = (ArrayList<User>)jdbcTemplate.query( UserSQL.getUserBaseMegSQL,
														new Object[]{userId}, 
														new int[]{java.sql.Types.INTEGER}, 
														new UserBaseRowMapper());
		
		if(!userList.isEmpty()){
			user = userList.get(0);
		}
		
		return user;
	}

	/**
	 * 查看企业信息
	 */
	@SuppressWarnings("unchecked")
	public Company getCompBaseMeg(Integer userId) {
		
		ArrayList<Company> companyList = new ArrayList<Company>();
		Company company = null;
		companyList = (ArrayList<Company>)jdbcTemplate.query( UserSQL.getCompanyBaseMegSQL,
														new Object[]{userId}, 
														new int[]{java.sql.Types.INTEGER}, 
														new CompanyRowMapper());
		
		if(!companyList.isEmpty()){
			company = companyList.get(0);
		}
		
		return company;
	}

	/**
	 * 上传头像
	 
	public boolean uploadPicture(User user) {
		
		boolean flag =false;
		int result1 = -1;
		result1 = jdbcTemplate.update(UserSQL.improveCarUserSQL1, 
				new Object[]{user.getUserPicUrl(),user.getUserId()},
				new int[]{java.sql.Types.VARCHAR,java.sql.Types.INTEGER});
		
		if(result1>0){
			flag = true;
		}
		
		return flag;
	}

	*/
}
