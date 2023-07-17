package com.springboot.project.orders.service;

import com.springboot.common.data.BatchData;
import com.springboot.common.util.RestfulBean;

public interface OrdersService {
    RestfulBean<Object> addOrders(BatchData data, String userName);
    RestfulBean<Object> deleteOrders(BatchData data, String userName);
}
