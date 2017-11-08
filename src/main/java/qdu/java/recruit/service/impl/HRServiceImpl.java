package qdu.java.recruit.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.mapper.HRMapper;
import qdu.java.recruit.service.HRService;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class HRServiceImpl implements HRService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private HRMapper HRMapper;

    @Override
    public HREntity getHR(int HRId) {
        return HRMapper.getHR(HRId);
    }

    @Override
    public boolean updateHR(HREntity HREntity) {

        int result = HRMapper.updateHR(HREntity);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean registerHR(HREntity HREntity) {

        String password = HREntity.getHrPassword();
        String mobile  = HREntity.getHrMobile();


        if(HRMapper.getHRByMobile(mobile) != null) {
            return false;
        }

        int result = -1;
        try {
            String encPass = this.EncodingByMd5(password);
            HREntity.setHrPassword(encPass);
            result = HRMapper.saveHR(HREntity);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("md5加密出错");
        } catch (UnsupportedEncodingException e) {
            System.out.println("编码转化出错");
        } finally {
            if (result > 0) {
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean loginHR(String mobile, String password) {

        String passwordDB = HRMapper.getHRByMobile(mobile).getHrPassword();

        try {
            if (this.EncodingByMd5(password).equals(passwordDB)) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("md5加密出错");
        } catch (UnsupportedEncodingException e) {
            System.out.println("编码转化错误");
        } finally {
            return false;
        }
    }

    @Override
    public HREntity getHRByMobile(String mobile){

        return HRMapper.getHRByMobile(mobile);
    }


    public String EncodingByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();

        //加密后的字符串
        String encStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return encStr;
    }


}
