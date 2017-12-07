<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/6
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(function(){
            console.log("test2");

            (function() {
                console.log("test3");
            })();

        });


        (function() {
            console.log("test1");
        })();


        $(document).ready(function () {
            $("div p").first().css("background-color", "yellow");
            $("div p").last().css("background-color", "red");
            $("p").eq(3).css("background-color", "blue");
            $("p").filter(".intro").css("background-color", "green");
            //$("p").not(".intro").css("background-color", "#ff000f");
        });
    </script>
</head>
<body>
<h1>欢迎来到我的主页</h1>

<div>
    <p>这是 div 中的一个段落。</p>
</div>

<div>
    <p>这是 div 中的另一个段落。</p>
</div>

<p>我是唐老鸭 (index 0)。</p>

<p>唐老鸭 (index 1)。</p>

<p class="intro">我住在 Duckburg (index 2)。</p>

<p class="intro">我最好的朋友是米老鼠 (index 3)。</p>
</body>
</html>
