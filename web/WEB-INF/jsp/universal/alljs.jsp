<%--
  Created by IntelliJ IDEA.
  User: huhaomeng
  Date: 2018/7/8
  Time: 下午4:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--All Js Here-->

<script>
    // 发送添加到购物车请求
    function add(productID, stock, addAmount) {
        Number(stock);
        Number(addAmount);
        if (stock < addAmount) {
            alert("库存不足")
            return;
        }
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: "/cart/add",
            cache: false,
            data: {productID: productID, addAmount},
            error: function () {
                location.reload(true);
            }, success: function (data) {
                    location.reload(true);
            }
        });
    }
    // 发送从购物车移除请求
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

    // 发送减商品请求
    function sub(productID) {
        productID =  Number(productID);
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
<!--Jquery 1.12.4-->
<script src="../../js/vendor/jquery-1.12.4.min.js"></script>
<!--Popper-->
<script src="../../js/popper.min.js"></script>
<!--Bootstrap-->
<script src="../../js/bootstrap.min.js"></script>
<!--Imagesloaded-->
<script src="../../js/imagesloaded.pkgd.min.js"></script>
<!--Isotope-->
<script src="../../js/isotope.pkgd.min.js"></script>
<!--Waypoints-->
<script src="../../js/waypoints.min.js"></script>
<!--Counterup-->
<script src="../../js/jquery.counterup.min.js"></script>
<!--Carousel-->
<script src="../../js/owl.carousel.min.js"></script>
<!--Slick-->
<script src="../../js/slick.min.js"></script>
<!--Meanmenu-->
<script src="../../js/jquery.meanmenu.min.js"></script>
<!--Easyzoom-->
<script src="../../js/easyzoom.min.js"></script>
<!--Nice Select-->
<script src="../../js/jquery.nice-select.min.js"></script>
<!--ScrollUp-->
<script src="../../js/jquery.scrollUp.min.js"></script>
<!--Wow-->
<script src="../../js/wow.min.js"></script>
<!--Venobox-->
<script src="../../js/venobox.min.js"></script>
<!--Jquery Ui-->
<script src="../../js/jquery-ui.js"></script>
<!--Countdown-->
<script src="../../js/jquery.countdown.min.js"></script>
<!--Plugins-->
<script src="../../js/plugins.js"></script>
<!--Main Js-->
<script src="../../js/main.js"></script>