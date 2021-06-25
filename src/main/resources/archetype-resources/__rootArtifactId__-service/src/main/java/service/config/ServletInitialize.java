#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;

/**
 * @author chenshun00@gmail.com
 * @since 2019/1/21
 */
@Service
public class ServletInitialize implements ServletContextInitializer {

    // TODO: 2020/6/4 如果不需要直接删除该类即可
    @Value("${symbol_dollar}{appkey}")
    private String appkey;
    @Value("${symbol_dollar}{spring.application.name}")
    private String name;

    @Override
    public void onStartup(ServletContext servletContext) {
        servletContext.setInitParameter("secret_app_key", name);
        servletContext.setInitParameter("groupdataid", appkey);
        // TODO: 2020/6/2 初始化Servlet的一些数据
        //servletContext.setInitParameter("call_back_class_name", SecretCallback.class.getName());
    }
}
