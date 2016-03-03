package com.atayun.hgs.wuliu.utils.sms.dxb;
import java.net.URLEncoder;;
@SuppressWarnings("unused")
public class sendMessage {

	public static void main(String[] args) {
		String username = "hwjv5";//短信宝帐户名
		String pass = new MD5().Md5("job774671");//短信宝帐户密码，md5加密后的字符串
		String phone = "15274955704";//电话号码
		String code = (int)((Math.random()*9+1)*100000)+"";
		String contents = "【长沙云端物流】您的验证码为"+code+"，在3分钟内有效。";
		@SuppressWarnings("deprecation")
		String content = java.net.URLEncoder.encode(contents);//发送内容
		httpSend send = new httpSend("http://www.smsbao.com/sms?u="+username+"&p="+pass+"&m="+phone+"&c="+content);
		try {
			send.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

