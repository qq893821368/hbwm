package nchu.software.ruanko.hbwmbase.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101300674577";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCoLiXY9BYeM0uSzFDL1gDHnbTqxn2RM8Tf/KdMAubQ58CBD4PfXDwCS6BONoUhIRknZj6xw130rLxnAkle8vB6GlpoahY/oV9gC1otxF1fbVEcTPLj8WGrY6D3kBO9Za/i4iYv69d9USX4KIVIMc1itZUN/2U0ex5DAxo7N7v6gjAvdYu4+J0y5GY0OuAGn3QE1xAeCPDc9GBqxmQcVgZW39ullY6LRQrUOOGAd/h4grT/jRvPV2w95CnQiu+mg6Di5SpY1yV21hYF7rmPv+z0DpLlYHtTwBJ0goZUvvhTmWJTsOWCOakzWGdCJunsBQtm8aoROfAwdl575h7X42S7AgMBAAECggEAFUyi5nMjSffdHl5Z7TbRKnbCrukshfM0f9mTpoDHkrWHH9FDNkqMHw5M4xrsiWGTufWtAv5nK9Gkmrcja68A3bXwtUCaSLy7Q4KsjRdNbASAPUFRkaTEZdxcSn4owdfbCrTxeSg5Rlm8oU0gdCA6jsHIetlZi+f70SjXbaCFYhhdzTArEnpisZU2pGsaUUBr3flnowAo4NkdeUMLjCDkQNhVrR/X9xBJXG32FIxQDRb/XRiUyJiqx8oYA4GtQpYm+2kT9s6hvkf4N4TC6c6wm9Ii3NhtBs2yHK3NiNWo+29yGNlEMceSreZuaKa1cBXujUiCwNVjDZpgDJuvGoof4QKBgQDt5UuWCw4tMNMWc4+M9H6uVM+MWkbzzObHlhJ8VUzCLoGWiGQRyaZTcmcyNNsQRZoLBDwnU289GgYK5urPxfB+RUj3yO+LiNWh/TxvKTVOeNPxF8gF34qMxEUOWLL0u2n5ay8U2Ffn7GCISF8EnUPBQvAPW+C1LU+akMxrqCAPdwKBgQC0+qaPEwl9066bFV99IVeD+qGO+qpN9vJiGmSNH5rVzueBxM+eWP3S/uT6Gw2m5Bo/A5V+IexGUft9Xgm1ZdYUIfzxDemO69oBzk+ETo69P07n6SE45r00b3i+OeHDpzk7f2maCd+ku4WeHAtRDMUIaCMpKc8OQkZMRwWZVTsN3QKBgB6K1791v+YxZIh3YbC5GyKKpPLiDEX0wpRRQiJQWUB89Ao9jIt0FjSUdYdOneli5joP2yE5ZwizNJ9lsjPmt8+eWZXmaErD0Jmxh3bGFZ/zh1JmuTl3z7Pu5ubGoIMnJJ3vEuZcOD0AbgxRLlHLVw2S6HswUpMwNA4mFPilZyhVAoGAP58p9HwtxwUmibBezD9vY581Mm1ed2AL338FwDLPHMDK0+zbTABe4tDmsCHYj8X6kBJzeE72n9XsQitP4Gw3cQZtxFQuuiahkibz/0Aan97kn1RucHF1LDzPxpfUjRJ5ZTgqaJN2ApRFqcqUE98YWRc63Rqshoe37NK+3iN/SiUCgYAsFS0syjnj31Xy+vcyVh1F5Zuvz0JWhYcX6ggQhNtI85lnowycbhwgPG5Hi3ZUHE5bra/4YaQeAcvxd71HFb3eZahagwabEeGcp0sTRxmiJPivT4+++G4GMdoBH8hb7rF/vjOLYszCvQvMxmxvsu08yfABAu/SaRNlwci70KGT+A==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqGdIOGeXPpUtSr9Av5KZVhaybmKLMV/peVJhlGVZbvL0fof8XhNnY9UW1xZKJoTUG+/UagxR8eZTEPxMtMmz2Viwrqi3A7mYfcp6KBCOb9BH8zG5e/FP3MEJ+rcjBwIvRZWdVquWKOIDLsZoqEMiMw4X9hv1pzMdTAp32dp6KN6ZWHCG6uHbLCO3VJE0T+7S+w6TPqDHhIQrLLPYbpsxZ4umHVBNrsl1C7gCECGJ7qjyXZvApdqq/ayeSkzr9h61nJ+YpeByUiTf7KBpiC1oRK/59RIeTSUMVY4i6fVIp2p+rVdlutbXe6AIVJGkEgObmliFcWFGIRDlTGWNIF66WQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost/mui";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "e:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

