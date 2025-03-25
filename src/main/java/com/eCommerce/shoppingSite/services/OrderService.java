package com.eCommerce.shoppingSite.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eCommerce.shoppingSite.DTO.ItemsOrderDTO;
import com.eCommerce.shoppingSite.DTO.OrderDTO;
import com.eCommerce.shoppingSite.DTO.ProductDTO;
import com.eCommerce.shoppingSite.repo.OrderRepository;
import com.eCommerce.shoppingSite.tables.ItemsOrder;
import com.eCommerce.shoppingSite.tables.order.Order;
import com.eCommerce.shoppingSite.tables.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemsService orderItemsService;

    @Transactional
    public OrderDTO createOrder(User user, List<ItemsOrder> items) {
        // Salvando o pedido

        Order newOrder = new Order();

        newOrder.setUser(user);
        newOrder.setItems(items);

        Double total = 0.0;

        // Associando os itens ao pedido e salvando cada item
        for (ItemsOrder item : items) {
            item.setOrder(newOrder); // Associa o pedido a cada item
            orderItemsService.createOrderItem(newOrder.getId(), item); // Cria o item no banco
            total += item.getSubtotal();
        }

        newOrder.setTotal(total);

        orderRepository.save(newOrder);
        // Retornando o DTO do pedido
        return convertToDTO(newOrder);
    }

    public void createOrderItem(Long orderId, ItemsOrder item) {
        orderItemsService.createOrderItem(orderId, item);
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Transactional
    public void deleteOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        // Convertendo os itens do pedido para ItemsOrderDTO
        List<ItemsOrderDTO> itemsDTO = order.getItems().stream()
                .map(item -> {
                    // Criando o DTO do item
                    OrderDTO orderDTO = new OrderDTO(order.getId(), order.getUser().getId(), order.getTotal(), null);
                    return new ItemsOrderDTO(
                            item.getId(),
                            new ProductDTO(
                                    item.getProduct().getId(),
                                    item.getProduct().getNameProduct(),
                                    item.getProduct().getDescription(),
                                    item.getProduct().getPrice(),
                                    item.getProduct().getCategoria(),
                                    item.getProduct().getSubCategoria(),
                                    item.getProduct().getGenero().getRole(),
                                    item.getProduct().getImageUrl()),
                            item.getQuantidade(),
                            item.getSubtotal(),
                            orderDTO // Adicionando o OrderDTO no ItemsOrderDTO
                    );
                })
                .toList();

        // Retornando o DTO do pedido
        return new OrderDTO(order.getId(), order.getUser().getId(), order.getTotal(), itemsDTO);
    }

}
