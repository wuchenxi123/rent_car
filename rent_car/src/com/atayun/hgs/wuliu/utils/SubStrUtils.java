package com.atayun.hgs.wuliu.utils;
/**
 * 工具类：实现字符串中关键词之前的字符串截取
 * @author chenlei
 *
 */
public final class SubStrUtils {
/**
 *截取方法 
 * @param str--目标字符串
 * @param key--关键字
 * @return	截取的结果
 */
	public static String SubStr(String str, String key) {
		// 将字符串转换为字符数组
		char[] ch = str.trim().toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (String.valueOf(ch[i]).equals(key)) {
				str = str.substring(0, i + 1);
				break;
			} else {
				str = str.trim();
			}
		}
		return str;
	}
}
