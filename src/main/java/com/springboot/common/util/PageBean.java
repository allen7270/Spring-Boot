package com.springboot.common.util;

import lombok.Data;

@Data
public class PageBean {
    private int curNum;
    private int size;
    private long totalElements;
    private int totalPages;
}
