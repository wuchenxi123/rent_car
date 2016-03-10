package com.atayun.hgs.wuliu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils{

	/**
	 * 使用正则表达式，判断字符串是否为手机号码
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {

		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		//System.out.println(m.matches() + "---");
		return m.matches();

	}
	//
	/**
	 * 使用正则表达式判断是否符合用户名规则
	 * @param userName
	 * @return
	 */
	public static boolean isUserName(String userName) {

		Pattern p = Pattern.compile("^[\\w\\u4e00-\\u9fa5]+$");
		//^[\\w\\u4e00-\\u9fa5]+$包含中文		
		//^[\u4e00-\u9fa5]+$只含中文
		Matcher m = p.matcher(userName);
		//System.out.println(m.matches() + "---");
		return m.matches();

	}
	
	/**
	 * 使用正则表达式判断是否符合密码规则
	 * 这里的密码的规则：密码的长度大于6，只能包含大小写字母或者数字
	 * @param userPassword
	 * @return
	 */
	public static boolean isPassword(String userPassword) {

		//这里的密码的规则：密码的长度大于6，只能包含大小写字母或者数字
		//1.判断长度
		if(userPassword!=null&&userPassword.length()>=6){
			
			Pattern p = Pattern.compile("^[a-zA-Z0-9]+$");
			Matcher m = p.matcher(userPassword);
			//System.out.println(m.matches() + "---");
			return m.matches();
			
		}else{
			return false;
		}
		
	}
	/**
	 * 判断登陆的手机号和密码是否符合要求
	 * @param userMobile
	 * @param userPassword
	 * @return
	 */
	public static String fitRequirement(String userMobile,String userPassword) {
		
		String result = "";
		if(userMobile.trim().length()==0){
			return result="40";//手机号码为空
		}
		if(userPassword.trim().length()==0){
			return result="50";//密码为空
		}
		if(!CommonUtils.isMobileNO(userMobile)){
			return result="30";//手机号不符合规范
		}else{
			result = "200";
		}
		return result;
	}
	/**
	 * 检查个人信息是否符合完善的要求
	 * @param userMobile
	 * @param userPassword
	 * @return
	 */
	
	
	
	
	/**
	 * 只要是企业认证信息中的任何一项没填，那么认为企业信息未完善
	 * @param company
	 * @return
	 */
	
	//车牌正则表达验证
	public static boolean isLpnum(String lpNum){
		// 查找以Java开头,任意结尾的字符串
		Pattern pattern = Pattern
				.compile("^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
		/**
		 * ^[\u4e00-\u9fa5]{1}代表以汉字开头并且只有一个，这个汉字是车辆所在省的简称
		 * 
		 * [A-Z]{1}代表A-Z的大写英文字母且只有一个，代表该车所在地的地市一级代码
		 * 
		 * [A-Z_0-9]{5}代表后面五个数字是字母和数字的组合。
		 */
		Matcher matcher = pattern.matcher(lpNum.trim());
		return matcher.matches(); // 当条件满足时，将返回true，否则返回false
	}
	
	
}

