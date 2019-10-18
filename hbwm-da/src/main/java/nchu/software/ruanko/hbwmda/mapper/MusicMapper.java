package nchu.software.ruanko.hbwmda.mapper;

import nchu.software.ruanko.hbwmcommon.model.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MusicMapper {

    @Select("SELECT * FROM music WHERE music_id=#{music_id};")
    List<Music> findById(int music_id);
    @Select("SELECT * FROM music;")
    List<Music> findAll();
    @Select("SELECT max(music_id) AS music_id FROM music;")
    List<Integer> findLastOne();

    @Update("UPDATE music SET play_volum = play_volum+1 WHERE music_id=#{music_id};")
    int playOne(int music_id);
}
