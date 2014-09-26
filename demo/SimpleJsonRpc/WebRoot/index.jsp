<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>JSON-RPC Sample</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript" src="js/jsonrpc.js"></script>
		<script type="text/javascript">
		window.onload = function(){
			jsonrpc = new JSONRpcClient("JSON-RPC");
		}
		function sayHello(){
			var result = jsonrpc.testService.getHello();
		   	alert(result);
		}
		
		function sum() {
		    var a = document.getElementById("a").value;
		    var b = document.getElementById("b").value;
		    var result = jsonrpc.testService.sum(a, b);
		    document.getElementById("result").innerHTML = result;
		 }
</script>
	</head>

	<body>
		<p>
			<button id="btn1" onClick="sayHello();">
				Say Hello
			</button>
		</p>
		<p>
			<input type="text" id="a" />
			&nbsp+&nbsp;
			<input type="text" id="b" />
			&nbsp;
			<button id="btn2" onClick="sum();">
				=
			</button>
			&nbsp;
			<span id="result"></span>
		</p>
	</body>
</html>
