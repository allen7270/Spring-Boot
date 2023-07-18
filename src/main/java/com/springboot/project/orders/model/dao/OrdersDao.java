package com.springboot.project.orders.model.dao;

import com.springboot.project.orders.model.bo.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersDao extends JpaRepository<Orders, String> {
    Optional<Orders> findById(String id);
    List<Orders> findByIdInAndIsCancelFalse(List<String> uuidList);
    Optional<Orders> findByIdAndIsCancelFalse(String uuid);
    List<Orders> findByIdInAndOrderNameAndIsCancelFalse(List<String> id, String orderName);
}
