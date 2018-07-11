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

<%--
<div class="wrapper">
    <!--Header Area Start-->
    <header>
        <div class="header-container">
            <div class="header-area header-sticky pt-30 pb-30">
                <div class="container">
                    <%@include file="universal/headerContent.jsp"%>
                </div>
            </div>
        </div>
    </header>
    <!--Header Area End-->
    &lt;%&ndash;﻿单个商品展示&ndash;%&gt;
    <div class="single-product-area mb-115" &lt;%&ndash;style="padding-top: 10%"&ndash;%&gt;>
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-lg-5">
                    <div class="product-details-img-tab">
                        <!--Product Tab Content Start-->
                        <div class="tab-content single-product-img">
                            <div class="tab-pane fade" id="product1">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay">
                                        <a href="img/single-product/large/single-product1.jpg">
                                            <img src="img/single-product/large/single-product1.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product1.jpg"
                                           class="popup-img venobox vbox-item" data-gall="myGallery"><i
                                                class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="product2">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay is-ready">
                                        <a href="img/single-product/large/single-product2.jpg">
                                            <img src="img/single-product/large/single-product2.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product2.jpg"
                                           class="popup-img venobox vbox-item" data-gall="myGallery"><i
                                                class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="product3">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay">
                                        <a href="img/single-product/large/single-product3.jpg">
                                            <img src="img/single-product/large/single-product3.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product3.jpg"
                                           class="popup-img venobox vbox-item" data-gall="myGallery"><i
                                                class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="product4">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay is-ready">
                                        <a href="img/single-product/large/single-product4.jpg">
                                            <img src="img/single-product/large/single-product4.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product4.jpg"
                                           class="popup-img venobox vbox-item" data-gall="myGallery"><i
                                                class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade active show" id="product5">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay is-ready">
                                        <a href="img/single-product/large/single-product5.jpg">
                                            <img src="img/single-product/large/single-product5.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product5.jpg"
                                           class="popup-img venobox vbox-item" data-gall="myGallery"><i
                                                class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="product6">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay is-ready">
                                        <a href="img/single-product/large/single-product6.jpg">
                                            <img src="img/single-product/large/single-product6.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product6.jpg"
                                           class="popup-img venobox vbox-item" data-gall="myGallery"><i
                                                class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Product Tab Content End-->
                        <!--Product Tab Menu Start-->
                        <div class="product-menu">
                            <div class="nav product-tab-menu slick-initialized slick-slider"><i
                                    class="fa fa-angle-left slick-arrow" style="display: block;"></i>


                                <div class="slick-list draggable">
                                    <div class="slick-track"
                                         style="opacity: 1; width: 1860px; transform: translate3d(-744px, 0px, 0px);">
                                        <div class="product-details-img slick-slide slick-cloned" style="width: 124px;"
                                             data-slick-index="-3" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product4" tabindex="-1"><img
                                                    src="img/single-product/small/single-product4.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-cloned" style="width: 124px;"
                                             data-slick-index="-2" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product5" tabindex="-1"><img
                                                    src="img/single-product/small/single-product5.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-cloned" style="width: 124px;"
                                             data-slick-index="-1" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product6" tabindex="-1"><img
                                                    src="img/single-product/small/single-product6.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide" style="width: 124px;"
                                             data-slick-index="0" aria-hidden="true" tabindex="-1">
                                            <a class="" data-toggle="tab" href="#product1" tabindex="-1"><img
                                                    src="img/single-product/small/single-product1.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide" style="width: 124px;"
                                             data-slick-index="1" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product2" tabindex="-1" class=""><img
                                                    src="img/single-product/small/single-product2.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide" style="width: 124px;"
                                             data-slick-index="2" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product3" tabindex="-1" class=""><img
                                                    src="img/single-product/small/single-product3.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-current slick-active"
                                             style="width: 124px;" data-slick-index="3" aria-hidden="false"
                                             tabindex="0">
                                            <a data-toggle="tab" href="#product4" tabindex="0" class=""><img
                                                    src="img/single-product/small/single-product4.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-active" style="width: 124px;"
                                             data-slick-index="4" aria-hidden="false" tabindex="0">
                                            <a data-toggle="tab" href="#product5" tabindex="0" class="active"><img
                                                    src="img/single-product/small/single-product5.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-active" style="width: 124px;"
                                             data-slick-index="5" aria-hidden="false" tabindex="0">
                                            <a data-toggle="tab" href="#product6" tabindex="0" class=""><img
                                                    src="img/single-product/small/single-product6.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-cloned" style="width: 124px;"
                                             data-slick-index="6" aria-hidden="true" tabindex="-1">
                                            <a class="" data-toggle="tab" href="#product1" tabindex="-1"><img
                                                    src="img/single-product/small/single-product1.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-cloned" style="width: 124px;"
                                             data-slick-index="7" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product2" tabindex="-1"><img
                                                    src="img/single-product/small/single-product2.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-cloned" style="width: 124px;"
                                             data-slick-index="8" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product3" tabindex="-1"><img
                                                    src="img/single-product/small/single-product3.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-cloned" style="width: 124px;"
                                             data-slick-index="9" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product4" tabindex="-1"><img
                                                    src="img/single-product/small/single-product4.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-cloned" style="width: 124px;"
                                             data-slick-index="10" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product5" tabindex="-1"><img
                                                    src="img/single-product/small/single-product5.jpg" alt=""></a>
                                        </div>
                                        <div class="product-details-img slick-slide slick-cloned" style="width: 124px;"
                                             data-slick-index="11" aria-hidden="true" tabindex="-1">
                                            <a data-toggle="tab" href="#product6" tabindex="-1"><img
                                                    src="img/single-product/small/single-product6.jpg" alt=""></a>
                                        </div>
                                    </div>
                                </div>
                                <i class="fa fa-angle-right slick-next-btn slick-arrow" style="display: block;"></i>
                            </div>
                        </div>
                        <!--Product Tab Menu End-->
                    </div>
                </div>
                <div class="col-md-12 col-lg-7">
                    <!--Product Details Content Start-->
                    <div class="product-details-content">
                        <!--Product Nav Start-->
                        <div class="product-nav">
                            <a href="#"><i class="fa fa-angle-left"></i></a>
                            <a href="#"><i class="fa fa-angle-right"></i></a>
                        </div>
                        <!--Product Nav End-->
                        <h2>Sit voluptatem</h2>
                        <div class="single-product-reviews">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-o"></i>
                            <a class="review-link" href="#">(1 customer review)</a>
                        </div>
                        <div class="single-product-price">
                            <span class="price new-price">$66.00</span>
                            <span class="regular-price">$77.00</span>
                        </div>
                        <div class="product-description">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fringilla augue nec est
                                tristique auctor. Donec non est at libero vulputate rutrum. Morbi ornare lectus quis
                                justo gravida semper. Nulla tellus mi, vulputate adipiscing cursus eu, suscipit id
                                nulla.</p>
                        </div>
                        <p class="stock in-stock">150 in stock</p>
                        <div class="single-product-quantity">
                            <form class="add-quantity" action="#">
                                <div class="product-quantity">
                                    <input value="1" type="number">
                                </div>
                                <div class="add-to-link">
                                    <button class="product-btn" data-text="add to cart">add to cart</button>
                                </div>
                            </form>
                        </div>
                        <div class="wishlist-compare-btn">
                            <a href="#" class="wishlist-btn">Add to Wishlist</a>
                            <a href="#" class="add-compare">Compare</a>
                        </div>
                        <div class="product-meta">
                                <span class="posted-in">
                                        Categories:
		                                <a href="#">Accessories</a>,
		                                <a href="#">Electronics</a>
		                            </span>
                        </div>
                        <div class="single-product-sharing">
                            <h3>Share this product</h3>
                            <ul>
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <!--Product Details Content End-->
                </div>
            </div>
        </div>
    </div>
    &lt;%&ndash;单个商品展示结束&ndash;%&gt;
    &lt;%&ndash;描述和评价&ndash;%&gt;
    &lt;%&ndash;<div class="product-description-review-area mb-100">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-review-tab">
                        <!--Review And Description Tab Menu Start-->
                        <ul class="nav dec-and-review-menu">
                            <li>
                                <a class="active show" data-toggle="tab" href="#description">Description</a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#reviews" class="">Reviews (1)</a>
                            </li>
                        </ul>
                        <!--Review And Description Tab Menu End-->
                        <!--Review And Description Tab Content Start-->
                        <div class="tab-content product-review-content-tab" id="myTabContent-4">
                            <div class="tab-pane fade active show" id="description">
                                <div class="single-product-description">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fringilla augue nec
                                        est tristique auctor. Donec non est at libero vulputate rutrum. Morbi ornare
                                        lectus quis justo gravida semper. Nulla tellus mi, vulputate adipiscing cursus
                                        eu, suscipit id nulla.</p>
                                    <p>Pellentesque aliquet, sem eget laoreet ultrices, ipsum metus feugiat sem, quis
                                        fermentum turpis eros eget velit. Donec ac tempus ante. Fusce ultricies massa
                                        massa. Fusce aliquam, purus eget sagittis vulputate, sapien libero hendrerit
                                        est, sed commodo augue nisi non neque. Lorem ipsum dolor sit amet, consectetur
                                        adipiscing elit. Sed tempor, lorem et placerat vestibulum, metus nisi posuere
                                        nisl, in accumsan elit odio quis mi. Cras neque metus, consequat et blandit et,
                                        luctus a nunc. Etiam gravida vehicula tellus, in imperdiet ligula euismod
                                        eget.</p>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="reviews">
                                <div class="review-page-comment">
                                    <h2>1 review for Sit voluptatem</h2>
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
                                                    </p>
                                                    <div class="description">
                                                        <p>Good Product</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                    <div class="review-form-wrapper">
                                        <div class="review-form">
                                            <span class="comment-reply-title">Add a review </span>
                                            <form action="#">
                                                <p class="comment-notes">
                                                    <span id="email-notes">Your email address will not be published.</span>
                                                    Required fields are marked
                                                    <span class="required">*</span>
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
                                                        <label>Comment</label>
                                                        <textarea name="message" cols="40" rows="8"></textarea>
                                                    </div>
                                                    <div class="review-comment-form-author">
                                                        <label>Name </label>
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
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Review And Description Tab Content End-->
                    </div>
                </div>
            </div>
        </div>
    </div>&ndash;%&gt;
    &lt;%&ndash;描述和评价&ndash;%&gt;
    &lt;%&ndash;猜你喜欢&ndash;%&gt;
    &lt;%&ndash;<div class="also-like-product">
        <div class="container">
            <div class="row">
                <!--Section Title Start-->
                <div class="col-12">
                    <div class="section-title text-center mb-35">
                        <h3>You may also like…</h3>
                    </div>
                </div>
                <!--Section Title End-->
            </div>
            <div class="row">
                <div class="product-slider-active slick-initialized slick-slider slick-dotted">


                    <div class="slick-list draggable">
                        <div class="slick-track"
                             style="opacity: 1; width: 1920px; transform: translate3d(0px, 0px, 0px);">
                            <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12 slick-slide slick-current slick-active"
                                 style="width: 320px;" data-slick-index="0" aria-hidden="false" tabindex="0"
                                 role="tabpanel" id="slick-slide00" aria-describedby="slick-slide-control00">
                                <!--Single Product Start-->
                                <div class="single-product mb-25">
                                    <div class="product-img img-full">
                                        <a href="single-product.html" tabindex="0">
                                            <img src="img/product/product1.jpg" alt="">
                                        </a>
                                        <div class="product-action">
                                            <ul>
                                                <li><a href="#open-modal" data-toggle="modal" title="Quick view"
                                                       tabindex="0"><i class="fa fa-search"></i></a></li>
                                                <li><a href="#" title="Whishlist" tabindex="0"><i
                                                        class="fa fa-heart-o"></i></a></li>
                                                <li><a href="#" title="Compare" tabindex="0"><i
                                                        class="fa fa-refresh"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-content">
                                        <h2><a href="single-product.html" tabindex="0">Eleifend quam</a></h2>
                                        <div class="product-price">
                                            <div class="price-box">
                                                <span class="regular-price">$115.00</span>
                                            </div>
                                            <div class="add-to-cart">
                                                <a href="#" tabindex="0">Add To Cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Single Product End-->
                            </div>
                            <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12 slick-slide slick-active"
                                 style="width: 320px;" data-slick-index="1" aria-hidden="false" tabindex="0"
                                 role="tabpanel" id="slick-slide01" aria-describedby="slick-slide-control01">
                                <!--Single Product Start-->
                                <div class="single-product mb-25">
                                    <div class="product-img img-full">
                                        <a href="single-product.html" tabindex="0">
                                            <img src="img/product/product3.jpg" alt="">
                                        </a>
                                        <div class="product-action">
                                            <ul>
                                                <li><a href="#open-modal" data-toggle="modal" title="Quick view"
                                                       tabindex="0"><i class="fa fa-search"></i></a></li>
                                                <li><a href="#" title="Whishlist" tabindex="0"><i
                                                        class="fa fa-heart-o"></i></a></li>
                                                <li><a href="#" title="Compare" tabindex="0"><i
                                                        class="fa fa-refresh"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-content">
                                        <h2><a href="single-product.html" tabindex="0">Nulla sed stg</a></h2>
                                        <div class="product-price">
                                            <div class="price-box">
                                                <span class="regular-price">$40.00</span>
                                            </div>
                                            <div class="add-to-cart">
                                                <a href="#" tabindex="0">Add To Cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Single Product End-->
                            </div>
                            <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12 slick-slide slick-active"
                                 style="width: 320px;" data-slick-index="2" aria-hidden="false" tabindex="0"
                                 role="tabpanel" id="slick-slide02" aria-describedby="slick-slide-control02">
                                <!--Single Product Start-->
                                <div class="single-product mb-25">
                                    <div class="product-img img-full">
                                        <a href="single-product.html" tabindex="0">
                                            <img src="img/product/product5.jpg" alt="">
                                        </a>
                                        <div class="product-action">
                                            <ul>
                                                <li><a href="#open-modal" data-toggle="modal" title="Quick view"
                                                       tabindex="0"><i class="fa fa-search"></i></a></li>
                                                <li><a href="#" title="Whishlist" tabindex="0"><i
                                                        class="fa fa-heart-o"></i></a></li>
                                                <li><a href="#" title="Compare" tabindex="0"><i
                                                        class="fa fa-refresh"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-content">
                                        <h2><a href="single-product.html" tabindex="0">Odio tortor consequat</a></h2>
                                        <div class="product-price">
                                            <div class="price-box">
                                                <span class="regular-price">$90.00</span>
                                            </div>
                                            <div class="add-to-cart">
                                                <a href="#" tabindex="0">Add To Cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Single Product End-->
                            </div>
                            <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12 slick-slide" style="width: 320px;"
                                 data-slick-index="3" aria-hidden="true" tabindex="-1" role="tabpanel"
                                 id="slick-slide03" aria-describedby="slick-slide-control03">
                                <!--Single Product Start-->
                                <div class="single-product mb-25">
                                    <div class="product-img img-full">
                                        <a href="single-product.html" tabindex="-1">
                                            <img src="img/product/product7.jpg" alt="">
                                        </a>
                                        <div class="product-action">
                                            <ul>
                                                <li><a href="#open-modal" data-toggle="modal" title="Quick view"
                                                       tabindex="-1"><i class="fa fa-search"></i></a></li>
                                                <li><a href="#" title="Whishlist" tabindex="-1"><i
                                                        class="fa fa-heart-o"></i></a></li>
                                                <li><a href="#" title="Compare" tabindex="-1"><i
                                                        class="fa fa-refresh"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-content">
                                        <h2><a href="single-product.html" tabindex="-1">Vulputate justo</a></h2>
                                        <div class="product-price">
                                            <div class="price-box">
                                                <span class="regular-price">$70.00</span>
                                            </div>
                                            <div class="add-to-cart">
                                                <a href="#" tabindex="-1">Add To Cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Single Product End-->
                            </div>
                            <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12 slick-slide" style="width: 320px;"
                                 data-slick-index="4" aria-hidden="true" tabindex="-1" role="tabpanel"
                                 id="slick-slide04" aria-describedby="slick-slide-control04">
                                <!--Single Product Start-->
                                <div class="single-product mb-25">
                                    <div class="product-img img-full">
                                        <a href="single-product.html" tabindex="-1">
                                            <img src="img/product/product9.jpg" alt="">
                                        </a>
                                        <div class="product-action">
                                            <ul>
                                                <li><a href="#open-modal" data-toggle="modal" title="Quick view"
                                                       tabindex="-1"><i class="fa fa-search"></i></a></li>
                                                <li><a href="#" title="Whishlist" tabindex="-1"><i
                                                        class="fa fa-heart-o"></i></a></li>
                                                <li><a href="#" title="Compare" tabindex="-1"><i
                                                        class="fa fa-refresh"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-content">
                                        <h2><a href="single-product.html" tabindex="-1">Ipsum imperdie</a></h2>
                                        <div class="product-price">
                                            <div class="price-box">
                                                <span class="regular-price">$100.00</span>
                                            </div>
                                            <div class="add-to-cart">
                                                <a href="#" tabindex="-1">Add To Cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Single Product End-->
                            </div>
                            <div class="col-md-3 col-lg-3 col-sm-4 col-xs-12 slick-slide" style="width: 320px;"
                                 data-slick-index="5" aria-hidden="true" tabindex="-1" role="tabpanel"
                                 id="slick-slide05" aria-describedby="slick-slide-control05">
                                <!--Single Product Start-->
                                <div class="single-product mb-25">
                                    <div class="product-img img-full">
                                        <a href="single-product.html" tabindex="-1">
                                            <img src="img/product/product11.jpg" alt="">
                                        </a>
                                        <div class="product-action">
                                            <ul>
                                                <li><a href="#open-modal" data-toggle="modal" title="Quick view"
                                                       tabindex="-1"><i class="fa fa-search"></i></a></li>
                                                <li><a href="#" title="Whishlist" tabindex="-1"><i
                                                        class="fa fa-heart-o"></i></a></li>
                                                <li><a href="#" title="Compare" tabindex="-1"><i
                                                        class="fa fa-refresh"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-content">
                                        <h2><a href="single-product.html" tabindex="-1">Pellentesque position</a></h2>
                                        <div class="product-price">
                                            <div class="price-box">
                                                <span class="regular-price">$90.00</span>
                                            </div>
                                            <div class="add-to-cart">
                                                <a href="#" tabindex="-1">Add To Cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Single Product End-->
                            </div>
                        </div>
                    </div>
                    <ul class="slick-dots" style="display: block;" role="tablist">
                        <li class="slick-active" role="presentation">
                            <button type="button" role="tab" id="slick-slide-control00" aria-controls="slick-slide00"
                                    aria-label="1 of 2" tabindex="0" aria-selected="true">1
                            </button>
                        </li>
                        <li role="presentation">
                            <button type="button" role="tab" id="slick-slide-control01" aria-controls="slick-slide01"
                                    aria-label="2 of 2" tabindex="-1">2
                            </button>
                        </li>
                        <li role="presentation">
                            <button type="button" role="tab" id="slick-slide-control02" aria-controls="slick-slide02"
                                    aria-label="3 of 2" tabindex="-1">3
                            </button>
                        </li>
                        <li role="presentation">
                            <button type="button" role="tab" id="slick-slide-control03" aria-controls="slick-slide03"
                                    aria-label="4 of 2" tabindex="-1">4
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>&ndash;%&gt;
    &lt;%&ndash;猜你喜欢&ndash;%&gt;
    <%@include file="universal/footer.jsp"%>
    <!--Footer Area Start-->
    &lt;%&ndash;<footer>
        <div class="footer-container">
            <!--Footer Top Area Start-->
            <div class="footer-top-area black-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-40">
                                <div class="footer-title">
                                    <h3>My Account</h3>
                                </div>
                                <ul class="link-widget">
                                    <li><a href="#">About Us</a></li>
                                    <li><a href="#">Team Member</a></li>
                                    <li><a href="#">Career</a></li>
                                    <li><a href="#">Specials</a></li>
                                    <li><a href="shop.html">Best sellers</a></li>
                                    <li><a href="#">Our stores</a></li>
                                    <li><a href="#">Contact us</a></li>
                                </ul>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-40">
                                <div class="footer-title">
                                    <h3>Information</h3>
                                </div>
                                <ul class="link-widget">
                                    <li><a href="#">About Us</a></li>
                                    <li><a href="#">Contact Us</a></li>
                                    <li><a href="#">My orders</a></li>
                                    <li><a href="#">Terms & Conditions</a></li>
                                    <li><a href="#">Returns & Exchanges</a></li>
                                    <li><a href="#">Shipping & Delivery</a></li>
                                    <li><a href="#">Privacy Policy</a></li>
                                </ul>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-40">
                                <div class="footer-title">
                                    <h3>Quick Links</h3>
                                </div>
                                <ul class="link-widget">
                                    <li><a href="#">Support Center</a></li>
                                    <li><a href="#">Term & Conditions</a></li>
                                    <li><a href="#">Shipping</a></li>
                                    <li><a href="#">Privacy Policy</a></li>
                                    <li><a href="#">Help</a></li>
                                    <li><a href="#">Products Return</a></li>
                                    <li><a href="#">FAQS</a></li>
                                </ul>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-40">
                                <div class="footer-title">
                                    <h3>Categories</h3>
                                </div>
                                <ul class="link-widget">
                                    <li><a href="#">Bedroom</a></li>
                                    <li><a href="#">Furniture</a></li>
                                    <li><a href="#">Livingroom</a></li>
                                    <li><a href="#">Mobiles & Tablets</a></li>
                                    <li><a href="#">Men</a></li>
                                    <li><a href="#">Women</a></li>
                                    <li><a href="#">Accessories</a></li>
                                </ul>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                    </div>
                </div>
            </div>
            <!--Footer Top Area End-->
            <!--Footer Middle Area Start-->
            <div class="footer-middle-area black-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-30">
                                <div class="footer-logo">
                                    <a href="index.html"><img src="img/logo/logo-footer.png" alt=""></a>
                                </div>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-30">
                                <div class="footer-info">
                                    <div class="icon">
                                        <i class="fa fa-home"></i>
                                    </div>
                                    <p>Address : No 40 Baria Sreet 15/2 NewYork City, NY, United States.</p>
                                </div>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-30">
                                <div class="footer-info">
                                    <div class="icon">
                                        <i class="fa fa-envelope-open-o"></i>
                                    </div>
                                    <p>Email: <br>info@yourmail.com</p>
                                </div>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-30">
                                <div class="footer-info">
                                    <div class="icon">
                                        <i class="fa fa-mobile"></i>
                                    </div>
                                    <p>Phone: <br>(+68) 123 456 7890</p>
                                </div>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                    </div>
                </div>
            </div>
            <!--Footer Middle Area End-->
            <!--Footer Bottom Area Start-->
            <div class="footer-bottom-area black-bg pt-50 pb-50">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <!--Footer Payment Start-->
                            <div class="footer-payments-image">
                                <img src="img/payment/payment-icon.png" alt="">
                            </div>
                            <!--Footer Payment End-->
                            <!--Footer Menu Start-->
                            <div class="footer-menu text-center">
                                <nav>
                                    <ul>
                                        <li><a href="#">Site Map</a></li>
                                        <li><a href="#">Search Terms</a></li>
                                        <li><a href="#">Advanced Search</a></li>
                                        <li><a href="#">Orders and Returns</a></li>
                                        <li><a href="#">Contact Us</a></li>
                                    </ul>
                                </nav>
                            </div>
                            <!--Footer Menu End-->
                            <!--Footer Copyright Start-->
                            <div class="footer-copyright">
                                <p>Copyright &copy; 2018.Company name All rights reserved.<a target="_blank"
                                                                                             href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
                                </p>
                            </div>
                            <!--Footer Copyright End-->
                        </div>
                    </div>
                </div>
            </div>
            <!--Footer Bottom Area End-->
        </div>
    </footer>&ndash;%&gt;
    <!--Footer Area End-->
    <!-- Modal Area Strat -->
    <div class="modal fade" id="open-modal" tabindex="-1" role="dialog" aria-hidden="true">
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
                                <div class="tab-pane fade show active" id="single-slide1" role="tabpanel"
                                     aria-labelledby="single-slide-tab-1">
                                    <!--Single Product Image Start-->
                                    <div class="single-product-img img-full">
                                        <img src="img/single-product/large/single-product1.jpg" alt="">
                                    </div>
                                    <!--Single Product Image End-->
                                </div>
                                <div class="tab-pane fade" id="single-slide2" role="tabpanel"
                                     aria-labelledby="single-slide-tab-2">
                                    <!--Single Product Image Start-->
                                    <div class="single-product-img img-full">
                                        <img src="img/single-product/large/single-product2.jpg" alt="">
                                    </div>
                                    <!--Single Product Image End-->
                                </div>
                                <div class="tab-pane fade" id="single-slide3" role="tabpanel"
                                     aria-labelledby="single-slide-tab-3">
                                    <!--Single Product Image Start-->
                                    <div class="single-product-img img-full">
                                        <img src="img/single-product/large/single-product3.jpg" alt="">
                                    </div>
                                    <!--Single Product Image End-->
                                </div>
                                <div class="tab-pane fade" id="single-slide4" role="tabpanel"
                                     aria-labelledby="single-slide-tab-4">
                                    <!--Single Product Image Start-->
                                    <div class="single-product-img img-full">
                                        <img src="img/single-product/large/single-product4.jpg" alt="">
                                    </div>
                                    <!--Single Product Image End-->
                                </div>
                                <div class="tab-pane fade" id="single-slide5" role="tabpanel"
                                     aria-labelledby="single-slide-tab-4">
                                    <!--Single Product Image Start-->
                                    <div class="single-product-img img-full">
                                        <img src="img/single-product/large/single-product5.jpg" alt="">
                                    </div>
                                    <!--Single Product Image End-->
                                </div>
                                <div class="tab-pane fade" id="single-slide6" role="tabpanel"
                                     aria-labelledby="single-slide-tab-4">
                                    <!--Single Product Image Start-->
                                    <div class="single-product-img img-full">
                                        <img src="img/single-product/large/single-product6.jpg" alt="">
                                    </div>
                                    <!--Single Product Image End-->
                                </div>
                            </div>
                            <!--Modal Content End-->
                            <!--Modal Tab Menu Start-->
                            <div class="single-product-menu">
                                <div class="nav single-slide-menu owl-carousel" role="tablist">
                                    <div class="single-tab-menu img-full">
                                        <a class="active" data-toggle="tab" id="single-slide-tab-1"
                                           href="#single-slide1"><img src="img/single-product/small/single-product1.jpg"
                                                                      alt=""></a>
                                    </div>
                                    <div class="single-tab-menu img-full">
                                        <a data-toggle="tab" id="single-slide-tab-2" href="#single-slide2"><img
                                                src="img/single-product/small/single-product2.jpg" alt=""></a>
                                    </div>
                                    <div class="single-tab-menu img-full">
                                        <a data-toggle="tab" id="single-slide-tab-3" href="#single-slide3"><img
                                                src="img/single-product/small/single-product3.jpg" alt=""></a>
                                    </div>
                                    <div class="single-tab-menu img-full">
                                        <a data-toggle="tab" id="single-slide-tab-4" href="#single-slide4"><img
                                                src="img/single-product/small/single-product4.jpg" alt=""></a>
                                    </div>
                                    <div class="single-tab-menu img-full">
                                        <a data-toggle="tab" id="single-slide-tab-5" href="#single-slide5"><img
                                                src="img/single-product/small/single-product5.jpg" alt=""></a>
                                    </div>
                                    <div class="single-tab-menu img-full">
                                        <a data-toggle="tab" id="single-slide-tab-6" href="#single-slide6"><img
                                                src="img/single-product/small/single-product6.jpg" alt=""></a>
                                    </div>
                                </div>
                            </div>
                            <!--Modal Tab Menu End-->
                        </div>
                        <!--Modal Img-->
                        <!--Modal Content-->
                        <div class="col-md-7">
                            <div class="modal-product-info">
                                <h1>Sit voluptatem</h1>
                                <div class="modal-product-price">
                                    <span class="old-price">$74.00</span>
                                    <span class="new-price">$69.00</span>
                                </div>
                                <a href="single-product.html" class="see-all">See all features</a>
                                <div class="add-to-cart quantity">
                                    <form class="add-quantity" action="#">
                                        <div class="modal-quantity">
                                            <input type="number" value="1">
                                        </div>
                                        <div class="add-to-link">
                                            <button class="form-button" data-text="add to cart">add to cart</button>
                                        </div>
                                    </form>
                                </div>
                                <div class="cart-description">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                        nostrud exercitation ullamco,Proin lectus ipsum, gravida et mattis vulputate,
                                        tristique ut lectus.</p>
                                </div>
                                <div class="social-share">
                                    <h3>Share this product</h3>
                                    <ul class="socil-icon2">
                                        <li><a href=""><i class="fa fa-facebook"></i></a></li>
                                        <li><a href=""><i class="fa fa-twitter"></i></a></li>
                                        <li><a href=""><i class="fa fa-pinterest"></i></a></li>
                                        <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                                        <li><a href=""><i class="fa fa-linkedin"></i></a></li>
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
    <!-- Modal Area End -->
</div>
--%>
<div class="wrapper">
    <!--Header Area Start-->
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

                            <%--<div class="tab-pane fade" id="product3">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay">
                                        <a href="img/single-product/large/single-product3.jpg">
                                            <img src="img/single-product/large/single-product3.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product3.jpg" class="popup-img venobox"
                                           data-gall="myGallery"><i class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="product4">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay">
                                        <a href="img/single-product/large/single-product4.jpg">
                                            <img src="img/single-product/large/single-product4.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product4.jpg" class="popup-img venobox"
                                           data-gall="myGallery"><i class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="product5">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay">
                                        <a href="img/single-product/large/single-product5.jpg">
                                            <img src="img/single-product/large/single-product5.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product5.jpg" class="popup-img venobox"
                                           data-gall="myGallery"><i class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="product6">
                                <div class="product-large-thumb img-full">
                                    <div class="easyzoom easyzoom--overlay">
                                        <a href="img/single-product/large/single-product6.jpg">
                                            <img src="img/single-product/large/single-product6.jpg" alt="">
                                        </a>
                                        <a href="img/single-product/large/single-product6.jpg" class="popup-img venobox"
                                           data-gall="myGallery"><i class="fa fa-search"></i></a>
                                    </div>
                                </div>
                            </div>--%>
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
                        <div class="product-nav">
                            <a href="#"><i class="fa fa-angle-left"></i></a>
                            <a href="#"><i class="fa fa-angle-right"></i></a>
                        </div>
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
                        <p class="stock in-stock">库存：${product.quantity}</p>
                        <div class="single-product-quantity">
                            <form class="add-quantity" action="#">
                                <div class="product-quantity">
                                    <input value="1" type="number">
                                </div>
                                <div class="add-to-link">
                                    <button class="product-btn" data-text="add to cart">添加至购物车</button>
                                </div>
                            </form>
                        </div>
                        <div class="wishlist-compare-btn">
                            <a href="#" class="wishlist-btn">Add to Wishlist</a>
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
                            <h3>分享商品</h3>
                            <ul>
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
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
                                <div class="review-form-wrapper">
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
                                </div>
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
                                            <li><a href="#open-modal${i}" data-toggle="modal" title="Quick view"><i
                                                    class="fa fa-search"></i></a></li>
                                            <li><a href="#" title="Whishlist"><i class="fa fa-heart-o"></i></a></li>
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
                                            <a href="#">添加至购物车</a>
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
                                        <div class="tab-pane <c:if test="${j==0}"> fade show active </c:if> " id="single-slide${j+1}" role="tabpanel"
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
                                                <a <c:if test="${j==0}"> class="active" </c:if> data-toggle="tab" id="single-slide-tab-1"
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
                                        <h3>分享商品</h3>
                                        <ul class="socil-icon2">
                                            <li><a href=""><i class="fa fa-facebook"></i></a></li>
                                            <li><a href=""><i class="fa fa-twitter"></i></a></li>
                                            <li><a href=""><i class="fa fa-pinterest"></i></a></li>
                                            <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href=""><i class="fa fa-linkedin"></i></a></li>
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

