package com.fish.rpm.configuration;

import com.fish.rpm.dao.util.Constants;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Component
public class LogConfig implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        try {
            MDC.clear();
            String logId = request.getHeader(Constants.LOG_ID);
            String ucId = request.getHeader(Constants.UC_ID);
            MDC.put(Constants.LOG_ID, StringUtils.isNotBlank(logId) ? logId : "wuzhong_" + UUID.randomUUID().toString().replace("-", ""));
            MDC.put(Constants.UC_ID, StringUtils.isBlank(ucId) ? (StringUtils.isNotBlank(logId) ? logId.split("_")[0] : "未知"): ucId);
            MDC.put(Constants.OCCUR, String.valueOf(System.currentTimeMillis()));
        } catch (Exception e) {
            log.error("logId accessInterceptor error!", e);
        }
        return true;
    }
}
