package qdu.java.recruit.controller.client;


import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.service.ResumeService;
import qdu.java.recruit.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@EnableAutoConfiguration
@RequestMapping("client")
@Api("ClientController有关API")
public class ClientController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private UserService userService;

    @Resource
    private ResumeService resumeService;

    /**
     * 用户注册返回 0 -> 失败 1 -> 成功
     * @param user
     * @return
     */
    @PostMapping(value = "register")
    @ResponseBody
    public int userRegister(@RequestParam UserEntity user) {

        String password = user.getPassword();

        //验证mobile 和 password是否为空
        if(user.getMobile() == null || user.getPassword() == null){
            return 0;
        }

        ResumeEntity resume = new ResumeEntity();
        resume.setUserId(user.getUserId());
        if(userService.registerUser(user)&&resumeService.createResume(resume)){
            return 1;
        }
        return 0;
    }

    @PostMapping(value = "login")
    public int userLogin(HttpSession httpSession,@RequestParam String mobile, @RequestParam String password) {

        if(mobile == null||password == null){
            return 0;
        }

        if(userService.loginUser(mobile,password)){

            httpSession.setAttribute("user",userService.getUserByMobile(mobile));
            return 1;
        }
        return 0;
    }
}
