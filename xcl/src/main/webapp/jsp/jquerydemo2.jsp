<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试js</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".button1").click(function(){
                $("#div1").fadeIn();
                $("#div2").fadeIn("slow");
                $("#div3").fadeIn(3000);
            });

            $(".button2").click(function(){
                $("#div1").fadeOut();
                $("#div2").fadeOut("slow");
                $("#div3").fadeOut(3000);
            });

            $(".button3").click(function(){
                $("#div1").fadeToggle();
                $("#div2").fadeToggle("slow");
                $("#div3").fadeToggle(3000);
            });

            $(".button4").click(function(){
                $("#div1").fadeTo("slow",0.1);
                $("#div2").fadeTo("slow",0.2);
                $("#div3").fadeTo("slow",0.3);
            });
        })
    </script>
</head>
<body>
<p>演示带有不同参数的 fadeIn() 方法。</p>
<button class="button1">点击这里，使三个矩形淡入</button>
<button class="button2">点击这里，使三个矩形淡出</button>
<button class="button3">点击这里，使三个矩形淡入淡出</button>
<button class="button4">点击这里，使三个矩形淡出</button>
<br/><br/>

<div id="div1" style="width: 80px;height: 80px;display: none;background-color: red"></div>
<br/>
<div id="div2" style="width: 80px;height: 80px;display: none;background-color: blue"></div>
<br/>
<div id="div3" style="width: 80px;height: 80px;display: none;background-color: green"></div>
</body>
</html>
