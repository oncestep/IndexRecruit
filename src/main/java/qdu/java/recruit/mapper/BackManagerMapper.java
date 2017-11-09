package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.CompanyEntity;
import qdu.java.recruit.entity.UserAreaEntity;

import java.util.ArrayList;


public interface BackManagerMapper {
    @Select("SELECT COUNT(*) FROM `admin` WHERE userid = #{userid} AND password = md5(#{password})")
    Integer backLogin(@Param("userid") Long userid,@Param("password") String password);

    @Select("SELECT COUNT(*) AS 'usernum',`province` AS 'area' FROM `user` GROUP BY `province`")
    ArrayList<UserAreaEntity> userArea();

    @Select("SELECT * FROM `company`")
    ArrayList<CompanyEntity> getAllCompanies();

}
