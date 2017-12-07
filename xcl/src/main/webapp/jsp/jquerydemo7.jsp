<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/5
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquert</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function () {
            $("#btn1").click(function () {
                alert("TEXT:" + $("#test").text());
            });
            $("#btn2").click(function () {
                alert("HYML:" + $("#test").html());
            });
            $("#btn3").click(function () {
                alert("VALUE:" + $("#val").val());
            });
            $("#btn4").click(function () {
                alert("ATTR:" + $("#w3s").attr("href"));
            });
        });
    </script>
</head>
<body>
<p id="test">这是段落中的<b>粗体</b>文本。</p>
<button id="btn1">显示文本</button>
<button id="btn2">显示 HTML</button>
<p>姓名：<input type="text" id="val" value="米老鼠"></p>
<button id="btn3">显示值</button>
<p><a href="http://www.w3school.com.cn" id="w3s">W3School.com.cn</a></p>
<button id="btn4">显示 href 值</button>
</body>
</html>
