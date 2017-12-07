<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/6
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function () {
            $("div").children("p.1").css({"color": "red", "border": "2px solid red"});
            $("div").find("span").css({"color": "blue", "border": "2px solid blue"});
        });
    </script>
</head>
<body>
<div class="descendants" style="width:500px;">div (当前元素)
    <p class="1">p (子)
        <span>span (孙)</span>
    </p>

    <p class="2">p (子)
        <span>span (孙)</span>
    </p>
</div>
</body>
</html>
