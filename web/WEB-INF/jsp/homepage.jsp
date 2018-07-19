<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mysql.cj.api.Session" %>
<%@ page import="whustore.model.User" %><%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/8
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>WHU STORE</title>
    <meta name="description" content="">
    <meta name="robots" content="noindex, follow"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="noindex, follow"/>
    <!--All Css Here-->

    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <!-- Linearicon CSS-->
    <link rel="stylesheet" href="../css/linearicons.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="../css/font-awesome.min.css">

    <!-- Animate CSS-->
    <link rel="stylesheet" href="../css/animate.css">
    <!-- Owl Carousel CSS-->
    <link rel="stylesheet" href="../css/owl.carousel.min.css">
    <!-- Slick CSS-->
    <link rel="stylesheet" href="../css/slick.css">
    <!-- Meanmenu CSS-->
    <link rel="stylesheet" href="../css/meanmenu.min.css">
    <!-- Easyzoom CSS-->
    <link rel="stylesheet" href="../css/easyzoom.css">
    <!-- Venobox CSS-->
    <link rel="stylesheet" href="../css/venobox.css">
    <!-- Jquery Ui CSS-->
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <!-- Nice Select CSS-->
    <link rel="stylesheet" href="../css/nice-select.css">
    <!-- Style CSS -->
    <link rel="stylesheet" href="../css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="../css/responsive.css">
    <!-- Modernizr Js -->
    <script>
        function remove(productID) {
            Number(productID);
            $.ajax({
                type: "GET",
                dataType: 'json',
                url: "/cart/remove",
                cache: false,
                data: {productID: productID},
                error: function () {
                    location.reload(true);
                }, success: function (data) {
                    location.reload(true);
                }
            });
        }
    </script>
</head>
<body>


<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<!--//
TODO
-->
<div class="wrapper">
    <!--网页头-->
    <header>
        <div class="header-container">
            <div class="header-area header-absolute header-sticky pt-30 pb-30">
                <div class="container-fluid pl-50 pr-50">
                    <%@include file="universal/headerContent.jsp" %>
                </div>
            </div>
        </div>
    </header>
    <!--网页头end-->

    <!--Slider Area Start-->
    <div class="slider-area">
        <div class="hero-slider owl-carousel">
            <!--Single Slider Start-->
            <div class="single-slider" style="background-image: url(../../img/slider/home1-slider1.jpg)">
                <div class="slider-progress"></div>
                <div class="container">
                    <div class="hero-slider-content">
                        <h1>武汉大学 <br> 校园文化展览</h1>
                        <div class="slider-border"></div>
                        <p>第一届武汉大学校园文化创意商品线上展览</p>
                        <div class="slider-btn">
                            <a href="shop/search?searching=校园文化">查看商品集 <i class="fa fa-chevron-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <!--Single Slider End-->
            <!--Single Slider Start-->
            <div class="single-slider" style="background-image: url(../../img/slider/home1-slider2.jpg)">
                <div class="slider-progress"></div>
                <div class="container">
                    <div class="hero-slider-content">
                        <h1>2018 毕业季</h1>
                        <div class="slider-border"></div>
                        <p>让我在看珈一眼，从南到北。</p>
                        <div class="slider-btn">
                            <a href="shop/search?searching=毕业">查看商品集 <i class="fa fa-chevron-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <!--Single Slider End-->
        </div>
    </div>
    <!--Slider Area End-->

    <!--网页尾部-->
    <footer>
        <div class="footer-container">
            <!--Footer Top Area Start-->
            <div class="footer-top-area black-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-40">
                                <div class="footer-title">
                                    <h3>账户管理</h3>
                                </div>
                                <ul class="link-widget">
                                    <li><a href="/user/home">我的账户</a></li>
                                    <li><a href="/order/myOrders">历史订单</a></li>
                                    <li><a href="/cart/myCart">购物车</a></li>
                                    <li><a href="/user/recommendForMe">为我推荐</a></li>
                                    <li><a href="/user/myStory">我的故事</a></li>
                                </ul>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-40">
                                <div class="footer-title">
                                    <h3>WHUSTORE</h3>
                                </div>
                                <ul class="link-widget">
                                    <li><a href="#">关于我们</a></li>
                                    <li><a href="#">联系我们</a></li>
                                    <li><a href="#">加入我们</a></li>
                                    <li><a href="#">团队成员</a></li>
                                    <li><a href="#">支持我们</a></li>
                                </ul>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-40">
                                <div class="footer-title">
                                    <h3>快速链接</h3>
                                </div>
                                <ul class="link-widget">
                                    <li><a href="#">支持中心</a></li>
                                    <li><a href="#">服务合同</a></li>
                                    <li><a href="#">配送服务</a></li>
                                    <li><a href="#">隐私条款</a></li>
                                    <li><a href="#">FAQS</a></li>
                                </ul>
                            </div>
                            <!--Single Footer Widget End-->
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <!--Single Footer Widget Start-->
                            <div class="single-footer-widget mb-40">
                                <div class="footer-title">
                                    <h3>分类列表</h3>
                                </div>
                                <ul class="link-widget">
                                    <li><a href="#">T恤</a></li>
                                    <li><a href="#">配件</a></li>
                                    <li><a href="#">摆件</a></li>
                                    <li><a href="#">CD</a></li>
                                    <li><a href="#">虚拟</a></li>
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
                                    <a href="index.html"><img src="../../img/logo/logo-footer.png" alt=""></a>
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
                                    <p>地址：湖北省 武汉市 武昌区 八一路 299 号</p>
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
                                    <p>Email: <br>whustore@gmail.com</p>
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
                                    <p>Phone: <br>(+86) 110 120 0000</p>
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
                            <!--Footer Menu Start-->
                            <div class="footer-menu text-center">
                                <nav>
                                    <ul>
                                        <li><a href="#">我的账户</a></li>
                                        <li><a href="#">账户管理</a></li>
                                        <li><a href="#">商品搜索</a></li>
                                        <li><a href="#">售后信息</a></li>
                                        <li><a href="#">联系我们</a></li>
                                        <li><a href="#">服务条款</a></li>
                                    </ul>
                                </nav>
                            </div>
                            <!--Footer Menu End-->
                            <!--Footer Copyright Start-->
                            <div class="footer-copyright">
                                <p>Copyright &copy; 2018.Company name All rights reserved.</p>
                            </div>
                            <!--Footer Copyright End-->
                        </div>
                    </div>
                </div>
            </div>
            <!--Footer Bottom Area End-->
        </div>
    </footer>
    <!--网页尾部End-->
</div>


<!--All Js Here-->

<!--Jquery 1.12.4-->
<script src="../js/vendor/jquery-1.12.4.min.js"></script>
<!--Popper-->
<script src="../js/popper.min.js"></script>
<!--Bootstrap-->
<script src="../js/bootstrap.min.js"></script>
<!--Imagesloaded-->
<script src="../js/imagesloaded.pkgd.min.js"></script>
<!--Isotope-->
<script src="../js/isotope.pkgd.min.js"></script>
<!--Waypoints-->
<script src="../js/waypoints.min.js"></script>
<!--Counterup-->
<script src="../js/jquery.counterup.min.js"></script>
<!--Carousel-->
<script src="../js/owl.carousel.min.js"></script>
<!--Slick-->
<script src="../js/slick.min.js"></script>
<!--Meanmenu-->
<script src="../js/jquery.meanmenu.min.js"></script>
<!--Easyzoom-->
<script src="../js/easyzoom.min.js"></script>
<!--Nice Select-->
<script src="../js/jquery.nice-select.min.js"></script>
<!--ScrollUp-->
<script src="../js/jquery.scrollUp.min.js"></script>
<!--Wow-->
<script src="../js/wow.min.js"></script>
<!--Venobox-->
<script src="../js/venobox.min.js"></script>
<!--Jquery Ui-->
<script src="../js/jquery-ui.js"></script>
<!--Countdown-->
<script src="../js/jquery.countdown.min.js"></script>
<!--Plugins-->
<script src="../js/plugins.js"></script>
<!--Main Js-->
<script src="../js/main.js"></script>
</body>
</html>


