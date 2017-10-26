package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.domain.Position;

import java.util.ArrayList;

public interface PositionMapper {

    @Select("select * from position where state = 1")
    ArrayList<Position> queryPosAll();

    @Select("select * from position where positionId = #{posId} and state = 1")
    Position queryPosition(@Param("posId") int posId);

    @Select("select * from position where hrId = #{hrId} and state = 1 order by releaseDate DESC")
    ArrayList<Position> queryPosHR(@Param("hrId") int hrId);
}
