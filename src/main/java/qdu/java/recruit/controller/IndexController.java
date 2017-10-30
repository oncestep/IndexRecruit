package qdu.java.recruit.controller;

import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.entity.Position;
import qdu.java.recruit.entity.User;
import qdu.java.recruit.service.PositionService;
import qdu.java.recruit.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@EnableAutoConfiguration
public class IndexController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private PositionService positionService;

    @Resource
    private UserService userService;


    @GetMapping("/")
    public String index(HttpServletRequest request, @RequestParam(value = "limit", defaultValue = "12") int limit) {

        return this.index(request,1,limit);
    }

    @GetMapping("page/{p}")
    public String index(HttpServletRequest request, @RequestParam int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {

        User user = new User();
        user = userService.getUser(1);

        page = (page < 0 || page > GlobalConst.MAX_PAGE) ? 1 : page;
        PageInfo<Position> posInfo = positionService.recPosition(user,page,limit);
        request.setAttribute("posInfo",posInfo);
        if(page>1) {
            this.title(request, "第" + page + "页");
        }

        return this.userDirect("user_test");
    }

    @RequestMapping("test")
    public String test(){
        return userDirect("index");
    }
}
