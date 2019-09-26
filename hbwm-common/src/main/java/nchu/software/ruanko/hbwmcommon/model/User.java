package nchu.software.ruanko.hbwmcommon.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    int id;
    String account;
    String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
