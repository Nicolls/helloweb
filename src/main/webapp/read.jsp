<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>让服务器帮你求和</title>
<
</head>
<body>
<td >链接成功，求和操作</td>
	<form action="OperatorServlet" method="post">
		<table>
			<tr>
				<td>输入第一个数：</td>
				<td><input type="text" name="first" value="${requestScope.first}" ></td>
			</tr>
			<tr>
				<td>输入第二个数：</td>
				<td><input type="text" name="second" value="${requestScope.second}"></td>
			</tr>
			<tr>
				<td ><input type="submit" value="求和"></td>
	</form>
			<td >答案是：${requestScope.answer} </td>
			</tr>
</body>
</html>