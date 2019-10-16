package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import nchu.software.ruanko.hbwmda.repository.MusicInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicInfoMapperImpl {
    @Autowired
    MusicInfoMapper musicInfoMapper;
    public int addMusic(@Param("musicInfo") MusicInfo musicInfo)
    {
        int i=0;
        System.out.print("music is get ?"+musicInfo.getMusic_name());
        i=musicInfoMapper.insertMusicInfo(musicInfo.getMusic_name(),musicInfo.getSinger(),musicInfo.getAlbum(),
                musicInfo.getDruation(),musicInfo.getMusic_file(),musicInfo.getLrc_file());

        return i;
    }
    public int uploadPic(String pic_file,int id)
    {
        //int j=musicInfoMapper.getNew();
        //System.out.print("\n获取最新的id"+j);
        int k=musicInfoMapper.updatepic(pic_file,id);
        return k;
    }
    public int uploadLrc(String lrc_file,int id)
    {
        //int j=musicInfoMapper.getNew();

        int k=musicInfoMapper.updatelrc(lrc_file,id);
        return k;
    }
    //get all music information
    public List<MusicInfo> getAll()
    {
        List<MusicInfo> musicInfoList=this.musicInfoMapper.getAll();
        return musicInfoList;
    }

    //delete the date where id=id
    public  int deletemusic(int info_id)
    {
        int j=this.musicInfoMapper.deletemusic(info_id);
        System.out.print("\n是否删除成功"+j);
        return j;
    }

    //update the music_file
    public int updateMusicFile(String musicfile,int info_id)
    {
        int  j=this.musicInfoMapper.updatefile(musicfile,info_id);
        return j;
    }

    public int GetNew()
    {
        int j=musicInfoMapper.getNew();
        return j;
    }
    public MusicInfo getTheMusic(int id)
    {
        MusicInfo musicInfo=this.musicInfoMapper.getTheId(id);
        return musicInfo;
    }
    public int UpdateMusicInformation(int id,String name,String singer,String album)
    {
        int j=this.musicInfoMapper.updateInformationg(id,name,singer,album);
        return j;
    }


}
