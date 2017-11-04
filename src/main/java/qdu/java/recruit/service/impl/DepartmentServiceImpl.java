package qdu.java.recruit.service.impl;

import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.DepartmentEntity;
import qdu.java.recruit.mapper.DepartmentMapper;
import qdu.java.recruit.service.DepartmentService;

import javax.annotation.Resource;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public DepartmentEntity getDepartment(int departId) {
        return departmentMapper.getDepartmentById(departId);
    }

}
