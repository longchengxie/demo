<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="${rc.contextPath}/js/jquery-3.1.0.min.js?verson=201608291402"></script>
</head>
<body>
	<form id="dataForm" action="${rc.contextPath}/json/encodeURIJFASTSON.do" method="post">
		<input type="text" id="orderJson" name="orderJson" value="" hidden="hidden">
		<input value="提交" type="submit">
	</form>
<script>
$(document).ready(function(){
	var itemList = new Array();
	for(var i=0;i<2;i++){
		var item = {};
        var hotelAdditation = {};//酒店的时候使用
        item.goodsId = i;
        item.goodType = i+"_"+i;
        hotelAdditation.leaveTime = "2017-06-10";
        hotelAdditation.arrivalTime = "2017-06-08";
        hotelAdditation.stayDays = "3";
        item.hotelAdditation = hotelAdditation;
        itemList.push(item);
	}
	var form = {};
    form.orderCreatingManner="谢承龙";
    form.itemList = itemList;
    form.quantity=5;
    form.productId=11111;
    form.lineRouteId=123456;
    $("#orderJson").val(encodeURI(encodeURI(JSON.stringify(form))));
});
</script>
</body>
</html>