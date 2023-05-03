package com.springboot.web.util;

public interface QueryPage {
    Integer getCurNum();

    Integer getSize();

    String getSort();

    String getDir();
}
