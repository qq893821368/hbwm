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
    UserRepository userRepository;

    public String query(String account){
        List<User> users = userRepository.findAll(account);
        if(users.size() == 0)
            return "not found";
        return users.get(0).toString();
    }

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
}
