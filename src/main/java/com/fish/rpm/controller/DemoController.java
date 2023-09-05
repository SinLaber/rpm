package com.fish.rpm.controller;

import com.fish.rpm.dao.util.ResultResp;
import com.fish.rpm.service.DemoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    public DemoService demoService;

    @PostMapping ( "/queryReq")
    public ResultResp queryReq(){
        try {
            return demoService.demoQuery();
        }catch (Exception e){
            return ResultResp.respFailed(e.getMessage());
        }
    }
}
