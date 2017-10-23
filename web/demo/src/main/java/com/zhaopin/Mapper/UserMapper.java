package com.zhaopin.Mapper;

import com.zhaopin.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Select("select * from Test where id = #{id}")
    User selectUserByID(int id);


}
