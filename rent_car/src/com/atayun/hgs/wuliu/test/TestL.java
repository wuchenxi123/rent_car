package com.atayun.hgs.wuliu.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestL {

	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
		System.out.println(format.format(date));
	}
}
