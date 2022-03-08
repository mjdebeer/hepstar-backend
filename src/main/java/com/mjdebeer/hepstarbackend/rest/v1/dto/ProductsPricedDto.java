package com.mjdebeer.hepstarbackend.rest.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsPricedDto {

    private String title;
    private String price;
    private String currency;
    private String productId;
    private List<ProductDetailDto> details;

}
