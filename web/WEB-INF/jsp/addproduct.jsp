<%--
  Created by IntelliJ IDEA.
  User: lsq_math
  Date: 2018/7/11
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Home</title>
    <meta name="description" content="">
    <meta name="robots" content="noindex, follow"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="noindex, follow"/>
    <%@include file="universal/allcss.jsp"%>
    <style type="text/css">
        .p_tag {
            height: 20px;
            width: 20px;
        }

        #result {
            width: 200px;
            height: 112px;
            border: 1px solid #eee;
        }

        #result img {
            width: 200px;
        }

        .pic_class {
            width: 80px;
            border: none;
            margin: 10% 15%;
        }
    </style>
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
                                <a href="index.html">
                                    <img src="img/logo/logo.png" alt="">
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
                                        <li><a href="index.html">首页</a></li>
                                        <li><a href="shop.html">逛一逛</a></li>
                                        <li><a href="single-product.html">为我推荐</a></li>
                                    </ul>
                                </nav>
                            </div>
                            <!--Main Menu Area End-->
                            <!--Header Option Start-->
                            <div class="header-option">
                                <div class="mini-cart-search">
                                    <div class="mini-cart">
                                        <a href="#">
                                                <span class="cart-icon">
                                                   <span class="cart-quantity">2</span>
                                                </span>
                                            <span class="cart-title">  购物车 <br><strong>¥190.00</strong></span>
                                        </a>
                                        <!--Cart Dropdown Start-->
                                        <div class="cart-dropdown">
                                            <ul>
                                                <li class="single-cart-item">
                                                    <div class="cart-img">
                                                        <a href="single-product.html"><img src="img/cart/cart1.jpg"
                                                                                           alt=""></a>
                                                    </div>
                                                    <div class="cart-content">
                                                        <h5 class="product-name"><a href="single-product.html">Odio
                                                            tortor consequat</a></h5>
                                                        <span class="cart-price">1 × ¥90.00</span>
                                                    </div>
                                                    <div class="cart-remove">
                                                        <a title="Remove" href="#"><i class="fa fa-times"></i></a>
                                                    </div>
                                                </li>
                                                <li class="single-cart-item">
                                                    <div class="cart-img">
                                                        <a href="single-product.html"><img src="img/cart/cart2.jpg"
                                                                                           alt=""></a>
                                                    </div>
                                                    <div class="cart-content">
                                                        <h5 class="product-name"><a href="single-product.html">Auctor
                                                            sem</a></h5>
                                                        <span class="cart-price">1 × ¥100.00</span>
                                                    </div>
                                                    <div class="cart-remove">
                                                        <a title="Remove" href="#"><i class="fa fa-times"></i></a>
                                                    </div>
                                                </li>
                                            </ul>
                                            <p class="cart-subtotal"><strong>Subtotal:</strong> <span
                                                    class="float-right">$190.00</span></p>
                                            <p class="cart-btn">
                                                <a href="#">购物车</a>
                                                <a href="#">结算</a>
                                            </p>
                                        </div>
                                        <!--Cart Dropdown End-->
                                    </div>
                                    <div class="header-search">
                                        <div class="search-box">
                                            <a href="#"><i class="fa fa-search"></i></a>
                                            <div class="search-dropdown">
                                                <form action="#">
                                                    <input name="search" id="search" placeholder=""
                                                           value="Search product..."
                                                           onblur="if(this.value==''){this.value='Search product...'}"
                                                           onfocus="if(this.value=='Search product...'){this.value=''}"
                                                           type="text">
                                                    <button type="submit"><i class="fa fa-search"></i></button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="currency">
                                        <div class="currency-box">
                                            <a href="#"><i class="fa fa-th"></i></a>
                                            <div class="currency-dropdown">
                                                <ul class="menu-top-menu">
                                                    <li><a href="#">我的账户</a></li>
                                                    <li><a href="#">历史订单</a></li>
                                                    <li><a href="#">购物车</a></li>
                                                    <li><a href="#">结算</a></li>
                                                    <li><a href="#">登陆</a></li>
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

    <!--adding product area-->

    <!--上面还有一个style-->

    <div class="add_area" style=" width:100%; height:auto;margin: 10% auto;">
        <div style="text-align: center">
            <h2>请添加一个商品</h2>
        </div>
        <br/>

        <form action="testaddproduct" method="post" enctype="multipart/form-data">

            <div class="single-feature mb-35" style="width: 60%;margin: 0 auto;min-width: 500px">
                <div class="p_photo" style=" width: 30%;float: left;margin-left: 5%">
                    <!--<input type="file" value="上传图片" style="margin-left:13%;height: 200px;width: 200px;">-->
                    <div id="result" style="height: 200px;width: 200px"></div>
                    <input id="pic" class="pic_class" type="file" name="" accept="image/*" onchange="selectFile()"/>
                    <input type="file" required="required" name="pic" id="exampleInputFile">
                    <!-- <button onclick = "handIn()">提交</button> -->

                </div>
                <div class="customer_info" style="width:70%;float: left;">
                    <p>商品名称</p>
                    <input type="text" id="product_name" name="productName" value="" maxlength="25">
                    <p>商品数量</p>
                    <input type="number" id="product_quantity" name="quantity" value="" maxlength="25">
                    <p>商品价格</p>
                    <input type="number" id="product_price" name="price">
                    <p>小组编号</p>
                    <input type="number" id = "product_type" name = "teamID">
                    <p>商品分类标签</p>
                    <!--根据需求增减-->
                    <%--<input type="checkbox" name="type" class="p_tag" value="" hidden="hidden" checked="checked">--%>
                    <input type="checkbox" name="type" class="p_tag" value="书籍" >书籍
                    <input type="checkbox" name="type" class="p_tag" value="服饰" >服饰
                    <input type="checkbox" name="type" class="p_tag" value="其他" >其他
                    <input type="checkbox" name="type" class="p_tag" value="" >1
                    <input type="checkbox" name="type" class="p_tag" value="" >1
                    <input type="checkbox" name="type" class="p_tag" value="" >1
                    <p>商品简述</p>
                    <textarea id="product_description" name="proIntro"></textarea>
                    <!--<input type="hidden" value="">-->
                    <br/><br/>
                    <input type="submit" value="添加商品">
                </div>

            </div>
            <script type="text/javascript">//上传图片
            //var files = document.getElementById('pic').files;
            var form = new FormData();//通过HTML表单创建FormData对象
            var url = '127.0.0.1:8080/'

            function selectFile() {
                var files = document.getElementById('pic').files
                console.log(files[0]);
                if (files.length == 0) {
                    return;
                }
                var file = files[0];
                //把上传的图片显示出来
                var reader = new FileReader();
                // 将文件以Data URL形式进行读入页面
                console.log(reader);
                reader.readAsBinaryString(file);
                reader.onload = function (f) {
                    var result = document.getElementById("result");
                    var src = "data:" + file.type + ";base64," + window.btoa(this.result);
                    result.innerHTML = '<img src ="' + src + '"/>';
                }
                console.log('file', file);
                form.append('file', file);
                console.log(form.get('file'));
            }

            //var xhr = new XMLHttpRequest();
            // function handIn(){
            //  console.log(form.get('file'));
            //  xhr.open("post", url, true);
            //  xhr.addEventListener("readystatechange", function() {
            //      var result = xhr;
            //      if (result.status != 200) { //error
            //          console.log('上传失败', result.status, result.statusText, result.response);
            //      }
            //      else if (result.readyState == 4) { //finished
            //          console.log('上传成功', result);
            //      }
            //  });
            // }
            </script>
        </form>
    </div>


    <!--adding product area end-->


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
    </footer>
    <!--网页尾部End-->

</div>

<%@include file="universal/alljs.jsp"%>
</body>
</html>
