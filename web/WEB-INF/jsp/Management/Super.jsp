<%--
  Created by IntelliJ IDEA.
  User: lsq_math
  Date: 2018/7/18
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>super</title>

</head>
<body>
<header>
    <%@ include file="../universal/allcss.jsp" %>
    <div class="header-container">
        <div class="header-area header-sticky pt-30 pb-30">
            <div class="container">
                <%@include file="../universal/headerContent.jsp" %>
            </div>
        </div>
    </div>
</header>
<div class="team-sidebar" style="width: 30%;float: left">

    <div class="categori-checkbox" style="height: 1000px; margin-left: 100px">
        <h3>管理员操作</h3>
        <ul>
            <li>
                <button class="form-button" style="width:50%"><a href="/gotoShelfGoods" style="color: white;font-size: larger;margin: auto auto">上架商品</a>
                </button>
            </li>
            <br/>
            <li>
                <button class="form-button" style="width:50%"><a href="/gotoLowerGoods" style="color: white;font-size: larger;margin: auto auto">下架商品</a>
                </button>
            </li>
            <br/>
            <li>
                <button class="form-button" style="width:50%"><p style="color: white;font-size: larger"></p>
                </button>
            </li>
            <br/>
            <li>
                <button class="form-button" style="width:50%"><p style="color: white;font-size: larger"></p>
                </button>
            </li>
            <br/>
            <li>
                <button class="form-button" style="width:50%"><p style="color: white;font-size: larger"></p>
                </button>
            </li>
            <br/>
            <li>
                <button class="form-button" style="width:50%"><p style="color: white;font-size: larger"></p>
                </button>
            </li>
            <br/>
        </ul>
    </div>
</div>
<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
