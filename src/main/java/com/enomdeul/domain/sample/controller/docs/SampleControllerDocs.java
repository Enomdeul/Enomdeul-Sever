package com.enomdeul.domain.sample.controller.docs;

import com.enomdeul.domain.sample.exception.SampleErrorCode;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiErrorCode;
import com.enomdeul.global.exception.swagger.ApiErrorCodeExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Sample", description = "샘플 관련 API")
public interface SampleControllerDocs {

    @Operation(summary = "샘플 작성", description = "샘플을 작성합니다.")
    @ApiErrorCodeExamples(
            value = {SampleErrorCode.class, ApiErrorCode.class},
            include = { "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR", "POST_NOT_FOUND_ERROR", "POST_NOT_ALLOWED_ANONYMOUS_COMMENT_ERROR", "PARENT_COMMENT_NOT_FOUND_ERROR",
            "CONTENT_NOTBLANK_ERROR", "CONTENT_SIZE_ERROR", "MENTIONED_MEMBER_NOT_FOUND_ERROR"})
    public ResponseEntity<ApiResponse<Void>> createSample();

}
