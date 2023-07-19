package com.springboot.project.book.service.impl;

import com.springboot.common.data.BatchData;
import com.springboot.common.data.RecordData;
import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultBean;
import com.springboot.project.book.model.bo.Book;
import com.springboot.project.book.model.dao.BookDao;
import com.springboot.project.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Override
    public RestfulBean<Object> deleteBook(BatchData data, String userName) {
        RestfulBean<Object> restful = new ResultBean<>();
        List<RecordData> recordList = data.getObjects();
        List<String> uuids = recordList.stream().map(RecordData::getUuid).collect(Collectors.toList());
        List<Book> books = this.bookDao.findByIdInAndIsCancelFalse(uuids);
        if (books.isEmpty()) {
            restful.setStatus(400);
            restful.setMessage("資料不存在");
            return restful;
        } else {
            books.forEach(obj -> {
                obj.setIsCancel(true);
                obj.setCancelDate(new Date());
                obj.setCancelId(userName);
            });
            this.bookDao.saveAll(books);
        }
        restful.setStatus(200);
        restful.setMessage("刪除成功");
        return restful;
    }

    @Override
    public String add(BigDecimal value) {
        try {
            update(value);
            return "success";
        }catch (ObjectOptimisticLockingFailureException e){
            return "fail";
        }
    }

    public void update(BigDecimal value) {
        System.out.println(Thread.currentThread().getName() + "，addAccountMoney start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Optional<Book> bookData = this.bookDao.findById("222c32ac-454b-0f0e-add1-fb0599b5d9c4");
        Book book = bookData.get();
        System.out.println(Thread.currentThread().getName() + "，find balance : " + book.getCount());
        book.setCount(book.getCount().add(value));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        book = this.bookDao.save(book);

        System.out.println(Thread.currentThread().getName() + "， update balance end ,balance : " + book.getCount());

        System.out.println(Thread.currentThread().getName() + "，addAccountMoney sleep...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "，addAccountMoney end...");

    }
}
