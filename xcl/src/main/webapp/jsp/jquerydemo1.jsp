<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/3
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<head>
    <title>jquery测试</title>
    <script type="text/javascript" src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        /*$(document).ready(function () {
         $("p").click(function () {
         $(this).hide();
         });
         });*/
        $(document).ready(function () {
            $("#click").click(function () {
                $("p").toggle("slow");
            });

            $(".ex .hide").click(function () {
                $(this).parent(".ex").hide("slow",function(){
                    $(this).show(10000);
                });
            });
        })
    </script>
    <style type="text/css">
        div.ex {
            background-color: #e5eecc;
            padding: 7px;
            border: solid 1px #c3c3c3;
        }
    </style>
</head>
<body>
<p>如果您点击我，我会消失。</p>

<p>点击我，我会消失。</p>

<p>也要点击我哦。</p>
<button type="button" id="click">Click me</button>

<h3>中国办事处</h3>

<div class="ex">
    <button class="hide" type="button">隐藏</button>
    <p>联系人：张先生<br/>
        北三环中路 100 号<br/>
        北京</p>
</div>

<h3>美国办事处</h3>

<div class="ex">
    <button class="hide" type="button">隐藏</button>
    <p>联系人：David<br/>
        第五大街 200 号<br/>
        纽约</p>
</div>
</body>
</html>
