package com.springboot.project.order.model.dao;

import com.springboot.project.order.model.bo.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDao extends JpaRepository<Orders, String> {
}
