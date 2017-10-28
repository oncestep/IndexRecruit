package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.Comment;

import java.util.ArrayList;

public interface CommentMapper {

    @Select("select * from comment where userId = #{userId}")
    ArrayList<Comment> queryComment(@Param("userId") int userId);

    @Select("select * from comment where userId = #{userId} and positionId = #{posId}")
    Comment queryMatch(@Param("userId") int userId, @Param("posId") int posId);
}
