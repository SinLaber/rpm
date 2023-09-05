package com.fish.rpm.service.impl;


import com.fish.rpm.dao.domain.BookInfo;
import com.fish.rpm.dao.domain.BookInfoExample;
import com.fish.rpm.dao.measure.BookInfoMapper;
import com.fish.rpm.dao.util.ResultResp;
import com.fish.rpm.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    public BookInfoMapper bookInfoMapper;

    @Override
    public ResultResp demoQuery() {
        BookInfoExample bookInfoExample = new BookInfoExample();
        BookInfoExample.Criteria criteria = bookInfoExample.createCriteria();
        criteria.andAuthorIsNotNull();
        List<BookInfo> bookInfoList = bookInfoMapper.selectByExample(bookInfoExample);
        if(bookInfoList.isEmpty()){
            return ResultResp.respSucc(0);
        }
        return ResultResp.respSucc(bookInfoList, "query success");
    }

}

