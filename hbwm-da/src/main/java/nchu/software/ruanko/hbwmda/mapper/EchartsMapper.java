package nchu.software.ruanko.hbwmda.mapper;

import nchu.software.ruanko.hbwmcommon.model.DownloadVolum;
import nchu.software.ruanko.hbwmcommon.model.PlayVolum;
import nchu.software.ruanko.hbwmcommon.model.UserVisit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EchartsMapper {

    @Select("select music_name,play_volum from music_info mi,music m where mi.info_id = m.info_id order by m.play_volum desc limit 10;")
    List<PlayVolum> getCharts2();

    @Select("select music_name,download_volum from music_info mi,music m where mi.info_id = m.info_id order by m.download_volum desc limit 10;")
    List<DownloadVolum> getCharts4();

    @Select("SELECT DISTINCT time date,count(*) as times\n" +
            "FROM user_behavior\n" +
            "WHERE DATEDIFF(CURDATE(),time)<10 and behavior = 'login'\n" +
            "GROUP BY time ORDER BY time;")
    List<UserVisit> getCharts1();

    @Select("SELECT DISTINCT time date,count(*) as times\n" +
            "FROM user_behavior\n" +
            "WHERE DATEDIFF(CURDATE(),time)<10 and behavior = 'register'\n" +
            "GROUP BY time ORDER BY time;")
    List<UserVisit> getCharts3();

}
