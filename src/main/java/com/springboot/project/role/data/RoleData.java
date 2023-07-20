package com.springboot.project.role.data;

import com.springboot.common.util.SuperBean;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RoleData extends SuperBean {
    @Schema(title = "角色名稱")
    private String roleName;
    @Schema(title = "使用者姓名")
    private String userName;
    private String uuid;

    @Data
    public static class updateRole {
        List<RoleData> objects;
    }
}