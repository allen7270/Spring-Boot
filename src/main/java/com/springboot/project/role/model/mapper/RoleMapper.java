package com.springboot.project.role.model.mapper;

import com.springboot.project.role.data.RoleData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleMapper {
    Optional<RoleData> getRoleByUserName(@Param("userName") String userName);
    List<RoleData> getAll();
}
