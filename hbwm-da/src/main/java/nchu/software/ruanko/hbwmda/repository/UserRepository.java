package nchu.software.ruanko.hbwmda.repository;

import nchu.software.ruanko.hbwmcommon.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface UserRepository {

    @Select("SELECT * FROM user WHERE account = #{account}")
    public List<User> findAll(@Param("account") String account);
}
