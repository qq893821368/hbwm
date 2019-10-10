package nchu.software.ruanko.hbwmda.repository;

import nchu.software.ruanko.hbwmcommon.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface UserRepository {

    @Select("SELECT * FROM user WHERE account = #{account}")
    public List<User> findAllByAccount(@Param("account") String account);

    @Insert("INSERT INTO user(account, password) VALUES(#{account}, #{password})")
    public int addUser(@Param("account") String account, @Param("password") String password);
}
