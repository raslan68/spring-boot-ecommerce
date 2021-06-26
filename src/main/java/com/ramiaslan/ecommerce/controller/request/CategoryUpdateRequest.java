package com.ramiaslan.ecommerce.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryUpdateRequest {

    @Positive(message = "Id must be greater than 0")
    @NotNull(message = "Id must not be null")
    private Long id;

    @Size(min = 4, max = 250, message = "Name size must be between {min} and {max}")
    @NotBlank(message = "Name must not be null or empty")
    private String name;

}
