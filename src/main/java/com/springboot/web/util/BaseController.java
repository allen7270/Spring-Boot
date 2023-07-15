package com.springboot.web.util;

import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

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

    protected ResponseEntity<RestfulBean<T>> error(ErrorCode error) {
        return error(error.errorStatus, error.errorCode, error.errorMessage, null, null);
    }

    protected ResponseEntity<RestfulBean<T>> error(ErrorCode error, String msg) {
        return error(error.errorStatus, error.errorCode, error.errorMessage, msg, null);
    }

    protected ResponseEntity<RestfulBean<T>> error(ErrorCode error, Map<String, String[]> errorDetail) {
        return error(error.errorStatus, error.errorCode, error.errorMessage, "", errorDetail);
    }

    protected ResponseEntity<RestfulBean<T>> error(ErrorCode error, String msg, Map<String, String[]> errorDetail) {
        return error(error.errorStatus, error.errorCode, error.errorMessage, msg, errorDetail);
    }

    private ResponseEntity<RestfulBean<T>> error(int httpStatus, String errorCode, String errorMsg, String msg, Map<String, String[]> errorDetail) {
        RestfulBean<T> result = new ResultBean<>();
        result.setStatus(httpStatus);
        result.setError(errorCode);
        result.setMessage(StringUtils.isEmpty(msg) ? errorMsg : msg);
        result.setErrorDetail(errorDetail);
        return new ResponseEntity<>(result, HttpStatus.valueOf(httpStatus));
    }

    protected ResponseEntity<RestfulBean<T>> error(RestfulBean<T> result) {
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    protected String userName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
