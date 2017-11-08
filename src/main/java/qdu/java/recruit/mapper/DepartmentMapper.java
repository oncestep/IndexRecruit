package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentMapper {

    @Select("select * from department where departmentId = #{departId}")
    DepartmentEntity getDepartmentById(@Param("departId") int departId);

    @Select("select * from department where companyId = #{companyId}")
    List<DepartmentEntity> getDepartmentByCompany(@Param("companyId") int companyId);

    @Select("select count(*) from department where companyId = #{companyId}")
    int getDepartmentCount(@Param("companyId") int companyId);
}
