package com.springboot.project.book.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.*;
import com.springboot.common.util.QueryPageBean;
import com.springboot.project.book.data.BookData;
import com.springboot.project.book.data.BookData.*;
import com.springboot.project.book.model.bo.Book;
import com.springboot.project.book.model.dao.BookDao;
import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultPage;
import com.springboot.project.book.model.mapper.BookMapper;
import com.springboot.web.util.BaseController;
import com.springboot.web.util.ErrorCode;
import com.springboot.web.util.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "書籍", description = "book")
@RestController
@RequestMapping("project/book")
public class BookController extends BaseController {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookMapper bookMapper;

    @Operation(summary = "新增書籍")
    @PostMapping
    public ResponseEntity<RestfulBean<Object>> add(@RequestBody AddBookData bean) {
        Book data = new Book();
        BeanUtils.copyProperties(bean, data);
        this.bookDao.save(data);
        return success(data);
    }

    @Operation(summary = "查詢書籍", description = "list")
    @GetMapping
    public ResponseEntity<ResultPage<List<BookData>>> getAll(QueryPageBean bean) {
        PageMethod.startPage(bean.getCurNum(), bean.getSize());
        Page<BookData> pageInfo = (Page<BookData>) this.bookMapper.getAll(bean);
        ResultPage<List<BookData>> result = new ResultPage<>();
        result.setObject(pageInfo.getResult());
        result.setPage(PageUtil.getPageBean(pageInfo));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "查詢單筆明細")
    @GetMapping("/{id}")
    public ResponseEntity<RestfulBean<Object>> getOne(@PathVariable String id) {
        Optional<Book> book = this.bookDao.findById(id);
        if (book.isPresent()) {
            Book bookData = book.get();
            return success(bookData);
        } else {
            return error(ErrorCode.DATA_NOT_EXIST, "資料不存在或已註銷");
        }
    }
}
