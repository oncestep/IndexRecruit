package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import qdu.java.recruit.entity.ResumeEntity;

public interface ResumeMapper {

    @Select("select resumeId from resume where userId = #{userId} limit 1")
    int getResumeId(@Param("userId") int userId);

    @Select("select * from resume where userId = #{userId} limit 1")
    ResumeEntity getResumeById(@Param("userId") int userId);

    @Update("update resume set ability = #{ability},internship=#{internship},workExperience=#{workExperience}," +
            "certificate = #{certificate},jobDesire = #{jobDesire} where userId = #{userId}")
    int saveResume(ResumeEntity resumeEntity);

    @Insert("insert into resume(ability,internship,workExperience,certificate,jobDesire,userId) " +
            "values (#{ability},#{internship},#{workExperience},#{certificate},#{jobDesire},#{userId})")
    int createResume(ResumeEntity resumeEntity);


}
