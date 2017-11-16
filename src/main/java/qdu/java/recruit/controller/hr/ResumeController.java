package qdu.java.recruit.controller.hr;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.HREntity;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ResumeController extends BaseController{


    @PostMapping("/hr/applyInfo")
    public String ResumeInfo(HttpServletRequest request) {
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        return null;
    }


}
