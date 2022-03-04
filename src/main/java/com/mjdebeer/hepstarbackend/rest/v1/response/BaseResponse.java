package com.mjdebeer.hepstarbackend.rest.v1.response;

public class BaseResponse<T> {

    private boolean success = true;
    private String message = "Success";
    private T data;

}
