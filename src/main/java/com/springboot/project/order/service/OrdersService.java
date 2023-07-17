package com.springboot.project.order.service;

import com.springboot.common.data.BatchData;
import com.springboot.common.util.RestfulBean;

public interface OrdersService {
    RestfulBean<Object> addOrder(BatchData data, String userName);
}
