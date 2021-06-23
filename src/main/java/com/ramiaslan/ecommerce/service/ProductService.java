package com.ramiaslan.ecommerce.service;

import com.ramiaslan.ecommerce.controller.request.ProductCreateRequest;
import com.ramiaslan.ecommerce.controller.request.ProductUpdateRequest;
import com.ramiaslan.ecommerce.controller.response.CategoryResponse;
import com.ramiaslan.ecommerce.controller.response.ProductResponse;
import com.ramiaslan.ecommerce.entity.Category;
import com.ramiaslan.ecommerce.entity.Product;
import com.ramiaslan.ecommerce.exception.ProductException;
import com.ramiaslan.ecommerce.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public void createProduct(ProductCreateRequest productCreateRequest) {
        String name = productCreateRequest.getName();
        String barcode = productCreateRequest.getBarcode();
        BigDecimal price = productCreateRequest.getPrice();
        Long stockAmount = productCreateRequest.getStockAmount();
        String description = productCreateRequest.getDescription();
        Category category = productCreateRequest.getCategory();

        Product product = new Product();
        product.setName(name);
        product.setBarcode(barcode);
        product.setPrice(price);
        product.setStockAmount(stockAmount);
        product.setDescription(description);
        product.setCategory(category);

        productRepo.save(product);
    }

    public void updateProduct(ProductUpdateRequest productUpdateRequest) {
        Long id = productUpdateRequest.getId();
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()){
            throw new ProductException("not found");
        }
        product.get().setName(productUpdateRequest.getName());
        product.get().setBarcode(productUpdateRequest.getBarcode());
        product.get().setPrice(productUpdateRequest.getPrice());
        product.get().setStockAmount(productUpdateRequest.getStockAmount());
        product.get().setDescription(productUpdateRequest.getDescription());
        product.get().setCategory(productUpdateRequest.getCategory());
        productRepo.save(product.get());
    }

    public ProductResponse getProductById(Long id) {
        Optional<Product> product = findById(id);
        ProductResponse productResponse = convert(product.get());
        return productResponse;
    }

    public void deleteProduct(Long id) {
        findById(id);
        productRepo.deleteById(id);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Optional<Product> findById(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()){
            throw new ProductException("not found");
        }
        return product;
    }
    // do we need this for Products??
    private void existsByProductName(String name){
        if (productRepo.existsByName(name)){
            throw new ProductException("Product name is already exists");
        }
    }

    private ProductResponse convert(Product product){
        CategoryResponse category = new CategoryResponse();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setBarcode(product.getBarcode());
        productResponse.setPrice(product.getPrice());
        productResponse.setStockAmount(product.getStockAmount());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategory(product.getCategory());

        return productResponse;
    }

    public  List<ProductResponse> slice(Pageable pageable){
        return productRepo.findAll(pageable)
                .stream().map(this::convert)
                .collect(Collectors.toList());
    }

}
