package it.pkg.service.threadlocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: threadLocal缓存
 * 一定要回收
 * User: ouzhouyou@raycloud.com
 * Date: 16/6/8
 * Time: 上午10:52
 * Version: 1.0
 */
public class RequestContext extends ConcurrentHashMap<String, Object> {

    private static final long serialVersionUID = -2485278422674945052L;

    private RequestContext() {
    }

    private static final ThreadLocal<RequestContext> LOCAL = ThreadLocal.withInitial(RequestContext::new);

    public static RequestContext getContext() {
        return LOCAL.get();
    }

    public static void removeContext() {
        LOCAL.remove();
    }

    public void setRequest(HttpServletRequest servletRequest) {
        this.put("request", servletRequest);
    }

    public void setResponse(HttpServletResponse httpServletResponse) {
        this.put("response", httpServletResponse);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) this.get("request");
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) this.get("response");
    }
}
