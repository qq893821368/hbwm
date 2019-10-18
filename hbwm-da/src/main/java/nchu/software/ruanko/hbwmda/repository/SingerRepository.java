package nchu.software.ruanko.hbwmda.repository;

import nchu.software.ruanko.hbwmcommon.model.Singer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SingerRepository extends Repository {
    @Results(id="singerMap",value = {
            @Result(property = "singer_id",column = "singer_id"),
            @Result(property = "singer_name",column = "singer_name"),
            @Result(property = "intro",column = "intro"),
            @Result(property = "exp",column = "exp"),
    })
    @Select("select * from singer_info")
    public List<Singer> searchsinger();

    @Select("select * from singer_info where singer_id=#{singer_id}")
    public Singer searchsingerbyid(@Param("singer_id") int singer_id);

    @Insert("insert into singer_info(singer_pic,singer_name,nation,intro,exp) values(#{singer_pic},#{singer_name},#{nation},#{intro},#{exp})")
    public boolean insertSinger(@Param("singer_pic") String singer_pic, @Param("singer_name") String singer_name, @Param("nation") String nation, @Param("intro") String intro, @Param("exp") String exp);

    @Update("update singer_info set singer_pic=#{singer_pic},singer_name=#{singer_name},nation=#{nation},intro=#{intro},exp=#{exp} where singer_id=#{singer_id}")
    public boolean update(@Param("singer_pic") String singer_pic, @Param("singer_id") int singer_id, @Param("singer_name") String singer_name, @Param("nation") String nation, @Param("intro") String intro, @Param("exp") String exp);

    @Delete("delete from singer_info where singer_id=#{singer_id}")
    public boolean delete(@Param("singer_id") int singer_id);

}
