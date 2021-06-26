package com.ramiaslan.ecommerce.service;

import com.ramiaslan.ecommerce.controller.request.ProductCreateRequest;
import com.ramiaslan.ecommerce.controller.request.ProductUpdateRequest;
import com.ramiaslan.ecommerce.controller.response.ProductResponse;
import com.ramiaslan.ecommerce.entity.Category;
import com.ramiaslan.ecommerce.entity.Product;
import com.ramiaslan.ecommerce.exception.CategoryException;
import com.ramiaslan.ecommerce.exception.ProductException;
import com.ramiaslan.ecommerce.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryService categoryService;

    public void createProduct(ProductCreateRequest productCreateRequest) {
        Category category = categoryService.findByName(productCreateRequest.getCategoryName()).get();

        String barcode = productCreateRequest.getBarcode();
        existsByBarcode(barcode);

        Product product = new Product();
        product.setName(productCreateRequest.getName());
        product.setBarcode(barcode);
        product.setPrice(productCreateRequest.getPrice());
        product.setStockAmount(productCreateRequest.getStockAmount());
        product.setDescription(productCreateRequest.getDescription());
        product.setCategory(category);

        productRepo.save(product);
    }

    public void updateProduct(ProductUpdateRequest productUpdateRequest) {
        Product product = findById(productUpdateRequest.getId()).get();
        Category category = categoryService.findByName(productUpdateRequest.getCategoryName()).get();

        String barcode = productUpdateRequest.getBarcode();
        existsByBarcode(barcode);

        product.setName(productUpdateRequest.getName());
        product.setBarcode(barcode);
        product.setPrice(productUpdateRequest.getPrice());
        product.setStockAmount(productUpdateRequest.getStockAmount());
        product.setDescription(productUpdateRequest.getDescription());
        product.setCategory(category);

        productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        findById(id);
        productRepo.deleteById(id);
    }

    public ProductResponse getProductById(Long id) {
        Product product = findById(id).get();
        return convert(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> slice(Pageable pageable) {
        return productRepo.findAll(pageable)
                .stream().map(this::convert)
                .collect(Collectors.toList());
    }

    private Optional<Product> findById(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()) {
            throw new ProductException("Product not found");
        }
        return product;
    }

    private void existsByBarcode(String barcode) {
        if (productRepo.existsByBarcode(barcode)) {
            throw new CategoryException("Barcode must be unique");
        }
    }

    private ProductResponse convert(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setBarcode(product.getBarcode());
        productResponse.setPrice(product.getPrice());
        productResponse.setStockAmount(product.getStockAmount());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategoryName(product.getCategory().getName());
        return productResponse;
    }

}
