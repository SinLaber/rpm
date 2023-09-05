package com.fish.rpm.dao.measure;

import com.fish.rpm.dao.domain.BookInfo;
import com.fish.rpm.dao.domain.BookInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoMapper {
    long countByExample(BookInfoExample example);

    int deleteByExample(BookInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BookInfo row);

    int insertSelective(BookInfo row);

    List<BookInfo> selectByExampleWithBLOBs(BookInfoExample example);

    List<BookInfo> selectByExample(BookInfoExample example);

    BookInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") BookInfo row, @Param("example") BookInfoExample example);

    int updateByExampleWithBLOBs(@Param("row") BookInfo row, @Param("example") BookInfoExample example);

    int updateByExample(@Param("row") BookInfo row, @Param("example") BookInfoExample example);

    int updateByPrimaryKeySelective(BookInfo row);

    int updateByPrimaryKeyWithBLOBs(BookInfo row);

    int updateByPrimaryKey(BookInfo row);
}