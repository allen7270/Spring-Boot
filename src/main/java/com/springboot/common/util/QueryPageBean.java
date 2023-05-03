package com.springboot.common.util;

import com.springboot.web.util.QueryPage;
import lombok.Data;

@Data
public class QueryPageBean implements QueryPage {
    private Integer curNum;
    private Integer size;
    private String sort;
    private String dir;
    private String uuid;
}
