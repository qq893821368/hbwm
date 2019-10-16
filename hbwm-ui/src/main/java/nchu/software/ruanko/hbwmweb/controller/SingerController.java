package nchu.software.ruanko.hbwmweb.controller;

import nchu.software.ruanko.hbwmbl.impl.MusicInfoMapperImpl;
import nchu.software.ruanko.hbwmbl.impl.SingerMapperImpl;
import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import nchu.software.ruanko.hbwmcommon.model.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@ComponentScan(basePackages = {"nchu"})
@ComponentScan
@Controller
public class SingerController {
    @Autowired
    SingerMapperImpl singerMapperImpl;
    @Autowired
    MusicInfoMapperImpl musicInfoMapperImpl;
    @GetMapping("/singer")//在bl层中
    public String getSingerList(Model model) {
        Singer[] chineseSinger = singerMapperImpl.getChinsesSinger();
        Singer[] eaSinger = singerMapperImpl.getEASinger();
        Singer[] jkSinger = singerMapperImpl.getJKSinger();
        Singer[] otherSinger = singerMapperImpl.getOtherSinger();
        model.addAttribute("chineseSinger",chineseSinger);
        model.addAttribute("eaSinger",eaSinger);
        model.addAttribute("jkSinger",jkSinger);
        model.addAttribute("othersSinger",otherSinger);
        return "/singer";
    }
    @GetMapping("/singerInfo")
    public String getSingerInfo(Model model,int singer_id){

        Singer singer = singerMapperImpl.getSingerInfo(singer_id);

        int[] singerMusicId = singerMapperImpl.getSingerMusicList(singer_id);
        MusicInfo[] musicList = new MusicInfo[singerMusicId.length];
        for(int i=0;i<singerMusicId.length;i++){
            musicList[i] = musicInfoMapperImpl.getTheMusic(singerMusicId[i]);
        }
        model.addAttribute("singer",singer);
        model.addAttribute("singerMusicList",musicList);
        return "/singerInfo";
    }
}
