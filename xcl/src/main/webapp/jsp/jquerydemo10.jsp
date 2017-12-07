<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/5
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试js</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function () {
            $("#btn1").click(function () {
                $("img").before("<b>Before</b>");
            });
            $("#btn2").click(function () {
                $("img").after("<i>After</i>");
            });
        });
    </script>
</head>
<body>
<img src="/mydemo/images/eg_w3school.gif" alt="W3School Logo"/>
<br><br>
<button id="btn1">在图片前面添加文本</button>
<button id="btn2">在图片后面添加文本</button>
</body>
</html>
