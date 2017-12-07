<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/12/8
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<input id="whichkey" value="type something">

<div id="log"></div>
<script>
    $('#whichkey').bind('keydown', function (e) {
        $('#log').html(e.type + ': ' + e.which);
    });
</script>
</body>
</html>