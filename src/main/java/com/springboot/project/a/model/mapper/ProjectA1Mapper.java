package com.springboot.project.a.model.mapper;

import com.springboot.common.util.QueryPageBean;
import com.springboot.project.a.data.ProjectA1Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectA1Mapper {
    List<ProjectA1Data> getAll(@Param("bean") QueryPageBean bean);
}
