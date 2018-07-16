<%@ page import="java.util.List" %>
<%@ page import="whustore.model.Product" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/14
  Time: 下午4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>订单详情</title>
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

        .tab {
            padding-right: 15%;
            padding-right: 15%;
        }
    </style>
</head>
<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<div class="wrapper">
    <%--头部引用开始--%>
    <header>

        <div class="header-container">
            <div class="header-area header-absolute header-sticky pt-30 pb-30">
                <div class="container-fluid pl-50 pr-50">
                    <%@include file="../universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <div class="shop-area mb-70" style="padding-top: 10%">
        <div class="container">
            <div class="row">

                <div id="app">
                    <h2 class="title">购物车</h2>
                    <table class="tab" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <thead>
                        <tr style="text-align: center">
                            <th colspan="1">商品信息</th>
                            <th style="width: 14%;">商品金额</th>
                            <th style="width: 14%;">商品数量</th>
                            <th style="width: 14%;">总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${items}" end="${items.size()}" var="product" varStatus="status">
                            <tr id="${product.id}">
                                <td class="goods">
                                    <img src="../../${product.picPath.get(0)}" class="goods-left"/>
                                    <div class="goods-right">
                                        <p>${product.productName}</p>
                                    </div>
                                </td>
                                <td><p id="price${product.id}">${product.price}</p></td>
                                <td class="num">
                                    <input id="num${product.id}" value="${order.items.get(product)}"
                                           type="number" readonly disabled/>&nbsp;&nbsp;
                                </td>
                                <td class="blackcolor"
                                    id="total${product.id}">${product.price * order.items.get(product)}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr class="footer" style="margin-top: 40px">
                            <td colspan="5">
                                <p>合计：</p>
                                <h1><span id="total">${order.total}</span>元</h1>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                    <div class="review-form-wrapper" style="padding-left: 25%; padding-right: 25%; text-align: center">
                        <HR size="2" style="color: #abd373">
                        <div class="review-form">
                            <h2>收货信息</h2>
                            <br>
                            <br>
                            <br>
                            <div class="input-element">
                                <div class="comment-form-comment">
                                    <label>收货地址:</label>
                                    <h3>${order.address}</h3>
                                </div>
                                <div class="review-comment-form-author">
                                    <label>姓名:</label>
                                    <h3>${order.name}</h3>
                                </div>
                                <div class="review-comment-form-email">
                                    <label>电话:</label>
                                    <h3>${order.phone}</h3>
                                </div>
                                <form action="/pay/payOrder">
                                    <input type="hidden" value="${order.idOrder}" name="orderId">
                                    <button type="submit" class="form-button">付款</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../universal/footer.jsp" %>
</div>
<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
