package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmda.mapper.MusicRankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicRankMapperImpl{
    @Autowired
    MusicRankMapper musicRankMapper;
    public int[] getHotRankList(){
        return musicRankMapper.getHotRankList();
    }
    public int[] getNewRankList(){
        return musicRankMapper.getNewRankList();
    }
    public int[] getDowRankList(){
        return musicRankMapper.getDowRankList();
    }

}
