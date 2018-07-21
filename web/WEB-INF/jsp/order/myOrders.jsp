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
    <title>历史订单</title>
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
    <script>
        function hid(id) {
            document.getElementById(id).remove();
            for (var a in document.getElementsByClassName(id))
                a.remove();
        }
    </script>
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
    <div class="shop-area mb-70" style="padding-top: 7%">
        <div class="container">
            <div class="row">
                <h2 class="title">历史订单</h2>
                <c:choose>
                    <c:when test="${orders.get(0).iduser > -1}">

                        <c:forEach items="${orders}" var="order">
                            <table id="${order.idOrder}" class="tab" width="100%" border="0" cellspacing="0"
                                   cellpadding="0"
                                   style="padding-bottom: 2%;">
                                <thead>
                                <tr style="text-align: center">
                                    <th colspan="1" style="width: 10%">商品信息</th>
                                    <th style="width: 8%;">商品单价</th>
                                    <th style="width: 10%;">总金额</th>
                                    <th style="width: 10%;">订单状态</th>
                                    <th style="width: 10%;">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr></tr>
                                <td style="text-align: left"><h5>订单号：${order.idOrder} 创建时间：${order.createDate}</h5></td>
                                <c:forEach items="${order.items.keySet()}" end="${order.items.keySet().size()}"
                                           var="product"
                                           varStatus="status">
                                    <tr id="${product.id}" style="padding-bottom: 1%">
                                        <td class="goods">
                                            <img src="../../${product.picPath.get(0)}" class="goods-left"/>
                                            <div class="goods-right">
                                                <p>${product.productName}</p>
                                            </div>
                                        </td>
                                        <td class="blackcolor">
                                            ¥${product.price}
                                        </td>
                                        <td class="blackcolor" id="total${product.id}">
                                                ${product.price}&nbsp;*&nbsp;${order.items.get(product)}&nbsp;=&nbsp;${product.price * order.items.get(product)}
                                        </td>
                                        <c:if test="${status.index==0}">
                                            <td class="blackcolor" id="status${product.id}">
                                                <c:if test="${order.status.equals('未付款')}">
                                                    <a href="/pay/payOrder?orderId=${order.idOrder}">去付款</a>
                                                </c:if>
                                                <c:if test="${order.status.equals('已付款')}">
                                                    ${order.status}
                                                </c:if>
                                            </td>
                                        </c:if>
                                        <c:if test="${status.index!=0}">
                                            <td/>
                                        </c:if>
                                        <td class="blackcolor" id="comment${product.id}">
                                            <a href="/comments/comment?idproduct=${product.id}">评价此商品</a>
                                            <a href="/product?productID=1">申请售后</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td><h5>地址：${order.info.addr}</h5></td>
                                    <td><h5>姓名：${order.info.name}</h5></td>
                                    <td></td>
                                    <td><h5>电话：${order.info.tel}</h5></td>
                                    <br/>
                                </tr>
                                <tr class="footer" style="margin-top: 40px">
                                    <td colspan="5">
                                        <h3><span id="total">${order.total}</span>元</h3>
                                        <form>
                                            <button type="button" class="form-button" onclick="hid(${order.idOrder})">
                                                隐藏
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="section-title text-center mb-35">
                            <h3>您当前还未有订单，快去买一些文化产品吧！</h3>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <%@include file="../universal/footer.jsp" %>
</div>
<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
