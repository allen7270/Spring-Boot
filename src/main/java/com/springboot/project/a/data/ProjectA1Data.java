package com.springboot.project.a.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProjectA1Data {
    @Schema(title = "作者")
    private String author;
    @Schema(title = "書名")
    private String book;
}
