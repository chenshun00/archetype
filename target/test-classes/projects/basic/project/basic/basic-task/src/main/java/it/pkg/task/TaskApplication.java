package it.pkg.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author luobo.cs@raycloud.com
 * @since 2020/4/9 2:02 下午
 */
@ServletComponentScan("it.pkg")
@SpringBootApplication
@MapperScan("it.pkg")
@EnableScheduling
public class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class);
    }
}
