package com.ramiaslan.ecommerce.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GenericResponse {

    private Integer code;
    private String message;

}
