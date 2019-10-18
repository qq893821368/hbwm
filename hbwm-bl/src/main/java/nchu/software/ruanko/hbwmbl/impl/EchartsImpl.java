package nchu.software.ruanko.hbwmbl.impl;


import nchu.software.ruanko.hbwmcommon.model.DownloadVolum;
import nchu.software.ruanko.hbwmcommon.model.PlayVolum;
import nchu.software.ruanko.hbwmcommon.model.UserVisit;
import nchu.software.ruanko.hbwmda.mapper.EchartsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsImpl {
    @Autowired
    EchartsMapper echartsMapper;

    public List<PlayVolum> getCharts2(){
        return echartsMapper.getCharts2();
    }

    public List<DownloadVolum> getCharts4() {
        return echartsMapper.getCharts4();
    }

    public List<UserVisit> getCharts1() {
        return echartsMapper.getCharts1();
    }

    public List<UserVisit> getCharts3() {
        return echartsMapper.getCharts3();
    }

}
