package nchu.software.ruanko.hbwmutil.util;

import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /* Create by hjb 2019/9/30
     * Util Method
     * 随机字符串
     * 给定一个长度以及是否纯数字的标识,
     * 返回一个符合条件的随机字符串
     * params: boolean, int
     * return: String
     * --------------------END
     */
    public static String random(boolean isPureNumber, int length){
        String code = "";
        if (isPureNumber)
            for(int i=0; i<length; i++)
                code += (char)((int)(10*Math.random()+48));
        else
            for (int i=0; i<length; i++)
                if(Math.random()> 0.5)
                    code += (char)((int)(10*Math.random()+48));
                else
                    code += (char)((int)(26*Math.random()+97));
        return code;
    }

    /* Create by hjb 2019/9/29
     * Util Method
     * 空字符串判断
     * 根据给定字符串判断是否为空(null, "")
     * params: String
     * return: boolean
     * --------------------END
     */
    public static boolean isEmpty(String string){
        if(string == null)
            return true;
        else if(string.equals(""))
            return true;
        return false;
    }

    /* Create by hjb 2019/9/20
     * Util Method
     * 属性格式字符串分割
     * 属性格式字符串, 如:
     *      ae86{
     *          color : white
     *          price : 8000
     *      }
     * 去除头部id和尾部括号,
     * 按行取属性值
     * params: String
     * return: String[]
     * --------------------END
     */
    public static String[] propertyToStringArray(String object){
        String[] res = new String[object.split("\n").length-2];
        for(int i=0; i<res.length; i++)
            res[i] = object.split("\n")[i+1];
        Pattern pattern = Pattern.compile(":\\s*\\[");
        for(int i=0; i<res.length; i++){
            Matcher matcher = pattern.matcher(res[i]);
            if(matcher.find())
                res[i] = res[i].substring(matcher.end()).trim();
        }
        return res;
    }

    /* Create by hjb 2019/9/20
     * Util Method
     * 属性格式字符串分割
     * 属性格式字符串, 如:
     *      ae86{
     *          color : white
     *          price : 8000
     *      }
     * 去除头部id和尾部括号,
     * 按行取属性值
     * params: String
     * return: Map
     * --------------------END
     */
    public static Map<String, String> propertyToMap(String object){
        Map<String, String> res = new HashMap<>();
        String[] strs = object.split("\n");
        for(int i=1; i<strs.length-1; i++){
            Pattern keyPat = Pattern.compile("[\\s\\S]*:");
            Pattern valPat = Pattern.compile(":[\\s\\S]*");
            Matcher keyMat = keyPat.matcher(strs[i]);
            Matcher valMat = valPat.matcher(strs[i]);
            if(keyMat.find() && valMat.find()){
                String key = strs[i].substring(keyMat.start(), keyMat.end()-1).trim().replace("]", "")
                        .replace("\"", "").replace("[", "");
                String val = strs[i].substring(valMat.start()+1, valMat.end()).trim().replace("]", "")
                        .replace("\"", "").replace("[", "");
                res.put(key, val);
            }
            else if(keyMat.find()){
                String key = strs[i].substring(keyMat.start(), keyMat.end()-1).trim().replace("]", "")
                        .replace("\"", "").replace("[", "");
                res.put(key, null);
            }
            else {
                String val = strs[i].substring(valMat.start()+1, valMat.end()).trim().replace("]", "")
                        .replace("\"", "").replace("[", "");
                res.put("res["+i+"]", val);
            }
        }
        return res;
    }

    public static String md5(String plaintext){
        return DigestUtils.md5DigestAsHex(plaintext.getBytes());
    }
}
