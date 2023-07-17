package com.springboot.project.orders.model.mapper;

import com.springboot.common.util.QueryPageBean;
import com.springboot.project.orders.data.OrdersData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersMapper {
    List<OrdersData> getAll(@Param("bean") QueryPageBean bean);
    Optional<OrdersData> getById(@Param("id") String id);
}
