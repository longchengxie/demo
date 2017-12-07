<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/5
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(document).ready(function () {
            var $div = $("div");
            $("#start").click(function () {
                $div.animate({left: '100px'}, 5000);
                $div.animate({fontSize: '3em'}, 5000);
            });
            $("#stop").click(function () {
                $div.stop();
            });
            $("#stop2").click(function () {
                $div.stop(true);
            });
            $("#stop3").click(function () {
                $("div").stop(true, true);
            });
        })
    </script>
</head>
<body>
<button id="start">开始</button>
<button id="stop">停止</button>
<button id="stop2">停止所有</button>
<button id="stop3">停止但要完成</button>
<p><b>"开始"</b> 按钮会启动动画。</p>

<p><b>"停止"</b> 按钮会停止当前活动的动画，但允许已排队的动画向前执行。</p>

<p><b>"停止所有"</b> 按钮停止当前活动的动画，并清空动画队列；因此元素上的所有动画都会停止。</p>

<p><b>"停止但要完成"</b> 会立即完成当前活动的动画，然后停下来。</p>

<div style="background:#98bf21;height:100px;width:200px;position:absolute;">HELLO</div>

</body>
</html>