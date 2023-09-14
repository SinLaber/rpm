package com.fish.rpm.service.impl;


import com.fish.rpm.dao.domain.BookInfo;
import com.fish.rpm.dao.domain.BookInfoExample;
import com.fish.rpm.dao.measure.BookInfoMapper;
import com.fish.rpm.dao.util.ResultResp;
import com.fish.rpm.service.BookInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService {

    private static final Logger logger = LoggerFactory.getLogger(BookInfoServiceImpl.class);

    @Resource
    public BookInfoMapper bookInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultResp queryBookInfo(Integer id) {
        logger.info("query param:{}", id);
        BookInfoExample bookInfoExample = new BookInfoExample();
        BookInfoExample.Criteria criteria = bookInfoExample.createCriteria();
        criteria.andIdEqualTo(id.longValue());
        List<BookInfo> bookInfoList = bookInfoMapper.selectByExample(bookInfoExample);
        if(bookInfoList.isEmpty()){
            return ResultResp.respSucc(0);
        }
        return ResultResp.respSucc(bookInfoList, "query success");
    }

}

