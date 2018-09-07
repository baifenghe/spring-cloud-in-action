package com.github.baifenghe.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Api网关过滤器
 *
 * @author bfh
 * @since 1.0.0
 */
@Component
public class AccessFilter extends ZuulFilter {


    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 过滤器执行的生命周期
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 指定过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 表示是否要执行该filter
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(">> send {} request to  ==> {}", request.getMethod(), request.getRequestURL());

        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)) {
            log.warn(">> access token is empty");
            ctx.setSendZuulResponse(false);
            // ctx.getResponse().setContentType("application/json;charset=UTF-8");
            ctx.setResponseBody("access token is empty");
            return null;
        }

        return null;
    }
}
