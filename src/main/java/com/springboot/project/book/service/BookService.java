package com.springboot.project.book.service;

import com.springboot.common.data.BatchDeleteData;
import com.springboot.common.util.RestfulBean;

public interface BookService {
    RestfulBean<Object> deleteAddProof(BatchDeleteData data, String userName);
}
