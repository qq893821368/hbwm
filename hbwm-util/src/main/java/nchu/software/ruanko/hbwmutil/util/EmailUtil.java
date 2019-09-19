package nchu.software.ruanko.hbwmutil.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
    public static void sendEmail(String addressee) throws EmailException {
        HtmlEmail email=new HtmlEmail();                            //创建http email对象, 用以完成email功能

        email.setHostName("smtp.qq.com");                           //设置服务器
        email.setCharset("utf-8");                                  //设置发送邮件编码
        email.addTo(addressee);                                     //添加收件人
        email.setFrom("893821368@qq.com", "窝师妮迭");  //设置发件人
        email.setAuthentication("893821368@qq.com", "zolzulparalhbbhh");//设置授权

        email.setSubject("登录兼邮件测试");//设置发送主题
        email.setMsg("Test Signature: HJB-19/9/18-test");//设置发送内容
        email.send();//进行发送
    }
}
