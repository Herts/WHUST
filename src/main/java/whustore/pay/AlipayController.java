package whustore.pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AlipayController {


    public static AlipayClient getAlipayClient() {
        return new DefaultAlipayClient(
                AlipayConfig.URL,
                AlipayConfig.APPID,
                AlipayConfig.APP_PRIVATE_KEY,
                AlipayConfig.FOMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE);
    }

}
