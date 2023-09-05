package com.fish.rpm.service.impl;


import com.fish.rpm.dao.measure.BookInfoMapper;
import com.fish.rpm.service.BookInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Resource
    public BookInfoMapper bookInfoMapper;
    @Override
    public int queryBooKListCount() {
        bookInfoMapper.selectByPrimaryKey(1L);
        return 1;
    }

}

