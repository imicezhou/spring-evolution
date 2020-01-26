<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登录门户</title>
	<script type="text/javascript" src="./static/js/jquery-3.4.1.js"></script>
</head>
<body>
	
	<%
       if(request.getAttribute("signin_errorMessage") != null){
    %>
          <p><input type="text" id="signin_errorMessage" value="${signin_errorMessage}" /></p>
    <%
       } 
    %>
    <form method="get" action="/springgeneral_mvc/signin">
    	<table style="text-align:center">
    		<tbody>
    			<tr>
    				<td>账号:</td>
    				<td><input type="text" name="account" id="account" /></td>
    			</tr>
    			<br/>
    			<tr>
    				<td>密码:</td>
    				<td><input type="password" name="password" id="password" /></td>
    			</tr>
    			<br/>
    			<tr>
    				<td>记住我:</td>
    				<td><input type="checkbox" name="ifremember" id="ifremember" /></td>
    			</tr>
    			<br/>
    			<tr>
    				<td colspan="2"><input type="submit" name="点击登录" id="loginButton" value="点击登录" style="width:210px" /></td>
    			</tr>
    		</tbody>
    	</table>
    </form>
</body>
<script type="text/javascript">
    /* var dataObj = {};

	$(document).ready(function(){
		
		$("#loginButton").on('click',function(){
			dataObj.account = $("#account").val();
		    dataObj.password = $("#password").val();
		    dataObj.ifremember = $("#ifremember").val();
			$.ajax({
				url:'/springgeneral_mvc/signin',
				contentType:'application/json',
				type:'post',
				data:JSON.stringify(dataObj),
				success:function(resp){
					console.log(resp);
				}
			});
		})
		
	}); */
	
</script>
</html>