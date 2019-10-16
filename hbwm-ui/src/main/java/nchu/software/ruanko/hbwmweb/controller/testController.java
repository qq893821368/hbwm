package nchu.software.ruanko.hbwmweb.controller;

import nchu.software.ruanko.hbwmbl.impl.MusicRankMapperImpl;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;

import nchu.software.ruanko.hbwmbl.impl.MusicInfoMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@ComponentScan(basePackages = {"nchu"})
@ComponentScan
@Controller
public class testController {
    @Autowired
    MusicInfoMapperImpl musicInfoMapperImpl;
    @Autowired
    MusicRankMapperImpl musicRankMapperImpl;

   /* @GetMapping("/musicIdList")
    public String getMusicIdList(Model model,int[] musicId) {
        model.addAttribute("musicId",musicId);
        return "redirect:/getMusicInfoList";
    }*/
    @GetMapping("/musicIdList")//在bl层中
    public String getMusicInfoList(Model model, int[] musicId) {
        // model.addAttribute("musicId",musicId);

        MusicInfo[] musicInfo = new MusicInfo[musicId.length];
        for(int i=0;i<musicId.length;i++){
            musicInfo[i]=musicInfoMapperImpl.getTheMusic(musicId[i]);
        }
        System.out.println(musicInfo[0].getMusic_name());
        model.addAttribute("musicList",musicInfo);
        return "play";
    }
    @GetMapping(value = {"/home","/"})
    public String home(Model model){
        int newMusicId[];//新歌
        newMusicId=musicRankMapperImpl.getNewRankList();//获取新歌id列表
        MusicInfo newmusicInfo[]=new MusicInfo[newMusicId.length];//创建歌曲信息列表
        for(int i=0;i<10&&i<newMusicId.length;i++){
            newmusicInfo[i]=musicInfoMapperImpl.getTheMusic(newMusicId[i]);//获取歌曲信息
        }
        model.addAttribute("newMusicList",newmusicInfo);//传歌曲信息列表给前台
        return "home";
    }

}
