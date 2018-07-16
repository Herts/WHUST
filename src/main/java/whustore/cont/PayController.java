package whustore.cont;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import whustore.model.Order;
import whustore.model.User;
import whustore.pay.AlipayConfig;
import whustore.pay.AlipayController;
import whustore.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PayController {
    OrderService service = new OrderService();

    @RequestMapping("/pay/payOrder")
    public void payOrder(HttpServletRequest request,
                           HttpServletResponse response,
                           ModelMap modelMap) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        User user = (User) request.getSession().getAttribute("user");
        Order order = service.getOrder(orderId,user.getUserid());
        AlipayClient alipayClient = AlipayController.getAlipayClient();
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:8080/order/myOrders");
        alipayRequest.setNotifyUrl("http://localhost:8080/order/myOrders");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + order.getIdOrder() + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + order.getTotal() + "," +
                "    \"subject\":\"" + order.getOrderItemNames() + "\"," +
                "    \"body\":\"" + order.getOrderDetails() + "\"" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
            service.orderPaid(orderId);
        } catch (AlipayApiException | IOException e) {
            e.printStackTrace();
        }
    }
}
