package com.springboot.web.util;

import com.springboot.common.util.PageBean;

public class PageUtil {
    public static PageBean getPageBean(com.github.pagehelper.Page<?> pageInfo) {
        PageBean pageBean = new PageBean();
        pageBean.setCurNum(pageInfo.getPageNum());
        pageBean.setSize(pageInfo.size());
        pageBean.setTotalPages(pageInfo.getPages());
        pageBean.setTotalElements(pageInfo.getTotal());
        return pageBean;
    }
}
