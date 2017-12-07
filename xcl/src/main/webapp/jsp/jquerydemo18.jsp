<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/6
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(function () {
            $("div").data("t", "test");
            console.log($("div").data("test"));  // blah设置为hello
            console.log($("div").data("t"));  // blah设置为hello
        });

        window.location.href
    </script>
</head>
<body>
<div data-test="2"></div>
<button type="button" onsubmit=""></button>
</body>
</html>
