package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.CommentEntity;

import java.util.ArrayList;

public interface CommentMapper {

    @Select("select * from comment where userId = #{userId}")
    ArrayList<CommentEntity> listCommentAll(@Param("userId") int userId);

    @Select("select * from comment where userId = #{userId} and positionId = #{posId}")
    CommentEntity listComment(@Param("userId") int userId, @Param("posId") int posId);
}
