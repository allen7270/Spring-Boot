package com.springboot.project.a.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.*;
import com.springboot.common.util.QueryPageBean;
import com.springboot.project.a.data.ProjectA1Data;
import com.springboot.project.a.model.bo.ProjectA1;
import com.springboot.project.a.model.dao.ProjectA1Dao;
import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultPage;
import com.springboot.project.a.model.mapper.ProjectA1Mapper;
import com.springboot.web.util.BaseController;
import com.springboot.web.util.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project/a1")
public class ProjectA1Controller extends BaseController {
    @Autowired
    private ProjectA1Dao projectA1Dao;
    @Autowired
    private ProjectA1Mapper projectA1Mapper;

    @GetMapping
    public ResponseEntity<ResultPage<List<ProjectA1Data>>> getAll(QueryPageBean bean) {
        PageMethod.startPage(bean.getCurNum(), bean.getSize());
        Page<ProjectA1Data> pageInfo = (Page<ProjectA1Data>) this.projectA1Mapper.getAll(bean);
        ResultPage<List<ProjectA1Data>> result = new ResultPage<>();
        result.setObject(pageInfo.getResult());
        result.setPage(PageUtil.getPageBean(pageInfo));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestfulBean<Object>> add(@RequestBody ProjectA1Data bean) {
        ProjectA1 data = new ProjectA1();
        BeanUtils.copyProperties(bean, data);
        this.projectA1Dao.save(data);
        return success(data);
    }
}
