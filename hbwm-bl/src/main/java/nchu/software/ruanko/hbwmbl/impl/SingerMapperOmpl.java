package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmda.repository.SingerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingerMapperOmpl {
    @Autowired
    SingerMapper singerMapper;
    public int[] getChinsesSinger(){
        return singerMapper.getChineseSingerId();
    }
    public int[] getEASinger(){
        return singerMapper.getEASingerId();
    }
    public int[] getJKSinger(){
        return singerMapper.getJKSingerId();
    }
    public int[] getOtherSinger(){
        return singerMapper.getOtherSingerId();
    }
}
