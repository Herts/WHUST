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
<a href="/gotoLowerGoods">下架商品</a>
<a href="/gotoShelfGoods">上架商品</a>
<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
