package com.fish.rpm.configuration;

import com.fish.rpm.dao.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class SingleSignConfig implements HandlerInterceptor {

    @Resource
    private DomainConfig domainConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求URL地址拦截::{}", domainConfig.getDomainName() + request.getRequestURI());
        HttpSession session = request.getSession();
        Object ucId = session.getAttribute(Constants.UC_ID);
        if (ucId == null) {
            try {
                response.sendRedirect(domainConfig.getDomainLogin());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
}