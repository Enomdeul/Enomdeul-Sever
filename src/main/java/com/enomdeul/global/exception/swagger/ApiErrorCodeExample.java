package com.enomdeul.global.exception.swagger;

import com.enomdeul.global.common.response.code.BaseErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface ApiErrorCodeExample {
    Class< ? extends BaseErrorCode> value();
    String[] include() default {}; // 사용할 에러 코드 이름들 저장

}
