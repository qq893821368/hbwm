package nchu.software.ruanko.hbwmutil.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class XMLUtil {
    private static DocumentBuilderFactory dFactory;
    private static DocumentBuilder builder;
    private static Document doc;

    static {
        try {
            dFactory = DocumentBuilderFactory.newInstance();
            builder = dFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /* Create by hjb 2019/9/25
     * Util Method
     * 获取Class
     * 从指定xml文件中获取指定class对象
     * params: String, String
     * return: Object
     * --------------------END
     */
    public static Object getBean(String xml, String clazz) {
        try {
            // 创建文档对象
            doc = builder.parse(new File(xml+".xml"));

            // 获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName(clazz);
            Node classNode = nl.item(0).getFirstChild();
            String cName = classNode.getNodeValue();

            // 通过类名生成实例对象并将其返回
            Class c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* Create by hjb 2019/9/25
     * Util Method
     * 获取String
     * 从指定xml文件中获取指定属性值
     * params: String, String
     * return: String
     * --------------------END
     */
    public static String getProperty(String xmlPath, String prop){
        try {
            // 创建文档对象
            doc = builder.parse(new File(xmlPath));

            //获取包含属性名的文本节点, 返回该节点中的属性值
            return doc.getElementsByTagName(prop).item(0).getFirstChild().getNodeValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
