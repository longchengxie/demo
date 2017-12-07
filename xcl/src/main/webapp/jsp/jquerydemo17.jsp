<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/6
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试jquery</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(function () {
            var obj = $("div > p");
            console.log(obj);
            $(document.body).css("background", "yellow");
            var myForm = $(".myForm");
            $(myForm.elements).hide();
            console.log($("input:radio", document.forms[0]));
            $("img").each(function (i) {
                this.src = "/mydemo/images/" + "test" + i + ".jpg";
                $(this).toggleClass("example");
            });
            console.log($("img").size());
            console.log($("img").length);
            console.log($("img").get(0));
            console.log($("img")[0]);
            console.log($("img").get().reverse());
            $("button").click(function () {
                $("div").each(function (index, domEle) {
                    //domEle=this
                    $(domEle).css("backgroundColor", "red");
                    if ($(this).is("#stop")) {
                        $("span").text("Stopped at div index #" + index);
                        return false;
                    }
                });
            });
        });
    </script>
</head>
<body>
<button>Change colors</button>
<span></span>

<div>1</div>
<div>2</div>

<div>3</div>
<div>4</div>
<div id="stop">Stop here</div>
<div>5</div>

<div>6</div>
<div>7</div>
<img/>
<img/>

<form action="#" class="myForm" method="get">
    <table style="background-color: #98bf21;width: 350px;height: 150px">
        <tr>
            <td>11</td>
            <td>22</td>
        </tr>
        <tr>
            <td>33</td>
            <td>44</td>
        </tr>
        <tr>
            <td>
                <input name="sex" type="radio" value="男">男</input>
                <input name="sex" type="radio" value="女">女</input>
            </td>
        </tr>
        <a onclick=""></a>
    </table>
</form>
</body>
</html>
