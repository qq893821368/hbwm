package nchu.software.ruanko.hbwmda.repository;

import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SearchMusicRepository extends Repository {
    @Results(id="musicMap", value={
            @Result(property = "music_name", column = "music_name"),
            @Result(property = "info_id",column = "info_id"),
            @Result(property = "singer",column = "singer"),
            @Result(property = "album",column = "album"),
            @Result(property = "duration",column = "duration"),
            @Result(property = "music_file",column = "music_file"),
            @Result(property = "lrc_file",column = "lrc_file")
    })
    @Select("select music_info.info_id,music_name,singer,album,duration,music_file,lrc_file,isVip from music_info,music where music_info.info_id=music.info_id && music_info.info_id=(select info_id from music_info where music_name like concat('%', #{music_name},'%')|| singer like concat('%', #{music_name},'%'))")
    public List<MusicInfo> SearchMusicbyname(@Param("music_name") String name);

}
