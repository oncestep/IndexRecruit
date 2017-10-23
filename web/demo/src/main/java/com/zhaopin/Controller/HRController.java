package com.zhaopin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
public class HRController {
    @RequestMapping("/hr_login")
    public String login(){
        return "hr_login";
    }
}
