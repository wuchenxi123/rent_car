<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <form action="user.do?method=register" method="post">
  
   	<input type="text" name="phone" /><br>
   	<input type="text" name="content" /><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	<br>
   	<h2>test lgoinlllllll</h2>
    <form action="test.do?method=testLogin" method="post">
   	手机号码：<input type="text" name="userAccount" /><br>
   	用户密码：<input type="text" name="userPassword" /><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	
   	<h2>test 获取手机号码</h2>
    <form action="test.do?method=testGetMobile" method="post">
   	<input type="submit" value="提交"/>
   	
   	</form>
   	
   	<br>
   	<h2>test lgoin2</h2>
    <form action="user.do?method=login" method="post">
   	用户账号：<input type="text" name="userAccount" /><br>
   	用户密码：<input type="text" name="userPassword" /><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	<br>
   	<h2>test 验证手机号码的有效性step1</h2>
    <form action="user.do?method=validateMobile" method="post">
   	手机号码：<input type="text" name="userMobile" /><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	<br>
   	
   	<h2>test 验证码输入和用户输入默认注册</h2>
    <form action="user.do?method=register" method="post">
   	手机号码：<input type="text" name="userMobile" /><br>
   	密码：<input type="text" name="userPassword"/><br>
   	用户身份号：<input type="text" name="userIDCard" /><br>
   	驾驶证号：<input type="text" name="userLicense"/><br>
   	短信验证码：<input type="text" name="userVerifyCode"/><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	<br>
 
   	<br>
   	====================================================================
   	文件上传
   	
   	<form action="test.do?method=upload" method="post" enctype="multipart/form-data" >
		选择文件：<input type="file" name="file">	
		<input type="submit" value="上传" >			
	</form>
	<br><br>
	
	<h2>test 找回密码，第一步，输入注册时候的手机号码</h2>
    <form action="user.do?method=validateMobileAtFindPsw" method="post">
   	手机号码：<input type="text" name="userMobile" /><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	<br><br>
   	
   	<h2>test 重置密码</h2>
    <form action="user.do?method=resetUserPassword" method="post">
   	短信验证码：<input type="text" name="code"/><br>
   	手机号码：<input type="text" name="userMobile" /><br>
   	重置密码：<input type="text" name="userPassword"/><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	
   	<h2>test 重新获取验证码</h2>
    <form action="user.do?method=reValidateMobile" method="post">
   	<input type="submit" value="提交"/>
   	
   	</form>
   	<br><br>
   	<h2>test 获取所有货源</h2>
    <form action="cargoInfo.do?method=getAllCargoInfo" method="post">
   	<input type="submit" value="提交"/>
   	</form>
   	
   	<br><br>
   	
   	<h2>test 根据起点和终点查询货源</h2>
    <form action="cargoInfo.do?method=getAllCargoInfoByStartAndEnd" method="post">
    	起点：<input type="text" name="start" /><br>
    	终点：<input type="text" name="end" /><br>
   	<input type="submit" value="提交"/>
   	</form>
   	
   	<br><br>
   	
   	<h2>test 根据货主的ID获取发布的货源</h2>
    <form action="cargoInfo.do?method=getMyCargoInfoById" method="post">
 	 货主的ID：<input type="text" name="userId"/><br>
   	<input type="submit" value="提交"/>
   	</form>
   	
   	
   	<hr/>
   	 <form action="rent.do?method=selectCar" method="post">
   	<input type="submit" value="提交"/>
   	</form>
   	<hr>
   	 <form action="rent.do?method=rentCar" method="post">
    userid:<input type=text name=userId value="1"><br>
   takeCartype <input type=text name=carTaketype value="0"><br>
    rentinfo.rentPlace<input type=text name=rentPlace value="长沙火车站"><br>
    takeTime<input type=text name=takeTime value="2015-04-03 16:13"><br>
    rentinfo.rentDays<input type=text name=rentDays value="3"><br>
    carid<input type=text name=carId value="1"><br>
    orderPrice<input type=text name=orderPrice value="200"><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	
  </body>
</html>
