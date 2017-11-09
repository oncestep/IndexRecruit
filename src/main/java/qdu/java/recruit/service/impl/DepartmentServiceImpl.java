package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.DepartmentEntity;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.mapper.DepartmentMapper;
import qdu.java.recruit.service.DepartmentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public DepartmentEntity getDepartment(int departId) {
        return departmentMapper.getDepartmentById(departId);
    }

    @Override
    public List<DepartmentEntity> getDepartmentByCompany(int companyId) {
        return departmentMapper.getDepartmentByCompany(companyId);
    }

    @Override
    public PageInfo<DepartmentEntity> getDepartmentByCompany(int companyId, int page, int limit) {
        int total = departmentMapper.getDepartmentCount(companyId);
        PageHelper.startPage(page, limit);
        List<DepartmentEntity> deplist = getDepartmentByCompany(companyId);
        PageInfo<DepartmentEntity> pagination = new PageInfo<>(deplist);
        pagination.setTotal(total);
        return pagination;
    }


}
