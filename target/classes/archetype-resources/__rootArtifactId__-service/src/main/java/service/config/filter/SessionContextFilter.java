#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.config.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ${package}.service.threadlocal.RequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenshun00@gmail.com
 * @since 2019/1/17
 */
@WebFilter(value = "/*")
public class SessionContextFilter implements Filter {

    static {
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            RequestContext.getContext().setRequest(servletRequest);
            RequestContext.getContext().setResponse(httpServletResponse);
            chain.doFilter(request, response);
        } finally {
            RequestContext.removeContext();
        }
    }

    @Override
    public void destroy() {

    }
}
