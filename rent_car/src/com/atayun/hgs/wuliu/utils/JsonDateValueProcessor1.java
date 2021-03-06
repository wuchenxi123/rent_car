package com.atayun.hgs.wuliu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor1 implements JsonValueProcessor {
	private String datePattern = "yyyy-MM-dd HH:mm:ss";// 日期格式

	public JsonDateValueProcessor1() {
		super();
	}

	// 构造函数
	public JsonDateValueProcessor1(String format) {
		super();
		this.datePattern = format;
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		// TODO Auto-generated method stub
		return process(value);
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		// TODO Auto-generated method stub
		return process(value);
	}

	private Object process(Object value) {
		try {
			if (value instanceof Date) {
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern,
						Locale.CHINA);
				return sdf.format((Date) value);
			}
			return value == null ? "" : value.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePaterns) {
		this.datePattern = datePaterns;
	}

}
