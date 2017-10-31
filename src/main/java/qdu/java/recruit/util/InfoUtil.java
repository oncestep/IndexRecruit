package qdu.java.recruit.util;

import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InfoUtil {

    /**
     * 返回当前登录用户
     */
    public static User getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(null == session){
            return null;
        }
        return (User)session.getAttribute(GlobalConst.LOGIN_SESSION_KEY);
    }


}
