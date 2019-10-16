package nchu.software.ruanko.hbwmda.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SingerMapper {
    @Select("select * from singer_info where nation='中国'")
    public int[] getChineseSingerId();
    @Select("select * from singer_info where nation='欧美'")
    public int[] getEASingerId();
    @Select("select * from singer_info where nation='日韩'")
    public int[] getJKSingerId();
    @Select("select * from singer_info where nation='其他'")
    public int[] getOtherSingerId();

}
