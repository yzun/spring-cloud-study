package com.zun.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zun.CoreConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yuzheng
 * @create 2017-06-15 13:33
 */
@Component
public class MyFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    /**
     * 过滤类型
     * pre: 路由之间
     * routing: 路由之时
     * post: 路由之后
     * error: 发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 写判断逻辑，是否要过滤，true永远过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体业务逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        //此处可对请求方法进行刷选
        if (!request.getRequestURI().contains("login")) {
            // 先取Header中X-Token
            String accessToken = request.getHeader(CoreConstants.X_TOKEN);
            // 如果令牌为空, 再取Cookie中X-Token
            if (accessToken == null || accessToken.isEmpty()) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (CoreConstants.X_TOKEN.equals(cookie.getName())) {
                            accessToken = cookie.getValue();
                            break;
                        }
                    }
                }
            }
            // 如果令牌为空, 再取QueryString中X-Token
            if (accessToken == null || accessToken.isEmpty()) {
                accessToken = request.getParameter(CoreConstants.X_TOKEN);
            }

            if (accessToken == null || accessToken.isEmpty()) {
                logger.error("token is empty");
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                try {
                    context.getResponse().getWriter().write("token is empty");
                } catch (Exception e) {

                }
                return null;
            } else {
                try {
                    Jws<Claims> claims = Jwts.parser().setSigningKey(CoreConstants.SECRET).parseClaimsJws(accessToken);
                    // 获取用户Id
                    String accountId = claims.getBody().get("accountId").toString();
                    logger.debug("accountId = {}", accountId);
                    //根据accountId去获取相关信息, 本demo省略

                    // 过期时间
                    String expTime = claims.getBody().get("expTime").toString();
                    logger.debug("expTime = {}", expTime);
                    //对过期时间进行相关判断, 本demo省略
                    return null;
                } catch (Exception ex) {
                    if (logger.isErrorEnabled()) {
                        logger.error(ExceptionUtils.getStackTrace(ex));
                    }
                    // header中令牌不对, 可能被篡改
                    logger.error("token is error");
                    context.setSendZuulResponse(false);
                    context.setResponseStatusCode(401);
                    try {
                        context.getResponse().getWriter().write("token is error");
                    } catch (Exception e) {

                    }
                    return null;
                }
            }
        }
        logger.debug("token is ok");
        return null;
    }
}
