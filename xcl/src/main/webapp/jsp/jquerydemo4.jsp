<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/5
  Time: 10:31
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
                var $div=$("div");
                $div.animate({height:'300px',opacity:'0.4'},"slow");
                $div.animate({width:'300px',opacity:'0.8'},"slow");
                $div.animate({height:'100px',opacity:'0.4'},"slow");
                $div.animate({width:'100px',opacity:'0.8'},"slow");
                $div.animate({left: '250px', opacity: '0.5', heigth: '150px', width: '150px'}, "slow");
                $div.animate({fontSize:'3em'},"slow");
            });
        })
    </script>
</head>
<body>
<button type="button">开始动画</button>
<p>默认情况下，所有 HTML 元素的位置都是静态的，并且无法移动。如需对位置进行操作，记得首先把元素的 CSS position 属性设置为 relative、fixed 或 absolute。</p>

<div style="background: green;height: 100px;width: 100px;position: absolute">HELLO</div>
</body>
</html>
