<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>后台门户</title>
	
	<link rel="stylesheet" href="./static/lib/layui/css/layui.css" />
	<link rel="stylesheet" href="./static/css/login.css" />
	<style>
		/* 覆盖原框架样式 */
		.layui-elem-quote{background-color: inherit!important;}
		.layui-input, .layui-select, .layui-textarea{background-color: inherit; padding-left: 30px;}
	</style>
</head>
<body style="background-color: #C9C9C9">
	
	<!-- Head -->
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-sm12 layui-col-md12 zyl_mar_01">
				<blockquote class="layui-elem-quote">后台登陆界面</blockquote>
			</div>
		</div>
	</div>
	<!-- Head End -->
	
	<!-- LoginForm -->
	<div class="zyl_lofo_main">
		<fieldset class="layui-elem-field layui-field-title zyl_mar_02">
			<legend>欢迎登陆</legend>
		</fieldset>
		<div class="layui-row layui-col-space15">
			<form class="layui-form zyl_pad_01" action="./signin" method="post">
				<div class="layui-col-sm12 layui-col-md12">
					<div class="layui-form-item">
						<input type="text" name="username" lay-verify="required|userName" autocomplete="off" placeholder="账号" class="layui-input">
						<i class="layui-icon layui-icon-username zyl_lofo_icon"></i>
					</div>
				</div>
				<div class="layui-col-sm12 layui-col-md12">
					<div class="layui-form-item">
						<input type="password" name="password" lay-verify="required|pass" autocomplete="off" placeholder="密码" class="layui-input">
						<i class="layui-icon layui-icon-password zyl_lofo_icon"></i>
					</div>
				</div>
				<!-- <div class="layui-col-sm12 layui-col-md12">
					<div class="layui-row">
						<div class="layui-col-xs4 layui-col-sm4 layui-col-md4">
							<div class="layui-form-item">
								<input type="text" name="vercode" id="vercode" lay-verify="required|vercodes" autocomplete="off" placeholder="验证码" class="layui-input" maxlength="4">
								<i class="layui-icon layui-icon-vercode zyl_lofo_icon"></i>
							</div>
						</div>
						<div class="layui-col-xs4 layui-col-sm4 layui-col-md4">
							<div class="zyl_lofo_vercode zylVerCode" onclick="zylVerCode()"></div>
						</div>
					</div>
				</div> -->
				<div class="layui-col-sm12 layui-col-md12">
					<!-- <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="submit">立即登录</button> -->
					<input class="layui-btn" type="submit" value="提交" />
				</div>
				<%
				   if(session.getAttribute("signin_errorMessage") != null){
				%>
					<!-- 登录消息提示 -->
				<div class="layui-col-sm12 layui-col-md12">
					<p><font color="red">${signin_errorMessage}</font></p>
				</div>
				<%
				   session.removeAttribute("signin_errorMessage");
				   } 
				%>
			</form>
		</div>
	</div>
	<!-- LoginForm End -->
	
</body>

<script type="text/javascript" src="./static/lib/layui/layui.js" charset="UTF-8"></script>
<script>
	<!-- 引入模块layer和form ， 后面的function显然是回调函数
	-->
	layui.use(['layer', 'form'], function(){
	  var layer = layui.layer
	  ,form = layui.form;
	  
	  layer.msg('Hello World');
	  
	  
	});
</script>

</html>