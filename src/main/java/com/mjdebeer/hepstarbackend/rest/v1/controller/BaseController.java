package com.mjdebeer.hepstarbackend.rest.v1.controller;

import com.mjdebeer.hepstarbackend.configuration.SwaggerIncluded;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
public class BaseController {

    // service
    private final RequestController requestController;

    @SwaggerIncluded
    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello and welcome.");
    }

    @SwaggerIncluded
    @GetMapping(value = "requestTest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> requestTest() throws IOException {
        String xml = Files.readString(Path.of("/resources/test.xml"));


        return ResponseEntity.ok(requestController.priceRequest(xml));
    }

}
