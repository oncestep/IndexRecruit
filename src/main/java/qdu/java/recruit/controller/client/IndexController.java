package qdu.java.recruit.controller.client;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.pojo.ApplicationPositionHRBO;
import qdu.java.recruit.pojo.FavorPositionBO;
import qdu.java.recruit.pojo.PositionCompanyBO;
import qdu.java.recruit.pojo.UserCommentBO;
import qdu.java.recruit.service.*;
import qdu.java.recruit.service.FavorService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;


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

    @Resource
    private ResumeService resumeService;

    @Resource
    private ApplicationService applicationService;

    @Resource
    private FavorService favorService;

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
        UserEntity user = userService.getUser(6);

        //推荐职位列表
        page = (page < 1 || page > GlobalConst.MAX_PAGE) ? 1 : page;
        PageInfo<PositionCompanyBO> posInfo = positionService.recPosition(user, page, limit);
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
     *
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
     *
     * @param request
     * @param id
     * @param limit
     * @return
     */
    @GetMapping(value = "position/{id}")
    public String getPosition(HttpServletRequest request, @PathVariable int id,
                              @RequestParam(value = "limit", defaultValue = "12") int limit) {

        return getPosition(request, id, 1, limit);
    }

    @GetMapping(value = "position/{id}/{page}")
    public String getPosition(HttpServletRequest request, @PathVariable int id, @PathVariable int page,
                              @RequestParam(value = "limit", defaultValue = "12") int limit) {

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

        request.setAttribute("position", position);
        request.setAttribute("department", department);
        request.setAttribute("company", company);
        request.setAttribute("category", category);
        request.setAttribute("comList", comList);
        this.title(request, "职位信息");
        if (!positionService.updateHits(id)) {
            this.errorDirect_404();
        }

        return this.userDirect("position_details");
    }


    /**
     * 用户申请职位
     *
     * @param id
     * @return
     */
    @GetMapping(value = "apply/{id}")
    public String apply(HttpServletRequest request, @PathVariable int id) {

        //当前用户
        UserEntity user = this.getUser(request);
//        UserEntity user = userService.getUser(1);

        //当前用户简历
        ResumeEntity resume = resumeService.getResumeById(user.getUserId());
        //当前浏览职位
        PositionEntity position = positionService.getPositionById(id);

        //获取当前日期时间
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date recentDate = new java.sql.Date(utilDate.getTime());

        if (user == null) {
            this.errorDirect_404();
        }

        boolean result = applicationService.applyPosition(resume.getResumeId(), position.getPositionId());
        if (!result) {
            this.errorDirect_404();
        }
        return this.userDirect("apply_success");
    }

    /**
     * 用户评论职位
     *
     * @param id
     * @param type
     * @param content
     * @return
     */
    @PostMapping(value = "comment/{id}")
    public String comment(HttpServletRequest request, @PathVariable int id,
                          @RequestParam int type, @RequestParam String content) {
        //当前用户
//        UserEntity user = this.getUser(request);
        UserEntity user = userService.getUser(5);

        if (user == null) {
            this.errorDirect_404();
        }

        boolean result = commentService.commentPosition(type, content, user.getUserId(), id);
        if (!result) {
            this.errorDirect_404();
        }
        return "redirect:/position/" + id;
    }


    /**
     * 用户个人中心
     *
     * @param request
     * @return
     */
    @GetMapping(value = "user")
    public String showInfo(HttpServletRequest request) {

        //用户个人信息
//        UserEntity user = this.getUser(request);
        UserEntity user = userService.getUser(5);
        if (user == null) {
            this.errorDirect_404();
        }

        //个人简历信息
        ResumeEntity resume = resumeService.getResumeById(user.getUserId());
        //个人收藏职位
        List<FavorPositionBO> favorPosList = favorService.listFavorPosition(user.getUserId());
        //个人应聘处理记录
        List<ApplicationPositionHRBO> applyPosList = applicationService.listApplyInfo(resume.getResumeId());

        request.setAttribute("user", user);
        request.setAttribute("resume", resume);
        request.setAttribute("favorPosList", favorPosList);
        request.setAttribute("applyPosList", applyPosList);

        return this.userDirect("user_info");
    }

    /**
     * 用户完整个人简历
     *
     * @param request
     * @param ability
     * @param internship
     * @param workExperience
     * @param certificate
     * @param jobDesire
     * @return
     */
    @PostMapping(value = "user/resumeUpdate")
    public String updateResume(HttpServletRequest request, @RequestParam("ability") String ability,
                               @RequestParam("internship") String internship, @RequestParam("workExperience") String workExperience,
                               @RequestParam("certificate") String certificate, @RequestParam("jobDesire") String jobDesire) {
        //当前用户
        int userId = this.getUserId(request);

        //参数对象
        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setAbility(ability);
        resumeEntity.setInternship(internship);
        resumeEntity.setWorkExperience(workExperience);
        resumeEntity.setCertificate(certificate);
        resumeEntity.setJobDesire(jobDesire);
        resumeEntity.setUserId(userId);

        if (resumeService.getResumeById(userId) != null) {
            if (!resumeService.updateResume(resumeEntity)) {
                this.errorDirect_404();
            }
        } else {
            if (!resumeService.createResume(resumeEntity)) {
                this.errorDirect_404();
            }
        }
        return "redirect:/user";
    }

    /**
     * 用户更新个人信息
     * @param request
     * @param password
     * @param name
     * @param nickname
     * @param email
     * @param city
     * @param eduDegree
     * @param graduation
     * @param dirDesire
     * @return
     */
    @PostMapping(value = "user/infoUpdate")
    public String updateInfo(HttpServletRequest request, @RequestParam("password") String password,@RequestParam("name") String name,@RequestParam("nickname") String nickname,
                             @RequestParam("email") String email,@RequestParam("city") String city,@RequestParam("eduDegree") String eduDegree,@RequestParam("graduation") String graduation,
                             @RequestParam("dirDesire") int dirDesire) {

        int userId = this.getUserId(request);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setPassword(password);
        userEntity.setName(name);
        userEntity.setNickname(nickname);
        userEntity.setEmail(email);
        userEntity.setCity(city);
        userEntity.setEduDegree(eduDegree);
        userEntity.setGraduation(graduation);
        userEntity.setDirDesire(dirDesire);


        if(!userService.updateUser(userEntity)){
            this.errorDirect_404();
        }
        return "redirect:/user";
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @GetMapping(value = "user/logout")
    public String userLogout(HttpServletRequest request){
        // 清除session
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().removeAttribute(GlobalConst.LOGIN_SESSION_KEY);
        request.getSession().invalidate();

        return userDirect("logout_success");
    }


    @RequestMapping("test")
    public String test() {
        return this.userDirect("index");
    }
}
