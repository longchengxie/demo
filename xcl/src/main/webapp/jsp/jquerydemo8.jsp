<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/5
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function () {
            $("#btn1").click(function () {
                $("#test1").text("Hello World!");
            });
            $("#btn2").click(function () {
                $("#test2").html("<b>Hello World!</b>");
            });
            $("#btn3").click(function () {
                $("#test3").val("Hello World!");
            });
        });
    </script>
</head>
<body>
<p id="test1">这是段落。</p>

<p id="test2">这是另一个段落。</p>

<p>Input field: <input type="text" id="test3" value="Mickey Mouse"></p>
<button id="btn1">设置文本</button>
<button id="btn2">设置 HTML</button>
<button id="btn3">设置值</button>
</body>
</html>
