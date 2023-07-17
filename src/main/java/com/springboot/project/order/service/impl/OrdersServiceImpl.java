package com.springboot.project.order.service.impl;

import com.springboot.common.data.BatchData;
import com.springboot.common.data.RecordData;
import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultBean;
import com.springboot.project.book.model.bo.Book;
import com.springboot.project.book.model.dao.BookDao;
import com.springboot.project.order.model.bo.Orders;
import com.springboot.project.order.model.dao.OrdersDao;
import com.springboot.project.order.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao orderDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public RestfulBean<Object> addOrder(BatchData data, String userName) {
        RestfulBean<Object> restful = new ResultBean<>();
        List<RecordData> recordList = data.getObjects();
        List<String> uuids = recordList.stream().map(RecordData::getUuid).collect(Collectors.toList());
        List<Book> books = this.bookDao.findByIdInAndIsCancelFalse(uuids);
        List<Orders> orderList = new ArrayList<>();
        if (books.isEmpty()) {
            restful.setStatus(400);
            restful.setMessage("資料不存在");
            return restful;
        } else {
            for (Book book : books) {
                Orders order = new Orders();
                order.setBookId(book.getId());
                order.setOrderName(userName);
                order.setOrderDate(new Date());
                order.setCount(BigDecimal.ONE);
                order.setPrice(book.getPrice());
                orderList.add(order);

                if (book.getCount().compareTo(BigDecimal.ONE) == -1) {
                    restful.setStatus(400);
                    restful.setMessage("庫存不足");
                    return restful;
                }

                book.setCount(book.getCount().subtract(BigDecimal.ONE));
                this.bookDao.save(book);
            }
            this.orderDao.saveAll(orderList);
        }
        restful.setStatus(200);
        restful.setMessage("新增成功");
        return restful;
    }
}
