<%@ page import="whustore.model.Recommend" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="whustore.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.ui.ModelMap" %><%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/9
  Time: 下午3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>逛一逛————WHU STORE</title>

    <%@ include file="universal/allcss.jsp" %>

</head>
<body>
<div class="wrapper">
    <%--头部引用开始--%>
    <header>
        <div class="header-container">
            <div class="header-area header-absolute header-sticky pt-30 pb-30">
                <div class="container-fluid pl-50 pr-50">
                    <%@include file="universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <c:if test="${products.size()<4}">
        <div class="shop-product" style="padding-left: 10%;padding-right: 10%;padding-top: 10%">
            <div id="myTabContent-2" class="tab-content">
                <div class="row">
                    <!--Section Title Start-->
                    <div class="col-12">
                        <div class="section-title text-center mb-35">
                            <h3>喜欢就加入购物车吧</h3>
                            <br/><br/>
                        </div>
                    </div>
                    <!--Section Title End-->
                </div>
                <div id="grid" class="tab-pane fade active show">
                    <div class="product-grid-view">
                        <div class="row">
                            <c:forEach items="${products}" var="product" varStatus="status">
                                <div class="col-md-4">
                                    <!--Single Product Start-->
                                    <div class="single-product mb-25">
                                        <div class="product-img img-full">
                                            <a href="/product?productID=${product.id}">
                                                <img src="../../${product.picPath.get(0)}" alt="">
                                            </a>
                                            <span class="onsale">特惠!</span>
                                            <div class="product-action">
                                                <ul>
                                                    <li><a href="#open-modal${product.id}"
                                                           data-toggle="modal"
                                                           title="浮窗预览"><i class="fa fa-search"></i></a>
                                                    </li>
                                                    <li><a target="_blank"
                                                           href="http://wpa.qq.com/msgrd?v=3&uin=2392651490&site=qq&menu=yes"
                                                           title="联系客服">
                                                        <i class="fa fa-qq"></i>
                                                    </a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="product-content">
                                            <h2>
                                                <a href="/product?productID=${product.id}">
                                                        ${product.productName}
                                                </a></h2>
                                            <div class="product-price">
                                                <div class="price-box">
                                                    <span class="regular-price">¥${product.price}</span>
                                                </div>
                                                <div class="add-to-cart" tabindex="0">
                                                    <a onclick="add(${product.id},${product.quantity},1)">加入购物车</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--Single Product End-->
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${products.size()>8}">
        <div class="product-area mt-85" style="margin:10% auto">
            <div class="container">
                <div class="row">
                    <!--Section Title Start-->
                    <div class="col-12">
                        <div class="section-title text-center mb-35">
                            <h3>喜欢就加入购物车吧</h3>
                            <br/><br/>
                        </div>
                    </div>
                    <!--Section Title End-->
                </div>
                ﻿
                <!--商品们  两个一组  目标是11组 22个-->
                <div class="row">
                    <div class="product-slider-active">
                        <c:forEach items="${products}" var="product" step="1" varStatus="sta">
                            <c:if test="${(sta.index)%2 == 0}">
                                <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12">
                            </c:if>
                            <!--Single Product Start-->
                            <div class="single-product mb-25">
                                <div class="product-img img-full">
                                    <a href="/product?productID=${product.id}">
                                        <img src="../../${product.picPath.get(0)}" alt="">
                                    </a>
                                    <span class="onsale">特惠!</span>
                                    <div class="product-action">
                                        <ul>
                                            <li><a href="#open-modal${product.id}" data-toggle="modal"
                                                   title="浮窗预览"><i class="fa fa-search"></i></a>
                                            </li>
                                            <li><a target="_blank"
                                                   href="http://wpa.qq.com/msgrd?v=3&uin=2392651490&site=qq&menu=yes"
                                                   title="联系客服">
                                                <i class="fa fa-qq"></i>
                                            </a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="product-content">
                                    <h2><a href="/product?productID=${product.id}">
                                            ${product.productName}
                                    </a></h2>
                                    <div class="product-price">
                                        <div class="price-box">
                                            <span class="regular-price">${product.price}</span>
                                        </div>
                                        <div class="add-to-cart">
                                            <a onclick="add(${product.id},2,1)">加入购物车</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--Single Product End-->
                            <c:if test="${sta.index % 2==1}">
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>

                </div>
            </div>
            <!--商品完-->
        </div>
    </c:if>
    <c:if test="${products.size() <= 8 && products.size()>3}">
        <div class="also-like-product" style="padding-top: 10%">
            <div class="container">
                <div class="row">
                    <!--Section Title Start-->
                    <div class="col-12">
                        <div class="section-title text-center mb-35">
                            <h3>喜欢就加入购物车吧</h3>
                        </div>
                    </div>
                    <!--Section Title End-->
                </div>
                <div class="row">
                    <div class="product-slider-active">
                        <c:forEach var="i" begin="0" end="${products.size()-1}" step="1">
                            <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12">
                                <!--Single Product Start-->
                                <div class="single-product mb-25">
                                    <div class="product-img img-full">
                                        <a href="/product?productID=${products.get(i).id}">
                                            <img src="../../${products.get(i).picPath.get(0)}" alt="">
                                        </a>
                                        <div class="product-action">
                                            <ul>

                                                <li><a href="#open-modal${products.get(i).id}" data-toggle="modal"
                                                       title="浮窗预览"><i class="fa fa-search"></i></a>
                                                </li>
                                                <li><a target="_blank"
                                                       href="http://wpa.qq.com/msgrd?v=3&uin=2392651490&site=qq&menu=yes"
                                                       title="联系客服">
                                                    <i class="fa fa-qq"></i>
                                                </a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-content">
                                        <h2>
                                            <a href="/product?productID=${products.get(i).id}">${products.get(i).productName}</a>
                                        </h2>
                                        <div class="product-price">
                                            <div class="price-box">
                                                <span class="regular-price">¥${products.get(i).price}</span>
                                            </div>
                                            <div class="add-to-cart">
                                                <a onclick="add(${products.get(i).id},${products.get(i).quantity},1)">添加至购物车</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Single Product End-->
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <%@include file="universal/footer.jsp" %>


    <c:forEach items="${products}" var="product" step="1" varStatus="status">
        <div class="modal fade" id="open-modal${product.id}" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><i
                                class="fa fa-close"></i></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <!--Modal Img-->
                            <div class="col-md-5">
                                <!--Modal Tab Content Start-->
                                <div class="tab-content product-details-large" id="myTabContent">
                                    <c:forEach var="j" begin="0" end="${product.picPath.size()-1}" step="1">
                                        <div class="tab-pane <c:if test="${j==0}"> fade show active </c:if> "
                                             id="single-slide${j+1}" role="tabpanel"
                                             aria-labelledby="single-slide-tab-1">
                                            <!--Single Product Image Start-->
                                            <div class="single-product-img img-full">
                                                <img src="../../${product.picPath.get(j)}" alt="">
                                            </div>
                                            <!--Single Product Image End-->
                                        </div>
                                    </c:forEach>
                                </div>
                                <!--Modal Content End-->
                                <!--Modal Tab Menu Start-->
                                <div class="single-product-menu">
                                    <div class="nav single-slide-menu owl-carousel" role="tablist">
                                        <c:forEach var="j" begin="0" end="${product.picPath.size()-1}"
                                                   step="1">
                                            <div class="single-tab-menu img-full">
                                                <a <c:if test="${j==0}"> class="active" </c:if> data-toggle="tab"
                                                                                                id="single-slide-tab-1"
                                                                                                href="#single-slide${j+1}"><img
                                                        src="../../${product.picPath.get(j)}"
                                                        alt=""></a>
                                            </div>
                                        </c:forEach>

                                    </div>
                                </div>
                                <!--Modal Tab Menu End-->
                            </div>
                            <!--Modal Img-->
                            <!--Modal Content-->
                            <div class="col-md-7">
                                <div class="modal-product-info">
                                    <h1>${product.productName}</h1>
                                    <div class="modal-product-price">
                                            <%--<span class="old-price"></span>--%>
                                        <span class="new-price">¥${product.price}</span>
                                    </div>
                                    <a href="/product?productID=${product.id}" class="see-all">查看商品详情</a>
                                    <div class="add-to-cart quantity">
                                        <form class="add-quantity" action="#">
                                            <div class="modal-quantity">
                                                <input type="number" value="1">
                                            </div>
                                            <div class="add-to-link">
                                                <button class="form-button" data-text="add to cart">添加至购物车</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="cart-description">
                                        <p>${product.proIntro}</p>
                                    </div>
                                    <div class="social-share">
                                        <h3>联系客服</h3>
                                        <ul class="socil-icon2">
                                            <li><a target="_blank"
                                                   href="http://wpa.qq.com/msgrd?v=3&uin=2392651490&site=qq&menu=yes"
                                                   title="联系客服">
                                                <i class="fa fa-qq"></i>
                                            </a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!--Modal Content-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<%@ include file="universal/alljs.jsp" %>
</body>
</html>