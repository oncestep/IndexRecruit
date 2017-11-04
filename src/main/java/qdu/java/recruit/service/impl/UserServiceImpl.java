package qdu.java.recruit.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.mapper.UserMapper;
import qdu.java.recruit.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity getUser(int userId){
        return userMapper.getUser(userId);
    }

    @Override
    public boolean updateUser(UserEntity userEntity){

        int result = userMapper.saveUser(userEntity);
        if(result > 0){
            return true;
        }
        return false;
    }
}
