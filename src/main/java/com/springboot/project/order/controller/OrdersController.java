package com.springboot.project.order.controller;

import com.springboot.common.data.BatchData;
import com.springboot.common.util.RestfulBean;
import com.springboot.project.order.service.OrdersService;
import com.springboot.web.util.BaseController;
import com.springboot.web.util.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "訂單", description = "orders")
@RestController
@RequestMapping("project/orders")
public class OrdersController extends BaseController {
    @Autowired
    private OrdersService orderService;

    @Operation(summary = "新增訂單")
    @PostMapping
    public ResponseEntity<RestfulBean<Object>> add(@RequestBody BatchData data) {
        try {
            RestfulBean<Object> result = this.orderService.addOrder(data, this.userName());
            if (result.getStatus() == 200) {
                return success(result);
            } else {
                return error(result);
            }
        }  catch (RuntimeException e) {
            return error(ErrorCode.UNKNOW);
        }
    }

}
