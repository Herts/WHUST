<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="whustore.model.User" %><%--

  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/10
  Time: 下午6:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <%@include file="universal/allcss.jsp" %>
</head>
<body>


<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<div class="wrapper">
    <!--Header Area Start-->
    <header>
        <div class="header-container">
            <div class="header-area header-sticky pt-30 pb-30">
                <div class="container-fluid pl-50 pr-50">
                    <%@include file="universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    <!--Breadcrumb One Start-->
    <div class="breadcrumb-one mb-120">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="breadcrumb-img">
                        <img src="img/page-banner/product-banner.jpg" alt="">
                    </div>
                    <div class="breadcrumb-content">
                        <ul>
                            <li><a href="home">
                                主页
                            </a></li>
                            <li class="active">${product.productName}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Breadcrumb One End-->
    <!--Single Product Area Start-->
    <div class="single-product-area mb-115">
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-lg-5">
                    <div class="product-details-img-tab">
                        <!--Product Tab Content Start-->
                        <div class="tab-content single-product-img">
                            <c:forEach var="i" begin="0" end="${product.picPath.size()-1}" step="1">
                                <div class="tab-pane fade<c:if test="${i == 0}"> show active</c:if>" id="product${i+1}">
                                    <div class="product-large-thumb img-full">
                                        <div class="easyzoom easyzoom--overlay">
                                            <a href="${product.picPath.get(i)}">
                                                <img src="${product.picPath.get(i)}" alt="">
                                            </a>
                                            <a href="${product.picPath.get(i)}"
                                               class="popup-img venobox"
                                               data-gall="myGallery"><i class="fa fa-search"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                        <!--Product Tab Content End-->
                        <!--Product Tab Menu Start-->
                        <div class="product-menu">
                            <div class="nav product-tab-menu">
                                <c:forEach var="i" begin="0" end="${product.picPath.size()-1}" step="1">
                                    <div class="product-details-img">
                                        <a<c:if test="${i==0}"> class="active" </c:if> data-toggle="tab"
                                                                                       href="#product${i+1}"><img
                                                src="${product.picPath.get(i)}" alt=""></a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <!--Product Tab Menu End-->
                    </div>
                </div>
                <div class="col-md-12 col-lg-7">
                    <!--Product Details Content Start-->
                    <div class="product-details-content">
                        <!--Product Nav Start-->
                        <!--Product Nav End-->
                        <h2>${product.productName}</h2>

                        <%-- <div class="single-product-reviews">
                             <i class="fa fa-star"></i>
                             <i class="fa fa-star"></i>
                             <i class="fa fa-star"></i>
                             <i class="fa fa-star"></i>
                             <i class="fa fa-star-o"></i>
                             <a class="review-link" href="#">(1 customer review)</a>
                         </div>--%>
                        <div class="single-product-price">
                            <%--活动价--%>
                            <%--<span class="price new-price">$66.00</span>--%>
                            <span class="regular-price">¥${product.price}</span>
                        </div>
                        <div class="product-description">
                            <p>${product.proIntro}</p>
                        </div>
                        库存：<p id="stock" class="stock in-stock">${product.quantity}</p>
                        <div class="single-product-quantity">
                            <div class="product-quantity">
                                <input id="add-quantity" value="1" type="number">
                            </div>
                            <button onclick="add(${product.id},document.getElementById('stock').innerText,document.getElementById('add-quantity').value)"
                                    class="product-btn">添加至购物车
                            </button>
                        </div>
                        <div class="wishlist-compare-btn">
                            <a onclick="addFav(${product.id})" class="wishlist-btn">收藏商品</a>
                        </div>
                        <div class="product-meta">
                                <span class="posted-in">
                                        分类:
                                    <c:forEach var="i" begin="0" end="${product.types.size()-1}" step="1">
                                        <a href="#">| ${product.types.get(i)} |   </a>
                                    </c:forEach>
		                            </span>
                        </div>
                        <div class="single-product-sharing">
                            <h3>联系客服</h3>
                            <ul class="socil-icon2">
                                <li><a target="_blank"
                                       href="http://wpa.qq.com/msgrd?v=3&uin=2392651490&site=qq&menu=yes" title="联系客服">
                                    <i class="fa fa-qq"></i>
                                </a></li>
                            </ul>
                        </div>
                    </div>
                    <!--Product Details Content End-->
                </div>
            </div>
        </div>
    </div>
    <!--Single Product Area End-->
    <!--Product Description Review Area Start-->
    <div class="product-description-review-area mb-100">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-review-tab">
                        <!--Review And Description Tab Menu Start-->
                        <ul class="nav dec-and-review-menu">
                            <li>
                                <a class="active" data-toggle="tab" href="#description">Description</a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#reviews">Reviews (1)</a>
                            </li>
                        </ul>
                        <!--Review And Description Tab Menu End-->
                        <!--Review And Description Tab Content Start-->
                        <div class="tab-content product-review-content-tab" id="myTabContent-4">
                            <div class="tab-pane fade active show" id="description">
                                <div class="single-product-description">
                                    <p>${product.proIntro}</p>
                                    <p>考虑添加商品详细介绍</p>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="reviews">
                                <div class="review-page-comment">
                                    <h2>商品评价(待实现)</h2>
                                    <ul>
                                        <li>
                                            <div class="product-comment">
                                                <img src="img/icon/author.png" alt="">
                                                <div class="product-comment-content">
                                                    <div class="product-reviews">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                    <p class="meta">
                                                        <strong>admin</strong> - <span>November 22, 2016</span>
                                                        <div class="description">
                                                    <p>Good Product</p>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                </li>
                                </ul>
                                <%--<div class="review-form-wrapper">
                                    <div class="review-form">
                                        <span class="comment-reply-title">评价商品 </span>
                                        <form action="#">
                                            <p class="comment-notes">
                                                <span id="email-notes">您的email将会被公开.</span>
                                                必填项由
                                                <span class="required">*</span>
                                                标记
                                            </p>
                                            <div class="comment-form-rating">
                                                <label>Your rating</label>
                                                <div class="rating">
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </div>
                                            </div>
                                            <div class="input-element">
                                                <div class="comment-form-comment">
                                                    <label>评论</label>
                                                    <textarea name="message" cols="40" rows="8"></textarea>
                                                </div>
                                                <div class="review-comment-form-author">
                                                    <label>姓名 </label>
                                                    <input required="required" type="text">
                                                </div>
                                                <div class="review-comment-form-email">
                                                    <label>Email </label>
                                                    <input required="required" type="text">
                                                </div>
                                                <div class="comment-submit">
                                                    <button type="submit" class="form-button">Submit</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>--%>
                            </div>
                        </div>
                    </div>
                    <!--Review And Description Tab Content End-->
                </div>
            </div>
        </div>
    </div>
    <!--Product Description Review Area Start-->
    <!--Also Like Product Start-->
    <div class="also-like-product">
        <div class="container">
            <div class="row">
                <!--Section Title Start-->
                <div class="col-12">
                    <div class="section-title text-center mb-35">
                        <h3>您可能也喜欢这些商品……</h3>
                    </div>
                </div>
                <!--Section Title End-->
            </div>
            <div class="row">
                <div class="product-slider-active">
                    <c:forEach var="i" begin="0" end="5" step="1">
                        <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12">
                            <!--Single Product Start-->
                            <div class="single-product mb-25">
                                <div class="product-img img-full">
                                    <a href="/product?productID=${alsoLikes.get(i).id}">
                                        <img src="${alsoLikes.get(i).picPath.get(0)}" alt="">
                                    </a>
                                    <div class="product-action">
                                        <ul>
                                            <li><a href="#open-modal${i}" data-toggle="modal" title="浮窗预览"><i
                                                    class="fa fa-search"></i></a></li>
                                            <li><a onclick="addFav(${product.id})" title="收藏商品"><i class="fa fa-heart-o"></i></a></li>
                                            <li><a target="_blank"
                                                   href="http://wpa.qq.com/msgrd?v=3&uin=2392651490&site=qq&menu=yes" title="联系客服">
                                                <i class="fa fa-qq"></i>
                                            </a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="product-content">
                                    <h2>
                                        <a href="/product?productID=${alsoLikes.get(i).id}">${alsoLikes.get(i).productName}</a>
                                    </h2>
                                    <div class="product-price">
                                        <div class="price-box">
                                            <span class="regular-price">¥${alsoLikes.get(i).price}</span>
                                        </div>
                                        <div class="add-to-cart">
                                            <a onclick="add(${alsoLikes.get(i).id},${alsoLikes.get(i).quantity},1)">添加至购物车</a>
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
    <!--Also Like Product End-->
    <!--Footer Area Start-->
    <%@include file="universal/footer.jsp" %>
    <!--Footer Area End-->
    <!-- Modal Area Strat -->
    <c:forEach var="i" begin="0" end="5" step="1">
        <div class="modal fade" id="open-modal${i}" tabindex="-1" role="dialog" aria-hidden="true">
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
                                    <c:forEach var="j" begin="0" end="${alsoLikes.get(i).picPath.size()-1}" step="1">
                                        <div class="tab-pane <c:if test="${j==0}"> fade show active </c:if> "
                                             id="single-slide${j+1}" role="tabpanel"
                                             aria-labelledby="single-slide-tab-1">
                                            <!--Single Product Image Start-->
                                            <div class="single-product-img img-full">
                                                <img src="${alsoLikes.get(i).picPath.get(j)}" alt="">
                                            </div>
                                            <!--Single Product Image End-->
                                        </div>
                                    </c:forEach>
                                </div>
                                <!--Modal Content End-->
                                <!--Modal Tab Menu Start-->
                                <div class="single-product-menu">
                                    <div class="nav single-slide-menu owl-carousel" role="tablist">
                                        <c:forEach var="j" begin="0" end="${alsoLikes.get(i).picPath.size()-1}"
                                                   step="1">
                                            <div class="single-tab-menu img-full">
                                                <a <c:if test="${j==0}"> class="active" </c:if> data-toggle="tab"
                                                                                                id="single-slide-tab-1"
                                                                                                href="#single-slide${j+1}"><img
                                                        src="${alsoLikes.get(i).picPath.get(j)}"
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
                                    <h1>${alsoLikes.get(i).productName}</h1>
                                    <div class="modal-product-price">
                                            <%--<span class="old-price"></span>--%>
                                        <span class="new-price">¥${alsoLikes.get(i).price}</span>
                                    </div>
                                    <a href="/product?productID=${alsoLikes.get(i).id}" class="see-all">查看商品详情</a>
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
                                        <p>${alsoLikes.get(i).proIntro}</p>
                                    </div>
                                    <div class="social-share">
                                        <h3>联系客服</h3>
                                        <ul class="socil-icon2">
                                            <li><a target="_blank"
                                                   href="http://wpa.qq.com/msgrd?v=3&uin=&site=qq&menu=yes">
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
    <!-- Modal Area End -->
</div>

<%@include file="universal/alljs.jsp" %>
</body>
</html>

