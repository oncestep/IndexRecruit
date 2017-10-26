package qdu.java.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/user_login")
    public String login(Model model) {
        return "user_login";

    }
}