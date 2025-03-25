package com.eCommerce.shoppingSite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsOrderDTO {
    private Long id;
    private ProductDTO product;
    private int quantidade;
    private Double subtotal;
    private OrderDTO order;
}
