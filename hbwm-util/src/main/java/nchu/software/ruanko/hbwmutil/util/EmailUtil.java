package nchu.software.ruanko.hbwmutil.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {

    private static HtmlEmail email;
    private static String xml = "hbwm-util/Email.xml";

    static {
        if(email==null){
            //email=SpringUtil.getBean(HtmlEmail.class);
            email = new HtmlEmail();//创建http email对象, 用以完成email功能
            email.setHostName("smtp.qq.com");                           //设置服务器
            email.setCharset("utf-8");                                  //设置发送邮件编码
            email.setAuthentication(XMLUtil.getProperty(xml, "email-admin"), XMLUtil.getProperty(xml, "email-code"));//设置授权
        }
    }

    /* Create by hjb 2019/9/20
     * Util Method
     * 发送邮件
     * 利用QQ邮箱授权, 向指定邮箱发送邮件
     * params: String, String, String, String
     * return:
     * --------------------END
     */
    public static void sendEmail(String addressee, String sender, String subject, String message) throws EmailException {

        email.addTo(addressee);                                     //添加收件人
        email.setFrom(XMLUtil.getProperty(xml, "email-admin"), sender);  //设置发件人
        email.setSubject(subject);//设置发送主题
        email.setMsg(message);//设置发送内容
        if(email.getMimeMessage() == null)
            email.buildMimeMessage();
        email.sendMimeMessage();//进行发送
    }

    /* Create by hjb 2019/9/20
     * Util Method
     * 简化方法
     * 添加默认参数以测试发送邮件功能sendEmail
     * params: String
     * return:
     * --------------------END
     */
    public static void sendEmail(String addressee) throws EmailException {
        sendEmail(addressee, "心随乐动", "测试邮件", "测试：[HBWM-Email]");
    }

    /* Create by hjb 2019/9/20
     * Util Method
     * 发送验证码
     * 向指定邮箱发送指定验证码
     * params: String, String
     * return:
     * --------------------END
     */
    public static void sendCaptcha(String addressee, String captcha) throws EmailException {
        String subject = XMLUtil.getProperty(xml, "captcha-subject");
        String message = XMLUtil.getProperty(xml, "captcha-head")+captcha+XMLUtil.getProperty(xml, "captcha-foot");
        sendEmail(addressee, XMLUtil.getProperty(xml, "captcha-sender"), subject, message);
    }
}
