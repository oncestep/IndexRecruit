package qdu.java.recruit.controller.hr;

import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PositionController extends BaseController{
    /**
     * postion part
     * private int positionId;
     private String title; *
     private String requirement; *
     private int quantity; *
     private String workCity; *
     private int salaryUp; *
     private int salaryDown; *
     private Date releaseDate;
     private Date validDate;
     private int statePub; *
     private int hits;
     private int categoryId;
     private int departmentId;
     private int hrIdPub;
     */

    @Autowired
    PositionService positionService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CommentService commentService;

    @Autowired
    CompanyService companyService;

    /**
     * 职位信息表
     * @param request
     * @return
     */
    @GetMapping("/hr{id}/position/{page}")
    @ResponseBody
    public String showPostionInfo(HttpServletRequest request,
                                  @PathVariable int id,
                                  @PathVariable int page,
                                  @RequestParam(value = "limit", defaultValue = "12") int limit){

        HREntity hr = this.getHR(request);

        if (hr == null) {
            this.errorDirect_404();
            //其实应该返回的是401，或者403
        }
        id = hr.getHrId();
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;


        PageInfo<PositionEntity> positionEntities = positionService.listPositionByHr(id,page,limit);

        Map output = new TreeMap();
        output.put("title", ("第" + page + "页"));
        output.put("hr", hr);
        output.put("positions",positionEntities);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }


    /**
     * 职位详情
     * @param request
     * @param id
     * @return
     */
    @PostMapping(value = "/hr/position/{id}")
    @ResponseBody
    public String getPosition(HttpServletRequest request, @PathVariable int id) {

        PositionEntity position = valide(request,id);

        //所属部门信息
        DepartmentEntity department = departmentService.getDepartment(position.getDepartmentId());
        //所属公司信息
        CompanyEntity company = companyService.getCompany(department.getCompanyId());
        //职位所属分类信息
        CategoryEntity category = categoryService.getCategory(position.getCategoryId());
        //分页评论信息

        if (!positionService.updateHits(id)) {
            this.errorDirect_404();
        }

        Map output = new TreeMap();
        output.put("position", position);
        output.put("department", department);
        output.put("company", company);
        output.put("category", category);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    @PostMapping("/position{id}/delete")
    public int deletePosition(HttpServletRequest request,@PathVariable int id) {
        valide(request,id);
        return positionService.deletePosition(id);

    }

    /**
     * private String title; *
     private String requirement; *
     private int quantity; *
     private String workCity; *
     private int salaryUp; *
     private int salaryDown; *
     private Date validDate;
     * @param request
     * @param id
     * @return
     */
    @PostMapping("/position{id}/update")
    public int updatePosition(HttpServletRequest request,
                              @PathVariable int id,
                              @RequestParam String title,
                              @RequestParam String requirement,
                              @RequestParam int quantity,
                              @RequestParam String workCity,
                              @RequestParam int salaryDown,
                              @RequestParam Date validDate
                              ) {
        PositionEntity positionEntity = valide(request,id);
        positionEntity.setTitle(title);
        positionEntity.setRequirement(requirement);
        positionEntity.setQuantity(quantity);
        positionEntity.setValidDate(validDate);
        positionEntity.setSalaryDown(salaryDown);
        positionEntity.setWorkCity(workCity);

        return positionService.updatePosition(positionEntity);
    }

    /**
     *职位下架,即不会在出现
     * @param request
     * @param id
     * @return
     */
    @PostMapping("/position{id}/withdraw")
    public int withdrawPosition(HttpServletRequest request,
                                @PathVariable int id) {
        PositionEntity positionEntity = valide(request,id);
        positionEntity.setStatePub(0);//0为下架
        return positionService.updatePosition(positionEntity);
    }

    @PostMapping("hr{id}/position/create")
    public int createPosition(ModelMap modelMap, HttpServletRequest request, @PathVariable int id, PositionEntity positionEntity) {
        HREntity hr = this.getHR(request);
        List<CategoryEntity> categoryEntities = categoryService.getAll();
        if(hr == null) {
            this.errorDirect_404();
        }
            id = hr.getHrId();
            modelMap.put("categoryEntities",categoryEntities);
            positionEntity.setReleaseDate(new Date());
            positionEntity.setStatePub(1);
            return positionService.savePosition(positionEntity);


    }

    /**
     * 权限验证
     * @param request
     * @param id
     * @return
     */
    public PositionEntity valide(HttpServletRequest request,int id) {
        HREntity hr = this.getHR(request);
        PositionEntity position = positionService.getPositionById(id);
        if(hr == null || position == null) {
            this.errorDirect_404();
            return null;
        }
        else {
            int hrid = hr.getHrId();
            if(position.getHrIdPub() != hrid) {
                this.errorDirect_404();
                return null;
            }
            return position;
        }

    }

}
