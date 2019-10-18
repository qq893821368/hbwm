package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import nchu.software.ruanko.hbwmda.mapper.MusicInfoMapper;
import nchu.software.ruanko.hbwmda.mapper.MusicMapper;
import nchu.software.ruanko.hbwmda.repository.SearchMusicRepository;
import nchu.software.ruanko.hbwmutil.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ComponentScan("nchu.software.ruanko.hbwmda")
public class MusicLoadImpl {
    //音乐的文件上传下载、加载, 歌曲信息修改
    @Autowired
    MusicMapper mapper;
    @Autowired
    MusicInfoMapper info;

    @Autowired
    SearchMusicRepository music;
    public List<MusicInfo> findmusic(String music_name){
        List<MusicInfo> song = null;
        song = music.SearchMusicbyname(music_name);
        return song;
    }

    public Music loadOne(int id){
        List<Music> list = mapper.findById(id);
        if(list.size() > 0)
            return list.get(0);
        else
            return null;
    }
    public String loadOneToString(int id){
        Music music = loadOne(id);
        if(music == null)
            return "not found";
        else return music.toString();
    }

    public Music[] loadRandom(int quantity){
        Music[] musics = new Music[quantity];
        Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
        List<Integer> list = mapper.findLastOne();
        int lastId = list.get(0);

        for(int i=0; i<musics.length; ){
            int id = (int)(Math.random()*lastId);
            if(!ids.containsValue(id) && mapper.findById(id).size()!=0)
                ids.put(i++, id);
        }

        for (int i = 0; i < musics.length; i++)
            musics[i] = loadOne(ids.get(i));

        return musics;
    }

    public Music[] getRecommend(Music music, Music[] list, int k) throws Exception {
        int[] ids = DataAnalyzeImpl.recommend(music, DataAnalyzeImpl.musicSimilarityMatrix(list, new String[]{"play_volume", "download_volume"}), k);
        Music[] musics = new Music[ids.length];
        for(int i=0; i<musics.length; i++)
            musics[i] = loadOne(ids[i]);
        return musics;
    }

    public MusicInfo[] musicToInfo(Music[] music){
        MusicInfo[] infos = new MusicInfo[music.length];
        for(int i=0; i<music.length; i++) {
            infos[i] = info.getTheId(music[i].getInfo_id());
        }
        return infos;
    }



}
