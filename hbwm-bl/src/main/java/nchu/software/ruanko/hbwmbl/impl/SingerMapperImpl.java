package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import nchu.software.ruanko.hbwmcommon.model.Singer;
import nchu.software.ruanko.hbwmda.mapper.SingerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingerMapperImpl {
    @Autowired
    SingerMapper singerMapper;
    public Singer[] getChinsesSinger(){
        return singerMapper.getChineseSingerId();
    }
    public Singer[] getEASinger(){
        return singerMapper.getEASingerId();
    }
    public Singer[] getJKSinger(){
        return singerMapper.getJKSingerId();
    }
    public Singer[] getOtherSinger(){
        return singerMapper.getOtherSingerId();
    }
    public Singer getSingerInfo(int singer_id){
        return singerMapper.getSingerInfo(singer_id);
    }
    public int[] getSingerMusicList(int singer_id){
        return singerMapper.getSingerMusicList(singer_id);
    }
}
