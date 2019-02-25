package qdu.java.recruit.controller.admin;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import qdu.java.recruit.entity.CompanyEntity;
import qdu.java.recruit.entity.UserAreaEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.entity.WebCountEntity;
import qdu.java.recruit.service.BackManagerService;
import sun.security.provider.MD5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/manager")
public class BackManagerController {

    @Autowired
    private BackManagerService backManagerService;

    @RequestMapping("/login")
    public String init(){
        return "manager/login";
    }

    @RequestMapping("/index")
    public String index(){
        return "manager/index";
    }

    @RequestMapping("/index_v3")
    public String index_v3() {
        return "manager/index_v3";
    }

    @RequestMapping("/nestable_list")
    public String teams_board() {
        return "manager/nestable_list";
    }

    @RequestMapping("/contacts")
    public String contacts() {
        return "manager/contacts";
    }

    @RequestMapping("/table_jqgrid")
    public String table() {
        return "manager/table_jqgrid";
    }



    @RequestMapping("/widgets")
    public String widgets() {
        return "manager/widgets";
    }

    @RequestMapping("/adminlogin")
    @ResponseBody
    public Map<String,Object> login(Long username, String password){
        Map<String,Object> map = new HashMap<>();
        int result = backManagerService.backLogin(username, password);
        if (result==0){
            map.put("state","0");
        }
        else {
            map.put("state","1");
        }
        return map;
    }

    @RequestMapping("/addcompany")
    @ResponseBody
    public Map<String,Object> addcompany(String companyName,String companyCode,String description){
        Map<String,Object> map = new HashMap<>();
        int result = backManagerService.addCompany(companyName,companyCode,description);
        if (result==0){
            map.put("state","0");
        }
        else {
            map.put("state","1");
        }
        return map;
    }


    @RequestMapping(value = "/userareachart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> area(){
        Map<String,Object> map = new HashMap<>();
        ArrayList<UserAreaEntity> area = backManagerService.userArea();
        UserAreaEntity userAreaEntity;
        for(int i=0; i<area.size(); i++){
            userAreaEntity = area.get(i);
            map.put(userAreaEntity.getArea(),userAreaEntity.getUsernum());
        }
        return map;
    }

    @RequestMapping("webcount")
    @ResponseBody
    public Map<String,Object> webcount(){
        Map<String,Object> map = new HashMap<>();
        WebCountEntity webCountEntity = backManagerService.getWebCount();
        map.put("companynum",webCountEntity.getCompanynum());
        map.put("offernum" ,webCountEntity.getOffernum());
        map.put("usernum",webCountEntity.getUsernum());
        map.put("visitnum",webCountEntity.getVisitnum());
        System.out.println(map);
        return map;
    }

    @RequestMapping("getcompany")
    @ResponseBody
    public String getCompany(){
        Gson gson = new Gson();
        ArrayList<CompanyEntity> companyEntities = backManagerService.getAllCompanies();
        String companyInfo = gson.toJson(companyEntities);
        System.out.println(companyInfo);
        return companyInfo;
    }


    @RequestMapping("getuser")
    @ResponseBody
    public String getUser(){
        Gson gson = new Gson();
        ArrayList<UserEntity> userEntities = backManagerService.getAllUsers();
        String userInfo = gson.toJson(userEntities);
        System.out.println(userInfo);
        return userInfo;
    }


}
