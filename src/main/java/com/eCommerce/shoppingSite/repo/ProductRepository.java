package com.eCommerce.shoppingSite.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eCommerce.shoppingSite.tables.product.Product;
import com.eCommerce.shoppingSite.tables.product.ProductIdenty;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByGenero(ProductIdenty genero);

    @Query("SELECT p FROM Product p WHERE p.nameProduct LIKE %?1% and p.genero = ?2")
    List<Product> findByNameProduct(String nameProduct, ProductIdenty genero);

    @Query("SELECT DISTINCT p.subCategoria FROM Product p where p.genero = ?1")
    List<String> findTypesCategory(ProductIdenty genero);

    // Consulta para filtrar por uma lista de categorias (filtros múltiplos)
    @Query("SELECT p FROM Product p WHERE p.genero = :genero AND p.subCategoria IN :categorias AND (:search IS NULL OR p.nameProduct LIKE %:search%)")
    List<Product> findByGeneroAndCategoriesAndSearch(
            @Param("genero") ProductIdenty genero,
            @Param("categorias") List<String> categorias,
            @Param("search") String search);

}
