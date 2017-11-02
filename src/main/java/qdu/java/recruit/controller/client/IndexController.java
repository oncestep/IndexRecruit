package qdu.java.recruit.controller.client;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.pojo.UserCommentBO;
import qdu.java.recruit.service.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@Controller
@EnableAutoConfiguration
@Api("indexController有关API")
public class IndexController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private PositionService positionService;

    @Resource
    private UserService userService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private CommentService commentService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private CompanyService companyService;

    /**
     * 主页输出用户个人信息,推荐职位信息
     *
     * @param request
     * @param limit
     * @return
     */
    @GetMapping("/")
    @ApiOperation(value = "主页信息输出", notes = "主页输出用户个人信息，推荐职位信息")
    public String index(HttpServletRequest request, @RequestParam(value = "limit", defaultValue = "12") int limit) {

        return this.index(request, 1, limit);
    }

    @GetMapping("page/{page}")
    @ApiOperation(value = "主页输出信息", notes = "主页输出用户个人信息，推荐职位信息分页")
    @ApiImplicitParam(name = "page", value = "推荐职位分页", dataType = "Integer")
    public String index(HttpServletRequest request, @PathVariable int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        //测试用户
        UserEntity user = new UserEntity();
        user = userService.getUser(1);

        //推荐职位列表
        page = (page < 1 || page > GlobalConst.MAX_PAGE) ? 1 : page;
        PageInfo<PositionEntity> posInfo = positionService.recPosition(user, page, limit);
        request.setAttribute("posInfo", posInfo);

        //标题栏用户个人信息
        request.setAttribute("user", user);

        //网页标题
        if (page > 1) {
            this.title(request, "第" + page + "页");
        } else {
            this.title(request, "Recruit主页");
        }

        return this.userDirect("user_test");
    }

    /**
     * 主页搜索功能
     *
     * @param request
     * @param keyword
     * @param limit
     * @return
     */
    @GetMapping(value = "search/{keyword}")
    public String search(HttpServletRequest request, @PathVariable String keyword, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return this.search(request, keyword, 1, limit);
    }

    @GetMapping(value = "search/{keyword}/{page}")
    public String search(HttpServletRequest request, @PathVariable String keyword, @PathVariable int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        PageInfo<PositionEntity> posInfo = positionService.searchPosition(keyword, page, limit);
        request.setAttribute("posList", posInfo);
        request.setAttribute("tag", "搜索");
        request.setAttribute("keyword", keyword);
        this.title(request, "Recruit搜索");

        return this.userDirect("position_list");
    }

    /**
     * 分类职位列表页
     * @param request
     * @param id
     * @param limit
     * @return
     */
    @GetMapping(value = "category/{id}")
    public String list(HttpServletRequest request, @PathVariable int id, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return this.list(request, id, 1, limit);
    }

    @GetMapping(value = "category/{id}/{page}")
    public String list(HttpServletRequest request, @PathVariable int id, @PathVariable int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {

        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        CategoryEntity category = categoryService.getCategory(id);
        if (category == null) {
            this.errorDirect_404();
        }
        PageInfo<PositionEntity> posList = positionService.listPosition(id, page, limit);

        request.setAttribute("posList", posList);
        request.setAttribute("category", category);
        request.setAttribute("tag", "分类");

        return this.userDirect("position_category");
    }


    /**
     * 职位细节页
     * @param request
     * @param id
     * @param limit
     * @return
     */
    @GetMapping(value = "position/{id}")
    public String getPosition(HttpServletRequest request, @PathVariable int id,@RequestParam(value = "limit", defaultValue = "12") int limit) {

        return getPosition(request,id,1,limit);
    }

    @GetMapping(value = "position/{id}/{page}")
    public String getPosition(HttpServletRequest request, @PathVariable int id, @PathVariable int page, @RequestParam(value = "limit", defaultValue = "12") int limit){

        PositionEntity position = positionService.getPositionById(id);
        if (position == null) {
            this.errorDirect_404();
        }

        //所属部门信息
        DepartmentEntity department = departmentService.getDepartment(position.getDepartmentId());
        //所属公司信息
        CompanyEntity company = companyService.getCompany(department.getCompanyId());
        //职位所属分类信息
        CategoryEntity category = categoryService.getCategory(position.getCategoryId());
        //分页评论信息
        PageInfo<UserCommentBO> comList = commentService.listComment(id, page, limit);

        request.setAttribute("position",position);
        request.setAttribute("department",department);
        request.setAttribute("company",company);
        request.setAttribute("category",category);
        request.setAttribute("comList",comList);
        this.title(request,"职位信息");
        if (!positionService.updateHits(id)){
            this.errorDirect_404();
        }

        return this.userDirect("position_details");
    }









    @RequestMapping("test")
    public String test() {
        return this.userDirect("index");
    }
}
