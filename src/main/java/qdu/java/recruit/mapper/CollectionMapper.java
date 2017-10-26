package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.domain.Collection;

import java.util.ArrayList;

public interface CollectionMapper {

    @Select("select * from collection where userId = #{userId}")
    ArrayList<Collection> queryCollection(@Param("userId") int userId);

    @Select("select top 1 * from collection where userId = #{userId} and positionId = #{posId}")
    Collection queryMatch(@Param("userId") int userId,@Param("posId") int posId);

    @Select("select positionId from collection where userId = #{userId} and positionId not in(select positionId from collection where userId = #{hostId})")
    ArrayList<Integer> queryElse(@Param("userId") int userId, @Param("hostId") int hostId);
}
