package nchu.software.ruanko.hbwmweb.controller;

import nchu.software.ruanko.hbwmcommon.model.MusicInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
@Controller
public class testController {
    @GetMapping("/")
    public String index(Model model){

        List<MusicInfo> musicList = new ArrayList<>();
        musicList.add(new MusicInfo("说好不哭","周杰伦","/music/周杰伦 - 说好不哭（with 五月天阿信）.flac","/music/lyric/周杰伦 - 说好不哭（with 五月天阿信）.txt","/music/picture/说好不哭.jpg"));
        musicList.add(new MusicInfo("童言无忌","王以太","/music/童言无忌.mp3","/music/lyric/童言无忌.txt","/music/picture/童言无忌.jpg"));
       // musicList.add(JSONArray.fromObject(new MusicInfo("说好不哭","周杰伦","/music/周杰伦 - 说好不哭（with 五月天阿信）.flac","/music/lyric/周杰伦 - 说好不哭（with 五月天阿信）.txt","/music/picture/说好不哭.jpg")).toString());
      //  musicList.add(JSONArray.fromObject(new MusicInfo("童言无忌","王以太","/music/童言无忌.mp3","/music/lyric/童言无忌.txt","/music/picture/童言无忌.jpg")).toString());
       // String musicListjson = JSONArray.fromObject(musicList).toString();
       // model.addAttribute("musicList", musicListjson);
        model.addAttribute("musicList",musicList);
        return "play";
    }
}
