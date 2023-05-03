package com.springboot.web.util;

import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController<T> {
    protected ResponseEntity<RestfulBean<T>> success(RestfulBean<T> result) {
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    protected ResponseEntity<RestfulBean<T>> success(T obj) {
        RestfulBean<T> result = new ResultBean<>();
        result.setStatus(200);
        result.setObject(obj);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
