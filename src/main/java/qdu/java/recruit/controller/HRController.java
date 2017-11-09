package qdu.java.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
public class HRController {

    @RequestMapping("/hr_login")
    public String login() {
        return "/hr/hr_login";
    }
}
