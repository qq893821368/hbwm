package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmda.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@ComponentScan("nchu.software.ruanko.hbwmda")
public class DataAnalyzeImpl {
    @Autowired
    DataMapper mapper;

    /* Create by hjb 2019/9/21
     * Logic Method
     * 添加登录记录并返回登录记录信息
     * 根据给定的用户以及成功与否信息,
     * 返回一个登录信息字符串
     * params: String, Boolean
     * return: String
     * --------------------END
     */
    public String visit(String account, Boolean flag){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
        String logging = "User[" + account + "] visit the system at " + dateFormat.format(date) + " and " + (flag?"access":"fail");
        /*
         * 访问数据入库
         */
        return logging;
    }

    /* Create by hjb 2019/10/02
     * Util Method
     * 计算两首音乐的相似度
     * 根据给定的两首音乐,
     * 以及给定的参数列表,
     * 对音乐的各参数进行计算距离
     * 返回这个距离(欧式距离)
     * params: Music, Music String[]
     * return: double
     * --------------------END
     */
    public double musicSimilarity(Music musicA, Music musicB, String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        double dis = 0;
        for(String arg: args){
            String argName = arg.toLowerCase();
            String getter = "get" + argName.substring(0, 1).toUpperCase() + argName.substring(1);
            Method method = Music.class.getMethod(getter, new Class[] {});
            double differ = (double)method.invoke(musicA, new Object()) - (double)method.invoke(musicB, new Object());
            dis += differ * differ;
        }
        dis = Math.sqrt(dis);
        return 0;
    }
}
