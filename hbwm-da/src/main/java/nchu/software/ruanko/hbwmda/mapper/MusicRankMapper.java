package nchu.software.ruanko.hbwmda.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface MusicRankMapper {
    @Select("select music_id from music ORDER BY play_volum DESC;")
    public int[] getHotRankList();
    @Select("select music_id from music ORDER BY public_date DESC;")
    public int[] getNewRankList();
    @Select("select music_id from music ORDER BY download_volum DESC;")
    public int[] getDowRankList();
}
