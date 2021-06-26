package com.ramiaslan.ecommerce.controller;

import com.ramiaslan.ecommerce.controller.request.CategoryCreateRequest;
import com.ramiaslan.ecommerce.controller.request.CategoryUpdateRequest;
import com.ramiaslan.ecommerce.controller.response.CategoryResponse;
import com.ramiaslan.ecommerce.controller.response.GenericResponse;
import com.ramiaslan.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
        categoryService.createCategory(categoryCreateRequest);
        return ResponseEntity.ok(new GenericResponse(201, "Successfully created"));
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        categoryService.updateCategory(categoryUpdateRequest);
        return ResponseEntity.ok(new GenericResponse(200, "Successfully updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new GenericResponse(200, "Successfully deleted"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Long id) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        List<CategoryResponse> categoryResponse = categoryService.getAllCategory();
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping("/slice")
    public ResponseEntity<List<CategoryResponse>> slice(Pageable pageable) {
        List<CategoryResponse> result = categoryService.slice(pageable);
        return ResponseEntity.ok(result);
    }

}
