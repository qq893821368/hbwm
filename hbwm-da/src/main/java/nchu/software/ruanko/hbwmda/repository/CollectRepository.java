package nchu.software.ruanko.hbwmda.repository;

import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CollectRepository extends Repository {
    @Results(id = "collectMap",value = {
            @Result(property = "music_id", column = "music_id"),
            @Result(property = "user_id",column = "user_id"),
            @Result(property = "time",column = "time"),
    })
    @Insert("insert into collection(music_id,user_id,time) values (#{music_id},#{user_id},#{time})")
    public boolean insertCollect(@Param("music_id") int music_id, @Param("user_id") int user_id, @Param("time") String time);
    @Select("select * from music_info where info_id=any(\n" +
            "\tselect info_id from music where music_id=any(\n" +
            "\t\tselect music_id from collection where user_id=#{user_id}\n" +
            "\t\t)\n" +
            "\t)")
    //上面一行sql语句可能只仅限于一条数据
    public List<MusicInfo> searchall(@Param("user_id") int user_id);
    @Select("Select music_id from music where info_id=#{info_id}")
    public String findid(@Param("info_id") int info_id);
}
