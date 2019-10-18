package nchu.software.ruanko.hbwmweb.controller;

import nchu.software.ruanko.hbwmbase.domain.FileAddress;
import nchu.software.ruanko.hbwmbl.impl.MusicInfoMapperImpl;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@SessionAttributes({"Id","message"})
@ComponentScan("nchu.software.ruanko.hbwmbl.impl")
public class MusicUploadController {

@Autowired
MusicInfoMapperImpl musicInfoMapperImpl;



    @RequestMapping("/MusicUploadController")
    public String musicUpload(MultipartFile filename,ModelMap model) throws Exception
    {

//        if(filename.isEmpty())
//        {
//            model.addAttribute("message","文件为空，请选择文件");
//            return "uploadMusic";
//        }
        String address=new FileAddress().music_file(filename.getOriginalFilename());
        filename.transferTo(new File(address));
        MusicInfo musicInfo=new MusicInfo();
        try {

            MP3File mp3File = (MP3File) AudioFileIO.read(new File(address));
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();

            String songname = mp3File.getID3v2Tag().frameMap.get("TIT2").toString();//歌名

            String artist = mp3File.getID3v2Tag().frameMap.get("TPE1").toString();//歌手
            String album1 = mp3File.getID3v2Tag().frameMap.get("TALB").toString();//专辑
            int duration = audioHeader.getTrackLength();//时长
           // mp3File.getID3v2Tag().frameMap.get("APIC").toString();

            String music_name=songname.substring(6,songname.length()-3);
            String songer=artist.substring(6,artist.length()-3);
            String album=album1.substring(6,album1.length()-3);

            musicInfo.setMusic_name(music_name);
            musicInfo.setSinger(songer);
            musicInfo.setAlbum(album);
            musicInfo.setDuration(duration);
            //String fileaddress="C:\\Users\\ASUS\\Desktop\\hb\\hbwm\\hbwm-ui\\src\\main\\resources\\static\\music\\"+filename.getOriginalFilename();
            musicInfo.setMusic_file("\\music\\"+filename.getOriginalFilename());


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("dddd\n"+musicInfo.getMusic_name()+"\n"+musicInfo.getSinger()+"\n"+musicInfo.getAlbum()+"\n"+
        musicInfo.getDuration()+"\n"+musicInfo.getMusic_file());

        int j=this.musicInfoMapperImpl.addMusic(musicInfo);
        model.remove("message");
        if(j==1)
        {
            model.addAttribute("message","上传成功");
        }else
            model.addAttribute("message","上传失败");

        return "admin/uploadMusicPic";
    }

    @RequestMapping("/MusicUploadPic")
    public String uploadPic(MultipartFile filename,ModelMap model) throws Exception
    {
        String address=new FileAddress().picture(filename.getOriginalFilename());
        filename.transferTo(new File(address));
      //  String fileaddress="C:\\Users\\ASUS\\Desktop\\hb\\hbwm\\hbwm-ui\\src\\main\\resources\\static\\picture\\"+filename.getOriginalFilename();
        int id=this.musicInfoMapperImpl.GetNew();
        int j=this.musicInfoMapperImpl.uploadPic("\\picture\\"+filename.getOriginalFilename(),id);
        model.remove("message");
        if(j==1)
        {
            model.addAttribute("message","上传成功");
        }else
            model.addAttribute("message","上传失败");
        return "admin/uploadMusicLrc";

    }
    @RequestMapping("/MusicUploadLrc")
    public String uploadLrc(MultipartFile filename,ModelMap model) throws Exception
    {
        String address=new FileAddress().lrc(filename.getOriginalFilename());
        filename.transferTo(new File(address));
        int id=this.musicInfoMapperImpl.GetNew();
        int j=this.musicInfoMapperImpl.uploadLrc("\\lrc\\"+filename.getOriginalFilename(),id);
        model.remove("message");
        if(j==1)
        {
            model.addAttribute("message","上传成功");
        }else
            model.addAttribute("message","上传失败");
        List<MusicInfo> musicInfoList=this.musicInfoMapperImpl.getAll();
        model.addAttribute("musicInfoList",musicInfoList);
        return "admin/transition";

    }
    @RequestMapping("/musicList")
    public String AllMusic(ModelMap model) throws Exception
    {
        model.remove("message");
        List<MusicInfo> musicInfoList=this.musicInfoMapperImpl.getAll();
        model.addAttribute("musicInfoList",musicInfoList);
        return "admin/musicList";

    }
    @GetMapping("/uploadMusicLrc")
    public String GoUploadMusicLrc()
    {

        return "admin/uploadMusicLrc";
    }
    @RequestMapping("deletemusic")
    public String deletemusic(int id, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException, IOException {
        System.out.print("\nid有没有传过来"+id);
        int j=this.musicInfoMapperImpl.deletemusic(id);
        model.remove("message");
        if(j==1)
        {
            model.addAttribute("message","删除成功");
        }else
        model.addAttribute("message","删除失败");
//        request.getRequestDispatcher("/musicList").forward(request, response);
//        return "redirect:/musicList";
        List<MusicInfo> musicInfoList=this.musicInfoMapperImpl.getAll();
        model.addAttribute("musicInfoList",musicInfoList);
        return "admin/transition";
    }
    @RequestMapping("updatemusic")
    public String updatemusic(int id, ModelMap model)
    {

        model.remove("id");
        model.addAttribute("Id",id);

        return "admin/updatemusicfile";

    }

    @RequestMapping("/MusicUpdateMusicfile")
    public String updatemusicfile(MultipartFile filename,@ModelAttribute("Id") Integer id,ModelMap model) throws Exception
    {

        String address=new FileAddress().music_file(filename.getOriginalFilename());
        System.out.print("\n是否获得； id的值"+id);
        filename.transferTo(new File(address));
        int j=this.musicInfoMapperImpl.updateMusicFile("\\music\\"+filename.getOriginalFilename(),id);
        model.remove("message");
        if(j==1)
        {
            model.addAttribute("message","修改成功");
        }else
            model.addAttribute("message","修改失败");
        List<MusicInfo> musicInfoList=this.musicInfoMapperImpl.getAll();
        model.addAttribute("musicInfoList",musicInfoList);
        return "admin/musicList";

    }

    @RequestMapping("updatepic")
    public String updatepic(int id, ModelMap model)
    {

        model.remove("id");
        model.addAttribute("Id",id);

        return "admin/updatepicfile";

    }
    @RequestMapping("/MusicUpdatepicfile")
    public String updatepicfile(MultipartFile filename,@ModelAttribute("Id") Integer id,ModelMap model) throws Exception
    {

        String address=new FileAddress().picture(filename.getOriginalFilename());
        System.out.print("\n是否获得； id的值"+id);
        filename.transferTo(new File(address));
        // String fileaddress="C:\\Users\\ASUS\\Desktop\\hb\\hbwm\\hbwm-ui\\src\\main\\resources\\static\\music\\"+filename.getOriginalFilename();
        int j=this.musicInfoMapperImpl.uploadPic("\\picture\\"+filename.getOriginalFilename(),id);
        model.remove("message");
        if(j==1)
        {
            model.addAttribute("message","修改成功");
        }else
            model.addAttribute("message","修改失败");
        List<MusicInfo> musicInfoList=this.musicInfoMapperImpl.getAll();
        model.addAttribute("musicInfoList",musicInfoList);
        return "admin/transition";

    }

    @RequestMapping("updatelrc")
    public String updatelrc(int id, ModelMap model)
    {

        model.remove("id");
        model.addAttribute("Id",id);

        return "admin/updatelrcfile";

    }
    @RequestMapping("/MusicUpdatelrcfile")
    public String updatelrfile(MultipartFile filename,@ModelAttribute("Id") Integer id,ModelMap model) throws Exception
    {

        String address=new FileAddress().lrc(filename.getOriginalFilename());
        System.out.print("\n是否获得； id的值"+id);
        filename.transferTo(new File(address));
        // String fileaddress="C:\\Users\\ASUS\\Desktop\\hb\\hbwm\\hbwm-ui\\src\\main\\resources\\static\\music\\"+filename.getOriginalFilename();
        int j=this.musicInfoMapperImpl.uploadLrc("\\lrc\\"+filename.getOriginalFilename(),id);
        model.remove("message");
        if(j==1)
        {
            model.addAttribute("message","修改成功");
        }else
            model.addAttribute("message","修改失败");
        List<MusicInfo> musicInfoList=this.musicInfoMapperImpl.getAll();
        model.addAttribute("musicInfoList",musicInfoList);
        return "admin/transition";

    }

    @RequestMapping("updatemusicinfo")
    public String GoUpdateInf(int id,ModelMap model)
    {
        MusicInfo musicInfo=this.musicInfoMapperImpl.getTheMusic(id);
        model.addAttribute("musicinfo",musicInfo);
        return "admin/updateMusicInformation";
    }

    @RequestMapping("updatemusicinformation")
    public String UpdateMusicInformation(int id,String name,String singer,String album,ModelMap model)
    {
        int j=this.musicInfoMapperImpl.UpdateMusicInformation(id, name, singer, album);
        model.remove("message");
        if(j==1)
        {
            model.addAttribute("message","修改成功");
        }else
            model.addAttribute("message","修改失败");
        List<MusicInfo> musicInfoList=this.musicInfoMapperImpl.getAll();
        model.addAttribute("musicInfoList",musicInfoList);
        return "admin/transition";
    }



}
