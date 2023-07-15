package com.springboot.common.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author allen.feng
 * @version 1.0
 * @since 2022/7/14
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RecordData {
    @NotEmpty(message = "UUID必填")
    private String uuid;
    @NotEmpty(message = "前端列表序號必填")
    private String rowNum;
}
