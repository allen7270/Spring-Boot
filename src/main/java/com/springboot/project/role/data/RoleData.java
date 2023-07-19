package com.springboot.project.role.data;

import com.springboot.common.util.SuperBean;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleData extends SuperBean {
    @Schema(title = "角色名稱")
    private String roleName;
}