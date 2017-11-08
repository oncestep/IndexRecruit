package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
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
    int updateUser(UserEntity userEntity);

    @Insert("insert into user(mobile,password,name,nickname,email,city,eduDegree,graduation,dirDesire) " +
            "values(#{mobile},#{password},#{name},#{nickname},#{email},#{city},#{eduDegree},#{graduation},#{dirDesire})")
    int saveUser(UserEntity userEntity);

    @Select("select * from user where mobile = #{mobile} limit 1")
    UserEntity getUserByMobile(@Param("mobile") String moblie);
}
