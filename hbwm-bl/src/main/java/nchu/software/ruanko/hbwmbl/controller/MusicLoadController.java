package nchu.software.ruanko.hbwmbl.controller;

import nchu.software.ruanko.hbwmbl.impl.MusicDisplayImpl;
import nchu.software.ruanko.hbwmbl.impl.MusicLoadImpl;
import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmda.mapper.MusicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MusicLoadController {
    @Autowired
    MusicLoadImpl musicLoader;
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    MusicDisplayImpl musicDisplayer;

    @RequestMapping("/playMusic/**")
    public void playMusic(HttpServletRequest request, HttpServletResponse response, String musicId) throws ServletException, IOException {
        Music music = musicLoader.loadMusic(musicId);
        request.getSession().setAttribute("music", music);
        request.getRequestDispatcher("/playMusic").forward(request, response);
    }

    @RequestMapping("/playList/**")
    public void playMusic(HttpServletRequest request, HttpServletResponse response, String[] musicId) throws ServletException, IOException {
        List<Music> list = new ArrayList<>();
        for(String id:musicId)
            list.add(musicLoader.loadMusic(id));
        request.getSession().setAttribute("music-list", list);
        request.getRequestDispatcher("/playList").forward(request, response);
    }

    @RequestMapping("/admin/uploadMusic/**")
    public void uploadMusic(HttpServletRequest request, HttpServletResponse response, Music music) throws Exception {
        if(musicLoader.uploadMusic(music).equals("success"))
            request.getSession().setAttribute("msg", "upload success");
        else
            throw new Exception("Can Not Upload Music File");
        request.getRequestDispatcher("/uploadMusic").forward(request, response);
    }

    @RequestMapping("/admin/downloadMusic/**")
    public Music downloadMusic(HttpServletRequest request, HttpServletResponse response, String musidId) throws Exception {
        Music music;
        music = musicLoader.downloadMusic(musidId);
        if(music != null)
            return music;
        else
            throw new Exception("Not Found Music File");
    }

    @RequestMapping("/admin/replaceMusic/**")
    public void replaceMusic(HttpServletRequest request, HttpServletResponse response,String musicId, Music music) throws Exception {
        if(musicLoader.findMusic(musicId))
            if(musicLoader.uploadMusic(music).equals("success"))
                request.getSession().setAttribute("msg", "replace success");
        else
            throw new Exception("Can Not Upload Music File");
    }

    @RequestMapping("/music/rank-list/**")
    public void getRankList(HttpServletRequest request, HttpServletResponse response,String keyword) throws Exception {
        Map<Integer, Music> rankList = musicDisplayer.getRankList(keyword);
        request.getSession().setAttribute("rand-list of"+keyword, rankList);
        request.getRequestDispatcher("/music/rank-list").forward(request, response);
    }

}
