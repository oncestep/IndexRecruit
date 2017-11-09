package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.UserEntity;

import java.util.ArrayList;

public interface UserMapper {

    @Select("select * from user")
    ArrayList<UserEntity> listUser();

    @Select("select * from user where userId = #{userId}")
    UserEntity getUser(@Param("userId") int userId);

    @Select("select COUNT(*) from user")
    int getUserSize();
}
