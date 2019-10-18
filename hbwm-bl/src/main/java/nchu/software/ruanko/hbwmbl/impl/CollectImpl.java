package nchu.software.ruanko.hbwmbl.impl;



import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import nchu.software.ruanko.hbwmda.repository.CollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectImpl {
    @Autowired
    CollectRepository collect;
    public boolean addcollect(int music_id,int user_id,String time){
        boolean store = false;
        try {
            store = collect.insertCollect(music_id,user_id,time);
        }catch (Exception ex){
            return false;
        }
        return store;
    }
    public String findid(String  info_id){
        int id=Integer.parseInt(info_id);
        collect.findid(id);
        return collect.findid(id);
    }
    public List<MusicInfo> find(String user_id){
        List<MusicInfo> list=null;
        int id=Integer.parseInt(user_id);
        list=collect.searchall(id);
        return list;
    }

}
