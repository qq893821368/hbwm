package nchu.software.ruanko.hbwmda.repository;

import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MusicInfoMapper {

    @Insert("insert into music_info " +
            "(music_name,singer,album,druation,music_file,lrc_file) " +
            "values " +
            "(#{music_name},#{singer},#{album},#{druation},#{music_file},#{lrc_file})")
    public int insertMusicInfo(@Param("music_name") String music_name, @Param("singer") String singer,
                               @Param("album") String album, @Param("druation") int druation, @Param("music_file") String music_file, @Param("lrc_file") String lrc_file);
    //修改图片地址
    @Update("update music_info set pic_file=#{pic_file} where info_id=#{info_id}")
    public int updatepic(@Param("pic_file") String pic_file, @Param("info_id") int info_id);

    //获得最新数据
    @Select("select info_id from music_info ORDER BY info_id DESC LIMIT 1")
    public int getNew();

    //update music information include name singer and album
    @Update("update music_info set music_name=#{music_name},singer=#{singer},album=#{album} where info_id=#{info_id}")
    public int updateInformationg(@Param("info_id") int info_id, @Param("music_name") String music_name, @Param("singer") String singer, @Param("album") String album);

    //修改歌词文件
    @Update("update music_info set lrc_file=#{lrc_file} where info_id=#{info_id}")
    public int updatelrc(@Param("lrc_file") String lrc_file, @Param("info_id") int info_id);

    //修改音频资源
    @Update("update music_info set music_file=#{music_file} where info_id=#{info_id}")
    public int updatefile(@Param("music_file") String music_file, @Param("info_id") int info_id);

    @Select("select * from music_info")
    public List<MusicInfo> getAll();

    //get the information where id equals my input id
    @Select("select * from music_info where info_id=#{info_id}")
    public MusicInfo getTheId(@Param("info_id") int info_id);
    //删除一条数据
    @Delete("delete from music_info where info_id=#{info_id}")
    public int deletemusic(@Param("info_id") int info_id);


    //public MusicInfo getMusicinfo(int info_id);
    //public MusicInfo updateMusicfile(String music_file);
    //public int deleteMusicinfo(int info_id);

}
