package com.mjdebeer.hepstarbackend;

import com.mjdebeer.hepstarbackend.services.request.RequestService;
import com.mjdebeer.hepstarbackend.services.response.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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

//        for (Iterator<Element> it = request.elementIterator("Username"); it.hasNext();) {
//            Element foo = it.next();
//            log.info(foo.getText());
//        }

        log.info(requestService.buildPolicyIssueDocument("Name",
                "Surname",
                "0000-00-00",
                "ZA",
                "123456789",
                "email",
                "ProductId",
                false,
                "0000-00-00",
                Optional.of("0000-00-00"),
                "ZA",
                "BH").asXML());

    }

}
