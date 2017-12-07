<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/5
  Time: 17:42
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
                $("p").append("<b>Appended text</b>.");
            });
            $("#btn2").click(function () {
                $("ol").append("<li>Appended item</li>");
            });
        });
    </script>
</head>
<body>
<p>This is a paragraph.</p>

<p>This is another paragraph.</p>
<ol>
    <li>List item 1</li>
    <li>List item 2</li>
    <li>List item 3</li>
</ol>
<button id="btn1">追加文本</button>
<button id="btn2">追加列表项</button>
</body>
</html>
