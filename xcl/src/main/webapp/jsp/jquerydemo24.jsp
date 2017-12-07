<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/12/8
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>body {
        background-color: #eef;
    }

    div {
        padding: 20px;
    }</style>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
</head>
<body>
<div id="log"></div>
<div id="log2"></div>
<script>
    $(document).bind('mousemove', function (e) {
        $("#log").text("e.pageX: " + e.pageX + ", e.pageY: " + e.pageY);
    });
</script>
<a href="http://jquery.com">default click action is prevented</a>

<script>
    $("a").click(function (event) {
        event.preventDefault();
        $('<div/>').append('default ' + event.type + ' prevented').appendTo('#log2');
    });
</script>

<button>display event.result</button>
<p></p>
<script>
    $("button").click(function (event) {
        return "hey";
    });
    $("button").click(function (event) {
        $("p").html(event.result);
    });
</script>


</body>
</html>
