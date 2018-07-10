package whustore.page.functions;

public class PageFunctions {

    String beforeUrl = "<!--Single Product Start-->\n" +
            "                        <div class=\"single-product mb-25\">\n" +
            "                            <div class=\"product-img img-full\">\n" +
            "                                <a href=\"";
    String afterUrlBeforeImgSrc = "\">\n" +
            "                                    <img src=\"";
    String afterImgSrcBeforeSale = "\" alt=\"\">\n" +
            "                                </a>";
    String sale = "<span class=\"促销!\">Sale!</span>";
    //等待添加打开悬浮商品界面和添加到心仪列表功能
    String afterSaleBeforeUrl2 = "<div class=\"product-action\">\n" +
            "                                    <ul>\n" +
            "                                        <li><a href=\"#open-modal\" data-toggle=\"modal\"\n" +
            "                                               title=\"Quick view\"><i class=\"fa fa-search\"></i></a>\n" +
            "                                        </li>\n" +
            "                                        <li><a href=\"#\" title=\"Whishlist\"><i\n" +
            "                                                class=\"fa fa-heart-o\"></i></a></li>\n" +
            "                                    </ul>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                            <div class=\"product-content\">\n" +
            "                                <h2><a href=\"";
    String afterUrl2BeforeName = "\">";
    String afterNameBeforePrice = "</a></h2>\n" +
            "                                <div class=\"product-price\">\n" +
            "                                    <div class=\"price-box\">\n" +
            "                                        <span class=\"regular-price\">￥";
    //等待添加加入购物车功能
    String afterPrice = "</span>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"add-to-cart\">\n" +
            "                                        <a href=\"#\">加入购物车</a>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!--Single Product End-->";

    public static String getSingleProduct() {
        //groupFoot = "null";
        return "";
    }

}
