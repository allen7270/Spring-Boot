package com.springboot.common.util;

import java.io.Serializable;
import java.util.Map;

public interface RestfulBean<T> extends Serializable {
    Integer getStatus();
    void setStatus(Integer status);

    String getError();
    void setError(String error);

    String getMessage();
    void setMessage(String message);

    T getObject();
    void setObject(T object);

    Map<String, String[]> getErrorDetail();
    void setErrorDetail(Map<String, String[]> errorDetail);
}
