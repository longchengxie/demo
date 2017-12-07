<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/6
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <style>
        .ancestors * {
            display: block;
            border: 2px solid lightgrey;
            color: lightgrey;
            padding: 5px;
            margin: 15px;
        }
    </style>
    <script>
        $(document).ready(function () {
            $("span").parent().css({"color": "red", "border": "2px solid red"});
        });
    </script>
</head>
<body>
<div class="ancestors">
    <div style="width:500px;">div (曾祖父)
        <ul>ul (祖父)
            <li>li (直接父)
                <span>span</span>
            </li>
        </ul>
    </div>

    <div style="width:500px;">div (祖父)
        <p>p (直接父)
            <span>span</span>
        </p>
    </div>
</div>
</body>
</html>
