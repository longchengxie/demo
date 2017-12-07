<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/6
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function () {
            $("button").click(function () {
                $("h1,h2,p").addClass("blue");
                $("div").addClass("important");
                $("#div1").addClass("important blue");
            });
        });
    </script>
    <style type="text/css">
        .important {
            font-weight: bold;
            font-size: xx-large;
        }

        .blue {
            color: blue;
        }
    </style>
</head>
<body>
<h1>标题 1</h1>

<h2>标题 2</h2>

<p>这是一个段落。</p>

<p>这是另一个段落。</p>

<div>这是非常重要的文本！</div>
<br>

<div id="div1">这是一些文本。</div>
<div id="div2">这是一些文本。</div>
<button>向元素添加类</button>
</body>
</html>
