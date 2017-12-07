<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/12/4
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jquerydemo20</title>
    <script type="text/javascript" src="/mydemo/js/jquery-2.1.4.js"></script>
    <script>
        $(function () {
            $("#run").click(function () {
                $("div:not(:animated)").animate({left: "+=20"}, 1000);
            });
        });
    </script>
</head>
<body>
<span id="foo:bar">qq</span>
<span id="foo[bar]">ww</span>
<span id="foo.bar">ee</span>

<div>div</div>
<p class="myClass">p class="myClass"</p>
<span>span</span>

<p class="notMyClass">p class="notMyClass"</p>

<form>
    <label>Name:</label>
    <input name="name"/>
    <input name="name2"/>
    <fieldset>
        <label>Newsletter:</label>
        <input name="newsletter"/>
    </fieldset>
    <input name="apple"/>
    <input name="flower" checked="checked"/>
</form>
<input name="none"/>
<input name="none2"/>
<ul>
    <li>list item 1</li>
    <li>list item 2</li>
    <li>list item 3</li>
    <li>list item 4</li>
    <li>list item 5</li>
</ul>
<table>
    <tr>
        <td>Header 1</td>
    </tr>
    <tr>
        <td>Value 1</td>
    </tr>
    <tr>
        <td>Value 2</td>
    </tr>
    <tr>
        <td>Value 3</td>
    </tr>
    <tr>
        <td>Value 4</td>
    </tr>
</table>
<h1>Header 1</h1>

<p>Contents 1</p>

<h2>Header 2</h2>

<p>Contents 2</p>
<button id="run">Run</button>
<div>是DVD是v</div>
</body>
</html>
