package com.enomdeul.domain.sample.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.enomdeul.global.common.response.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SampleErrorCode implements BaseErrorCode {

    POST_NOT_ALLOWED_ANONYMOUS_COMMENT_ERROR("POST_NOT_ALLOWED_ANONYMOUS_COMMENT_ERROR", HttpStatus.FORBIDDEN, "익명 게시글에만 익명 댓글을 달 수 있습니다."),
    PARENT_COMMENT_NOT_FOUND_ERROR("PARENT_COMMENT_NOT_FOUND_ERROR", HttpStatus.NOT_FOUND, "부모 댓글을 찾을 수 없습니다."),
    MENTIONED_MEMBER_NOT_FOUND_ERROR("MENTIONED_MEMBER_NOT_FOUND_ERROR", HttpStatus.NOT_FOUND, "멘션된 사용자를 찾을 수 없습니다."),
    COMMENT_NOT_FOUND_ERROR("COMMENT_NOT_FOUND_ERROR", HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    COMMENT_FORBIDDEN_ERROR("COMMENT_FORBIDDEN_ERROR", HttpStatus.FORBIDDEN, "본인이 작성한 댓글만 수정/삭제가 가능합니다."),
    COMMENT_ALREADY_DELETED_ERROR("COMMENT_ALREADY_DELETED_ERROR", HttpStatus.CONFLICT, "이미 삭제된 댓글입니다."),
    WITHDRAWN_USER_COMMENT_ERROR("WITHDRAWN_USER_COMMENT_ERROR", HttpStatus.FORBIDDEN, "탈퇴한 사용자의 댓글입니다."),
    ALREADY_LIKED_ERROR("ALREADY_LIKED_ERROR", HttpStatus.CONFLICT, "이미 좋아요한 댓글입니다."),
    NOT_LIKED_ERROR("NOT_LIKED_ERROR", HttpStatus.FORBIDDEN, "좋아요를 하지 않은 댓글입니다."),
    COMMENT_HIDDEN_ERROR("COMMENT_HIDDEN_ERROR", HttpStatus.FORBIDDEN, "숨김 처리된 댓글입니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
