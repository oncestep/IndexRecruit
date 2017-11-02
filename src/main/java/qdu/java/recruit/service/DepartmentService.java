package qdu.java.recruit.service;

import qdu.java.recruit.entity.DepartmentEntity;

public interface DepartmentService  {

    /**
     * 通过Id返回部门
     * @param departId
     * @return
     */
    DepartmentEntity getDepartment(int departId);
}
