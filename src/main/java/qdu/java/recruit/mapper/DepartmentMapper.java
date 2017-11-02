package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.DepartmentEntity;

public interface DepartmentMapper {

    @Select("select * from department where departmentId = #{departId}")
    DepartmentEntity getDepartmentById(@Param("departId") int departId);

}
