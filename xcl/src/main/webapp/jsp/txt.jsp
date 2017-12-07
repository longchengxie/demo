<%--
  Created by IntelliJ IDEA.
  User: xiechenglong
  Date: 2015/11/3
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试文本</title>
    <style type="text/css">
        body {
            color: red
        }

        h1 {
            color: green;
            text-align: center;
            text-decoration: overline;
            text-transform: uppercase;
        }

        p.uppercase {
            text-transform: uppercase
        }

        p.lowercase {
            text-transform: lowercase
        }

        p.capitalize {
            text-transform: capitalize
        }

        h2 {
            text-align: left;
            text-decoration: line-through;
        }

        h3 {
            text-align: right;
            text-decoration: underline;
        }

        h4 {
            letter-spacing: 20px;
            text-decoration: blink;
        }

        a {
            text-decoration: none;
        }

        p.ex {
            color: blue
        }

        span.highlight {
            background-color: yellow
        }

        p.small {
            line-height: 90%
        }

        p.big {
            line-height: 200%
        }

        p {
            text-indent: 1cm
        }

        a:link {
            color: #ff0000
        }

        a:visited {
            color: #00ff00
        }

        a:hover {
            color: #ff00ff
        }

        a:active {
            color: #0000ff
        }

        p.ab {
            border: red solid thin;
            outline: #ff9046 dotted thick
        }
    </style>
</head>
<body>
<h1>This Is An H1 Element</h1>

<p class="uppercase">This is some text in a paragraph.</p>

<p class="lowercase">This is some text in a paragraph.</p>

<p class="capitalize">This is some text in a paragraph.</p>

<h2>这是标题 2</h2>

<h4>这是标题 4</h4>

<p><a href="http://www.w3school.com.cn/index.html">这是一个链接</a></p>

<p>这是一段普通的段落。请注意，该段落的文本是红色的。在 body 选择器中定义了本页面中的默认文本颜色。</p>

<p class="ex">该段落定义了 class="ex"。该段落中的文本是蓝色的。</p>

<p>
    <span class="highlight">这是文本。</span> 这是文本。 这是文本。 这是文本。 这是文本。 这是文本。 这是文本。 这是文本。 这是文本。 这是文本。 这是文本。 这是文本。 这是文本。 这是文本。
    这是文本。 这是文本。 这是文本。 <span class="highlight">这是文本。</span>
</p>

<p>
    这是拥有标准行高的段落。
    在大多数浏览器中默认行高大约是 110% 到 120%。
    这是拥有标准行高的段落。
    这是拥有标准行高的段落。
    这是拥有标准行高的段落。
    这是拥有标准行高的段落。
    这是拥有标准行高的段落。
</p>

<p class="small">
    这个段落拥有更小的行高。
    这个段落拥有更小的行高。
    这个段落拥有更小的行高。
    这个段落拥有更小的行高。
    这个段落拥有更小的行高。
    这个段落拥有更小的行高。
    这个段落拥有更小的行高。
</p>

<p class="big">
    这个段落拥有更大的行高。
    这个段落拥有更大的行高。
    这个段落拥有更大的行高。
    这个段落拥有更大的行高。
    这个段落拥有更大的行高。
    这个段落拥有更大的行高。
    这个段落拥有更大的行高。
</p>

<p><b><a href="/index.html" target="_blank">这是一个链接</a></b></p>

<p><b>注释：</b>为了使定义生效，a:hover 必须位于 a:link 和 a:visited 之后！！</p>

<p><b>注释：</b>为了使定义生效，a:active 必须位于 a:hover 之后！！</p>

<p class="ab"><b>注释：</b>只有在规定了 !DOCTYPE 时，Internet Explorer 8 （以及更高版本） 才支持 outline 属性。</p>
</body>
</html>
