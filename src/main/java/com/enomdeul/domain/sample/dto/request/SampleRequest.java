package com.enomdeul.domain.sample.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class SampleRequest {
    @Getter
    @AllArgsConstructor
    public static class SampleCreateDTO {
        private Long parentId;
        @NotBlank(message = "댓글 내용을 1자 이상 작성해주세요.")
        @Size(max = 1000, message = "댓글 내용은 1000자 이하여야 합니다.")
        private String content;
        private Boolean isAnonymous;
        private List<Long> mentionedList;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CursorDTO {
        @Min(value = 1, message = "PAGESIZE는 1 이상이어야 합니다.")
        Integer pageSize = 5;

        @Min(value = 1, message = "CURSOR는 1 이상이어야 합니다.")
        Long cursor;
    }
}
