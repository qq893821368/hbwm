package nchu.software.ruanko.hbwmbl.impl;

import com.aliyuncs.exceptions.ClientException;
import nchu.software.ruanko.hbwmbase.domain.UserInfo;
import nchu.software.ruanko.hbwmcommon.model.User;
import nchu.software.ruanko.hbwmcommon.model.Order;
import nchu.software.ruanko.hbwmcommon.model.VIP;
import nchu.software.ruanko.hbwmda.mapper.UserMapper;
import nchu.software.ruanko.hbwmda.repository.UserRepository;
import nchu.software.ruanko.hbwmutil.util.EmailUtil;
import nchu.software.ruanko.hbwmutil.util.MessageUtil;
import nchu.software.ruanko.hbwmutil.util.StringUtil;
import org.apache.commons.mail.EmailException;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@ComponentScan("nchu")
@Service
public class UserImpl {//登录、注册、找回密码、修改个人信息
    @Autowired
    UserMapper mapper;

    /* Create by hjb 2019/10/05
     * Logic Method
     * 发送验证码
     * 根据指定的接收者, 是否为手机号, 验证码, 发送一个验证码
     * params: String, boolean, String
     * return: boolean
     * --------------------END
     */
    public boolean sendCaptcha(String addressee, boolean isPhone, String captcha) throws ClientException, EmailException {
        if (isPhone)
            MessageUtil.sendMessage(addressee, captcha);
        else
            EmailUtil.sendCaptcha(addressee, captcha);
        return true;
    }

    /* Create by hjb 2019/10/05
     * Administrate Method
     * 生成验证码
     * 根据是否纯数字, 长度生成一个验证码
     * params: boolean, int
     * return: String
     * --------------------END
     */
    public String createCaptcha(boolean isPureNumber, int length){
        return StringUtil.random(isPureNumber, length);
    }

    /* Create by hjb 2019/9/21
     * Logic Method
     * 查询用户, 根据所给用户名查询用户存在性
     * 存在则返回user.toString(), 否则返回提示
     * params: String
     * return: String
     * --------------------END
     */
    public String query(String account){
        List<User> users = mapper.findAllByAccount(account);
        if(users.size() == 0)
            return "not found";
        else {
            User user = users.get(0);
            user.setPassword(mapper.findPasswordById(user.getUser_id()).get(0));
            return user.toString();
        }
    }

    public User getUser(String account){
        List<User> users = mapper.findAllByAccount(account);
        if(users.size()>0)
            return users.get(0);
        else
            return null;
    }

    /* Create by hjb 2019/9/21
     * Logic Method
     * 验证用户, 根据所给账户信息以及用户验证用户信息一致性
     * 给定一组用户信息以及一个用户,
     * 对用户信息和用户进行比对, 验证是否一致,
     * 返回一个响应的提示作为验证结果
     * params: String, String, String
     * return: String
     * --------------------END
     */
    public String check(String account, String password, String user){
        if(user.equals("not found"))
            return "account not be found";
        Map<String, String> properties = StringUtil.propertyToMap(user);
        String acc = null, psd = null;
        if(((acc = properties.getOrDefault("account", null)) == null) || ((psd = properties.getOrDefault("password", null)) == null))
            return "account or password not be exit";
        else{
            if(!acc.equals(account))
                return "account mismatch";
            else{
                password = StringUtil.md5(password);
                if(!psd.equals(password)){
                    return "password mismatch";
                }

                return "access";
            }
        }
    }

    /* Create by hjb 2019/9/26
     * Logic Method
     * 录入用户
     * 根据给定用户信息, 将信息存入数据库
     * 返回一个提示作为录入结果
     * params: String
     * return: String
     * --------------------END
     */
    public String register(String account, String password){
        int rows = mapper.addUser(account);
        if(rows == 1){
            int id = mapper.findAllByAccount(account).get(0).getUser_id();
            rows = mapper.addPassword(id, password);
            if(rows == 1)
                return "success";
        }
        return "fail";
    }

    public String modifyUserInfo(String account, UserInfo userInfo){
        int i = mapper.modifyUserInfo(account,userInfo);
        if(i == 1)
            return "修改成功";
        else
            return "修改失败";
    }

    public UserInfo getUserInfo(String account) {
        return mapper.getUserInfo(account);
    }

   /* public int addOrders(Order order) {
        System.out.println("impl content"+order.getContent());
        return mapper.addOrders(order);
    }*/

    public int addOrders(Order order) {
        return mapper.addOrders(order.getOrder_id(),order.getUser_id(),order.getContent(),order.getMoney(),order.getShipDate());
    }
    public int addVIP(int user_id) {
        return mapper.addVIP(user_id);
    }

    public VIP getVIP(int user_id) {
        return mapper.getVIP(user_id);
    }

    public int reVIP(int user_id) {
        return mapper.reVIP(user_id);
    }

    public int modifyVIP(int user_id) {
        return mapper.modifyVIP(user_id);
    }



    public String ModifyUser(HttpServletRequest request, HttpServletResponse response, String account) throws ServletException, IOException {
        //List<User> users = mapper.findAllByAccount(account);
        String user = query(account);
        Map<String,String> map = StringUtil.propertyToMap(user);
        String acc = map.getOrDefault("user_id", null);
        /*
        {
            account:'abc'
            password:'123'
            users:{'user1', 'user2'}
            String:Object
            .
            ..
            ...
        }
        map.getOrDefault(属性名, null);
         */
        //request.getSession().setAttribute("list", mapper.findAllByAccount(account));
        request.getSession().setAttribute("user_id", acc);
        request.getSession().setAttribute("account", map.getOrDefault("account", null));
        request.getSession().setAttribute("isvip", map.getOrDefault("isVip", null));
        //request.getRequestDispatcher("/ModifyUser").forward(request, response);
        return "";
        //return "redirect:/login";
    }
    public String update(HttpServletRequest request, HttpServletResponse response,String newaccount,String oldaccount){

        mapper.update(newaccount,oldaccount);
        return "";

    }
    public String updatevip(HttpServletRequest request, HttpServletResponse response,String isvip,String account){

        mapper.updatevip(isvip,account);
        return "";

    }
    public String  updateaddress(HttpServletRequest request, HttpServletResponse response,String address,String account){

        mapper.updateaddress(address,account);
        return "";

    }
    public String  updategender(HttpServletRequest request, HttpServletResponse response,String address,String account){

        mapper.updategender(address,account);
        return "";

    }
    public String  updateintro(HttpServletRequest request, HttpServletResponse response,String intro,String account){

        mapper.updateintro(intro,account);
        return "";

    }
    public String  updateispublic(HttpServletRequest request, HttpServletResponse response,String ispublic,String account){

        mapper.updateispublic(ispublic,account);
        return "";

    }
    public String ModifyUserinfo(HttpServletRequest request, HttpServletResponse response, String user_id) throws ServletException, IOException {
        String user = chainfo(user_id);
        Map<String,String> map = StringUtil.propertyToMap(user);
        String acc = map.getOrDefault("user_id", null);
        request.getSession().setAttribute("user_id", acc);
        request.getSession().setAttribute("email", map.getOrDefault("email", null));
        request.getSession().setAttribute("address", map.getOrDefault("address", null));
        request.getSession().setAttribute("gender", map.getOrDefault("gender", null));
        request.getSession().setAttribute("intro", map.getOrDefault("intro", null));
        request.getSession().setAttribute("birthday", map.getOrDefault("birthday", null));
        request.getSession().setAttribute("isPublic", map.getOrDefault("isPublic", null));


        return "";
    }
    public String chainfo(String user_id){
        List<User> users = mapper.findinfo(user_id);
        if(users.size() == 0)
            return "not found";

        return users.get(0).toString();
    }
}
