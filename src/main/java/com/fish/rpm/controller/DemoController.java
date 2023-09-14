package com.fish.rpm.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fish.rpm.dao.util.Constants;
import com.fish.rpm.dao.util.OkHttp3Util;
import com.fish.rpm.dao.util.ResultResp;
import com.fish.rpm.service.BookInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Resource
    public BookInfoService bookInfoService;

    @PostMapping ( "/queryDemo")
    public ResultResp queryDemo(@RequestBody JSONObject param){
        try {
            logger.info("query params: {}", param);
            return bookInfoService.queryBookInfo(param.getInteger("id"));
        }catch (Exception e){
            return ResultResp.respFailed(e.getMessage());
        }
    }

    @PostMapping ( "/httpDemo")
    public ResultResp testHttp(@RequestParam String type, @RequestParam String tail) {
        try {
            String httpReq;
            String queryUrl = Constants.HTTPS_URL + tail;
            if(type.equals("post")) {
                httpReq = OkHttp3Util.post(queryUrl);
            }else {
                httpReq = OkHttp3Util.get(queryUrl);
            }
            logger.info("http response: {}", httpReq);
            return ResultResp.respSucc(JSON.parseObject(httpReq));
        }catch (Exception e){
            return ResultResp.respFailed(e.getMessage());
        }
    }
}
