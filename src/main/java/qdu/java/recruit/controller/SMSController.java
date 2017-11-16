package qdu.java.recruit.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import static qdu.java.recruit.util.SMSUtil.Sender.sendCode;
import static qdu.java.recruit.util.SMSUtil.Verify.verifyCode;

@Controller
@RequestMapping(value = "sms")
public class SMSController {

    @ResponseBody
    @PostMapping(value = "/sendCode")
    public void smssendCode(@RequestParam String mobile){
    sendCode(mobile);
    }

    @ResponseBody
    @PostMapping(value = "/verifyCode")
    public String smsverifyCode(@RequestParam String mobile,@RequestParam String code){
        int state =verifyCode(mobile,code);
        if(state==Integer.valueOf("1")){
            return "1";
        }else {
            return "0";
        }
    }

}

