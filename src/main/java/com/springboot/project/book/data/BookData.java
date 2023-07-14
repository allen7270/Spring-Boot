package com.springboot.project.book.data;

import com.springboot.common.util.QueryPageBean;
import com.springboot.common.util.SuperBean;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookData extends SuperBean{
    @Schema(title = "書名")
    private String book;
    @Schema(title = "作者")
    private String author;
    @Schema(title = "單價")
    private BigDecimal price;
    @Schema(title = "數量")
    private BigDecimal count;

    @Data
    public static class queryBookData extends QueryPageBean {
        @Schema(title = "書名")
        private String book;
        @Schema(title = "作者")
        private String author;
        @Schema(title = "單價")
        private BigDecimal price;
        @Schema(title = "數量")
        private BigDecimal count;
        @Schema(title = "語言")
        private String language;
        @Schema(title = "內容")
        private String content;
    }

    @Data
    public static class AddBookData {
        @Schema(title = "作者")
        private String author;
        @Schema(title = "書名")
        private String book;
        @Schema(title = "單價")
        private BigDecimal price;
        @Schema(title = "數量")
        private BigDecimal count;
        @Schema(title = "語言")
        private String language;
        @Schema(title = "內容簡介")
        private String content;
    }
}
