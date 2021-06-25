#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author luobo.cs@raycloud.com
 * @since 2020/6/17 10:33 上午
 */
@Configuration
public class SpringUtils implements ApplicationContextAware {

    private static SpringUtils springUtils;

    @Bean
    public SpringUtils init() {
        return springUtils = new SpringUtils();
    }

    @Resource
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return springUtils.applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> name) {
        return springUtils.applicationContext.getBean(name);
    }
}
