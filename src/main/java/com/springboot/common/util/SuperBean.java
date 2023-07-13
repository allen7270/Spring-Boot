package com.springboot.common.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class SuperBean {
    @Schema(title = "資料唯一值", accessMode = Schema.AccessMode.READ_ONLY)
    private String uuid = "";
    @Schema(title = "建檔人員", accessMode = Schema.AccessMode.READ_ONLY)
    private String createId = "";
    @Schema(title = "建檔日期", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createDateTime;
    @Schema(title = "建檔程式代號", accessMode = Schema.AccessMode.READ_ONLY)
    private String createProcessId = "";
    @Schema(title = "異動人員", accessMode = Schema.AccessMode.READ_ONLY)
    private String modifyId = "";
    @Schema(title = "異動時間", accessMode = Schema.AccessMode.READ_ONLY)
    private Date modifyDateTime;
    @Schema(title = "最後異動修改程式代號", accessMode = Schema.AccessMode.READ_ONLY)
    private String modifyProcessId = "";
    @Schema(title = "是否註銷", accessMode = Schema.AccessMode.READ_ONLY)
    private Boolean isCancel = false;
    @Schema(title = "註銷備註, 註銷刪除時傳入使用", maxLength = 50)
    private String cancelNote = "";
    @Schema(title = "註銷人員", accessMode = Schema.AccessMode.READ_ONLY)
    private String cancelId = "";
    @Schema(title = "註銷程式代號", accessMode = Schema.AccessMode.READ_ONLY)
    private String cancelProcessId = "";
    @Schema(title = "註銷日期", accessMode = Schema.AccessMode.READ_ONLY)
    private Date cancelDateTime;
}
