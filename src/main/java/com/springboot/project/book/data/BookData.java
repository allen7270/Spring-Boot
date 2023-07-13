package com.springboot.project.book.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookData {
    @Schema(title = "作者")
    private String author;
    @Schema(title = "書名")
    private String book;

    @Data
    public static class AddBookData {
        @Schema(title = "作者")
        private String author;
        @Schema(title = "書名")
        private String book;
        @Schema(title = "價格")
        private BigDecimal price;
        @Schema(title = "數量")
        private BigDecimal count;
        @Schema(title = "語言")
        private String language;
        @Schema(title = "內容簡介")
        private String content;
    }
}
