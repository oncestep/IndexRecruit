
package com.zhaopin.Controller;


import com.zhaopin.Entity.User;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

@RequestMapping("/")
    public String index(Model model){
            User user =new User();
            user.setName("洗刷刷");
        //    model.addAttribute("user",user);
            return "index";
    }
}
