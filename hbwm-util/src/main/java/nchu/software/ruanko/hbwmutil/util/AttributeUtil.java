package nchu.software.ruanko.hbwmutil.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AttributeUtil {

    static public String getAttributeFromSession(HttpSession session, String attribute, boolean delete){
        String attr = null;
        try{
            attr = (String) session.getAttribute(attribute);
        }catch (NullPointerException NPE){
            attr = null;
        }finally {
            if(delete)
                session.removeAttribute(attribute);
            return attr;
        }
    }
    static public String getAttributeFromRequest(HttpServletRequest request, String attribute, boolean delete){
        return getAttributeFromSession(request.getSession(), attribute, delete);
    }
    static public String getMsgFromSession(HttpSession session){
        return getAttributeFromSession(session, "msg", true);
    }
    static public String getMsgFromRequest(HttpServletRequest request){
        return getMsgFromSession(request.getSession());
    }
}
