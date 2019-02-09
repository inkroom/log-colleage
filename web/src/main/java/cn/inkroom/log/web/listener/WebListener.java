package cn.inkroom.log.web.listener;

import org.springframework.core.env.AbstractEnvironment;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author 墨盒
 * @date 19-2-9
 */
public class WebListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {


        String profile = servletContextEvent.getServletContext().getInitParameter("profile");
        if (profile != null) {
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profile);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
