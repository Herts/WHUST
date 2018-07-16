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
                    <div class="row">
                        <!--Header Logo Start-->
                        <div class="col-lg-3 col-md-3">
                            <div class="logo-area">
                                <a href="home">
                                    <img src="../../img/logo/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <!--Header Logo End-->
                        <!--Header Menu And Mini Cart Start-->
                        <div class="col-lg-9 col-md-9 text-lg-right">
                            <!--Main Menu Area Start-->
                            <div class="header-menu">
                                <nav>
                                    <ul class="main-menu">
                                        <li><a href="/home">首页</a></li>
                                        <li><a href="/shop">找一找</a></li>
                                        <li><a href="/HangingAround">逛一逛</a></li>
                                        <li><a href="/HangingAround">为我推荐</a></li>
                                    </ul>
                                </nav>
                            </div>
                            <!--Main Menu Area End-->
                            <!--Header Option Start-->
                            <div class="header-option">
                                <div class="mini-cart-search">
                                    <div class="mini-cart">
                                        <a href="/cart/myCart">
                                        <span class="cart-icon">
                                            <span class="cart-quantity">
                                                ${sessionScope.cart.items.keySet().size()}
                                            </span>
                                        </span>
                                            <span class="cart-title">  购物车 <br><strong>¥${sessionScope.cart.getTotal()}</strong></span>
                                        </a>
                                        <!--Cart Dropdown Start-->
                                        <div class="cart-dropdown">
                                            <ul>
                                                <c:if test="${sessionScope.cart!=null && sessionScope.cart.items.keySet().size()>0}">
                                                    <c:forEach items="${sessionScope.cart.items.keySet()}" var="product"
                                                               varStatus="status" end="3">
                                                        <li class="single-cart-item">
                                                            <div class="cart-img">
                                                                <a href="/product?productID=${product.id}"><img
                                                                        src="../../${product.picPath.get(0)}"
                                                                        alt=""></a>
                                                            </div>
                                                            <div class="cart-content">
                                                                <h5 class="product-name"><a
                                                                        href="/product?productID=${product.id}">
                                                                        ${product.productName}
                                                                </a></h5>
                                                                <span class="cart-price">¥${product.price}</span>
                                                            </div>
                                                            <div class="cart-remove">
                                                                <a title="Remove" onclick="remove(${product.id})"><i
                                                                        class="fa fa-times"></i></a>
                                                            </div>
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${sessionScope.cart.items.keySet().size() > 4}">
                                                        <h3 style="text-align: center">。。。</h3>
                                                    </c:if>
                                                </c:if>
                                            </ul>
                                            <p class="cart-subtotal"><strong>合计:</strong> <span
                                                    class="float-right">¥${sessionScope.cart.getTotal()}</span></p>
                                            <p class="cart-btn">
                                                <a href="/cart/myCart">购物车</a>
                                                <a href="/addorder">结算</a>
                                            </p>
                                        </div>
                                        <!--Cart Dropdown End-->
                                    </div>
                                    <div class="header-search">
                                        <div class="search-box">
                                            <a href="#"><i class="fa fa-search"></i></a>
                                            <div class="search-dropdown">
                                                <form action="/shop/search">
                                                    <input name="searching" id="search" placeholder="在此处输入搜索信息"
                                                           onblur="if(this.value==''){this.value='在此处输入搜索信息'}"
                                                           onfocus="if(this.value=='在此处输入搜索信息'){this.value=''}"
                                                           type="text">
                                                    <button type="submit"><i class="fa fa-search"></i></button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="currency">
                                        <div class="currency-box">
                                            <a href="#"><i class="fa fa-user"></i></a>
                                            <div class="currency-dropdown">
                                                <ul class="menu-top-menu">
                                                    <%
                                                        User user = (User) session.getAttribute("user");
                                                        Integer idteam = (Integer) session.getAttribute("idteam");
                                                        if (user != null) {
                                                            if (idteam != null)
                                                                out.println("<li><a href=\"/gototeamAccout\">团队账户</a></li>");
                                                            out.println("<li><a href=\"/user/home\">" +
                                                                    user.getUsername() +
                                                                    "的账户</a></li>" +
                                                                    "<li><a href=\"/order/myOrders\">历史订单</a></li>\n" +
                                                                    "<li><a href=\"/fav/my\">我的收藏</a></li>\n" +
                                                                    "<li><a href=\"/cart/myCart\">购物车</a></li>\n" +
                                                                    "<li><a href=\"/addorder\">结算</a></li>" +
                                                                    "<li><a href='/logOut'>注销</a></li>");
                                                        }
                                                        else
                                                            out.println("<li><a href=\"/log\">" +
                                                                    "请登录" +
                                                                    "</a></li>" +
                                                                    "<li><a href='/reg'>注册</a></li>");
                                                    %>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--Header Option End-->
                        </div>
                        <!--Header Menu And Mini Cart End-->
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <!--Mobile Menu Area Start-->
                            <div class="mobile-menu d-lg-none"></div>
                            <!--Mobile Menu Area End-->
                        </div>
                    </div>
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
                                    <li><a href="/HangingAround">为我推荐</a></li>
                                    <li><a href="#">售后服务</a></li>
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


