package com.enomdeul.domain.sample.controller;

import com.enomdeul.domain.sample.controller.docs.SampleControllerDocs;
import com.enomdeul.domain.sample.service.SampleService;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiSuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SampleController implements SampleControllerDocs {

    private final SampleService sampleService;

    @GetMapping("/samples")
    public ResponseEntity<ApiResponse<Void>> createSample() {

        return ResponseEntity.ok(ApiResponse.of(ApiSuccessCode.SUCCESS));
    }
}
