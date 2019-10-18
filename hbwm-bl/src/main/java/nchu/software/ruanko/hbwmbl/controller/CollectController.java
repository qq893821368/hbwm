package nchu.software.ruanko.hbwmbl.controller;


import nchu.software.ruanko.hbwmbl.impl.CollectImpl;
import nchu.software.ruanko.hbwmbl.impl.UserImpl;
import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import nchu.software.ruanko.hbwmutil.util.AttributeUtil;
import nchu.software.ruanko.hbwmutil.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class CollectController {
    @Autowired
    CollectImpl impl;
    @Autowired
    UserImpl userImpl;

    @GetMapping(value = "/save")
    public String save(Model model, HttpServletRequest request, HttpServletResponse response, String info_id)throws ServletException,IOException {
        String music_id=impl.findid(info_id);
        int id=Integer.parseInt(music_id);

        String user_id="";
        String user = AttributeUtil.getAttributeFromRequest(request, "user", false);
        if(!StringUtil.isEmpty(user))
            if (!(user = userImpl.query(user)).equals("not found"))
                user_id = StringUtil.propertyToMap(user).get("user_id");
            else
                return "error";

        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(time);
        boolean list=false;
        list=impl.addcollect(id,Integer.valueOf(user_id),date);
        request.setAttribute("list",list);
        if(list)
            return "success";
        request.setAttribute("error", "收藏失败, 可能已收藏或其他未知错误。");
        return "error";
    }
    @GetMapping(value = "/collect")
    public String  collect(Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        List<MusicInfo> list=null;

        String user_id="";
        String user = AttributeUtil.getAttributeFromRequest(request, "user", false);
        if(!StringUtil.isEmpty(user))
            if (!(user = userImpl.query(user)).equals("not found"))
                user_id = StringUtil.propertyToMap(user).get("user_id");
            else
                return "error";

        list=impl.find(user_id);
        model.addAttribute("list",list);
        return "collectlist";
    }


}
