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
        function productAdd(id, stock) {
            stock = Number(stock)
            var oldNum = document.getElementById("num" + id).value;
            var newNum = Number(oldNum) + 1;
            if (newNum > stock) {
                alert("库存不足");
                return;
            }
            /*
            document.getElementById("num" + id).value = newNum;
            var price = document.getElementById("price" + id).innerText;
            var singleTotal = document.getElementById("total" + id).innerText;
            var newSingleTotal = Number(singleTotal) + Number(price);
            document.getElementById("total" + id).innerText = newSingleTotal;
            var oldTotal = document.getElementById("total").innerText;
            var newTotal = Number(oldTotal) + Number(price);
            document.getElementById("total").innerText = newTotal;*/
            add(id, 1, 1);
        }

        function productMin(id) {
            var oldNum = document.getElementById("num" + id).value;
            var newNum = Number(oldNum) - 1;
            if (newNum < 0)
                return;
            sub(id);
            /*document.getElementById("num" + id).value = newNum;
            var price = document.getElementById("price" + id).innerText;
            var singleTotal = document.getElementById("total" + id).innerText;
            var newSingleTotal = Number(singleTotal) - Number(price);
            document.getElementById("total" + id).innerText = newSingleTotal;
            var oldTotal = document.getElementById("total").innerText;
            var newTotal = Number(oldTotal) - Number(price);
            document.getElementById("total").innerText = newTotal;
        */
        }
    </script>
</head>

<body>
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
    <div class="blog-area ml-50 mr-50 mt-105" style="padding-top: 10%">
        <div class="container">
            <div id="app">
                <h2 class="title">购物车</h2>
                <table class="tab" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <thead>
                    <tr style="text-align: center">
                        <th colspan="1">商品信息</th>
                        <th style="width: 14%;">商品金额</th>
                        <th style="width: 14%;">商品数量</th>
                        <th style="width: 14%;">总金额</th>
                        <th style="width: 10%">编辑</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${items.size()==0}">
                        <h2 style="text-align: center">当前购物车为空</h2>
                    </c:if>
                    <c:forEach items="${items}" end="${items.size()}" var="product" varStatus="status">
                        <tr id="${product.id}">
                            <td class="goods">
                                <img src="../../${product.picPath.get(0)}" class="goods-left"/>
                                <div class="goods-right">
                                    <p>${product.productName}</p>
                                </div>
                            </td>
                            <td class="blackcolor">${product.price}</td>
                            <td class="num">
                                <a onclick="productMin(${product.id})">-</a>&nbsp;&nbsp;
                                <input id="num${product.id}" value="${sessionScope.cart.items.get(product)}"
                                       type="number" disabled/>&nbsp;&nbsp;
                                <a onclick="productAdd(${product.id},${product.quantity})">+</a>&nbsp;&nbsp;
                            </td>
                            <td class="blackcolor"
                                id="total${product.id}">${product.price*sessionScope.cart.items.get(product)}</td>
                            <td class="del" onclick="remove(${product.id});
                                    document.getElementById(${product.id}).remove()">删除
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr class="footer" style="margin-top: 40px">
                        <td colspan="5">
                            合计：<h1><span id="total">${cart.total}</span>元</h1>
                        </td>
                    </tr>
                    </tfoot>

                </table>

                <div class="review-form-wrapper" style="padding-left: 25%; padding-right: 25%; text-align: center">
                    <HR size="2" style="color: #abd373">
                    <div class="review-form">
                        <h2>收货信息</h2>
                        <form action="/addorder">
                            <p class="comment-notes">
                                <br>
                                <br>
                                <span id="email-notes">请如实填写以下全部信息</span>
                            </p>
                            <br>
                            <div class="input-element">
                                <div class="comment-form-comment">
                                    <label>收货地址</label>
                                    <textarea name="address" required="required" cols="40" rows="8" >${address}</textarea>
                                </div>
                                <div class="review-comment-form-author">
                                    <label>姓名</label>
                                    <input name="name" required="required" value="${name}" type="text">
                                </div>
                                <div class="review-comment-form-email">
                                    <label>电话</label>
                                    <input name="phone" required="required" value="${phone}" type="text">
                                </div>
                                <div class="comment-submit">
                                    <button type="submit" class="form-button">结算</button>
                                </div>
                            </div>
                        </form>
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
