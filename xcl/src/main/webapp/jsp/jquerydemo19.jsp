<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/20
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<ul>
    <li id="aa" class="bb">item 1
        <ul>
            <li>sub item 1-a</li>
            <li>sub item 1-b</li>
        </ul>
    </li>
    <li id="aa" class="bb">item 2
        <ul>
            <li>sub item 2-a</li>
            <li>sub item 2-b</li>
        </ul>
    </li>
</ul>
<script>
    function handler(event) {
        var $target = $(event.target);
        if ($target.is("li")) {
            $target.children().toggle();
        }
    }
    $("ul").click(handler).find("ul").hide();
</script>
</body>
</html>

