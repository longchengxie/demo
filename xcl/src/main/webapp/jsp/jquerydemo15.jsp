<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/6
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .siblings * {
            display: block;
            border: 2px solid lightgrey;
            color: lightgrey;
            padding: 5px;
            margin: 15px;
        }
    </style>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function () {
            $("h2").siblings("p").css({"color": "red", "border": "2px solid red"});
            $("h2").next().css({"color": "blue", "border": "3px solid blue"});
        });
    </script>
</head>
<body class="siblings">

<div>div (父)
    <p>p</p>
    <span>span</span>

    <h2>h2</h2>

    <h3>h3</h3>

    <p>p</p>
</div>

</body>
</html>
