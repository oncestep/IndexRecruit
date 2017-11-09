package qdu.java.recruit.service;

import com.github.pagehelper.PageInfo;
import qdu.java.recruit.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentService  {

    /**
     * 通过Id返回部门
     * @param departId
     * @return
     */
    DepartmentEntity getDepartment(int departId);

    List<DepartmentEntity> getDepartmentByCompany(int companyId);

    PageInfo<DepartmentEntity> getDepartmentByCompany(int companyId, int page, int limit);

}
