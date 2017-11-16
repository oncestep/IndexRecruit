package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.*;
import qdu.java.recruit.entity.CommentEntity;
import qdu.java.recruit.pojo.UserCommentBO;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface CommentMapper {

    @Select("select * from comment where userId = #{userId}")
    ArrayList<CommentEntity> listCommentAll(@Param("userId") int userId);

    @Select("select * from comment where userId = #{userId} and positionId = #{posId} limit 1")
    CommentEntity listComment(@Param("userId") int userId, @Param("posId") int posId);

    @Select("select commentId,type,content,releaseTime,user.* from comment,user where comment.userId = user.userId and positionId = #{posId} order by releaseTime DESC")
    ArrayList<UserCommentBO> listUserComment(@Param("posId") int posId);

    @Insert("insert into comment(type,content,releaseTime,userId,positionId) values (#{type},#{content},#{releaseTime},#{userId},#{positionId})")
    int saveComment(@Param("type") int type, @Param("content") String content, @Param("releaseTime") Timestamp releaseTime, @Param("userId") int userId, @Param("positionId") int positionId);
}

