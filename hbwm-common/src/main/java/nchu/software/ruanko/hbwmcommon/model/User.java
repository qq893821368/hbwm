package nchu.software.ruanko.hbwmcommon.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    int id;
    String account;
    String password;

    @Override
    public String toString(){
        return this.id + "{\naccount:["+this.account+"]\npassword:["+this.password+"]\n}";
    }

    public Map<String, String> toMap(){
        Map<String, String> map = new HashMap<>();
        map.put("account", this.account);
        map.put("password", this.password);
        return map;
    }
}
