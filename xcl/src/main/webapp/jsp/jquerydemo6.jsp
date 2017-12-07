<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/5
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试jquery的链接</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function(){
            $("button").click(function(){
                $("#p1").css("color","red").slideUp(2000).slideDown(2000);
            });
        });
    </script>
</head>
<body>
<p id="p1">jQuery 乐趣十足！</p>
<button>点击这里</button>
</body>
</html>
