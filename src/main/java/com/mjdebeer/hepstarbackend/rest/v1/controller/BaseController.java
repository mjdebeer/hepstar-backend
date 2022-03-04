package com.mjdebeer.hepstarbackend.rest.v1.controller;

import com.mjdebeer.hepstarbackend.configuration.SwaggerIncluded;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

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
    public ResponseEntity<String> requestTest() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("test.xml");

//        Todo: Change this
        assert inputStream != null;

        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String xmlString = s.hasNext() ? s.next() : "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(xmlString, headers);

        RestTemplate restTemplate = new RestTemplate();

        return ResponseEntity.ok(restTemplate.postForEntity("https://uat.gateway.insure/products/priced", request, String.class).getBody());
    }

}
