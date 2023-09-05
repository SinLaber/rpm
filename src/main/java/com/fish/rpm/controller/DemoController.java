package com.fish.rpm.controller;

import com.fish.rpm.service.BookInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    public BookInfoService bookInfoService;

    @PostMapping ( "/queryReq")
    public int queryReq(){
        try {
            return bookInfoService.queryBooKListCount();
        }catch (Exception e){
            return 0;
        }
    }
}
