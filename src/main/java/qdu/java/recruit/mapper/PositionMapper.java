package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.PositionCompanyBO;

import java.util.ArrayList;

public interface PositionMapper {

    @Select("select * from position where statePub = 1")
    ArrayList<PositionEntity> listPosAll();

    @Select("select * from position where positionId = #{posId} and statePub = 1")
    PositionEntity getPosition(@Param("posId") int posId);

    @Select("select * from position where hrIdPub = #{hrId} and statePub = 1 order by releaseDate DESC")
    ArrayList<PositionEntity> listHRPos(@Param("hrId") int hrId);

    @Select("select p.*,c.* from position p,department d,company c \n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId \n" +
            "and title like #{keyword} and statePub = 1 \n" +
            "order by ${order} DESC")
    ArrayList<PositionCompanyBO> listSearchPos(@Param("keyword") String keyword,@Param("order") String order);

    @Select("select p.*,c.* from position p,department d,company c\n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId \n" +
            "and categoryId = #{categoryId} and statePub = 1 \n" +
            "order by releaseDate DESC")
    ArrayList<PositionCompanyBO> listCategoryPos(@Param("categoryId") int categoryId);

    @Select("select count(*) from position where categoryId = #{categoryId}")
    int countCategoryPos(@Param("categoryId") int categoryId);

    @Update("update position set hits = hits+1 where positionId = #{positionId}")
    int updateHits(@Param("positionId") int positionId);

    @Select("select p.*,c.* from position p,department d,company c \n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId and p.positionId = #{posId} limit 1")
    PositionCompanyBO listPosCompany(@Param("posId") int posId);

    @Select("select count(*) from position where hrIdPub=#{hrIdPub}")
    int countHRPos(@Param("hrIdPub") int hrIdPub);

    @Delete("delete position where positionId = #{posId}")
    int delete(@Param("posId") int posId);

    @Update("update position set title = #{title},requirement=#{requirement},quantity=#{quantity}," +
            "workCity=#{workCity},salaryUp=#{salaryUp},salaryDown=#{salaryDown}," +
            "validDate=#{validDate},statePub=#{statePub}" +
            " where positionId = #{posId}")
    int updatePosition(PositionEntity positionEntity);

    @Update("update position set statePub= #{statePub} where positionId = #{posId}")
    int updatePositionState(@RequestParam("statePub") int statePub, @RequestParam("posId") int posId);

    @Insert("insert into position(title,requirement,quantity,workCity,salaryUp,salaryDown,releaseDate,validDate,statePub," +
            "departmentId,categoryId,hrIdPub) " +
            "values(#{title},#{requirement},#{quantity},#{workCity},#{salaryUp},#{salaryDown},#{releaseDate},#{validDate},#{statePub}," +
            "#{departmentId},#{categoryId},#{hrIdPub}")
    int savePosition(PositionEntity positionEntity);

}

