package com.atayun.hgs.wuliu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atayun.hgs.wuliu.dao.CarInformationDao;
import com.atayun.hgs.wuliu.dao.UserDao;
import com.atayun.hgs.wuliu.po.CarOwner;
import com.atayun.hgs.wuliu.po.CargoOwner;
import com.atayun.hgs.wuliu.po.Company;
import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.utils.MD5;

/**
 * UserService
 * 概述：
 * 用户的业务逻辑，调用UserDaoImpl的持久层进行业务逻辑的实现
 * <p>
 * 1.用户发送短信
 * 2.用户手机号码登陆
 * 3.用户使用用户名登陆
 * 4.验证手机号是否注册
 * 5.验证用户名是否已存在
 * 6.用户注册
 * </p>
 * @author HWJ
 * @version 0.5, 2015/05/11
 */
@Transactional
@Service
public class UserService {
	
	/** spring 注入方式 将userDao注入*/
	@Autowired
	private UserDao userDao;

	@Autowired
	private CarInformationDao carInformationDao;
	/**
	 * sendSMSRequest:发送短信验证码
	 * @param phone
	 * @param content
	 * @return boolean
	 */
	public boolean sendSMSRequest(String phone, String content){
		
		boolean flag = false;	
		flag = userDao.sendSMSRequest(phone, content);
		return flag;
		
	}
	/**
	 * loginUserByMobile：根据用户手机号和密码进行登陆验证
	 * @param userMobile
	 * @param userPassword
	 * @return User
	 */
	public User loginUserByMobile(String userMobile, String userPassword){
		
//		String userVerifyCode = userDao.getUserVerifyCode(userMobile);//获取密码验证码
//		userPassword = MD5.getMD5(userVerifyCode+userPassword);
		return userDao.loginUserByMobile(userMobile, userPassword);
		
	}
	/**
	 * loginUserByName：根据用户名和密码进行登录验证
	 * @param userName
	 * @param userPassword
	 * @return User
	 */
	public User loginUserByName(String userName, String userPassword){
		
		//userDao.userLoginRecrod(loginRecord);
		return userDao.loginUserByName(userName, userPassword);
		
	}
	/**
	 * validateMobile：验证手机号是否已经注册
	 * @param userMobile
	 * @return User
	 */
	public User validateMobile(String userMobile){
		
		return userDao.validateMobile(userMobile);
		
	}
	/**
	 * validateName:验证用户的用户名是否存在
	 * @param userName
	 * @return User
	 */
	public User validateName(String userName){
		
		return userDao.validateName(userName);
		
	}
	/**
	 * registerUser用户的默认注册方式
	 * @param user
	 */
	public User registerUser(User user){
		
		userDao.registerUser(user);
		
		return userDao.getUserId(user.getUserMobile());
	}
	/**
	 * 重新设置密码
	 * @param userMobile
	 * @param userPassword
	 */
	public void resetUserPswByMObile(String userMobile, String userPassword){
		
		String userVerifyCode = userDao.getUserVerifyCode(userMobile);//获取密码验证码
		userPassword = MD5.getMD5(userVerifyCode+userPassword);
		userDao.resetUserPassword(userMobile, userPassword);
		
	}

	/**
	 * 完善用户信息
	 */
	public boolean improveUserMessage(CargoOwner owner,User user,Integer userId) {
		
		return userDao.improveCargoUser(owner, user, userId);
	}
	/**
	 * 企业货主认证
	 * @param company
	 * @param userId
	 * @return
	 */
	public boolean accreditationCargoOwner(Company company, Integer userId) {
		boolean flag = false;
		flag = userDao.accreditationCargoOwner(company, userId);
		return flag;
	}
	
	/**
	 * 个人基本信息完善标志
	 * @param userId
	 * @return
	 */
	public int validateUserBaseFlag(Integer userId){
		
		return userDao.validateUserBaseFlag(userId);
	}
	
	/**
	 * 个人货主是否完善信息
	 * @param userId
	 * @return
	 */
	public int validateCagoFlag(Integer userId){
		
		return userDao.validateCagoFlag(userId);
	}
	/**
	 * 企业货主是否完善信息
	 * @param userId
	 * @return
	 */
	public int validateCagoCompFlag(Integer userId){
		
		return userDao.validateCagoCompFlag(userId);
	}
	/**
	 * 车主的个人信息是否完善
	 * @param userId
	 * @return
	 */
	public int validateCarUserFlagSQL(Integer userId){
		return userDao.validateCarUserFlagSQL(userId);
	}
	
	/**
	 * 初始化表
	 * @param userId
	 * @param userFlag
	 * @return
	 */
	public boolean initTable(Integer userId,Integer userFlag){
		
		boolean flag = false;
		int result1 = -1;
		int result2 = -1;
		int result3 = -1;
		int result4 = -1;
		if(userFlag==0){//车主
			result1 = userDao.initCarUser(userId);
			result2 = carInformationDao.initCarInfoMation(userId);
			if(result1>0&&result2>0){
				flag = true;
			}
		}else{//货主
			result3 = userDao.initCargoUser(userId);
			result4 = userDao.initCompUser(userId);
			if(result3>0&&result4>0){
				flag = true;
			}
			
		}
		
		return flag;
	}
	/**
	 * 查看用户的身份号是否已经存在
	 * @param userIDCard
	 * @return
	 */
	public boolean validateUserIDCard(String userIDCard) {
		return userDao.validateUserIDCard(userIDCard);
	}
	
	/**
	 * 完善车主个人信息
	 * @param user
	 * @param owner
	 * @param userId
	 * @return
	 */
	public boolean improveCarUser(User user,CarOwner owner, Integer userId){
		
		return userDao.improveCarUser(user, owner, userId);
	}
	
	/**
	 *  由货主--->车主
	 * @param userId
	 * @param carUserFlag
	 * @return
	 */
	public boolean changeCargo2Car(Integer userId, Integer carUserFlag) {
		boolean flag = false;
		boolean changeFlag = false;
		int initCarUserFlag = -1;
		int initCarInfoUserFlag = -1;
		changeFlag = userDao.changeCargo2Car(userId, carUserFlag);
		//如果carUserFlag=-1说明还没有初始化车主相关的表，需要进行初始化
		if(carUserFlag==-1){	
			initCarUserFlag = userDao.initCarUser(userId);
			initCarInfoUserFlag = carInformationDao.initCarInfoMation(userId);
			if(changeFlag&&initCarUserFlag>0&&initCarInfoUserFlag>0){
				flag = true;
			}
		}else{
			if(changeFlag){
				flag = true;
			}
		}

		return flag;
	}
	/**
	 * 身份切换
	 * 由车主--->货主
	 * @param userId
	 * @param cargoFlag
	 * @param companyFlag
	 * @return
	 */
	public boolean changeCar2Cargo(Integer userId, Integer cargoFlag,Integer companyFlag) {
		boolean flag = false;
		boolean changeFlag = false;
		int initCargoUserFlag = -1;
		int initCompUserFlag = -1;
		changeFlag = userDao.changeCar2Cargo(userId, cargoFlag, companyFlag);
		//如果carUserFlag=-1说明还没有初始化车主相关的表，需要进行初始化
		if(cargoFlag==-1&&companyFlag==-1){	
			initCargoUserFlag = userDao.initCargoUser(userId);
			initCompUserFlag = userDao.initCompUser(userId);
			if(changeFlag&&initCargoUserFlag>0&&initCompUserFlag>0){
				flag = true;
			}
		}else{
			if(changeFlag){
				flag = true;
			}
		}

		return flag;
	}
	
	/**
	 * 获取个人信息
	 * @param userId
	 * @return
	 */
	public User getUserBaseMeg(Integer userId) {
		
		return userDao.getUserBaseMeg(userId);
	}
	
	/**
	 * 查看企业信息
	 * @param userId
	 * @return
	 */
	public Company getCompBaseMeg(Integer userId) {
		
		return userDao.getCompBaseMeg(userId);
	}
	
	/**
	 *  查看货主信息
	 * @param userId
	 * @return
	 */
	public CargoOwner getCargoUserBaseMeg(Integer userId) {
		
		return userDao.getCargoUserBaseMeg(userId);
	}
	
	public boolean uploadPicture(User user) {
		
		return userDao.uploadPicture(user);
	}
	
}
