package com.springboot.project.book.service.impl;

import com.springboot.common.data.BatchData;
import com.springboot.common.data.RecordData;
import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultBean;
import com.springboot.project.book.model.bo.Book;
import com.springboot.project.book.model.dao.BookDao;
import com.springboot.project.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
}
