package com.atayun.hgs.wuliu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atayun.hgs.wuliu.dao.UserDao;
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
		
		String userVerifyCode = userDao.getUserVerifyCode(userMobile);//获取密码验证码
		System.out.println(userVerifyCode+userPassword+"------------");
		userPassword = MD5.getMD5(userVerifyCode+userPassword);
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
		System.out.println(userVerifyCode+"=========="+userPassword+"---");
		userDao.resetUserPassword(userMobile, userPassword);
		
	}

	
	/**
	 * 查看用户的身份号是否已经存在
	 * @param userIDCard
	 * @return
	 */
//	public boolean validateUserIDCard(String userIDCard) {
//		return userDao.validateUserIDCard(userIDCard);
//	}
	
}
