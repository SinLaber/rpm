package com.fish.rpm.service;


import com.fish.rpm.dao.util.ResultResp;
import org.springframework.stereotype.Service;

@Service
public interface DemoService {

    ResultResp demoQuery();
}
