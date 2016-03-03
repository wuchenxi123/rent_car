package com.atayun.hgs.wuliu.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class OutJsonUtils {
	
	public static void putOutJson(JSONObject jsonObject,HttpServletRequest request,HttpServletResponse response){
		PrintWriter out = null;
		response.setContentType("application/json;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();					
			out.write(jsonObject.toString());					
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.flush();//缓冲区清空的方法。 一些输出流的实现是带缓冲的，你往里面写，不一定立刻输出而是要等缓冲区满或flush()
				out.close();
			}
		}
	}

}
