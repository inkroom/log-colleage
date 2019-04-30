package cn.inkroom.log.web.interceptors;

import cn.inkroom.log.web.config.Asserts;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 墨盒
 * @date 19-4-30
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute(Asserts.AUTH_SESSION_KEY) == null) {
            response.sendRedirect(request.getContextPath() + "/auth/index");
            return false;
        }
        return true;
    }
}
