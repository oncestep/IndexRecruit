package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import qdu.java.recruit.entity.UserEntity;

import java.util.ArrayList;

public interface UserMapper {

    @Select("select * from user")
    ArrayList<UserEntity> listUser();

    @Select("select * from user where userId = #{userId}")
    UserEntity getUser(@Param("userId") int userId);

    @Select("select COUNT(*) from user")
    int getUserSize();

    @Update("update user set password = #{password},name=#{name},nickname=#{nickname},email=#{email},city=#{city}," +
            "eduDegree = #{eduDegree},graduation=#{graduation},dirDesire=#{dirDesire} where userId = #{userId}")
    int saveUser(UserEntity userEntity);
}
