package nchu.software.ruanko.hbwmda.mapper;

import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import nchu.software.ruanko.hbwmcommon.model.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SingerMapper {
    @Select("select * from singer_info where nation='中国'")
    public Singer[] getChineseSingerId();
    @Select("select * from singer_info where nation='欧美'")
    public Singer[] getEASingerId();
    @Select("select * from singer_info where nation='日韩'")
    public Singer[] getJKSingerId();
    @Select("select * from singer_info where nation='其他'")
    public Singer[] getOtherSingerId();

    @Select("select * from singer_info where singer_id=#{singer_id}")
    public Singer getSingerInfo(@Param("singer_id") int singer_id);

    @Select("select music.music_id from music,singer_info where singer_info.singer_id=#{singer_id} and music.singer_id=singer_info.singer_id")
    public int[] getSingerMusicList(@Param("singer_id") int singer_id);

}
