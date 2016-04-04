package com.atayun.hgs.wuliu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountDays {
    
	public long countDays(Date date,Date end){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String begin=sdf.format(date);
		// String begin=date;
		//  String end=sdf.format(new Date());  //系统日期
		  //	 Date passDate=simpleDateFormat.parse(defaultDate);
		 
	            //间隔天数
	            long days=0;
				try {
					 
//					days = (sdf.parse(end).getTime()-sdf.parse(begin).getTime())/(24*60*60*1000);
//					long re=(sdf.parse(end).getTime()-sdf.parse(begin).getTime())%(24*60*60*1000);
					days = (end.getTime()-sdf.parse(begin).getTime())/(24*60*60*1000);
					long re=(end.getTime()-sdf.parse(begin).getTime())%(24*60*60*1000);
 					if(re!=0){
						days=days+1;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return days;
	       
	      
        
	}
//	public static void main(String[] args) {
//		CountDays c=new CountDays();
//	 String end=sdf.format(new Date());  //系统日期
//		System.out.println(c.countDays("2016-3-3 12:12"),end);
//
//	}
}
