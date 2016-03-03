package com.atayun.hgs.wuliu.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.atayun.hgs.wuliu.po.User;
import com.atayun.hgs.wuliu.service.UserService;
import com.atayun.hgs.wuliu.utils.IpUtils;
import com.atayun.hgs.wuliu.utils.CommonUtils;
import com.atayun.hgs.wuliu.utils.MD5;

@Controller
@RequestMapping("/test.do")
public class TestController {	
	
	@Resource
	private UserService userService;
	
	@RequestMapping(params="method=test",method={RequestMethod.POST})
	public String test(){	
		return "index";
	}
	@RequestMapping(params="method=upload")
	public String upload(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
		
		CommonsMultipartResolver multipartResolver  = new CommonsMultipartResolver(request.getSession().getServletContext());
		
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest  multiRequest = (MultipartHttpServletRequest)request;
			
			Iterator<String>  iter = multiRequest.getFileNames();
			while(iter.hasNext()){
					MultipartFile file = multiRequest.getFile((String)iter.next());
				if(file != null){
					String fileName = new Date().getTime() + file.getOriginalFilename();
					request.setAttribute("name", fileName);
					String path = request.getSession().getServletContext().getRealPath("/")+fileName;
					
					System.out.println(path);
					//String path = "D:/" + fileName;				
					File localFile = new File(path);					
					file.transferTo(localFile);
				}
				
			}

		}
		return "success";
	}
	@RequestMapping(params ="method=testLogin",method = RequestMethod.POST)
	public void loginUser(String userAccount, String userPassword,HttpServletRequest request,HttpServletResponse response){
		
		PrintWriter out = null;
		response.setContentType("application/json;charset=UTF-8");  
		//response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		String sessionId = "";
		int userId = -1;
		//测试使用start
			//如果是从网页进行测试那么将再次先对密码进行MD5加密
			if(userPassword.length()!=32&&CommonUtils.isPassword(userPassword)){
				if(userPassword.length()<=18){
					System.out.println("login===原来的是==="+userPassword);
					userPassword = MD5.getMD5(userPassword);		
				}
			}
		//暂时有个问题
		//测试使用end
		
		//添加IP记录
		System.out.println("===================IP:"+IpUtils.getIpAddr(request));	
			
		String resultState = "10";// 未知错误
		//1.判断用户账号是否为空
		if (userAccount.trim().length() == 0) {
			resultState = "40";// 用户账号为空
		} else if (CommonUtils.isMobileNO(userAccount)) {// 如果符合手机号码的规范
			// 判断用户输入的手机号是否已经注册
			if (userService.validateMobile(userAccount) == null) {
				resultState = "60";// 账号不存在，手机号未注册
			} else if (userPassword.trim().length() == 0) {// 密码为空
				resultState = "50";// 密码为空
			} else {
				User user = userService.loginUserByMobile(userAccount,userPassword);
				if (user != null) {
					resultState = "200";// 登陆成功
					request.getSession().setAttribute("loginUserMobile", user.getUserMobile());
					sessionId = request.getSession().getId();
					userId = user.getUserId();
					System.out.println("seeeionId===>"+sessionId);
				} else {
					resultState = "20";// 密码错误
				}
			}
		}else{
			resultState = "30";// 用户手机号输入不规范
		}
		jsonObject.put("loginState", resultState);
		jsonObject.put("sessionId", sessionId);
		jsonObject.put("userId", userId);
		try {
			out = response.getWriter();
			out.write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭out
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	@RequestMapping(params="method=testGetMobile",method={RequestMethod.POST})
	public void getUserMobile(HttpServletRequest request,HttpServletResponse response){
		
		PrintWriter out = null;
		response.setContentType("application/json;charset=UTF-8");  
		JSONObject jsonObject = new JSONObject();
		String userMobile = (String) request.getSession().getAttribute("loginUserMobile");
		System.out.println("===================getUserMobile尝试获取手机号："+userMobile);
		if(userMobile!=null){
			jsonObject.put("testState", "200");//获取到放在session中的手机号
		}else{
			jsonObject.put("testState", "10");//没有获取到
		}
		try {
			out = response.getWriter();
			out.write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭out
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
}
