package com.springboot.project.book.model.mapper;

import com.springboot.common.util.QueryPageBean;
import com.springboot.project.book.data.BookData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    List<BookData> getAll(@Param("bean") QueryPageBean bean);
}
