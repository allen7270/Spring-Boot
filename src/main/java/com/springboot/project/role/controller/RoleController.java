package com.springboot.project.role.controller;

import com.springboot.common.util.RestfulBean;
import com.springboot.project.role.data.RoleData;
import com.springboot.project.role.model.mapper.RoleMapper;
import com.springboot.web.util.BaseController;
import com.springboot.web.util.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/project/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleMapper roleMapper;

    @Operation(summary = "取得角色名稱")
    @GetMapping("/{userName}")
    public ResponseEntity<RestfulBean<Object>> getRoleByUserName(@PathVariable String userName) {

        Optional<RoleData> role = this.roleMapper.getRoleByUserName(userName);
        if (role.isPresent()) {
            RoleData roleData = role.get();
            return success(roleData);
        } else {
            return error(ErrorCode.DATA_NOT_EXIST, "資料不存在或已註銷");
        }
    }
}
