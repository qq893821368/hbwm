package nchu.software.ruanko.hbwmda.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Mapper
@Service
public interface DataRepository {
    @Insert("INSERT INTO user_behavior(user_id, behavior, time) VALUES (#{user_id}, #{behavior}, CURDATE())")
    int userAction(int user_id, String behavior);
}
