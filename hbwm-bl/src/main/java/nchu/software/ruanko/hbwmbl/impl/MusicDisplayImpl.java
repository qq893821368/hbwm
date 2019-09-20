package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmda.mapper.MusicMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicDisplayImpl {
    //各类榜单和歌曲推荐
    @Autowired
    MusicMapper musicMapper;

    public Map<Integer, Music> getRankList(String keyword) throws Exception {
        Map<Integer, Music> rankList = new HashMap<>();
        List<Music>  list = musicMapper.findListByKeyword(keyword);
        if(list.size()<10)
            throw new Exception("A Lack Of Music Be Found");
        for(Music music:list)
            rankList.put(list.indexOf(music), music);
        return rankList;
    }
}
