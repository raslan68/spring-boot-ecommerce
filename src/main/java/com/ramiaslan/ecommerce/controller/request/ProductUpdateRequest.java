package com.ramiaslan.ecommerce.controller.request;

import com.ramiaslan.ecommerce.entity.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductUpdateRequest {

   @Positive(message = "id must be greater than 0")
    private Long id;

    @Size(min = 2, max = 250, message = "Name size must be between {min} and {max}")
    @NotBlank(message = "This field must not null and empty")
    private String name;

    private String barcode;
    private BigDecimal price;
    private Long stockAmount;
    private String description;
    private Category category;
}
