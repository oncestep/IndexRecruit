package qdu.java.recruit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import qdu.java.recruit.constant.GlobalConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {


    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
//        addInterceptor.excludePathPatterns("/user/login");
//        addInterceptor.excludePathPatterns("/register");

        // 拦截配置
        addInterceptor.addPathPatterns("");
        addInterceptor.addPathPatterns("/");
//        addInterceptor.addPathPatterns("/search/**");
//        addInterceptor.addPathPatterns("/category/**");
//        addInterceptor.addPathPatterns("/position/**");
        addInterceptor.addPathPatterns("/user/info");
        addInterceptor.addPathPatterns("/user/resume");

        addInterceptor.addPathPatterns("#");
        addInterceptor.addPathPatterns("/user/info#");
        addInterceptor.addPathPatterns("/user/resume#");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            System.out.println("拦截");
            HttpSession session = request.getSession();
            if (session.getAttribute(GlobalConst.SESSION_KEY) != null) {
                return true;
            }
            // 跳转登录
            String url = "/user/login";
            response.sendRedirect(url);
            return false;
        }

        @Override
        public void afterCompletion(
                HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
                throws Exception {
            System.out.println("拦截2");
            HttpSession session = request.getSession();
            if (session.getAttribute(GlobalConst.SESSION_KEY) == null) {
                // 跳转登录
                String url = "/user/login";
                response.sendRedirect(url);
            }
        }

    }
}
