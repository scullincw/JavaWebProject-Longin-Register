<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.net.*" pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>首页</title>
</head>
<body>
	<center>
	<button class="btn">LOGOUT</button>
	</center>
	<script type="text/javascript" src = "js/jquery.js"></script>
	<script type="text/javascript" src = "js/jquerySession.js"></script>
	<script>
		alert($.session.get('userid'));
		$("body").append($.session.get('userid'));
		$("body").append($.session.get('tel'));
		$("body").append($.session.get('email'));
		$(".btn").click(function(){
			
			$.ajax({	
				
				type:"POST",
				url:"/First_Web_zongjunhao/index/logout",
				data: {
					account: $.session.get('userid')
				},
				success: function(json){
					if(json.resultCode=="4004")
					{
						//退出登录成功
						alert(json.resultDesc);
						$.session.remove('userid')
			    		window.location.href = "login.jsp";
					}	
					else
					{
						//退出登录失败
						alert(json.resultDesc);
					}
				},
				error: function(jqXHR){
					alert("您所请求的页面有异常");
				}
			});
		});
	</script>
</body>
</html>