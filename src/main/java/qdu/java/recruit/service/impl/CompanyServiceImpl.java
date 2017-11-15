package qdu.java.recruit.service.impl;

import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.CompanyEntity;
import qdu.java.recruit.mapper.CompanyMapper;
import qdu.java.recruit.service.CompanyService;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public CompanyEntity getCompany(int comId){
        return companyMapper.getCompanyById(comId);
    }

    @Override
    public CompanyEntity getCompany(String companyCode) {
        return companyMapper.getCompanyCode(companyCode);
    }

    @Override
    public boolean registerCompany(CompanyEntity companyEntity) {
        String companyCode = companyEntity.getCompanyCode();
        if(companyCode != null) {
            throw new RuntimeException("该公司已存在");
        }
        else {
            String Code = companyEntity.getCompanyName()+companyEntity.getCompanyId()+companyEntity.getState();
            int result = -1;
            try {
                companyCode = this.EncodingByMd5(Code);
                companyEntity.setCompanyCode(companyCode);
                result = companyMapper.saveCompany(companyEntity);

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
