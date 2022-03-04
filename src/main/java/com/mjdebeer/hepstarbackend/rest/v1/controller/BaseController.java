package com.mjdebeer.hepstarbackend.rest.v1.controller;

import com.mjdebeer.hepstarbackend.configuration.SwaggerIncluded;
import com.mjdebeer.hepstarbackend.rest.v1.response.BaseResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
public class BaseController {

    // service

    @SwaggerIncluded
    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> test() {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("You have successfully reached the back-end!")
                .build());
    }

}
