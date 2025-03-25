package com.eCommerce.shoppingSite.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eCommerce.shoppingSite.DTO.ProductDTO;
import com.eCommerce.shoppingSite.repo.ProductRepository;
import com.eCommerce.shoppingSite.tables.FilterBody;
import com.eCommerce.shoppingSite.tables.product.Product;
import com.eCommerce.shoppingSite.tables.product.ProductIdenty;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository pr) {
        productRepository = pr;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDTO(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public List<ProductDTO> getProductsByGenero(String type) {
        try {
            // Convert the string to the corresponding enum based on the role
            ProductIdenty generoEnum = getProductIdentyFromRole(type);
            return productRepository.findByGenero(generoEnum)
                    .stream()
                    .map(this::convertToDTO)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid gender type: " + type);
        }
    }

    private ProductIdenty getProductIdentyFromRole(String type) {
        for (ProductIdenty productIdenty : ProductIdenty.values()) {
            if (productIdenty.getRole().equalsIgnoreCase(type)) {
                return productIdenty;
            }
        }
        throw new IllegalArgumentException("Invalid gender type: " + type);
    }

    public List<ProductDTO> getProductByName(String name, String genero) {
        ProductIdenty generoEnum = getProductIdentyFromRole(genero);
        return productRepository.findByNameProduct(name, generoEnum)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<String> getTypesCategory(String genero) {
        ProductIdenty productIdenty = getProductIdentyFromRole(genero);
        return productRepository.findTypesCategory(productIdenty);
    }

    public List<ProductDTO> getProductsByGeneroAndCategories(String genero, String search,
            List<String> categories) {
        ProductIdenty generoEnum = getProductIdentyFromRole(genero);
        return productRepository.findByGeneroAndCategoriesAndSearch(generoEnum, categories, search)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getNameProduct(), product.getDescription(), product.getPrice(),
                product.getCategoria(), product.getSubCategoria(), product.getGenero().name(),
                product.getImageUrl());
    }

}
