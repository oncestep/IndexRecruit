package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.ApplicationEntity;
import qdu.java.recruit.pojo.ApplicationPositionHRBO;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface ApplicationMapper {

    @Select("select * from application where resumeId = #{resumeId}")
    ArrayList<ApplicationEntity> listApplication(@Param("resumeId") int resumeId);

    @Select("select * from application where resumeId = #{resumeId} and positionId = #{posId}")
    ApplicationEntity getApplication(@Param("resumeId") int resumeId, @Param("posId") int posId);

    @Insert("insert into application(state,recentTime,resumeId,positionId) values (0,#{recentTime},#{resumeId},#{positionId})")
    int saveApplication(@Param("recentTime") Timestamp recentTime, @Param("resumeId") int resumeId, @Param("positionId") int positionId);

    @Select("select a.applicationId,a.state,a.recentTime,a.resumeId,p.*,h.hrId,h.hrMobile,h.hrName\n" +
            " from application a,position p,hr h \n" +
            " where a.positionId = p.positionId and a.hrId = h.hrId and a.resumeId = #{resumeId}")
    ArrayList<ApplicationPositionHRBO> listAppPosHR(@Param("resumeId") int resumeId);


}
