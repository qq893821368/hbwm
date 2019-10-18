package nchu.software.ruanko.hbwmbl.controller;

import nchu.software.ruanko.hbwmbl.impl.DataAnalyzeImpl;
import nchu.software.ruanko.hbwmbl.impl.MusicInfoMapperImpl;
import nchu.software.ruanko.hbwmbl.impl.MusicLoadImpl;
import nchu.software.ruanko.hbwmbl.impl.MusicRankMapperImpl;
import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import nchu.software.ruanko.hbwmutil.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ComponentScan("nchu")
@Controller
public class MusicLoadController {

    @Autowired
    MusicLoadImpl impl;
    @Autowired
    MusicRankMapperImpl rankImpl;
    @Autowired
    MusicInfoMapperImpl infoImpl;

    @RequestMapping("/loadMusic")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        /*********在session里放推荐歌曲数据**************/
        Music[] musics = impl.loadRandom(10);
        musics = impl.getRecommend(musics[(int)(Math.random()*10)], musics, 7);
        MusicInfo[] recommends = impl.musicToInfo(musics);
        request.getSession().setAttribute("recommendList", recommends);
        /*********在session里放推荐歌曲数据**************/

        int newMusicId[];//新歌
        newMusicId=rankImpl.getNewRankList();//获取新歌id列表
        MusicInfo newmusicInfo[]=new MusicInfo[newMusicId.length];//创建歌曲信息列表
        for(int i=0;i<10&&i<newMusicId.length;i++){
            newmusicInfo[i]=infoImpl.getTheMusic(newMusicId[i]);//获取歌曲信息
        }
        request.getSession().setAttribute("newMusicList",newmusicInfo);//传歌曲信息列表给前台
        return "redirect:/home";
    }
}
