<%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/6/14
  Time: 上午9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册WHUStore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
    <script>
        function changeRAndL() {
            var temp = document.getElementById("logDiv").style.visibility;
            document.getElementById("logDiv").style.visibility = document.getElementById("RegDiv").style.visibility;
            document.getElementById("RegDiv").style.visibility = temp;
        }
    </script>
</head>
<body>
<div id="particles-js">

    <!-- particles.js lib (JavaScript CodePen settings): https://github.com/VincentGarreau/particles.js -->
    <script src="${pageContext.request.contextPath}/js/user.js"></script>

    <div class="login" id="logDiv" style="visibility: visible">
        <h1>WHU Store</h1>
        <form action="/login" method="post">
            <input type="text" name="username" placeholder="用户名" required="required"/>
            <input type="password" name="password" placeholder="密码" required="required"/>
            <br>
            <button type="submit" class="btn btn-primary btn-block btn-large" name="user">登录</button>

        </form>
        <form>
            <button type="button" class="btn btn-primary btn-block btn-large" name="toRegister" onclick="changeRAndL()">
                注册
            </button>
        </form>
        <b>${message}</b>
    </div>

    <div class="login" id="RegDiv" style="visibility: hidden">
        <h1>WHU Store</h1>
        <form action="/reg" method="post">
            <input type="text" name="username" placeholder="用户名" required="required"/>
            <input type="password" name="password" placeholder="密码" required="required"/>
            <input type="text" name="email" placeholder="邮箱" required="required"/>
            <input type="text" name="phone" placeholder="电话" required="required"/>
            <br>
            <button type="submit" class="btn btn-primary btn-block btn-large" name="user">注册</button>
        </form>
        <form>
            <button type="button" class="btn btn-primary btn-block btn-large" name="toRegister" onclick="changeRAndL()">
                登陆
            </button>
        </form>
        <b>${message}</b>
    </div>
</div>
<script>
    var y = "yes";
    if (${isReg})
        changeRAndL();
</script>
</body>
</html>
