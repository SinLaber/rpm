package com.fish.rpm.dao.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogInfoUtil {
    /**
     * 项目标识
     */
    private int project = 1;

    /**
     * 请求业务方appKey
     */
    private String appKey;

    /**
     * 请求方法
     */
    private String method;

    /**
     * ucId系统号
     */
    private String ucId;

    /**
     * logId标识，一般为ucId_timestamp
     */
    private String logId;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 服务器IP
     */
    private String server;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 相应结果
     */
    private String response;

    /**
     * 发生时间
     */
    private long occur;

    /**
     * 消耗时间
     */
    private long timeCost;


}
