package com.ramiaslan.ecommerce.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryCreateRequest {

    @Size(min = 4, max = 250, message = "Name size must be between {min} and {max}")
    @NotBlank(message = "This field must not null and empty")
    private String name;

}



