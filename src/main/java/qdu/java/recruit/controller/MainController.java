package qdu.java.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qdu.java.recruit.domain.Position;
import qdu.java.recruit.domain.User;
import qdu.java.recruit.service.RecServiceImpl;
import qdu.java.recruit.util.PositionRec;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@EnableAutoConfiguration
public class MainController {

    @Autowired
    private RecServiceImpl recService;

    @RequestMapping("/")
    String home() {


        return "index";
    }

    @RequestMapping("findUser")
    @ResponseBody
    ArrayList<User> findUser() {

        return recService.findUserAll();
    }

    @RequestMapping("recommend")
    @ResponseBody
    String recommend() {

        PositionRec rec = new PositionRec();

        User user = recService.findUser(1);

        HashMap<String, String> pvMap = new HashMap<String, String>();
        pvMap.put("IOS Developer", "5");
        pvMap.put("Android Developer", "12");
        pvMap.put("Java Developer", "65");
        pvMap.put("PHP", "8");
        pvMap.put("Python", "130");

        ArrayList<Position> posList = rec.recommend(pvMap, user);
        return posList.toString();
    }

}
