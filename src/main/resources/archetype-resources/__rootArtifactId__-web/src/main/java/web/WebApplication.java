#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web;

import org.apache.commons.lang.SystemUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan("${package}")
@SpringBootApplication
@MapperScan("${package}")
@EnableScheduling
public class WebApplication {
    static {
        if (!SystemUtils.IS_OS_LINUX) {
            System.setProperty("spring.profiles.active", "dev");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
