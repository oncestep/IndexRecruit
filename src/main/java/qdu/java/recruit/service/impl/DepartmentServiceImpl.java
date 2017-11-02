package qdu.java.recruit.service.impl;

import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.DepartmentEntity;
import qdu.java.recruit.service.DepartmentService;

import javax.annotation.Resource;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentService departmentService;

    @Override
    public DepartmentEntity getDepartment(int departId) {
        return departmentService.getDepartment(departId);
    }

}
