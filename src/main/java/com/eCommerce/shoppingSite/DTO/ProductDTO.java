package com.eCommerce.shoppingSite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
    private String subCategoria;
    private String genero;
    private String imageUrl;
}
