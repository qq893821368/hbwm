package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmcommon.model.Comment;
import nchu.software.ruanko.hbwmda.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentImpl {
    @Autowired
    CommentRepository comment;
    public boolean addcomment( int user_id,int music_id,String details, String time){
        boolean content = false;
        content = comment.insertComment(user_id,music_id,details,time);
        return content;
    }
    public List<Comment> findcomment(int  info_id){
        List<Comment> content = null;
        content = comment.searchcomment(info_id);
        return content;
    }
    public String findid(String  info_id){
        int id=Integer.parseInt(info_id);
        comment.findid(id);
        return comment.findid(id);
    }


}
