package com.springboot.common.util;

import lombok.Data;

import java.util.Map;

@Data
public class ResultBean<T> implements RestfulBean<T>{
    private Integer status;
    private String error;
    private String message;
    private T object;
    private Map<String, String[]> errorDetail;
}
