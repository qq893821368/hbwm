package nchu.software.ruanko.hbwmda.mapper;

import nchu.software.ruanko.hbwmcommon.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface UserMapper {

    @Select("SELECT user_id, account, avatar, isVIP, email, address, gender, intro, birthday, isPublic FROM user, user_info WHERE user.user_id=user_info.user_id")
    public List<User> findAllUser();

    @Select("SELECT account FROM user WHERE account=#{account}")
    public List<String> accountIsExist(@Param("account") String account);

    @Select("SELECT password FROM user, password WHERE user.user_id=password.user_id AND account=#{account}")
    public List<String> findPasswordByAccount(@Param("account") String account);

    @Select("SELECT user_id, account, avatar, isVIP, email, address, gender, intro, birthday, isPublic  FROM user, user_info WHERE user.account=#{account} AND user.user_id=user_info.user_id")
    public List<User> findUserByAccount(@Param("account") String account);

    @Insert("INSERT INTO user(account) VALUES(#{account});INSERT INTO psd VALUES(md5('555'));")
    public int addNewUser(String account, String password);
}
