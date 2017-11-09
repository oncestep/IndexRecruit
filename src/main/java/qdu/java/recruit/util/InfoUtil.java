package qdu.java.recruit.util;

import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InfoUtil {

    /**
     * 返回当前登录用户
     */
    public static UserEntity getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(null == session){
            return null;
        }
        return (UserEntity)session.getAttribute(GlobalConst.LOGIN_SESSION_KEY);
    }


    public static HREntity getLoginHR(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(null == session){
            return null;
        }
        return (HREntity) session.getAttribute(GlobalConst.LOGIN_SESSION_KEY);
    }
}
