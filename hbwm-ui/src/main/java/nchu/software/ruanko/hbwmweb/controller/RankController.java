package nchu.software.ruanko.hbwmweb.controller;

import nchu.software.ruanko.hbwmbl.impl.MusicInfoMapperImpl;
import nchu.software.ruanko.hbwmbl.impl.MusicRankMapperImpl;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@ComponentScan(basePackages = {"nchu"})
@ComponentScan
@Controller
public class RankController {
    @Autowired
    MusicRankMapperImpl musicRankMapperImpl;
    @Autowired
    MusicInfoMapperImpl musicInfoMapperImpl;
    @GetMapping("/ranklist")//在bl层中
    public String getMusicInfoList(Model model) {
        // model.addAttribute("musicId",musicId);

        int hotMusicId[]=null;
        int newMusicId[]=null;
        int dowMusicId[]=null;
       //热门
            hotMusicId=musicRankMapperImpl.getHotRankList();

       //新歌
            newMusicId=musicRankMapperImpl.getNewRankList();

        //下载
            dowMusicId=musicRankMapperImpl.getDowRankList();


        MusicInfo hotmusicInfo[]=new MusicInfo[hotMusicId.length];
        MusicInfo newmusicInfo[]=new MusicInfo[newMusicId.length];
        MusicInfo dowmusicInfo[]=new MusicInfo[dowMusicId.length];
        for(int i=0;i<hotMusicId.length;i++){
            hotmusicInfo[i]=musicInfoMapperImpl.getTheMusic(hotMusicId[i]);
        }
        for(int i=0;i<newMusicId.length;i++){
            newmusicInfo[i]=musicInfoMapperImpl.getTheMusic(newMusicId[i]);
        }
        for(int i=0;i<dowMusicId.length;i++){
            dowmusicInfo[i]=musicInfoMapperImpl.getTheMusic(dowMusicId[i]);
        }
        model.addAttribute("hotMusicList",hotmusicInfo);
        model.addAttribute("newMusicList",newmusicInfo);
        model.addAttribute("dowMusicList",dowmusicInfo);

        return "ranklist";
    }
}
