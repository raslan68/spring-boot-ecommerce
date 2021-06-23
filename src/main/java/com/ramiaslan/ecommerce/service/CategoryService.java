package com.ramiaslan.ecommerce.service;

import com.ramiaslan.ecommerce.controller.request.CategoryCreateRequest;
import com.ramiaslan.ecommerce.controller.request.CategoryUpdateRequest;
import com.ramiaslan.ecommerce.controller.response.CategoryResponse;
import com.ramiaslan.ecommerce.entity.Category;
import com.ramiaslan.ecommerce.exception.CategoryException;
import com.ramiaslan.ecommerce.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public void createCategory(CategoryCreateRequest categoryCreateRequest) {
        String name = categoryCreateRequest.getName();
        existsByCategoryName(name);

        Category category = new Category();
        category.setName(name);
        categoryRepo.save(category);
    }

    public void updateCategory(CategoryUpdateRequest categoryUpdateRequest) {
        Long id = categoryUpdateRequest.getId();
        String name = categoryUpdateRequest.getName();

        existsByCategoryName(name);

        Optional<Category> category = findById(id);
        category.get().setName(name);
        categoryRepo.save(category.get());
    }

    public CategoryResponse getCategoryById(Long id) {
        Optional<Category> category = findById(id);
        CategoryResponse categoryResponse = convert(category.get());

        return categoryResponse;
    }

    public void deleteCategory(Long id) {
        findById(id);
        categoryRepo.deleteById(id);
    }

    public List<CategoryResponse> getAllCategory() {
        return categoryRepo.findAll().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Optional<Category> findById(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isEmpty()) {
            throw new CategoryException("Category is not found");
        }
        return category;
    }

    private void existsByCategoryName(String name) {
        if (categoryRepo.existsByName(name)) {
            throw new CategoryException("Category name already exists");
        }
    }

    private CategoryResponse convert(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }

    public List<CategoryResponse> slice(Pageable pageable) {
        return categoryRepo.findAll(pageable)
                .stream().map(this::convert)
                .collect(Collectors.toList());
    }
}
