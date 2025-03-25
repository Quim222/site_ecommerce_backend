package com.eCommerce.shoppingSite.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eCommerce.shoppingSite.DTO.CreateOrderRequest;
import com.eCommerce.shoppingSite.DTO.ItemsOrderDTO;
import com.eCommerce.shoppingSite.DTO.OrderDTO;
import com.eCommerce.shoppingSite.services.OrderItemsService;
import com.eCommerce.shoppingSite.services.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderItemsService orderItemsService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequest createOrderRequest,
            UriComponentsBuilder uriBuilder) {
        OrderDTO orderDTO = orderService.createOrder(createOrderRequest.getUser(), createOrderRequest.getItems());
        return ResponseEntity.created(uriBuilder.path("/orders/{id}")
                .buildAndExpand(orderDTO.getId())
                .toUri())
                .body(orderDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints de itens de pedidos

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<ItemsOrderDTO>> getItemsByOrderId(@PathVariable Long orderId) {
        List<ItemsOrderDTO> items = orderItemsService.findItemsByOrderId(orderId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<Void> deleteItemByOrderId(@PathVariable Long orderId, @PathVariable Long itemId) {
        orderItemsService.deleteItemById(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{orderId}/items")
    public ResponseEntity<Void> deleteItemsByOrderId(@PathVariable Long orderId) {
        orderItemsService.deleteItensByOrderId(orderId);
        return ResponseEntity.noContent().build();
    }

}
