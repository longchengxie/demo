<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/5
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquery</title>
    <style type="text/css">
        div.panel,p.flip1,p.flip2 ,p.flip3{
            margin:0px;
            padding:5px;
            text-align:center;
            background:#e5eecc;
            border:solid 1px #c3c3c3;
        }
        div.panel {
            height: 120px;
            display: none;
        }
    </style>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function(){
            $(".flip1").click(function(){
                $(".panel").slideDown("slow");
            });
            $(".flip2").click(function(){
                $(".panel").slideUp("slow");
            });
            $(".flip3").click(function(){
                $(".panel").slideToggle("slow");
            });
        })
    </script>
</head>
<body>
<div class="panel">
    <p>W3School - 领先的 Web 技术教程站点</p>

    <p>在 W3School，你可以找到你所需要的所有网站建设教程。</p>
</div>

<p class="flip1">请点击这里滑出</p>
<p class="flip2">请点击这里滑近</p>
<p class="flip3">请点击这里滑近或滑出</p>
</body>
</html>
