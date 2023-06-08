package com.tutorial.apidemo.controllers;

import com.tutorial.apidemo.models.Product;
import com.tutorial.apidemo.models.ResponseObject;
import com.tutorial.apidemo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// Để nhận biết Class này là controller nào
@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {

    // DI: Dependency Injection
    /// Autowired: tạo 1 lần đối tượng ProductRepository, không cần tạo lại (Singleton pattern)
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get product detail
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        // Let's return an object with: data, message, status
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfuly", foundProduct)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id = " + id, "")
                );
    }

    // insert new product with POST method
    // Postman: Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        // 2 products must not have the same name!
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        // NOT_IMPLEMENTED: lỗi 501, request này không thể xử lý bởi server
        if (foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert product successfully", repository.save(newProduct))
        );
    }

    // Ta sẽ dùng upsert để update product
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        // có orElseGet không cần Optional nữa
        Product updatedProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setProductionYear(newProduct.getProductionYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product successfully", updatedProduct)
        );
    }

    // Delete a product
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find product to delete", "")
            );
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete product successfully", "")
        );
    }
}
