package com.atayun.hgs.wuliu.utils.sms.dxb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpSend {
	String urlString;

	/*
	 * public static void main(String[] args) throws Exception { HttpTest client
	 * = new HttpTest(网址); client.run(); }
	 */
	public httpSend(String urlString) {
		this.urlString = urlString;
	}

	public boolean send() throws Exception {
		boolean flag = false;
		// 生成一个URL对象
		URL url = new URL(urlString);
		// 打开URL
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		// 得到输入流，即获得返回值
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream()));
		String line;
		// 读取返回值，进行判断
		while ((line = reader.readLine()) != null) {
			int result = Integer.valueOf(line);
			if (result == 0) {
				System.out.println("发送成功");
				flag = true;
			}
			/*
			 * else{ System.out.println(line); }
			 */
		}
	
		return flag;
	}
}