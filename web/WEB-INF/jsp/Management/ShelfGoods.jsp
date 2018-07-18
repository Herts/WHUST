<%--
  Created by IntelliJ IDEA.
  User: 葛鹭
  Date: 2018/7/14
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="whustore.model.Product" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>团队商品</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <%@ include file="../universal/allcss.jsp" %>
    <style type="text/css">
        .blackcolor {
            color: black;
        }

        .title {
            text-align: center;
            position: relative;
            font-weight: normal;
            margin-bottom: 30px;
            color: #abd373;
        }

        .title:after, .title:before {
            content: '';
            position: absolute;
            top: 50%;
            width: 45%;
            background: #abd373;
            height: 1px;
        }

        .title:before {
            left: 0;
        }

        .title:after {
            right: 0;
        }

        .tab thead {
            background-color: black;
            color: white;
            vertical-align: center;
        }

        .tab thead th {
            padding: 10px 0;
            font-weight: normal;
            vertical-align: center;
        }

        .tab td {
            text-align: center;
            padding: 10px 0;
            vertical-align: center;
        }

        .goods {
            width: 40%;
            position: relative;
            vertical-align: center;
        }

        .goods-left {
            width: 20%;
            float: left;
            vertical-align: center;
        }

        .goods-right {
            width: 78%;
            float: right;
            text-align: left;
            vertical-align: center;
        }

        .goods-right .tip {
            color: #999;
            position: absolute;
            bottom: 10px;
            font-size: 12px;
            vertical-align: center;
        }

        .num input {
            width: 50px;
            text-align: center;
            vertical-align: center;
        }

        .num a {
            color: #333;
            text-decoration: none;
            vertical-align: center;
        }

        .del {
            cursor: pointer;
            vertical-align: center;
        }

        .footer td {
            border-top: 1px solid #abd373;
            vertical-align: center;
        }

        .footer td:nth-child(2) {
            text-align: left;
            vertical-align: center;
        }

        .footer td:nth-child(3) {
            text-align: right;
            vertical-align: center;
        }

        .chbsty {
            height: 15px;
            width: 15px;
        }
    </style>
</head>
<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->
<%--improt header-part--%>
<header>
    <div class="header-container">
        <div class="header-area header-sticky pt-30 pb-30">
            <div class="container">
                <%@include file="../universal/headerContent.jsp" %>
            </div>
        </div>
    </div>
</header>
<%--header-part import end--%>
<div class="shop-area mb-70" style="padding-top: 3%">
    <div class="container">

        <h3>待上架商品列表</h3>
        <div class="row">
            <h2 class="title">发布的商品</h2>
            <table class="tab" width="100%" border="0" cellspacing="0" cellpadding="0"
                   style="padding-bottom: 2%;padding-bottom: 2%">
                <thead>
                <tr style="text-align: center">
                    <th colspan="1">商品信息</th>
                    <th style="width: 14%;">团队编号</th>
                    <th style="width: 14%;">商品金额</th>
                    <th style="width: 14%;">商品数量</th>
                    <th style="width: 14%;">编辑</th>
                </tr>
                </thead>

            <c:forEach items="${productList}" var="product" varStatus="status">
                <tr id="${product.id}" style="padding-bottom: 1%">
                    <td class="goods">
                        <img src="../../${product.picPath.get(0)}" class="goods-left"/>
                        <div class="goods-right">
                            <p>${product.productName}</p>
                        </div>
                    </td>
                    <td><p>${product.teamID}</p></td>
                    <td><p>${product.price}</p></td>
                    <td class="num">${product.quantity}&nbsp;&nbsp;</td>
                    <td><button class="form-button"><a  href="/ShelfGoods" style="color: white">上架商品</a></button> </td>
                </tr>
                <hr/>
            </c:forEach>
            </table>
        </div>
    </div>
    <%--import footer-part--%>
    <%@include file="../universal/footer.jsp" %>
</div>
<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
