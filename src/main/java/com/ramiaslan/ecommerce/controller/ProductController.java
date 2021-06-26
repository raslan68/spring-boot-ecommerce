package com.ramiaslan.ecommerce.controller;

import com.ramiaslan.ecommerce.controller.request.ProductCreateRequest;
import com.ramiaslan.ecommerce.controller.request.ProductUpdateRequest;
import com.ramiaslan.ecommerce.controller.response.GenericResponse;
import com.ramiaslan.ecommerce.controller.response.ProductResponse;
import com.ramiaslan.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        productService.createProduct(productCreateRequest);
        return ResponseEntity.ok(new GenericResponse(201, "Successfully created"));
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductUpdateRequest productUpdateRequest) {
        productService.updateProduct(productUpdateRequest);
        return ResponseEntity.ok(new GenericResponse(200, "Successfully updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new GenericResponse(200, "Successfully deleted"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponse = productService.getAllProducts();
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/slice")
    public ResponseEntity<List<ProductResponse>> slice(Pageable pageable) {
        List<ProductResponse> result = productService.slice(pageable);
        return ResponseEntity.ok(result);
    }

}
