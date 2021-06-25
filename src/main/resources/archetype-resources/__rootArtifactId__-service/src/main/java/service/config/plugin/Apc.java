#set( $symbol_pound = '#' )
        #set( $symbol_dollar = '$' )
        #set( $symbol_escape = '\' )
        package ${package}.service.config.plugin;

import com.alibaba.druid.pool.DruidPooledPreparedStatement;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * @author chenshun00@gmail.com
 * @since 2019/4/19
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
@Component
@Profile("dev")
public class Apc implements Interceptor {

    public static final Logger logger = LoggerFactory.getLogger(Apc.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        PreparedStatement preparedStatement;
        if (args[0] instanceof DruidPooledPreparedStatement) {
            preparedStatement = (DruidPooledPreparedStatement) args[0];
        } else {
            Field field = args[0].getClass().getSuperclass().getDeclaredField("h");
            field.setAccessible(true);
            PreparedStatementLogger preparedStatementLogger = (PreparedStatementLogger) field.get(args[0]);
            preparedStatement = preparedStatementLogger.getPreparedStatement();
        }
        int len = 39 + Integer.toHexString(preparedStatement.hashCode()).length();
        Object result = invocation.proceed();
        int size;
        if (result instanceof List) {
            size = ((List) result).size();
        } else {
            size = (int) result;
        }
        logger.info("执行的sql语句:[{}] 受影响行数 [{}]", preparedStatement.toString().substring(len).trim().replaceAll("\n", "")
                .replaceAll("\\s+", " "), size);
        return result;
    }
}