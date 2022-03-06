package com.mjdebeer.hepstarbackend;

import com.mjdebeer.hepstarbackend.services.request.RequestService;
import com.mjdebeer.hepstarbackend.services.response.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

@SpringBootTest
@Slf4j
class ApplicationTests {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ResponseService responseService;

    @Test
    void contextLoads() throws DocumentException {

//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        InputStream inputStream = classloader.getResourceAsStream("xsd/products_priced_request.xml");
//        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
//        String result = s.hasNext() ? s.next() : "";
//
//
//        System.out.println(result);

//        Document document = requestService.buildRequestDocument(true,
//                "DEP",
//                "RES",
//                "DES",
//                "2022-05-05",
//                Optional.of("2022-06-05"));

//        Element request = document.getRootElement();

//        Node node = document.selectSingleNode("//Request/Authentication/Channel");

//        log.info(node.getText());

//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        InputStream inputStream = classloader.getResourceAsStream("xsd/products_priced_response.xml");
//        SAXReader reader = new SAXReader();
//        Document productsResponse = reader.read(inputStream);
//
//        responseService.readProductsDocumentAndGenerateResponse(productsResponse);

        // iterate through child elements of root with element name "foo"
//        for (Iterator<Element> it = request.elementIterator("Username"); it.hasNext();) {
//            Element foo = it.next();
//            log.info(foo.getText());
//        }

    }

}
