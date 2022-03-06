package com.mjdebeer.hepstarbackend.rest.v1.controller;

import com.mjdebeer.hepstarbackend.builder.request.DocumentWriter;
import com.mjdebeer.hepstarbackend.configuration.SwaggerIncluded;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.dom4j.Document;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
public class BaseController {

    private final DocumentWriter documentWriter;

    @SwaggerIncluded
    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello and welcome.");
    }

    @SwaggerIncluded
    @GetMapping(value = "requestTest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> requestTest(@RequestParam("oneWay") final boolean oneWay,
                                              @RequestParam("countryOfResidency") final String countryOfResidency,
                                              @RequestParam("departureCountry") final String departureCountry,
                                              @RequestParam("destinationCountry") final String destinationCountry,
                                              @RequestParam("departureDate") final String departureDate,
                                              @RequestParam("returnDate") final String returnDate) {
        Document productsPriced = documentWriter.createDocument(oneWay,
                departureCountry,
                countryOfResidency,
                destinationCountry,
                departureDate,
                Optional.of(returnDate));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(productsPriced.asXML(), headers);

        RestTemplate restTemplate = new RestTemplate();

        return ResponseEntity.ok(restTemplate.postForEntity("https://uat.gateway.insure/products/priced",
                                                            request,
                                                            String.class).getBody());
    }

}
