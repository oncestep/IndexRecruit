package qdu.java.recruit.service;

import qdu.java.recruit.entity.CompanyEntity;
import qdu.java.recruit.entity.UserAreaEntity;

import java.util.ArrayList;

public interface BackManagerService {
    int backLogin(Long userid,String password);
    ArrayList<UserAreaEntity> userArea();
    ArrayList<CompanyEntity> getAllCompanies();
}
