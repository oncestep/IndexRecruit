package qdu.java.recruit.service.impl;

import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.CompanyEntity;
import qdu.java.recruit.mapper.CompanyMapper;
import qdu.java.recruit.service.CompanyService;

import javax.annotation.Resource;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public CompanyEntity getCompany(int comId){
        return companyMapper.getCompanyById(comId);
    }
}
