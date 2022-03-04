package com.mjdebeer.hepstarbackend.rest.v1.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/request")
public class RequestController {

    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/sample/endpoint", method = RequestMethod.POST)
    public String priceRequest(@RequestBody String xmlString) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> entity = new HttpEntity<>(xmlString, headers);

        return restTemplate.exchange("https://uat.gateway.insure/", HttpMethod.POST, entity, String.class).getBody();
    }

}
