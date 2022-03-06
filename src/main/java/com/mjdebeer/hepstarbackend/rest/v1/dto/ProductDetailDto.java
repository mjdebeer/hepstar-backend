package com.mjdebeer.hepstarbackend.rest.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {

    private String header;
    private String currency;
    private String amount;
    private String categoryHeader;

}
