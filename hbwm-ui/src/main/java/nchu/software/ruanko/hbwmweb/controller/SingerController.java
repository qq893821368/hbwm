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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ComponentScan(basePackages = {"nchu"})
@ComponentScan
@Controller
public class SingerController {
    @Autowired
    SingerMapperImpl singerMapperImpl;
    @Autowired
    MusicInfoMapperImpl musicInfoMapperImpl;
    @GetMapping("/singer")//在bl层中
    public String getSingerList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Singer[] chineseSinger = singerMapperImpl.getChinsesSinger();
        Singer[] eaSinger = singerMapperImpl.getEASinger();
        Singer[] jkSinger = singerMapperImpl.getJKSinger();
        Singer[] otherSinger = singerMapperImpl.getOtherSinger();
        request.getSession().setAttribute("chineseSinger",chineseSinger);
        request.getSession().setAttribute("eaSinger",eaSinger);
        request.getSession().setAttribute("jkSinger",jkSinger);
        request.getSession().setAttribute("othersSinger",otherSinger);
        return "/singer";
    }
    @GetMapping("/singerInfo")
    public String getSingerInfo(HttpServletRequest request, HttpServletResponse response, Model model, int singer_id){

        Singer singer = singerMapperImpl.getSingerInfo(singer_id);

        int[] singerMusicId = singerMapperImpl.getSingerMusicList(singer_id);
        MusicInfo[] musicList = new MusicInfo[singerMusicId.length];
        for(int i=0;i<singerMusicId.length;i++){
            musicList[i] = musicInfoMapperImpl.getTheMusic(singerMusicId[i]);
        }
        request.getSession().setAttribute("singer",singer);
        request.getSession().setAttribute("singerMusicList",musicList);
        return "/singerInfo";
    }
}
