package nchu.software.ruanko.hbwmutil.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUtil {
    @Autowired
    private static HtmlEmail email;
    static {
        if(email==null){
            //email=SpringUtil.getBean(HtmlEmail.class);
            email = new HtmlEmail();//创建http email对象, 用以完成email功能
        }
    }
    public static void sendEmail(String addressee, String sender, String subject, String message) throws EmailException {
        email.setHostName("smtp.qq.com");                           //设置服务器
        email.setCharset("utf-8");                                  //设置发送邮件编码
        email.addTo(addressee);                                     //添加收件人
        email.setFrom("893821368@qq.com", sender);  //设置发件人
        email.setAuthentication("893821368@qq.com", "zolzulparalhbbhh");//设置授权
        email.setSubject(subject);//设置发送主题
        email.setMsg(message);//设置发送内容
        email.send();//进行发送
    }
    public static void sendEmail(String addressee) throws EmailException {
        sendEmail(addressee, "心随乐动", "测试邮件", "测试：[HBWM-Email]");
    }

    public static void sendCaptcha(String addressee, String captcha) throws EmailException {
        String subject = "【心随乐动】邮箱验证码";
        String message = "【心随乐动】您的验证码为"+captcha+", 您正在进行身份验证, 请不要将验证码告诉其他人！";
        sendEmail(addressee, "心随乐动", subject, message);
    }
}
