package com.springboot.project.role.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.springboot.common.data.RecordData;
import com.springboot.common.util.QueryPageBean;
import com.springboot.common.util.RestfulBean;
import com.springboot.common.util.ResultPage;
import com.springboot.project.role.data.RoleData;
import com.springboot.project.role.data.RoleData.*;
import com.springboot.project.role.model.bo.Role;
import com.springboot.project.role.model.dao.RoleDao;
import com.springboot.project.role.model.mapper.RoleMapper;
import com.springboot.project.user.model.bo.User;
import com.springboot.project.user.model.dao.UserDao;
import com.springboot.web.util.BaseController;
import com.springboot.web.util.ErrorCode;
import com.springboot.web.util.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

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

    @Operation(summary = "取得角色名稱")
    @GetMapping("/getRoleName")
    public ResponseEntity<RestfulBean<Object>> getRoleName() {
        String userName = this.userName();
        Optional<RoleData> role = this.roleMapper.getRoleByUserName(userName);
        if (role.isPresent()) {
            RoleData roleData = role.get();
            return success(roleData);
        } else {
            return error(ErrorCode.DATA_NOT_EXIST, "資料不存在或已註銷");
        }
    }

    @Operation(summary = "查詢角色資料", description = "list")
    @GetMapping
    public ResponseEntity<ResultPage<List<RoleData>>> getAll(QueryPageBean bean) {
        PageMethod.startPage(bean.getCurNum(), bean.getSize());
        Page<RoleData> pageInfo = (Page<RoleData>) this.roleMapper.getAll();
        ResultPage<List<RoleData>> result = new ResultPage<>();
        result.setObject(pageInfo.getResult());
        result.setPage(PageUtil.getPageBean(pageInfo));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "更新")
    @PutMapping
    public ResponseEntity<RestfulBean<Object>> update(@Valid @RequestBody updateRole bean) {
        try {
            List<User> userList = new ArrayList<>();
            bean.getObjects().forEach(obj -> {
                User user = this.userDao.findById(obj.getUuid()).get();
                Role role = this.roleDao.findByRoleName(obj.getRoleName());
                user.setRoleId(role.getId());
                userList.add(user);
            });
            this.userDao.saveAll(userList);
            return success("更新成功");
        } catch (Exception e) {
            return error(ErrorCode.UNKNOW);
        }
    }
}
