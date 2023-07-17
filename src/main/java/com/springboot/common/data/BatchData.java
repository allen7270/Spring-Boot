package com.springboot.common.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author allen.feng
 * @version 1.0
 * @since 2022/7/14
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BatchData {
    @Valid
    @NotEmpty(message = "objects不得為空")
    @Schema(title = "批次物件")
    private List<RecordData> objects;
}
