package com.springboot.project.orders.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.springboot.common.data.BatchData;
import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultPage;
import com.springboot.project.book.model.bo.Book;
import com.springboot.project.book.model.dao.BookDao;
import com.springboot.project.orders.data.OrdersData;
import com.springboot.project.orders.data.OrdersData.*;
import com.springboot.project.orders.model.bo.Orders;
import com.springboot.project.orders.model.dao.OrdersDao;
import com.springboot.project.orders.model.mapper.OrdersMapper;
import com.springboot.project.orders.service.OrdersService;
import com.springboot.project.role.data.RoleData;
import com.springboot.project.role.model.mapper.RoleMapper;
import com.springboot.web.util.BaseController;
import com.springboot.web.util.ErrorCode;
import com.springboot.web.util.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Tag(name = "訂單", description = "orders")
@RestController
@RequestMapping("project/orders")
public class OrdersController extends BaseController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private RoleMapper roleMapper;

    @Operation(summary = "新增訂單")
    @PostMapping
    public ResponseEntity<RestfulBean<Object>> add(@RequestBody BatchData data) {
        try {
            RestfulBean<Object> result = this.ordersService.addOrders(data, this.userName());
            if (result.getStatus() == 200) {
                return success(result);
            } else {
                return error(result);
            }
        }  catch (RuntimeException e) {
            return error(ErrorCode.UNKNOW);
        }
    }

    @Operation(summary = "查詢訂單", description = "list")
    @GetMapping
    public ResponseEntity<ResultPage<List<OrdersData>>> getAll(queryOrdersData bean) {
        Optional<RoleData> roleData = this.roleMapper.getRoleByUserName(this.userName());
        RoleData role = roleData.get();
        bean.setOrderName(role.getRoleName().equals("admin") ? "" : this.userName());

        PageMethod.startPage(bean.getCurNum(), bean.getSize());
        Page<OrdersData> pageInfo = (Page<OrdersData>) this.ordersMapper.getAll(bean);
        ResultPage<List<OrdersData>> result = new ResultPage<>();
        result.setObject(pageInfo.getResult());
        result.setPage(PageUtil.getPageBean(pageInfo));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "查詢單筆訂單明細")
    @GetMapping("/{id}")
    public ResponseEntity<RestfulBean<Object>> getOne(@PathVariable String id) {
        Optional<OrdersData> OrdersData = this.ordersMapper.getById(id);
        if (OrdersData.isPresent()) {
            OrdersData orders = OrdersData.get();
            return success(orders);
        } else {
            return error(ErrorCode.DATA_NOT_EXIST, "資料不存在或已註銷");
        }
    }

    @Operation(summary = "批次刪除訂單")
    @DeleteMapping
    public ResponseEntity<RestfulBean<Object>> delete(@Valid @RequestBody BatchData data) {
        try {
            RestfulBean<Object> result = this.ordersService.deleteOrders(data, this.userName());
            if (result.getStatus() == 200) {
                return success(result);
            } else {
                return error(result);
            }
        }  catch (RuntimeException e) {
            return error(ErrorCode.UNKNOW);
        }
    }

    @Operation(summary = "單筆更新")
    @PutMapping("/{uuid}")
    public ResponseEntity<RestfulBean<Object>> update(@PathVariable String uuid, @Valid @RequestBody updateOrdersData bean) {
        try {
            Optional<Orders> op = this.ordersDao.findByIdAndIsCancelFalse(uuid);
            if (op.isPresent()) {
                Orders orders = op.get();

                BigDecimal adjustCount = bean.getCount().subtract(orders.getCount());

                Optional<Book> bookData = this.bookDao.findByIdAndIsCancelFalse(orders.getBookId());
                Book book = bookData.get();

                orders.setCount(bean.getCount());

                if (orders.getCount().compareTo(book.getCount().add(orders.getCount())) == 1) {
                    return error(ErrorCode.PARAMETER_OUT_OF_RANGE, "庫存不足");
                }

                this.ordersDao.save(orders);

                book.setCount(book.getCount().subtract(adjustCount));
                this.bookDao.save(book);

                return success("更新完成");
            } else {
                return error(ErrorCode.DATA_NOT_EXIST);
            }
        } catch (Exception e) {
            return error(ErrorCode.UNKNOW);
        }
    }

}
