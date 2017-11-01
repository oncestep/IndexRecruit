package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.Position;

import java.util.ArrayList;

public interface PositionMapper {

    @Select("select * from position where state = 1")
    ArrayList<Position> listPosAll();

    @Select("select * from position where positionId = #{posId} and state = 1")
    Position getPosition(@Param("posId") int posId);

    @Select("select * from position where hrId = #{hrId} and state = 1 order by releaseDate DESC")
    ArrayList<Position> listPosHR(@Param("hrId") int hrId);
}
