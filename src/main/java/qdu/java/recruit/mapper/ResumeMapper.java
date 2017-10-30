package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ResumeMapper {

    @Select("select top 1 resumeId from resume where userId = #{userId}")
    int getResumeId(@Param("userId") int userId);

}
