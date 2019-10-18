package nchu.software.ruanko.hbwmbl.controller;


import nchu.software.ruanko.hbwmbl.impl.EchartsImpl;
import nchu.software.ruanko.hbwmcommon.model.DownloadVolum;
import nchu.software.ruanko.hbwmcommon.model.PlayVolum;
import nchu.software.ruanko.hbwmcommon.model.UserVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EchartsController {
    @Autowired
    EchartsImpl echartsImpl;


    @RequestMapping (value = "/echarts1")
    @ResponseBody
    public Map<String, Object> echarts1(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<UserVisit> list = echartsImpl.getCharts1();

        resultMap.put("chartOne",list);
        return resultMap;
    }

    @RequestMapping (value = "/echarts2")
    @ResponseBody
    public Map<String, Object> echarts2(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<PlayVolum> list = echartsImpl.getCharts2();

        resultMap.put("chartTwo",list);
        return resultMap;
    }

    @RequestMapping (value = "/echarts3")
    @ResponseBody
    public Map<String, Object> echarts3(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<UserVisit> list = echartsImpl.getCharts3();

        resultMap.put("chartThree",list);
        return resultMap;
    }

    @RequestMapping (value = "/echarts4")
    @ResponseBody
    public Map<String, Object> echarts4(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<DownloadVolum> list = echartsImpl.getCharts4();

        resultMap.put("chartFour",list);
        return resultMap;
    }

}
