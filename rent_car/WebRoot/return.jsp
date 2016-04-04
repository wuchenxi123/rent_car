<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <form action="rent.do?method=rentCar" method="post">
    userid:<input type=text name=userid value="1"><br>
   takeCartype <input type=text name=takeCartype value="0"><br>
    rentinfo.rentPlace<input type=text name=rentinfo.rentPlace value="长沙火车站"><br>
    takeTime<input type=text name=takeTime value="2015-04-03 16:13"><br>
    rentinfo.rentDays<input type=text name=rentinfo.rentDays value="3"><br>
    carid<input type=text name=carid value="1"><br>
    orderPrice<input type=text name=orderPrice value="200"><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	
   		 		//付款
 	 <form action="rent.do?method=payOrder" method="post">
    rent orderid:<input type=text name=orderid value="1"><br>
                 <input type=text name=payType value="1"><br>
   	<input type="submit" value="提交"/>
   	</form>
   	
   	当前用户获取未还车订单信息
    <form action="return.do?method=getOrders" method="post">
    userid:<input type=text name=userid value="1"><br>
  
   	<input type="submit" value="提交"/>
   	</form>
   	 		<br>
   	 		//获取订单当前费用 及车辆信息 租车的取车时间
 	 <form action="return.do?method=carOnUse" method="post">
    return orderid:<input type=text name=orderid value="62"><br>
  
   	<input type="submit" value="提交"/>
   	</form>
   	
   	 		//已付款
 	 <form action="return.do?method=afterPaied" method="post">
    return orderid:<input type=text name=orderid value="1"><br>
  
   	<input type="submit" value="提交"/>
   	</form>
   
   //添加还车信息
   	<form action="return.do?method=returnCar" method="post">
    orderid:<input type=text name=orderid value="2"><br>
    CarRentPri<input type=text name=carRentPrice value="100"><br>
    returntype <input type=text name=returnType value="1"><br>
    returnPlace<input type=text name=returnPlace value="世纪动物园"><br>
    setReturnTime<input type=text name=setReturnTime value="2016-04-24 16:00"><br>
    rentTakeTime<input type=text name=rentTakeTime value="2016-03-24 16:00"><br>
    predictReturnTime<input type=text name=predictReturnTime value="2016-05-24 16:00"><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	 		<br>
   	 //查询订单详情	
   	 <form action="return.do?method=showOrder" method="post">
    orderid:<input type=text name=orderid value="1"><br>
  
   	<input type="submit" value="提交"/>
   	
   	</form>
    
     //支付超支费用 payOverSpend
   	 <form action="return.do?method=payOverSpend" method="post">
    orderid:<input type=text name=orderid value="63"><br>
    overSpend:<input type=text name=overSpend value="310"><br>
  
   	<input type="submit" value="提交"/>
   	
   	</form>
    rentcontroller	
    <form action="rent.do?method=payOrder" method="post">
    orderid:<input type=text name=orderid value="3"><br>
  
   	<input type="submit" value="提交"/>
   	
   	</form>
    
    
    <form action="user.do?method=register" method="post">
  
   	<input type="text" name="phone" /><br>
   	<input type="text" name="content" /><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	<br>
   	
   	用户judgUserOrder
   	<form action="user.do?method=judgUserOrder" method="post">
   	userid:<input type="text" name="userid" value="" /><br>
   	
   	<input type="submit" value="提交"/>
   	
   	</form><br>
   	
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
   	
   	
   	
   	
   	
   		 //列出用户简单订单信息	showOrderDetail
   	 <form action="order.do?method=getOrderList" method="post">
    uerid:<input type=text name=userid value="1"><br>
  
   	<input type="submit" value="提交"/>
   	
   	</form>
   	//详尽订单信息
   	 <form action="order.do?method=showOrderDetail" method="post">
    orderid:<input type=text name=orderid value="1"><br>
  orderStatus:<input type=text name=orderStatus><br>
   	<input type="submit" value="提交"/>
   	
   	</form>
   	
</html>
