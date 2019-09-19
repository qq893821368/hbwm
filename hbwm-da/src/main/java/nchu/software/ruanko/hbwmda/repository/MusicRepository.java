package nchu.software.ruanko.hbwmda.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
public interface MusicRepository extends Repository {
    @Results(id="musicMap", value={
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
    })
    @Insert("insert into music (id, name) values (#{mId},#{mName})")
    void uploadMusic(@Param("mId") int mId, @Param("mName") int mName);
    //以上是样版, 并不实际投入使用
}
