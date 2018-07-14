<%@ page import="whustore.model.Customer" %><%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/8
  Time: 下午4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>Title</title>
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
        function productAdd(id) {
            var oldNum = document.getElementById("num" + id).value;
            var newNum = Number(oldNum) + 1;
            document.getElementById("num" + id).value = newNum;

        }
    </script>
</head>

<body>
<div class="wrapper">
    <%--头部引用开始--%>
    <header>
        <div class="header-container">
            <div class="header-area header-sticky pt-30 pb-30">
                <div class="container">
                    <%@include file="../universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <div class="blog-area ml-50 mr-50 mt-105">
        <div class="container">
            <div id="app">
                <h2 class="title">购物车</h2>
                <table class="tab" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <thead>
                    <tr style="text-align: center">
                        <th colspan="2">商品信息</th>
                        <th style="width: 14%;">商品金额</th>
                        <th style="width: 14%;">商品数量</th>
                        <th style="width: 14%;">总金额</th>
                        <th style="width: 10%">编辑</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${items}" end="${items.size()}" var="product" varStatus="status">
                        <tr>
                            <td style="width: 5%;"><input id="product${product.id}" type="checkbox" class="chbsty"/>
                            </td>
                            <td class="goods">
                                <img src="" class="goods-left"/>
                                <div class="goods-right">
                                    <p>${product.productName}</p>
                                </div>
                            </td>
                            <td><p id="price${product.id}">${product.price}</p></td>
                            <td class="num">
                                <a href="javascript">-</a>&nbsp;&nbsp;
                                <input id="num${product.id}" value="${sessionScope.cart.items.get(product)}"
                                       type="number" disabled/>&nbsp;&nbsp;
                                <a onclick="productAdd(${product.id})">+</a>&nbsp;&nbsp;
                            </td>
                            <td class="blackcolor"
                                id="total${product.id}">${product.price*sessionScope.cart.items.get(product)}</td>
                            <td class="del">删除</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr class="footer">
                        <td><input type="checkbox" class="chbsty " onclick=""/></td>
                        <td>
                            <span class="blackcolor">全选</span>&nbsp;&nbsp;
                        </td>
                        <td colspan="4">
                            总计：<span>${cart.total}</span>元
                            <button type="button" class="form-button">结账</button>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>

    <%@include file="../universal/footer.jsp" %>

</div>

<%@ include file="../universal/alljs.jsp" %>
</body>
</html>
