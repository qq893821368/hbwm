package nchu.software.ruanko.hbwmcommon.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class User {
    private int user_id;
    private String account;
    private String avator;
    private boolean isVip;
    private String QQ_id;
    private String WeChat_id;
    private String password;

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public boolean isVip() {
        return isVip;
    }

    public String getQQ_id() {
        return QQ_id;
    }

    public void setQQ_id(String QQ_id) {
        this.QQ_id = QQ_id;
    }

    public String getWeChat_id() {
        return WeChat_id;
    }

    public void setWeChat_id(String weChat_id) {
        WeChat_id = weChat_id;
    }

    @Override
    public String toString(){
        String user = this.user_id+"{\n";
        for(Field f : User.class.getDeclaredFields()) {
            try {
                user += f.getName()+":"+f.get(this)+"\n";
            } catch (IllegalAccessException e) {
                System.out.println("user."+f.getName()+" is undefined.");
            }
        }
        user += "}";
        return user;
    }
}

