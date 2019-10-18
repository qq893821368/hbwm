package nchu.software.ruanko.hbwmbl.controller;

import nchu.software.ruanko.hbwmbl.impl.CommentImpl;
import nchu.software.ruanko.hbwmcommon.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class CommentController {
    @Autowired
    CommentImpl impl;
    @RequestMapping(value = "/comm")
    public String test(){
        return "/comment";
    }
    @GetMapping(value = "/discuss")
    public String discuss(Model model, HttpServletRequest request, HttpServletResponse response,String info_id)throws ServletException,IOException{

        String music_id=impl.findid(info_id);
        model.addAttribute("music_id",music_id);
        return "comment";
    }
    @GetMapping(value = "/add")
    public String add(HttpServletRequest request, HttpServletResponse response, String content,String info_id)throws ServletException,IOException {
        boolean list = false;
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(time);
        String uid = request.getParameter("user_id");
        int user_id = Integer.parseInt(uid);
        String mid = request.getParameter("music_id");
        int music_id = Integer.parseInt(mid);
        list = impl.addcomment(user_id,music_id,content,date);
        request.setAttribute("list",list);
        return "success";
    }
    @GetMapping(value = "/review")
    public String find(Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        List<Comment> list = null;
        String bt = request.getParameter("info_id");
        int ht = Integer.parseInt(bt);
        list = impl.findcomment(ht);
        model.addAttribute("list",list);
//        request.setAttribute("list",list);
//        request.getRequestDispatcher("/commentlist").forward(request,response);
            return "commentlist";
    }

}
