package com.ramiaslan.ecommerce.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryCreateRequest {

    @Size(min = 2, max = 50, message = "Name size must be between {min} and {max}")
    @NotBlank(message = "Name must not be null or empty")
    private String name;

}



