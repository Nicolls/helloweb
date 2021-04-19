<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>让服务器帮你求和</title>

<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

function clickButton()
{
        var selecttime='参数';
      //设置时间 5-秒  1000-毫秒  这里设置你自己想要的时间 
        setTimeout(clickButton,2*1000);
$.ajax({
       url:'ReadServerValue',
       type:'POST',//GET 或POST
       async:true,//false是否异步
                       data:{selecttime:selecttime},    
       dataType:'text',//返回的数据格式类型json/xml/html/script/jsonp/text    （返回的值很关键，如果不是text类型，页面可能会被重写）
       success:function(data){
           console.log(data);
           $("#readData").html(data);
       },
       error:function(data){
           console.log(data);//在前端控制台打印请求的状态
       }
   });
}
</script>
</head>
<body>
<td >链接成功</td>
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
	
	
	<br/>
	<br/>
	<br/>	
	
<form>
<input type="button" id="button1" onclick="clickButton()"
value="读取服务器数据" />
<div id="readData"></div>
</form>
			
</body>
</html>