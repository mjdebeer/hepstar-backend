package com.mjdebeer.hepstarbackend.rest.v1.response;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Api
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private boolean success = true;
    private String message = "Success";
    private T data;

}
