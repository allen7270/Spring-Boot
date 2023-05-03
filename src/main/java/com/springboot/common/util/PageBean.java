package com.springboot.common.util;

import lombok.Data;

/**
 * @author chinning.tsao
 * @version 1.0
 * @since 2022/1/26
 */
@Data
public class PageBean {
    private int curNum;
    private int size;
    private long totalElements;
    private int totalPages;
}
