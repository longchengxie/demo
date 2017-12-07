<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="${rc.contextPath}/js/jquery-3.1.0.min.js?verson=${verson}"></script>
<script src="${rc.contextPath}/js/dateFormat.js?verson=${verson}"></script>
</head>
<body>
	${rc}<br/>
	${rc.contextPath}<br/>
	<input type="button" id="button1">点击按钮</input>
	<input type="button" id="button2">点击按钮</input>
	
	
	<script>
	
		(function(){
			alert("自执行")
		})();
	
		$(function(){
			var date = new Date(${date});
			alert(date.format("yyyy-MM-dd"));
			
			$("#button1").off("click",function(){});
			$("#button1").on("click",function(){
				$.ajax({
					type: "POST",
					url: "${rc.contextPath}/date/stringFormatDate.do",
					data: "date=2016-08-29",
					success: function(data){
						alert(date);
					}
				});
			});
			
			
			$("#button2").on("click",function(){
				$.ajax({
					type: "POST",
					url: "${rc.contextPath}/date/stringFormatDate2.do",
					data: "date=2016-08-29",
					success: function(data){
						alert(date);
					}
				});
			});
		});
		
	</script>
</body>
</html>