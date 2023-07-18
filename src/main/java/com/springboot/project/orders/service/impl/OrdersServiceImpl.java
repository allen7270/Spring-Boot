package com.springboot.project.orders.service.impl;

import com.springboot.common.data.BatchData;
import com.springboot.common.data.RecordData;
import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultBean;
import com.springboot.project.book.model.bo.Book;
import com.springboot.project.book.model.dao.BookDao;
import com.springboot.project.orders.model.bo.Orders;
import com.springboot.project.orders.model.dao.OrdersDao;
import com.springboot.project.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public RestfulBean<Object> addOrders(BatchData data, String userName) {
        RestfulBean<Object> restful = new ResultBean<>();
        List<RecordData> recordList = data.getObjects();
        List<String> uuids = recordList.stream().map(RecordData::getUuid).collect(Collectors.toList());
        List<Book> books = this.bookDao.findByIdInAndIsCancelFalse(uuids);
        List<Orders> orderList = new ArrayList<>();
        List<Orders> newOrderList = new ArrayList<>();
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
//                orderList.add(order);
                order = this.ordersDao.save(order);
                newOrderList.add(order);

                if (book.getCount().compareTo(BigDecimal.ONE) == -1) {
                    restful.setStatus(400);
                    restful.setMessage("庫存不足");
                    return restful;
                }

                book.setCount(book.getCount().subtract(BigDecimal.ONE));
                this.bookDao.save(book);
            }
//            this.ordersDao.saveAll(orderList);
        }
        restful.setStatus(200);
        restful.setMessage("新增成功");
        restful.setObject(newOrderList);
        return restful;
    }

    @Override
    public RestfulBean<Object> deleteOrders(BatchData data, String userName) {
        RestfulBean<Object> restful = new ResultBean<>();
        List<RecordData> recordList = data.getObjects();
        List<String> uuids = recordList.stream().map(RecordData::getUuid).collect(Collectors.toList());
        List<Orders> orders = this.ordersDao.findByIdInAndIsCancelFalse(uuids);
        if (orders.isEmpty()) {
            restful.setStatus(400);
            restful.setMessage("資料不存在");
            return restful;
        } else {
            orders.forEach(order -> {
                order.setIsCancel(true);
                order.setCancelDate(new Date());
                order.setCancelId(userName);

                Optional<Book> bookData = this.bookDao.findByIdAndIsCancelFalse(order.getBookId());
                if (bookData.isPresent()) {
                    Book book = bookData.get();
                    book.setCount(book.getCount().add(order.getCount()));
                    this.bookDao.save(book);
                }

            });
            this.ordersDao.saveAll(orders);
        }
        restful.setStatus(200);
        restful.setMessage("刪除成功");
        return restful;
    }

}
