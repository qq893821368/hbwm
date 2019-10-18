package nchu.software.ruanko.hbwmda.repository;


import nchu.software.ruanko.hbwmcommon.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface CommentRepository extends Repository {
        @Results(id="commentMap",value = {
                @Result(property = "comment_id", column = "comment_id"),
                @Result(property = "user_id",column = "user_id"),
                @Result(property = "music_id",column = "music_id"),
                @Result(property = "content",column = "content"),
                @Result(property = "time",column = "time"),
        })
    @Insert("insert into comment(user_id,music_id,content,time) values (#{user_id},#{music_id},#{content},#{time})")
    public boolean insertComment(@Param("user_id") int user_id, @Param("music_id") int music_id, @Param("content") String content, @Param("time") String time);
        @Select("Select music_id from music where info_id=#{info_id}")
        public String findid(@Param("info_id") int info_id);
    @Select("select * from comment where music_id=(select music_id from music where info_id=#{info_id})")
    public List<Comment> searchcomment(@Param("info_id") int info_id);

}
