package nchu.software.ruanko.hbwmbl.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.utils.HttpsUtils;
import nchu.software.ruanko.hbwmbase.config.AlipayConfig;
import nchu.software.ruanko.hbwmbase.domain.UserInfo;
import nchu.software.ruanko.hbwmbl.impl.*;
import nchu.software.ruanko.hbwmcommon.model.*;
import nchu.software.ruanko.hbwmutil.util.*;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ComponentScan("nchu")
@Controller
public class UserController {
    @Autowired
    UserImpl impl;
    @Autowired
    MusicLoadImpl musicImpl;
    @Autowired
    MusicInfoMapperImpl musicInfoMapperImpl;


    @RequestMapping("/verifyImpl")
    @ResponseBody
    public Boolean verify(HttpServletRequest request, HttpServletResponse response, String account, String password, Model model) throws IOException, ServletException {
        String user = impl.query(account);
        String msg = impl.check(account, password, user);
        String message;
        switch (msg){
            case "not found":
                message = "未找到该用户, 请检查所输入用户名";
                break;
            case "account or password not be exit":
                message = "用户名或密码不存在";
                break;
            case "account mismatch":
                message = "用户名不匹配";
                break;
            case "password mismatch":
                message = "密码错误, 请检查所输入密码";
                break;
            case "access":
                message = "access";
                break;
            default:
                message = "未知错误";
        }
        request.getSession().setAttribute("msg", message);
        request.getRequestDispatcher("/verify").forward(request, response);
        if(message.equals("access"))
            return true;
        return false;
    }

    @RequestMapping("/registerImpl")
    @ResponseBody
    public Boolean register(HttpServletRequest request, HttpServletResponse response, String account, String password, Model model) throws IOException, ServletException {
        String user = impl.query(account);
        String message;
        if(!user.equals("not found"))
            message = "用户已存在";
        else
            message = impl.register(account, password);
        request.getSession().setAttribute("msg", message);
        request.getRequestDispatcher("/register").forward(request, response);
        if(message.equals("success"))
            return true;
        return false;
    }

    @ResponseBody
    @RequestMapping("/sendCode")
    public String sendCode(HttpServletRequest request, HttpServletResponse response, Model model, String addressee, boolean isPhone) throws EmailException, ClientException {
        String captcha = impl.createCaptcha(true, 6);
        if(impl.sendCaptcha(addressee, isPhone, captcha)){
            request.getSession().setAttribute("captcha", captcha);
            model.addAttribute("captcha", captcha);
            return captcha;
        }
        else {
            request.getSession().setAttribute("msg", "cannot send captcha");
            return null;
        }
    }

    @RequestMapping("/check/qq")
    public String checkQQ(HttpServletRequest request, HttpServletResponse response, String accessToken, String expiresIn, String state, Model model) throws IOException, ServletException {
        String result = JacksonUtil.get("https://graph.qq.com/oauth2.0/me","access_token="+accessToken);
        Map props;
        Pattern pattern = Pattern.compile("\\{[\\s\\S]*\\}");
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            result = result.substring(matcher.start(), matcher.end());
            result = "qq_id" + result.replace(",", "\n").replace("{", "{\n").replace("}", "\n}");
        }
        props = StringUtil.propertyToMap(result);
        String client = (String) props.getOrDefault("client_id", null);
        if(StringUtil.isEmpty(client))
            request.getSession().setAttribute("msg", "腾讯端请求失败");
        else {
            String psd = StringUtil.md5(client);
            if(impl.query(client).equals("not found"))
                impl.register(client, psd);
            request.getSession().setAttribute("msg", "success");
            request.getSession().setAttribute("account", client);
            request.getSession().setAttribute("password", psd);
            request.getRequestDispatcher("/login/qq/transit").forward(request, response);
        }
        return "agent";
    }

    @RequestMapping("/logoutImpl")
    public String logout(HttpServletRequest request, HttpServletResponse response, String account) throws IOException, ServletException {
        request.getSession().invalidate();
        return "redirect:/logout";
    }

    @GetMapping(value = "/find",produces = "text/html;charset=UTF-8")
    public String find(Model model, HttpServletRequest request, HttpServletResponse response, String music_name) throws ServletException,IOException {
        List<MusicInfo> list = null;
        list = musicImpl.findmusic(music_name);
        System.out.println(list);
        model.addAttribute("list",list);
        return "findlist";
    }
    @GetMapping(value = "/play")
    public String play(){
        return "play";
    }


    @GetMapping("/mui")
    public String modify(Model model, HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        user.setAccount((String) request.getSession().getAttribute("user"));
        UserInfo userInfo = new UserInfo();
        user = impl.getUser(user.getAccount());
        userInfo = impl.getUserInfo(user.getAccount());

        //生成订单并添加到数据库
        Order order =  (Order) request.getSession().getAttribute("order");

        if(order != null){System.out.println("mui content is "+order.getContent());
            order.setUser_id(user.getUser_id());
            int addOrders = impl.addOrders(order);
            if("VIP升级".equals(order.getContent())){
                int addVIP = impl.addVIP(user.getUser_id());
                int modifyVIP = impl.modifyVIP(user.getUser_id());
                user = impl.getUser(user.getAccount());
            }
            if("VIP续费".equals(order.getContent())){
                int reVIP = impl.reVIP(user.getUser_id());
            }
            request.getSession().removeAttribute("order");
        }
        if(user.isVip()){
            VIP vip = impl.getVIP(user.getUser_id());
            model.addAttribute("vip",vip) ;
        }

        model.addAttribute("user",user);
        model.addAttribute("userInfo",userInfo);
        return "modifyUserInfo";
    }

    @RequestMapping("/modifyInfo")
    public String modifyUserInfo(Model model, String sex, String address, String birthday, String intro, String email, HttpServletRequest request) throws IOException {
        String message = "";
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setAddress(address);
        userInfo.setGender(sex);
        userInfo.setBirthday(birthday);
        userInfo.setIntro(intro);

        User user = new User();
        user.setAccount((String) request.getSession().getAttribute("user"));
        message = impl.modifyUserInfo(user.getAccount(),userInfo);

        user = impl.getUser(user.getAccount());
        userInfo = impl.getUserInfo(user.getAccount());

        if(user.isVip()){
            VIP vip = impl.getVIP(user.getUser_id());
            model.addAttribute("vip",vip) ;
        }
        model.addAttribute("message",message);
        model.addAttribute("user",user);
        model.addAttribute("userInfo",userInfo);
        return  "modifyUserInfo";
    }

    @GetMapping("/beVIP")
    public String beVIP(Model model,HttpServletRequest request,String content) throws AlipayApiException {
        Order order = new Order();
        int order_id1 = (int)(Math.random()*(9999999-1000000)+1000000);
        int order_id2 = (int)(Math.random()*(9999999-1000000)+1000000);
        String order_id = order_id1 +""+ order_id2 ;

        order.setOrder_id(order_id);
        order.setContent(content);
        order.setMoney(20);

        Date time = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sdf.format(time);
        order.setShipDate(date);

        HttpSession session = request.getSession();
        session.setAttribute("order",order);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        String out_trade_no = order.getOrder_id();
        //付款金额，必填
        //String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
        String total_amount = order.getMoney()+"";
        //订单名称，必填
        //String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        String subject = "心随乐动";
        //商品描述，可空
        //String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
        String body = order.getContent();
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\"5m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        model.addAttribute("result",result);

        return "alipay";
    }


    @GetMapping("/musicIdList")//在bl层中
    public String getMusicInfoList(Model model, int[] musicId) {
        // model.addAttribute("musicId",musicId);

        MusicInfo[] musicInfo = new MusicInfo[musicId.length];
        for(int i=0;i<musicId.length;i++){
            musicInfo[i]=musicInfoMapperImpl.getTheMusic(musicId[i]);
        }
        model.addAttribute("musicList",musicInfo);
        return "play";
    }
}
