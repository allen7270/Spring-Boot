package com.springboot.project.order.model.bo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity // create table
@Table(name = "ORDERS")
public class Orders {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2", parameters = {@org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    private String id;
    @Column(name = "bookId")
    private String bookId;
    @Column(name = "orderName")
    private String orderName;
    @Column(name = "orderDate")
    private Date orderDate;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "count")
    private BigDecimal count;

    @Column(name = "isCancel", nullable = false)
    private Boolean isCancel = false;
    @Column(name = "cancelId", length = 50)
    private String cancelId;
    @Column(name = "cancelDate")
    private Date cancelDate;
}
