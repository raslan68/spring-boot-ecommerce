package com.ramiaslan.ecommerce.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateRequest {

    @Size(min = 2, max = 50, message = "Name size must be between {min} and {max}")
    @NotBlank(message = "Name must not be null or empty")
    private String name;

    @Size(min = 2, max = 25, message = "Barcode size must be between {min} and {max}")
    @NotBlank(message = "Barcode must not be null or empty")
    private String barcode;

    @Positive(message = "Price must be greater than 0")
    @NotNull(message = "Price must not be null ")
    private BigDecimal price;

    @Positive(message = "Stock amount must be greater than 0")
    @NotNull(message = "Stock amount must not be null ")
    private Long stockAmount;

    @Size(min = 5, max = 500, message = "Description size must be between {min} and {max}")
    @NotBlank(message = "Description must not be null or empty")
    private String description;

    @Size(min = 2, max = 50, message = "Category Name size must be between {min} and {max}")
    @NotBlank(message = "Category Name must not be null or empty")
    private String categoryName;

}
