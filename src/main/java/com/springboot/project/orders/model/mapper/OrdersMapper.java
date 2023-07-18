package com.springboot.project.orders.model.mapper;

import com.springboot.common.util.QueryPageBean;
import com.springboot.project.orders.data.OrdersData;
import com.springboot.project.orders.model.bo.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersMapper {
    List<OrdersData> getAll(@Param("bean") QueryPageBean bean);
    Optional<OrdersData> getById(@Param("id") String id);
    Orders getByBookIdAndOrderName(@Param("id") String id, @Param("orderName") String orderName);
}
