package com.atayun.hgs.wuliu.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;

import com.atayun.hgs.wuliu.service.UserService;
import com.atayun.hgs.wuliu.utils.CountDays;

public class test {

	/*
	 * private static UserService userService;
	 * 
	 * @BeforeClass public static void setUpBeforeClass() throws Exception { try
	 * { ApplicationContext cxt = new
	 * ClassPathXmlApplicationContext("beans.xml"); personService =
	 * (PersonService) cxt.getBean("personService"); } catch (RuntimeException
	 * e) { e.printStackTrace(); } }
	 */
	public static void main(String[] args) {
/*
			String dateString = "2016-03-04 00:00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Calendar calendar = GregorianCalendar.getInstance();
			try {
				calendar.setTime(sdf.parse(dateString));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(calendar.get(Calendar.DATE));
			System.out.println(calendar.get(Calendar.MONTH) + 1);
			System.out.println(calendar.get(Calendar.YEAR));
*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
		Date date = null;
	    try {
			date = sdf.parse("2016-03-12 11:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Date end=new Date();  //系统日期
	       long days=new CountDays().countDays(date,end);  //计算当前租车天数
	       System.out.println(days);
	}

}