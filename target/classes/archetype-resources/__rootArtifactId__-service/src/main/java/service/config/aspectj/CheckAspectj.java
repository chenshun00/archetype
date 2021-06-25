#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.config.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author luobo.cs@raycloud.com
 * @since 2021/2/3 11:37 上午
 */
@Aspect
@Component
public class CheckAspectj {

    @Pointcut("execution(public * ${package}.web.view..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof AbstractRequest) {
                    AbstractRequest baseRequest = (AbstractRequest) arg;
                    baseRequest.check();
                }
            }
        }
    }
}
