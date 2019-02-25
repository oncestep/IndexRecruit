package qdu.java.recruit.service;

import qdu.java.recruit.entity.CompanyEntity;
import qdu.java.recruit.entity.UserAreaEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.entity.WebCountEntity;

import java.util.ArrayList;

public interface BackManagerService {
    int backLogin(Long userid,String password);
    ArrayList<UserAreaEntity> userArea();
    ArrayList<CompanyEntity> getAllCompanies();
    ArrayList<UserEntity> getAllUsers();
    WebCountEntity getWebCount();
    int addCompany(String companyName,String companyCode,String description);
}
