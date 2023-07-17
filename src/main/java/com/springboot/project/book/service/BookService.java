package com.springboot.project.book.service;

import com.springboot.common.data.BatchData;
import com.springboot.common.util.RestfulBean;

public interface BookService {
    RestfulBean<Object> deleteBook(BatchData data, String userName);
}
