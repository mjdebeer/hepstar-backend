package com.mjdebeer.hepstarbackend;

import com.mjdebeer.hepstarbackend.builder.request.DocumentWriter;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;

@SpringBootTest
@Slf4j
class ApplicationTests {

    @Autowired
    private DocumentWriter documentWriter;

    @Test
    void contextLoads() {

//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        InputStream inputStream = classloader.getResourceAsStream("xsd/products_priced_request.xml");
//        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
//        String result = s.hasNext() ? s.next() : "";
//
//
//        System.out.println(result);

        Document document = documentWriter.createDocument(true,
                "DEP",
                "RES",
                "DES",
                "2022-05-05",
                Optional.of("2022-06-05"));

//        Element request = document.getRootElement();

        Node node = document.selectSingleNode("//Request/Authentication/Channel");

        log.info(node.getText());

        // iterate through child elements of root with element name "foo"
//        for (Iterator<Element> it = request.elementIterator("Username"); it.hasNext();) {
//            Element foo = it.next();
//            log.info(foo.getText());
//        }

    }

}
