<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/5
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function(){
            $(".btn1").click(function(){
                $("#div1").remove();
            });
            $(".btn2").click(function(){
                $("#div1").empty();
            });
        });
    </script>
</head>
<body>
<div id="div1" style="height:100px;width:300px;border:1px solid black;background-color:yellow;">
    This is some text in the div.
    <p>This is a paragraph in the div.</p>

    <p>This is another paragraph in the div.</p>
</div>

<br>
<button class="btn1">删除 div 元素</button>
<button class="btn2">清空 div 元素</button>
</body>
</html>
