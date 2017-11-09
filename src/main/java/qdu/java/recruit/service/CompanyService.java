package qdu.java.recruit.service;

import qdu.java.recruit.entity.CompanyEntity;

public interface CompanyService {

    CompanyEntity getCompany(int comId);

    CompanyEntity getCompany(String companyCode);

    boolean registerCompany(CompanyEntity companyEntity);
}
