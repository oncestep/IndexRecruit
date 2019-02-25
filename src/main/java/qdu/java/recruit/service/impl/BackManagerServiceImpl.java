package qdu.java.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.CompanyEntity;
import qdu.java.recruit.entity.UserAreaEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.entity.WebCountEntity;
import qdu.java.recruit.mapper.BackManagerMapper;
import qdu.java.recruit.service.BackManagerService;

import java.util.ArrayList;

@Service
public class BackManagerServiceImpl implements BackManagerService{

    @Autowired
    private BackManagerMapper backManagerMapper;

    @Override
    public int backLogin(Long userid, String password) {
        return backManagerMapper.backLogin(userid,password);
    }

    @Override
    public ArrayList<UserAreaEntity> userArea(){
        return backManagerMapper.userArea();
    }

    @Override
    public ArrayList<CompanyEntity> getAllCompanies() {
        return backManagerMapper.getAllCompanies();
    }

    @Override
    public ArrayList<UserEntity> getAllUsers() {
        return backManagerMapper.getAllUsers();
    }

    @Override
    public WebCountEntity getWebCount(){
        return backManagerMapper.getWebCount();
    }

    @Override
    public int addCompany(String companyName,String companyCode,String description){
        return backManagerMapper.addCompany(companyName,companyCode,description);
    }
}
