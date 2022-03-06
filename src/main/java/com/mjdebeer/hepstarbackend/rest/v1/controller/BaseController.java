package com.mjdebeer.hepstarbackend.rest.v1.controller;

import com.mjdebeer.hepstarbackend.configuration.SwaggerIncluded;
import com.mjdebeer.hepstarbackend.rest.v1.dto.ProductsPricedDto;
import com.mjdebeer.hepstarbackend.services.request.RequestService;
import com.mjdebeer.hepstarbackend.services.response.ResponseService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
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

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
public class BaseController {

    private final RequestService requestService;
    private final ResponseService responseService;

    @SwaggerIncluded
    @GetMapping(value = "productsPriced", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductsPricedDto>> requestTest(@RequestParam("oneWay") final boolean oneWay,
                                                               @RequestParam("countryOfResidency") final String countryOfResidency,
                                                               @RequestParam("departureCountry") final String departureCountry,
                                                               @RequestParam("destinationCountry") final String destinationCountry,
                                                               @RequestParam("departureDate") final String departureDate,
                                                               @RequestParam("returnDate") final String returnDate) throws DocumentException {
        Document productsPriced = requestService.buildRequestDocument(oneWay,
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

}
