<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="${rc.contextPath}/js/jquery-3.1.0.min.js?verson=${version}"></script>
<script src="${rc.contextPath}/js/Utils.js?verson=${version}"></script>
</head>
<body>
	<form id="roleForm" action="save.do" method="post">
		<ul class="gl3_top">
			<li>职位角色：<input type="text" name="permRole.roleName" ></li>
		    <li>所属组织：
		    	<select id="levelSlct" onchange="levelChangeHandler()">
					<option value="1">一级</option>
					<option value="2">二级</option>
					<option value="3">三级</option>
					<option value="4">四级</option>
				</select>
				<select id="orgSlct" name="permRole.roleLabel" selected="">
				</select>
			</li>
		    <li style="width:600px; align:left;">角色描述：<textarea cols="70" rows="3" name="permRole.description"></textarea></li>
		</ul>
		<div class="gl3_zs_b">
			<table width="100%">
				<tr>
					<td align="center">
						<input value="新增" type="submit">
					</td>
				</tr>
			</table>
		</div>
	</form>
	<button id="json1">测试ajax请求返回json</button></br>
	<button id="json2">测试ajax请求返回json</button></br>
<script>
	
	$(document).ready(function(){
	
		//加载一级部门
		Utils.setComboxDataSource("${rc.contextPath}/json/get_org_list_by_level.do", "orgSlct", true, undefined);
	});
	
	function levelChangeHandler(){
		Utils.setComboxDataSource("${rc.contextPath}/json/get_org_list_by_level.do", "orgSlct", true, undefined);
	}
	
	//getJson('age');  
  
	function getJson(key){  
	    var jsonObj={"name":"傅红雪","age":"24","profession":"刺客"};  
	    //1、使用eval方法      
	    var eValue=eval('jsonObj.'+key);  
	    alert(eValue);  
	    //2、遍历Json串获取其属性  
	    for(var item in jsonObj){  
	        if(item==key){  //item 表示Json串中的属性，如'name'  
	            var jValue=jsonObj[item];//key所对应的value  
	            alert(jValue);  
	        }  
	    }  
	    //3、直接获取  
	    alert(jsonObj[''+key+'']); 
	}
	
	//each();
	function each(){
		var data=[{name:"a",age:12},{name:"b",age:11},{name:"c",age:13},{name:"d",age:14}];  
		for(var i in data){  
			alert(i);  
			//alert(data[i]);  
			alert("text:"+data[i].name+" value:"+data[i].age );  
		}  
	}
	
	//text();
	function text(){  
		var json = {options:[{text:"王家湾",value:"9"},{text:"李家湾",value:"10"},{text:"邵家湾",value:"13"}]}   
		json = eval(json.options)  
		for(var i=0; i<json.length; i++){  
			alert(json[i].text+" " + json[i].value)  
		}  
	}
	
	//数组转json
	var dataArr=[{name:"a",age:12},{name:"b",age:11},{name:"c",age:13},{name:"d",age:14}]; 
	var jsonObject = Utils.arrayToJson(dataArr);
	alert(jsonObject);
	
	$(document).on("click","#json1",function(){
		$.ajax({
			type: "POST",
			url: "/xcl/json/json1.do",
		    success: function(data){
		    	for(var i=0; i<data.length; i++){
		    		alert(data[i].id +".........." + data[i].text);
		    	}
		    }
		});
	});
	$(document).on("click","#json2",function(){
		$.ajax({
			type: "POST",
			url: "/xcl/json/json2.do",
		    success: function(data){
		        for(var i=0; i<data.length; i++){
		    		alert(data[i].id +".........." + data[i].text);
		    	}
		    }
		});
	});
	  
</script>
</body>
</html>