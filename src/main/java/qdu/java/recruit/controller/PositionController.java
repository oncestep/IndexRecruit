package qdu.java.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/position")
public class PositionController {

    @RequestMapping("/details/{posId}")
    public String getDetail(Model model, @PathVariable int posId){
        return "job_detail";
    }
}
