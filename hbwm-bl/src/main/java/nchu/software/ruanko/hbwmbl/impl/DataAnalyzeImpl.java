package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmda.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@ComponentScan("nchu.software.ruanko.hbwmda")
public class DataAnalyzeImpl {
    @Autowired
    DataMapper mapper;

    /*
     *
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
}
