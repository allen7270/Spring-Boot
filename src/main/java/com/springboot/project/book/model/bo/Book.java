package com.springboot.project.book.model.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity // create table
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2", parameters = {@org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    private String id;
    @Column(name = "book")
    private String book;
    @Column(name = "author")
    private String author;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "count")
    private BigDecimal count;
    @Column(name = "language")
    private String language;
    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "isCancel", nullable = false)
    private Boolean isCancel = false;
    @Column(name = "cancelId", length = 50)
    private String cancelId;
    @Column(name = "cancelDate")
    private Date cancelDate;
}
