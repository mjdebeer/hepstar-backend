package com.mjdebeer.hepstarbackend.services.response;

import com.mjdebeer.hepstarbackend.rest.v1.dto.ProductDetailDto;
import com.mjdebeer.hepstarbackend.rest.v1.dto.ProductsPricedDto;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ResponseService {

    public List<ProductsPricedDto> readProductsDocumentAndGenerateResponse(final Document document) {
        List<ProductsPricedDto> response = new ArrayList<>();
        Element packagesRoot = (Element) document.selectSingleNode("//Response/ResponseParameters/Packages");

        for (Iterator<Element> it = packagesRoot.elementIterator("Package"); it.hasNext();) {
            Element product = it.next();
            Node currency = product.selectSingleNode("//PriceDetails/PriceDetail[1]");
            Element productDetailsRoot = (Element) product.selectSingleNode("//PricedProduct/ProductInformation/productcovers");
            String currencyAttribute = currency.valueOf("@Currency");

            log.info(productDetailsRoot.getName());

            Optional<List<ProductDetailDto>> detailsList = Optional.empty();

            if (productDetailsRoot.hasContent()) {
                detailsList = productDetailsList(productDetailsRoot);
            }

            response.add(ProductsPricedDto.builder()
                    .title(product.selectSingleNode("//PricedProduct/ProductInformation/Name").getText())
                    .price(product.selectSingleNode("//PriceDetails/PriceDetail[1]").getText())
                    .currency(currencyAttribute)
                    .details(detailsList.orElse(Collections.emptyList()))
                    .build());
        }

        return response;
    }

    private Optional<List<ProductDetailDto>> productDetailsList(final Element productDetailsRoot) {
        List<ProductDetailDto> detailsList = new ArrayList<>();

        log.info("OUTSIDE");

        for (Iterator<Element> it = productDetailsRoot.elementIterator("productcover"); it.hasNext();) {
            Element productDetails = it.next();

            detailsList.add(ProductDetailDto.builder()
                            .header(productDetails.selectSingleNode("//header").getText())
                            .categoryHeader(productDetails.selectSingleNode("//productcovercatergory/header").getText())
                            .amount(productDetails.selectSingleNode("//amount").getText())
                            .currency(productDetails.selectSingleNode("//currency").getText())
                    .build());
        }

        return Optional.of(detailsList);
    }
}
