#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.config.monitor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 发送钉钉告警
 *
 * @author luobo.cs@raycloud.com
 * @since 2020/5/3 8:01 下午
 */
@Slf4j
public class Global {
    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public static void sendError(Exception e) {
        StringBuilder stackBuilder = new StringBuilder();
        stackBuilder.append("[xxxx错误]").append(e instanceof NullPointerException ? "空指针异常" : e.getMessage());
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();
            stackBuilder.append(methodName).append(":").append(lineNumber).append("(").append(className).append(")").append("${symbol_escape}n");
        }
        sendOpeResult(stackBuilder.toString(),
                "https://oapi.dingtalk.com/robot/send?access_token=baaf36c98d2df1c2f956c02a3e4c507843d0441f6a0efbce4af1cdb1a1c71c86");
    }

    public static void sendOpeResult(final String result, final String DING_HOOK) {
        Global.scheduledExecutorService.submit(() -> {
            HttpClient client = new HttpClient();
            client.setConnectionTimeout(6000);
            client.setTimeout(6000);
            PostMethod method = new PostMethod(DING_HOOK);
            method.addRequestHeader("Content-Type", "application/json;charset=UTF-8");
            JSONObject object = new JSONObject();
            object.put("msgtype", "markdown");
            JSONObject linkObject = new JSONObject();
            linkObject.put("title", "容器告警");
            linkObject.put("text", result);
            object.put("markdown", linkObject);
            method.setRequestBody(object.toJSONString());
            try {
                final int i = client.executeMethod(method);
                log.info("发送钉钉消息:{} [{}]", result, i);
            } catch (IOException e) {
                log.info("发送钉钉消息错误:{} [{}]", result, e.getMessage());
            }
        });
    }


}
