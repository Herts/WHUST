<%@ page import="java.util.List" %>
<%@ page import="whustore.model.Product" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/10
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <title>逛一逛————WHU STORE</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="robots" content="noindex, follow"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="noindex, follow"/>
    <%@ include file="universal/allcss.jsp" %>
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
            <div class="header-area header-sticky pt-30 pb-30">
                <div class="container">
                    <%@include file="universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <div class="shop-area mb-70" style="padding-top: 3%">
        <div class="container">
            <div class="row">
                <!--Product Category Widget Start-->
                <div class="col-lg-3 order-2 order-lg-1">

                    <div class="shop-sidebar">
                        <h4>分类检索</h4>
                        <div class="categori-checkbox">
                            <form action="/shop/byCates" name="categories">
                                <ul>
                                    <c:forEach items="${categories}" var="category" varStatus="status">
                                        <li>
                                            <label>
                                                <input name="categories" type="checkbox" value="${category}"
                                                       <c:if test="${userFilter.contains(category)}">checked</c:if> >
                                                <a href="/shop/byCates?categories=${category}&page=1">
                                                        ${category}
                                                </a>
                                            </label>
                                        </li>
                                    </c:forEach>
                                    <input type="hidden" value="1" name="page" id="page">
                                </ul>
                                <input type="submit" class="form-button" value="检索" style="width: 50%">
                            </form>
                        </div>
                    </div>

                </div>
                <!--Product Category Widget End-->
                <%--商品页和排序页--%>
                <div class="col-lg-9 order-1 order-lg-2">
                    <div class="shop-layout">
                        <!--Grid & List View Start-->
                        <div class="shop-topbar-wrapper d-md-flex justify-content-md-between align-items-center">
                            <div class="grid-list-option">
                                <ul class="nav">
                                    <li>
                                        <a class="active show" data-toggle="tab" href="#grid"><i
                                                class="fa fa-th-large"></i></a>
                                    </li>
                                    <li>
                                        <a data-toggle="tab" href="#list" class=""><i class="fa fa-th-list"></i></a>
                                    </li>
                                </ul>
                            </div>
                            <!--Toolbar Short Area Start-->
                            <div class="toolbar-short-area d-md-flex align-items-center">
                                <form action="/shop/byOrder" method="get">
                                    <div class="toolbar-shorter ">
                                        <select name="orderType" class="wide" style="width: 80%" title="">
                                            <option data-display="选择排序依据">Nothing</option>
                                            <option value="default">默认</option>
                                            <option value="nameASC">名字升序</option>
                                            <option value="nameDES">名字降序</option>
                                            <option value="priceASC">价格低 > 高</option>
                                            <option value="priceDES">价格高 > 低</option>
                                        </select>
                                        <button class="btn-outline-dark" type="submit">排</button>
                                    </div>
                                </form>
                                <p class="show-product">正在展示 ${allProductsSize} 个商品中的 ${1+9*(page-1)} - ${9*page}</p>
                            </div>
                            <!--Toolbar Short Area End-->
                        </div>
                        <!--Grid & List View End-->
                        <!--Shop Product Start-->
                        <div class="shop-product">
                            <div id="myTabContent-2" class="tab-content">
                                <div id="grid" class="tab-pane fade active show">
                                    <div class="product-grid-view">
                                        <div class="row">
                                            <c:forEach items="${productList}" var="product" varStatus="status">
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
                                                                        <%--<li><a href="#open-modal" data-toggle="modal"
                                                                               title="Quick view"><i
                                                                                class="fa fa-search"></i></a></li>--%>
                                                                    <li><a href="#" title="Whishlist"><i
                                                                            class="fa fa-heart-o"></i></a></li>
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
                                                                <div class="add-to-cart">
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
                                <div id="list" class="tab-pane fade">
                                    <div class="product-list-view">
                                        <div class="product-list-item mb-40">
                                            <div class="row">
                                                <c:forEach items="${productList}" var="product" varStatus="status">
                                                    <!--Single List Product Start-->
                                                    <div class="col-md-4">
                                                        <div class="single-product">
                                                            <div class="product-img img-full">
                                                                <a href="product?productID=${product.id}">
                                                                    <img src="../../${product.picPath.get(0)}" alt="">
                                                                </a>
                                                                <span class="onsale">特惠!</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="product-content shop-list">
                                                            <h2><a href="product?productID=${product.id}">
                                                                    ${product.productName}
                                                            </a></h2>
                                                                <%--<div class="product-reviews">
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                </div>--%>
                                                            <div class="product-description">
                                                                <p>
                                                                        ${product.proIntro}
                                                                </p>
                                                            </div>
                                                            <div class="product-price">
                                                                <div class="price-box">
                                                                    <span class="old-price">¥${product.price}</span>
                                                                </div>
                                                            </div>
                                                            <div class="product-list-action">
                                                                <div class="add-btn">
                                                                    <a onclick="add(${product.id},${product.quantity},1)">加入购物车</a>
                                                                </div>
                                                                <ul>
                                                                    <li><a href="#" title="Whishlist"><i
                                                                            class="fa fa-heart-o"></i></a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!--Single List Product End-->
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--商品页--button--%>
                                <div class="product-pagination">
                                    <ul>
                                        <c:forEach var="i"
                                                   begin="${(page-(page%9))+1}"
                                                   end="${((allProductsSize/9) < ((page-(page%9))+5) ? (allProductsSize/9) :((page-(page%9))+5))+1}"
                                                   step="1">
                                            <li <c:if test="${i==page}"> class="active"</c:if>><a
                                                    href="/shop?page=${i}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${((allProductsSize/9) < ((page-(page%9))+5) ? (allProductsSize/9) :((page-(page%9))+5)) < allProductsSize/9}">
                                            <li>
                                                <a href="/shop?page=${((allProductsSize/9) < ((page-(page%9))+5) ? (allProductsSize/9) :((page-(page%9))+5))+1}"><i
                                                        class="fa fa-angle-double-right"></i></a></li>
                                        </c:if>
                                    </ul>
                                </div>
                                <%--页button End--%>
                            </div>
                        </div>
                        <!--Shop Product End-->
                    </div>
                </div>
            </div>
        </div>
        <%@include file="universal/footer.jsp" %>
    </div>
    <%@ include file="universal/alljs.jsp" %>
</div>
</body>
</html>
