package nchu.software.ruanko.hbwmutil.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class MessageUtil {
    private static String xml = "hbwm-util/Message.xml";
    private static DefaultProfile profile;
    private static CommonRequest request;
    private static IAcsClient client;

    private static String regionId;
    private static String domain;


    static {
        if(StringUtil.isEmpty(xml))
            xml = "hbwm-util/Message.xml";
        regionId = XMLUtil.getProperty(xml, "region-id");
        domain = XMLUtil.getProperty(xml, "domain");

        if(profile == null){
            String key = XMLUtil.getProperty(xml, "access-key-id");
            String secret = XMLUtil.getProperty(xml, "secret");
            profile = DefaultProfile.getProfile(regionId, key, secret);
        }

        if (client == null)
             client = new DefaultAcsClient(profile);

        if(request == null)
            request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("SignName", XMLUtil.getProperty(xml, "signName"));
        request.putQueryParameter("TemplateCode", XMLUtil.getProperty(xml, "template-code"));

    }

    public static void sendMessage(String phone, String code) throws ClientException {
        //to be continued
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());
    }
}
