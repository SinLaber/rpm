package com.fish.rpm.controller;

import com.alibaba.fastjson.JSONObject;
import com.fish.rpm.dao.util.ResultResp;
import com.fish.rpm.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Resource
    public DemoService demoService;

    @PostMapping ( "/queryReq")
    public ResultResp queryReq(@RequestBody JSONObject params){
        try {
            logger.info("queryReq start:");
            return demoService.demoQuery(params.getInteger("id"));
        }catch (Exception e){
            return ResultResp.respFailed(e.getMessage());
        }
    }
}
