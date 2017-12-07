<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/12/4
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/mydemo/js/jquery-2.1.4.js"></script>
</head>
<body>
<p> This is just a test.</p>

<p> So is this</p>

<p>Hello</p>

<p>Hello Again</p>

<p class="selected">And Again</p>

<div class="protected">a</div>
<div>b</div>

<ul>
    <li>11</li>
    <li>22</li>
</ul>
<ul>
    <li><b>Click me!</b></li>
    <li>You can also <b>Click me!</b></li>
</ul>

<script>
    $(function () {
        $("div").click(function () {
            if ($(this).hasClass("protected")) {
                $(this).animate({left: -1000})
                        .animate({right: 1000})
                        .animate({left: -1000})
                        .animate({left: 1000})
                        .animate({left: 0});

            }
        });
        $(document).bind("click", function (e) {
            $(e.target).closest("li").toggleClass("hilight");
        });
    });
</script>
</body>
</html>
