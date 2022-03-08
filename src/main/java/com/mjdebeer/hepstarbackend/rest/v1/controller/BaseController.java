package com.mjdebeer.hepstarbackend.rest.v1.controller;

import com.mjdebeer.hepstarbackend.configuration.SwaggerIncluded;
import com.mjdebeer.hepstarbackend.rest.v1.dto.ProductsPricedDto;
import com.mjdebeer.hepstarbackend.services.request.RequestService;
import com.mjdebeer.hepstarbackend.services.response.ResponseService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Api
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
public class BaseController {

    private final RequestService requestService;
    private final ResponseService responseService;

    @SwaggerIncluded
    @GetMapping(value = "productsPriced", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductsPricedDto>> productsPriced(@RequestParam("oneWay") final boolean oneWay,
                                                               @RequestParam("countryOfResidency") final String countryOfResidency,
                                                               @RequestParam("departureCountry") final String departureCountry,
                                                               @RequestParam("destinationCountry") final String destinationCountry,
                                                               @RequestParam("departureDate") final String departureDate,
                                                               @RequestParam("returnDate") final String returnDate) throws DocumentException {
        Document productsPriced = requestService.buildPricedProductRequestDocument(oneWay,
                departureCountry,
                countryOfResidency,
                destinationCountry,
                departureDate,
                Optional.ofNullable(returnDate));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(productsPriced.asXML(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity("https://uat.gateway.insure/products/priced",
                request,
                String.class);

        InputStream inputStream = new ByteArrayInputStream(response.getBody().getBytes(StandardCharsets.UTF_16));
        SAXReader reader = new SAXReader();
        Document productsResponse = reader.read(inputStream);

        return ResponseEntity.ok(responseService.readProductsDocumentAndGenerateResponse(productsResponse));
    }

    @SwaggerIncluded
    @GetMapping(value = "policyIssue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> policyIssue(@RequestParam("oneWay") final boolean oneWay,
                                              @RequestParam("countryOfResidency") final String countryOfResidency,
                                              @RequestParam("departureCountry") final String departureCountry,
                                              @RequestParam("destinationCountry") final String destinationCountry,
                                              @RequestParam("departureDate") final String departureDate,
                                              @RequestParam("returnDate") final String returnDate,
                                              @RequestParam("productId") final String productId,
                                              @RequestParam("firstName") final String firstName,
                                              @RequestParam("surname") final String surname,
                                              @RequestParam("dateOfBirth") final String dateOfBirth,
                                              @RequestParam("residency") final String residency,
                                              @RequestParam("nationalId") final String nationalId,
                                              @RequestParam("email") final String email) throws DocumentException {

        Document policyIssue = requestService.buildPolicyIssueDocument(firstName,
                surname,
                dateOfBirth,
                residency,
                nationalId,
                email,
                productId,
                oneWay,
                departureDate,
                Optional.ofNullable(returnDate),
                departureCountry,
                destinationCountry);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(policyIssue.asXML(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity("https://uat.gateway.insure/policy/issue",
                request,
                String.class);

        log.info(policyIssue.asXML());

        InputStream inputStream = new ByteArrayInputStream(response.getBody().getBytes(StandardCharsets.UTF_16));
        SAXReader reader = new SAXReader();
        Document policyIssueResponse = reader.read(inputStream);
        String responseStatus = policyIssueResponse.selectSingleNode("//Response/Status/Code").getText();

        return responseStatus.equals("200") ? ResponseEntity.ok("Success") : ResponseEntity.internalServerError().body("Fail");
    }
}
