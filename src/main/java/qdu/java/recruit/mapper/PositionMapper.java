package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import qdu.java.recruit.entity.PositionEntity;

import java.util.ArrayList;

public interface PositionMapper {

    @Select("select * from position where state = 1")
    ArrayList<PositionEntity> listPosAll();

    @Select("select * from position where positionId = #{posId} and state = 1")
    PositionEntity getPosition(@Param("posId") int posId);

    @Select("select * from position where hrId = #{hrId} and state = 1 order by releaseDate DESC")
    ArrayList<PositionEntity> listHRPos(@Param("hrId") int hrId);

    @Select("select * from position where title like '%#{keyword}%' and state = 1 order by releaseDate DESC")
    ArrayList<PositionEntity> listSearchPos(@Param("keyword") String keyword);

    @Select("select * from position where categoryId = #{categoryId} and state = 1 order by releaseDate DESC")
    ArrayList<PositionEntity> listCategoryPos(@Param("categoryId") int categoryId);

    @Select("select count(*) from position where categoryId = #{categoryId}")
    int countCategoryPos(@Param("categoryId") int categoryId);

    @Update("update position set hits = hits+1 where positionId = #{positionId}")
    int updateHits(@Param("positionId") int positionId);

}
