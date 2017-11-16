package qdu.java.recruit.controller.user;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import javafx.application.Application;
import net.sf.json.JSONObject;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@EnableAutoConfiguration
@Api("ajax返回json控制器")
public class DataController extends BaseController {
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
     * 用户注册返回 0 -> 失败 1 -> 成功
     *
     * @return
     */
    @PostMapping(value = "user/registerPost")
    @ResponseBody
    public int userRegister(@RequestParam String mobile, @RequestParam String password, @RequestParam String nickName) {

        //验证mobile 和 password是否为空
        if (mobile == null || password == null || nickName == null) {
            return 0;
        }

        if (userService.getUserByMobile(mobile) != null) {
            return 0;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setMobile(mobile);
        userEntity.setPassword(password);
        userEntity.setNickname(nickName);

        if (!userService.registerUser(userEntity)) {
            return 0;
        }

        ResumeEntity resume = new ResumeEntity();
        resume.setUserId(userService.getUserByMobile(mobile).getUserId());
        if (!resumeService.createResume(resume)) {
            return 0;
        }
        return 1;
    }

    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping(value = "user/loginPost")
    @ResponseBody
    public int userLogin(HttpSession httpSession, @RequestParam String userName, @RequestParam String userPass) {

        String mobile = userName;
        String password = userPass;

        if (mobile == null || password == null) {
            return 0;
        }

        if (userService.loginUser(mobile, password)) {

            httpSession.setAttribute("user", userService.getUserByMobile(mobile));
            return 1;
        }
        return 0;
    }

    /**
     * 主页分页输出 （用户信息，职位列表）
     *
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = "/page/{page}")
    @ResponseBody
    public String index(HttpSession httpSession, @PathVariable int page, @RequestParam(value = "limit", defaultValue = "6") int limit) {
        //测试用户
        UserEntity user = (UserEntity) httpSession.getAttribute("user");
        HREntity hr = (HREntity) httpSession.getAttribute("hr");

//        UserEntity user = new UserEntity();
//        user = userService.getUser(5);

        //推荐职位列表
        page = (page < 1 || page > GlobalConst.MAX_PAGE) ? 1 : page;

        Map output = new TreeMap();
        try {
            List<PositionCompanyBO> posInfo = positionService.recPosition(user, page, limit);
            output.put("posInfo", posInfo);
        } catch (Exception ex) {
            return "out";
        }

        if (user != null) {
            output.put("user", user);
        }
        if (hr != null) {
            output.put("hr", hr);
        }

        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    /**
     * 职位搜索页分页输出 （关键字，职位列表）
     *
     * @param request
     * @param keyword
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = "/search")
    @ResponseBody
    public String search(HttpServletRequest request, @RequestParam(value = "keyword",defaultValue="") String keyword,
                         @RequestParam(value="orderBy",defaultValue = "salaryUp") String orderBy,@RequestParam(value="page",defaultValue = "1") int page,
                         @RequestParam(value = "limit", defaultValue = "6") int limit) {
        UserEntity user = this.getUser(request);

        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        PageInfo<PositionCompanyBO> posInfo = positionService.searchPosition(keyword,orderBy, page, limit);

        Map output = new TreeMap();
        output.put("user",user);
        output.put("title", ("第" + page + "页"));
        output.put("keyword", keyword);
        output.put("orderBy", orderBy);
        output.put("posInfo", posInfo);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    /**
     * 职位分类页分页输出 （职位分类，职位列表）
     *
     * @param request
     * @param id
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = "/category/{id}/{page}")
    @ResponseBody
    public String list(HttpServletRequest request, @PathVariable int id, @PathVariable int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {

        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        CategoryEntity category = categoryService.getCategory(id);
        if (category == null) {
            this.errorDirect_404();
        }
        PageInfo<PositionCompanyBO> posInfo = positionService.listPosition(id, page, limit);

        Map output = new TreeMap();
        output.put("title", ("第" + page + "页"));
        output.put("category", category);
        output.put("posInfo", posInfo);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    /**
     * 职位细节页 评论分页输出 （职位，部门，公司，分类，评论列表）
     *
     * @param httpSession
     * @param id
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = "/position/{id}/{page}")
    @ResponseBody
    public String getPosition(HttpSession httpSession, @PathVariable int id, @PathVariable int page,
                              @RequestParam(value = "limit", defaultValue = "12") int limit) {

        PositionEntity position = positionService.getPositionById(id);
        if (position == null) {
            this.errorDirect_404();
        }

        //当前用户信息
        UserEntity user = (UserEntity) httpSession.getAttribute("user");
        HREntity hr = (HREntity) httpSession.getAttribute("hr");

        //所属部门信息
        DepartmentEntity department = departmentService.getDepartment(position.getDepartmentId());
        //所属公司信息
        CompanyEntity company = companyService.getCompany(department.getCompanyId());
        //职位所属分类信息
        CategoryEntity category = categoryService.getCategory(position.getCategoryId());
        //分页评论信息
        PageInfo<UserCommentBO> comList = commentService.listComment(id, page, limit);

        if (!positionService.updateHits(id)) {
            this.errorDirect_404();
        }

        Map output = new TreeMap();
        output.put("position", position);
        output.put("department", department);
        output.put("company", company);
        output.put("category", category);
        output.put("comList", comList);
        if (user != null) {
            output.put("user", user);
        }
        if (hr != null) {
            output.put("hr", hr);
        }

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }


    /**
     * 职位申请 功能
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping(value = "/user/apply/{id}")
    public String apply(HttpServletRequest request, @PathVariable int id) {

        //当前用户
        UserEntity user = this.getUser(request);

        //当前用户简历
        ResumeEntity resume = resumeService.getResumeById(user.getUserId());
        //当前浏览职位
        PositionEntity position = positionService.getPositionById(id);

        if (user == null) {
            this.errorDirect_404();
        }
        if (resume == null) {
            return "redirect:/user/info?type=person";
        }
        boolean result = applicationService.applyPosition(resume.getResumeId(), position.getPositionId());
        if (!result) {
            this.errorDirect_404();
        }

        return "redirect:/user/success";
    }

    /**
     * 职位收藏与否
     * @param request
     * @param id
     * @return
     */
    @GetMapping(value = "/user/favorOrNot/{id}")
    @ResponseBody
    public int favorOrNot(HttpServletRequest request, @PathVariable int id) {

        //当前用户
        UserEntity user = this.getUser(request);
        //当前浏览职位
        PositionEntity position = positionService.getPositionById(id);

        if (!favorService.favorOrNot(user.getUserId(), id)) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 用户收藏职位
     * @param request
     * @param id
     * @return
     */
    @GetMapping(value = "/user/favor/{id}")
    @ResponseBody
    public String favor(HttpServletRequest request, @PathVariable int id) {

        //当前用户
        UserEntity user = this.getUser(request);
        //当前浏览职位
        PositionEntity position = positionService.getPositionById(id);

        if (user == null) {
            return "fail";
        }
        boolean result = favorService.favorPosition(user.getUserId(), id);
        if (!result) {
            return "fail";
        }
        return "success";
    }

    /**
     * 用户取消收藏职位
     * @param request
     * @param id
     * @return
     */
    @GetMapping(value = "/user/disfavor/{id}")
    @ResponseBody
    public String disfavor(HttpServletRequest request, @PathVariable int id) {

        //当前用户
        UserEntity user = this.getUser(request);
        //当前浏览职位
        PositionEntity position = positionService.getPositionById(id);

        if (user == null) {
            return "fail";
        }
        boolean result = favorService.disfavorPosition(user.getUserId(), id);
        if (!result) {
            return "fail";
        }
        return "success";
    }


    /**
     * 职位评论 功能
     *
     * @param id
     * @param type
     * @param content
     * @return
     */
    @PostMapping(value = "/user/comment")
    public String comment(HttpServletRequest request, @RequestParam("posId") int id,
                          @RequestParam("type") int type, @RequestParam("content") String content) {
        //当前用户
        UserEntity user = this.getUser(request);
//        UserEntity user = userService.getUser(5);

        if (user == null) {
            return this.errorDirect_404();
        }

        boolean result = commentService.commentPosition(type, content, user.getUserId(), id);
        if (!result) {
            return this.errorDirect_404();
        }
        return "redirect:/position/"+id;
    }

    /**
     * 用户个人信息 输出
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/user/info")
    @ResponseBody
    public String showInfo(HttpServletRequest request) {

        //用户个人信息
        UserEntity user = this.getUser(request);
//        UserEntity user = userService.getUser(5);
        if (user == null) {
            return "fail";
        }

        //个人简历信息
        ResumeEntity resume = resumeService.getResumeById(user.getUserId());
        //个人收藏职位
        List<FavorPositionBO> favorPosList = favorService.listFavorPosition(user.getUserId());
        //处理完成记录
        List<ApplicationPositionHRBO> applyPosList = applicationService.listApplyInfo(resume.getResumeId());
        //待处理记录
        List<ApplicationPositionHRBO> prePosList = applicationService.listApplyInfoPub(resume.getResumeId());
        //所有分类记录
        List<CategoryEntity> allCategoryList = categoryService.getAll();

        Map output = new TreeMap();
        output.put("user", user);
        output.put("resume", resume);
        output.put("favorPosList", favorPosList);
        output.put("applyPosList", applyPosList);
        output.put("prePosList",prePosList);
        output.put("allCategoryList",allCategoryList);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    /**
     * 用户简历信息 输出
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/user/resume")
    @ResponseBody
    public String showResume(HttpServletRequest request) {

        //用户个人信息
//        UserEntity user = this.getUser(request);
        UserEntity user = userService.getUser(1);

        ResumeEntity resume = resumeService.getResumeById(user.getUserId());

        Map output = new TreeMap();
        output.put("user", user);
        output.put("resume", resume);

        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    /**
     * 简历更新 功能
     *
     * @param request
     * @param ability
     * @param internship
     * @param workExperience
     * @param certificate
     * @param jobDesire
     * @return
     */
    @PostMapping(value = "/user/resume/update")
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
        return "redirect:/user/info?type=resume";
    }

    /**
     * 个人信息更新 功能
     *
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
    @PostMapping(value = "/user/info/update")
    @ResponseBody
    public String updateInfo(HttpServletRequest request,@RequestParam("password") String password,@RequestParam("nickname") String nickname,
                             @RequestParam("email") String email, @RequestParam("name") String name,@RequestParam("gender") int gender,
                             @RequestParam("birthYear") int birthYear,@RequestParam("graYear") int graYear,@RequestParam("province") String province,
                             @RequestParam("city") String city, @RequestParam("eduDegree") String eduDegree, @RequestParam("graduation") String graduation,
                             @RequestParam("major") String major,@RequestParam("dirDesire") int dirDesire) {

        int userId = this.getUserId(request);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setPassword(password);
        userEntity.setNickname(nickname);
        userEntity.setEmail(email);
        userEntity.setName(name);
        userEntity.setGender(gender);
        userEntity.setBirthYear(birthYear);
        userEntity.setGraYear(graYear);
        userEntity.setProvince(province);
        userEntity.setCity(city);
        userEntity.setEduDegree(eduDegree);
        userEntity.setGraduation(graduation);
        userEntity.setMajor(major);
        userEntity.setDirDesire(dirDesire);

        boolean result = userService.updateUser(userEntity);
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("user",userService.getUser(userId));
        if (!result) {
            return "fail";
        }
        return "success";
    }

    /**
     * 用户注销 功能
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/user/logout")
    public String userLogout(HttpServletRequest request) {
        // 清除session
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().removeAttribute(GlobalConst.LOGIN_SESSION_KEY);
        request.getSession().invalidate();

        return "redirect:/user/login";
    }


}
