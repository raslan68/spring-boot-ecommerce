package com.ramiaslan.ecommerce.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private String barcode;
    private BigDecimal price;
    private Long stockAmount;
    private String description;
    private String categoryName;

}
