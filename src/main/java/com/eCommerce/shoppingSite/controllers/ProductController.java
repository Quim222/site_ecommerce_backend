package com.eCommerce.shoppingSite.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eCommerce.shoppingSite.DTO.ProductDTO;
import com.eCommerce.shoppingSite.services.ProductService;
import com.eCommerce.shoppingSite.tables.FilterBody;
import com.eCommerce.shoppingSite.tables.product.Product;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000") // Permite requisições do frontend
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/genero/{type}")
    public ResponseEntity<List<ProductDTO>> getProductsByGenero(@PathVariable String type) {
        try {
            // Call the service method to fetch the products by genero
            List<ProductDTO> products = productService.getProductsByGenero(type);
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            // Return a 400 Bad Request if the gender type is invalid
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    @GetMapping("/name/{genero}/{name}")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable("name") String name,
            @PathVariable("genero") String genero) {
        try {
            List<ProductDTO> products = productService.getProductByName(name, genero);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    @GetMapping("/types/{genero}")
    public ResponseEntity<List<String>> getTypesCategory(@PathVariable String genero) {
        return ResponseEntity.ok(productService.getTypesCategory(genero));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTO>> getProductsByFilter(@RequestParam String genero,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(productService.getProductsByGeneroAndCategories(genero, search, categories));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product, UriComponentsBuilder uriBuilder) {
        ProductDTO createdProduct = productService.createProduct(product);
        return ResponseEntity.created(uriBuilder.path("/products/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri())
                .body(createdProduct);
    }

}
