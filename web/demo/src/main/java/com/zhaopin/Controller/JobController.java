package com.zhaopin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/job")
public class JobController {
    @RequestMapping("/detail/{id}")
    public String getDetail(Model model, @PathVariable String id){
        return "job_detail";
    }
}
