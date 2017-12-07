<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/12/8
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<ul>
    <li>item 1
        <ul>
            <li>sub item 1-a</li>
            <li>sub item 1-b</li>
        </ul>
    </li>
    <li>item 2
        <ul>
            <li>sub item 2-a</li>
            <li>sub item 2-b</li>
        </ul>
    </li>
</ul>
<script>function handler(event) {
    console.info(event.currentTarget);
    event.preventDefault();
    event.stopPropagation();

    var $target = $(event.target);
    if ($target.is("li")) {
        $target.children().toggle();
    }
}
$("ul").click(handler).find("ul").hide();
$("li").click(handler);
$("body").click(handler);
</script>
</body>
</html>
