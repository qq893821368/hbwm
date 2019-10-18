package nchu.software.ruanko.hbwmda.mapper;

import nchu.software.ruanko.hbwmbase.domain.UserInfo;
import nchu.software.ruanko.hbwmcommon.model.Order;
import nchu.software.ruanko.hbwmcommon.model.User;
import nchu.software.ruanko.hbwmcommon.model.VIP;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface UserMapper {

    /*@Select("SELECT user_id, account, avatar, isVIP, email, address, gender, intro, birthday, isPublic FROM user, user_info WHERE user.user_id=user_info.user_id")
    public List<User> findAllUser();

    @Select("SELECT account FROM user WHERE account=#{account}")
    public List<String> accountIsExist(@Param("account") String account);

    @Select("SELECT password FROM user, password WHERE user.user_id=password.user_id AND account=#{account}")
    public List<String> findPasswordByAccount(@Param("account") String account);

    @Select("SELECT user_id, account, avatar, isVIP, email, address, gender, intro, birthday, isPublic  FROM user, user_info WHERE user.account=#{account} AND user.user_id=user_info.user_id")
    public List<User> findUserByAccount(@Param("account") String account);

    @Insert("INSERT INTO user(account) VALUES(#{account});INSERT INTO psd VALUES(md5('555'));")
    public int addNewUser(String account, String password);*/

    @Select("SELECT * FROM user_info WHERE user_id = #{user_id}")
    List<User> findinfo(@Param("user_id") String user_id);

    @Update("UPDATE user SET account = #{newaccount} WHERE account = #{oldaccount}")
    int update(String newaccount,@Param("oldaccount") String oldaccount);

    @Update("UPDATE user SET isvip = #{isvip} WHERE account = #{account}")
    int updatevip(String isvip,@Param("account") String account);

    @Update("UPDATE user_info SET address = #{address} WHERE user_id = #{user_id}")
    int updateaddress(String address,@Param("user_id") String user_id);

    @Update("UPDATE user_info SET gender = #{gender} WHERE user_id = #{user_id}")
    int updategender(String gender,@Param("user_id") String user_id);

    @Update("UPDATE user_info SET intro = #{intro} WHERE user_id = #{user_id}")
    int updateintro(String intro,@Param("user_id") String user_id);

    @Update("UPDATE user_info SET isPublic = #{isPublic} WHERE user_id = #{user_id}")
    int updateispublic(String isPublic,@Param("user_id") String user_id);


    @Update("update user_info set email=#{userInfo.email},address=#{userInfo.address},gender=#{userInfo.gender},intro=#{userInfo.intro},birthday=#{userInfo.birthday} where user_id in (select user_id from user where account=#{account})")
    int modifyUserInfo(@Param("account") String account, @Param("userInfo") UserInfo userInfo);
    @Select("select * from user_info where user_id in (select user_id from user where account = #{account});")
    UserInfo getUserInfo(@Param("account") String account);

    /*@Insert("insert into orders values(#{order.order_id},#{order.user_id},#{order.content},#{order.money},#{order.shipDate})")
    int addOrders(@Param("order") Order order);*/
    @Insert("insert into orders values(#{order_id},#{user_id},#{content},#{money},#{shipDate});")
    int addOrders(String order_id,int user_id,String content,double money,String shipDate);

    @Insert("insert into VIP VALUES(#{user_id},CURDATE(),DATE_ADD(CURDATE(),INTERVAL 1 MONTH));")
    int addVIP(@Param("user_id") int user_id);
    @Select("select * from VIP where user_id = #{user_id}")
    VIP getVIP(@Param("user_id") int user_id);
    @Update("update VIP set end_time = DATE_ADD(end_time,INTERVAL 1 MONTH) where user_id = #{user_id};")
    int reVIP(@Param("user_id") int user_id);
    @Update("update user set isVIP = true where user_id = #{user_id};")
    int modifyVIP(@Param("user_id") int user_id);



    @Select("SELECT * FROM user WHERE account = #{account}")
    List<User> findAllByAccount(@Param("account") String account);
    @Select("SELECT password FROM password WHERE user_id = #{user_id}")
    List<String> findPasswordById(int user_id);

    @Insert("INSERT INTO user(account) VALUES(#{account});")
    int addUser(@Param("account") String account);

    @Insert("INSERT INTO password(user_id, password) VALUES(#{user_id}, md5(#{password}));")
    int addPassword(int user_id, String password);
}
