<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.net.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户注册</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="This is my page"/>
</head>

<body>
	<div class="main-wrapper">
	
		<h1>用户注册</h1>
		<hr/><br>
			
		<table>
			<tr>
				<td valign="top" align="center">账号：</td>
				<td><input type="text" id="account" name="account" onblur="checkIDNull()">
					<div id="accountInfo" style="color:red;font-size:10px"></div>
					<br><br>
				</td>
			</tr>
			<tr>
				<td valign="top" align="center">密码：</td>
				<td><input type="password" id="password" name="password" onblur="checkPasswordNull()">
					<div id="passwordInfo" style="color:red;font-size:10px"></div>
					<br><br>
				</td>
			</tr>
			<tr>
				<td valign="top" align="center">手机号：</td>
				<td><input type="text" id="tel" name="tel" onblur="checktelNull()">
					<div id="telInfo" style="color:red;font-size:10px"></div>
					<br><br>
				</td>
			</tr>
			<tr>
				<td valign="top" align="center">邮箱：</td>
				<td><input type="text" id="email" name="email" onblur="checkEmailNull()">
					<div id="emailInfo" style="color:red;font-size:10px"></div>
					<br><br>
				</td>
			</tr>
		</table>
		
		
		<button class="btn" onclick="return submit();">SUBMIT</button>
		<br/><br/>
		
		<script type="text/javascript">
			function checkIDNull()
			{
				var account = document.getElementById ("account").value;
				var accountInfo = document.getElementById ("accountInfo");
				
				if( account == "" )
				{
					accountInfo.innerHTML = "账号不能为空";
				}
				if( account != "" )
				{
					accountInfo.innerHTML = "";
				}
			}
			
			function checkPasswordNull()
			{
				var password = document.getElementById ("password").value;
				var passwordInfo = document.getElementById ("passwordInfo");
				
				if( password == "" )
				{
					passwordInfo.innerHTML = "密码不能为空";
				}
				if( password != "" )
				{
					passwordInfo.innerHTML = "";
				}
			}
			
			function checktelNull()
			{
				var tel = document.getElementById ("tel").value;
				var telInfo = document.getElementById ("telInfo");
				
				if( tel == "" )
				{
					telInfo.innerHTML = "手机号不能为空";
				}
				if( tel != "" )
				{
					telInfo.innerHTML = "";
				}
			}
			
			function checkEmailNull()
			{
				var email = document.getElementById ("email").value;
				var emailInfo = document.getElementById ("emailInfo");
				
				if( email == "" )
				{
					emailInfo.innerHTML = "邮箱不能为空";
				}
				if( email != "" )
				{
					emailInfo.innerHTML = "";
				}
			}
			
			//定义XMLHttpRequest对象
			var xmlrequest;
			
			/*****************完成XMLHttpRequest对象的初始化******************/
			function createXMLHttpRequest()
			{
				if( window.XMLHttpRequest )
				{
					//DOM2浏览器
					xmlrequest = new XMLHttpRequest();
				}
				else if( window.ActiveXObject )
				{
					//IE浏览器
					try
					{
						xmlrequest = new ActiveXObject("Msxm12.XMLHTTP");
					}
					catch(e)
					{
						try
						{
							xmlrequest = new ActiveXObject("Microsoft.XMLHTTP");
						}
						catch(e)
						{
							
						}
					}
				}
			}
			
			/*********事件处理函数，当点击登录按钮时，触发该事件,发送ajax请求*********/
			function submit()
			{
				//初始化XMLHttpRequest对象
				createXMLHttpRequest();
				
				//获取value值
				var account = document.getElementById ("account").value;
				var password = document.getElementById ("password").value;
				var tel = document.getElementById ("tel").value;
				var email = document.getElementById ("email").value;
				
				var content = "account=" + account + "&password=" + password + "&tel=" + tel + "&email=" + email;
				
				if( account == "" || password == "" || tel == "" || email == "")
				{
					return false;
				}
				
				alert(content);
				//设置URL
				var url = "/First_Web_zongjunhao/index/register";
				
				//设置处理响应的回调函数
				xmlrequest.onreadystatechange = processResponse;
				
				//设置以POST方式发送请求，并打开连接
				xmlrequest.open ("POST", url, true);
				
				//设置POST请求的请求头
				xmlrequest.setRequestHeader ("Content-Type", 
						"application/x-www-form-urlencoded");
				
				//发送请求
				xmlrequest.send(content);
				
				return true;
			}
			
			/*****************定义处理响应的回调函数*******************/
			function processResponse()
			{
				var account = document.getElementById ("account").value;
				//响应完成且响应正常
				if(xmlrequest.readyState == 4)
				{
					if( xmlrequest.status == 200)
					{
						
				    	//信息已经成功返回，开始处理信息
				    	//获取服务器的json响应
				    	var jsondata = xmlrequest.responseText;
				    	
				    	console.log(jsondata);
				    	alert(jsondata);
				    	
				    	var jsonobj = JSON.parse(jsondata);
				    	
				    	if( jsonobj.resultCode == "2002")
				    	{
				    		//注册成功
				    		alert(jsonobj.resultDesc);
				    		//存储用户信息
				    		//$.session.set('userid', account);
				    		window.location.href = "login.jsp";
				    	}
				    	else
				    	{
				    		//注册失败
					    	var passwordInfo = document.getElementById ("passwordInfo");
					    	
					    	passwordInfo.innerHTML = jsonobj.resultDesc;
				    	}
				    	
					}
					else
					{
						//页面不正常
						window.alert ("您所请求的页面有异常");
					}
				}
			}
		</script>
		
	
	</div>
</body>
</html>