package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmcommon.model.User;
import nchu.software.ruanko.hbwmda.repository.UserRepository;
import nchu.software.ruanko.hbwmutil.util.StringUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@ComponentScan("nchu")
@Service
public class UserImpl {//登录、注册、找回密码、修改个人信息
    @Autowired
    UserRepository mapper;

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
        return users.get(0).toString();
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
                if(!psd.equals(StringUtil.md5(password)))
                    return "password mismatch";
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
    public String register(User user){
        //需要修改
        Map<String, String> properties = StringUtil.propertyToMap(user.toString());
        int rows = mapper.addUser(properties.getOrDefault("account", null), properties.getOrDefault("password", null));
        return rows + "be affected";
    }
}
