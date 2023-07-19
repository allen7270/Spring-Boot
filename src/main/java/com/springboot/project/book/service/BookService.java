package com.springboot.project.book.service;

import com.springboot.common.data.BatchData;
import com.springboot.common.util.RestfulBean;

import java.math.BigDecimal;

public interface BookService {
    RestfulBean<Object> deleteBook(BatchData data, String userName);
    String add(BigDecimal value);
}
