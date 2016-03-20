package com.atayun.hgs.wuliu.dao;

import com.atayun.hgs.wuliu.po.User;
/**
 * UserDao 
 * user（tb_user）表的持久层接口定义
 * @author HWJ
 * @date   2015-05-11
 */
public interface UserDao {
	
	//向第三方请求发送验证码
	public boolean sendSMSRequest(String phone,String content);
	
	//登陆，参数为手机号码、密码
	public User loginUserByMobile(String userMobile,String userPassword);
	
	//注册成功后根据手机号，返回UserID
	public User getUserId(String userMobile);
	
	//登陆，参数为用户名、密码
	public User loginUserByName(String userName,String userPassword);

	//登陆手机号验证手机号码是否已经被注册，参数为手机号码
	public User validateMobile(String userMobile);
	
	//登陆用户名验证，参数为用户名
	public User validateName(String userName);
	
	//用户登陆记录
	//public  void userLoginRecrod(LoginRecord loginRecord);
	
	//默认注册，只有必填项手机号，密码等信息
	public void registerUser(User user);
	
	//找回密码,输入手机号，重新设置密码
	public void resetUserPassword(String userMobile,String userPassword);
	
	public User getRemainder(int userId);
	
	//根据手机号码查询密码验证码
	public String getUserVerifyCode(String userMobile);
	
	//完善货源个人信息
	//public boolean improveCargoUser(CargoOwner owner,User user,Integer userId); 
	
	//完善车主个人信息
	//public boolean improveCarUser(User user,CarOwner owner, Integer userId);
	
	//认证企业货主
	//public boolean accreditationCargoOwner(Company company,Integer userId);
	
	//用户的个人基本信息是否完善
	//public int validateUserBaseFlag(Integer userId);
	
	//查看(个人货主)信息是否完善的标志
	//public int validateCagoFlag(Integer userId);
	
	//查看企业信息是否完善，这里是针对企业货主
	//public int validateCagoCompFlag(Integer userId);
	
	//车主信息是否完善
	//public int validateCarUserFlagSQL(Integer userId);
	
	//初始化货主信息表
	//public int initCargoUser(Integer userId);
	
	//初始化企业货主信息表
	//public int initCompUser(Integer userId);
	
	//初始化车主信息表
	//public int initCarUser(Integer userId);
	
	//验证身份证号是否已经存在被使用
	//public boolean validateUserIDCard(String userIDCard);
	
	//切换车主身份,根据carUserFlag的返回值判断是否需要初始化车主的相关信息表    由货主--->车主
	//public boolean changeCargo2Car(Integer userId,Integer carUserFlag);
	
	//切换货主身份,根据cargoFlag,companyFlag的返回值判断是否需要初始化货主的相关信息表  由车主--->货主
	//public boolean changeCar2Cargo(Integer userId,Integer cargoFlag,Integer companyFlag);
	
	//查看货主基本信息
	//public CargoOwner getCargoUserBaseMeg(Integer userId);
	
	///企业信息查看
	//public Company getCompBaseMeg(Integer userId);
	
	//上传头像
	//public boolean uploadPicture(User user);
	
	//查看个人基本信息
	public User getUserBaseMeg(Integer userId);
	
}
