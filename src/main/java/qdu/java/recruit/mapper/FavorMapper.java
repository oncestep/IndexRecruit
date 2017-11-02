package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.FavorEntity;

import java.util.ArrayList;

public interface FavorMapper {

    @Select("select * from favor where userId = #{userId}")
    ArrayList<FavorEntity> listFavor(@Param("userId") int userId);

    @Select("select * from favor where userId = #{userId} and positionId = #{posId} limit 1")
    FavorEntity getFavor(@Param("userId") int userId, @Param("posId") int posId);

    @Select("select positionId from favor where userId = #{userId} and positionId not in(select positionId from favor where userId = #{hostId})")
    ArrayList<Integer> getQuery(@Param("userId") int userId, @Param("hostId") int hostId);
}
