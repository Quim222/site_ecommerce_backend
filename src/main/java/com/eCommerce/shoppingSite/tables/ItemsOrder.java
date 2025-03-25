package com.eCommerce.shoppingSite.tables;

import com.eCommerce.shoppingSite.tables.order.Order;
import com.eCommerce.shoppingSite.tables.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    private Product product;

    private int quantidade;
    private Double subtotal;
}
