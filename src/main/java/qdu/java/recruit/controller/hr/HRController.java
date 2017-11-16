package qdu.java.recruit.controller.hr;

import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.pojo.ApplicationPositionHRBO;
import qdu.java.recruit.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
  <p>
 private int hrId;
 private String hrMobile;
 private String hrPassword;
 private String hrName;
 private String hrEmail;
 private String description;
 private int departmentId;
 </p>
 */
@RestController
@Api(value = "HR接口",description = "HR接口")
public class HRController extends BaseController{

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    HRService hrService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    PositionService positionService;

    @Autowired
    CompanyService companyService;

    @Autowired
    DepartmentService departmentService;

    /**
     * 用户注册返回 0 -> 失败 1 -> 成功
     */

    @PostMapping("/hr/register/first")
    @ResponseBody
    public String checkCompanyCode(ModelMap map,
                                   @RequestParam String CompanyCode,
                                   HttpServletRequest request,
                                   DepartmentEntity departmentEntity) {
        CompanyEntity companyEntity = companyService.getCompany(CompanyCode);
        if(companyEntity == null) {
            throw  new RuntimeException("公司不存在");
        }
        else {
            List<DepartmentEntity> departmentEntities = departmentService.getDepartmentByCompany(
                    companyEntity.getCompanyId());
            map.put("departments",departmentEntities);
            request.setAttribute("department",departmentEntity.getDepartmentId());
            return hrDirect("hr/register/second");
        }
    }



    @PostMapping(value = "hr/register/second")
    @ResponseBody
    public int userRegister(@RequestParam HREntity user,
                            HttpServletRequest request){

            int deparmentId = (int) request.getAttribute("department");
            user.setDepartmentId(deparmentId);
            String password = user.getHrPassword();

            //验证mobile 和 password是否为空
            if (user.getHrMobile() == null || user.getHrPassword() == null) {
                return 0;
            }
            if (hrService.registerHR(user)) {
                return 1;
            }
            return 1;
        }



    /**
     * 用户登录
     *
     * @param httpSession
     * @param mobile
     * @param password
     * @return
     */
    @PostMapping(value = "hr/login")
    public int userLogin(HttpSession httpSession,
                         @RequestParam String mobile,
                         @RequestParam String password) {

        if (mobile == null || password == null) {
            return 0;
        }

        if (hrService.loginHR(mobile, password)) {

            httpSession.setAttribute("hr", hrService.getHRByMobile(mobile));
            return 1;
        }
        return 0;
    }



    /**
     * 用户个人信息 输出
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/hr/info")
    @ResponseBody
    public String showInfo(HttpServletRequest request) {

        //用户个人信息
//        UserEntity user = this.getUser(request);
        HREntity hr = this.getHR(request);
//        int id = hr.getHrId();
//        HREntity user = hrService.getHR(5);
        if (hr == null) {
            this.errorDirect_404();
            //其实应该返回的是401，或者403
        }
        int id = hr.getHrId();

        //收件箱
        List<ApplicationPositionHRBO> applyPosList = applicationService.listApplyInfoByHr(id);

        //创建的职位
        List<PositionEntity> positionEntities = positionService.listPositionByHr(id);

        Map output = new TreeMap();
        output.put("hr", hr);
        output.put("applyPosList", applyPosList);
        output.put("positions",positionEntities);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    /**
     *
     <p>
     private int hrId;
     private String hrMobile;
     private String hrPassword;
     private String hrName;
     private String hrEmail;
     private String description;
     private int departmentId;
     </p>
     * 个人信息更新 功能
     *
     * @param request
     * @param password
     * @param name
     * @param mobile
     * @param email
     * @param description
     * @param departmentId
     * @return
     */
    @PostMapping("/hr/info/update")
    public String updateInfo(HttpServletRequest request,
                             @RequestParam("hrMobile") String mobile,
                             @RequestParam("hrPassword") String password,
                             @RequestParam("hrName") String name,
                             @RequestParam("hrEmail") String email,
                             @RequestParam("description") String description,
                             @RequestParam("departmentId") int departmentId) {

        int hrId = this.getHRId(request);

        HREntity HREntity = new HREntity();
        HREntity.setHrId(hrId);
        HREntity.setHrPassword(password);
        HREntity.setHrName(name);
        HREntity.setHrEmail(email);
        HREntity.setDepartmentId(departmentId);

        if (!hrService.updateHR(HREntity)) {
            this.errorDirect_404();
        }
        return this.hrDirect("hr_info");
    }

    /**
     * 用户注销 功能
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/logout")
    public String userLogout(HttpServletRequest request) {
        // 清除session
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().removeAttribute(GlobalConst.LOGIN_SESSION_KEY);
        request.getSession().invalidate();

        return userDirect("logout_success");
    }





}
