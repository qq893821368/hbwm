package nchu.software.ruanko.hbwmbl.impl;

import ch.qos.logback.core.util.FileUtil;
import nchu.software.ruanko.hbwmcommon.model.Music;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@ComponentScan("nchu")
@Service
public class MusicLoadImpl {
    //音乐的文件上传下载、加载, 歌曲信息修改
    FileUtil fileUtil;

    public Music loadMusic(String musicId){
        return null;
    }

    public String uploadMusic(Music music){ return ""; }

    public Music downloadMusic(String musicId){return null;}

    public String replaceMusic(String musicId, Music music){return "";}

    public Boolean findMusic(String musicId){return true;}

}
