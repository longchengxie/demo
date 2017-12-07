<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/12/7
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(function () {
                    $("#go").click(function () {
                        $(".block").animate({
                            width: "90%",
                            height: "100%",
                            fontSize: "10em",
                            borderWidth: 10
                        }, 1000);
                    });
                    $("#right").click(function () {
                        $(".block").animate({left: '+50px'}, "slow");
                    });

                    $("#left").click(function () {
                        $(".block").animate({left: '-50px'}, "slow");
                    });

                    var fields = $("select, :radio").serializeArray();
                    jQuery.each(fields, function (i, field) {
                        $("#results").append(field.value + " ");
                    });

                    $("p").click(function (event) {
                        alert(event.currentTarget === this); // true
                    });

                    $("a").each(function (i) {
                        $(this).bind("click", {index: i}, function (event) {
                            alert("my index is " + event.data.index);
                        })
                    });

                    $(".box").on("click", "button", function (event) {
                        $(event.delegateTarget).css("background-color", "red");
                    })
                }
        );
    </script>
</head>
<body>
<a>1</a>
<a>2</a>
<a>3</a>
<button id="go"> Run</button>
<button id="left">«</button>
<button id="right">»</button>

<div class="block">Hello!</div>
<p id="results"><b>Results:</b></p>

<form class="box">
    <select name="single">
        <option>Single</option>
        <option>Single2</option>
    </select>
    <select name="multiple" multiple="multiple">
        <option selected="selected">Multiple</option>
        <option>Multiple2</option>
        <option selected="selected">Multiple3</option>
    </select><br/>
    <input type="checkbox" name="check" value="check1"/> check1
    <input type="checkbox" name="check" value="check2" checked="checked"/> check2
    <input type="radio" name="radio" value="radio1" checked="checked"/> radio1
    <input type="radio" name="radio" value="radio2"/> radio2
    <button>提交</button>
</form>
<div></div>
<div></div>
</body>
</html>
