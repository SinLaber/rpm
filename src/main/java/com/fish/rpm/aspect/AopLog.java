package com.fish.rpm.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fish.rpm.dao.util.Constants;
import com.fish.rpm.dao.util.LogInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @author Bin
 * @date 2023/1/6
 */
@Slf4j
@Aspect
@Component
public class AopLog {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<String> APPKEY_LIST = Collections.singletonList("admin");

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.fish.rpm.controller.*Controller.*(..))")
    public void aopWebLog() {

    }

    @Before("aopWebLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        checkAppKey(request.getHeader("appKey"));
        startTime.set(System.currentTimeMillis());
        MDC.put("appKey", request.getHeader("appKey"));
        MDC.put("method", request.getMethod());
        MDC.put("url", request.getRequestURI());
        MDC.put("server", request.getRemoteHost());
        //注意，这里尽可以获取到url中的参数，后续可修改根据get/post获取参数
        MDC.put("params", request.getQueryString());
    }

    @AfterReturning(returning = "ret", pointcut = "aopWebLog()")
    public void doAfterReturning(Object ret) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("data");
        MDC.put("response", JSON.toJSONString(ret, filter));
        MDC.put("timeCost", String.valueOf(System.currentTimeMillis() - startTime.get()));
        LogInfoUtil logInfo = LogInfoUtil.builder()
                .appKey(MDC.get("appKey"))
                .method(MDC.get("method"))
                .ucId(MDC.get(Constants.UC_ID))
                .logId(MDC.get(Constants.LOG_ID))
                .url(MDC.get("url"))
                .server(MDC.get("server"))
                .params(MDC.get("params"))
                .response(MDC.get("response"))
                .occur(startTime.get())
                .timeCost(Long.parseLong(MDC.get("timeCost")))
                .build();
        logger.info("LogInfo::{}", JSON.toJSONString(logInfo));
    }

    @AfterThrowing(throwing = "ex", pointcut = "aopWebLog()")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
        logger.info("执行异常：", ex);
    }

    public void checkAppKey(String appKey) {
        if(!APPKEY_LIST.contains(appKey)) {
            throw new IllegalArgumentException("appKey Failed!");
        }
    }
}

