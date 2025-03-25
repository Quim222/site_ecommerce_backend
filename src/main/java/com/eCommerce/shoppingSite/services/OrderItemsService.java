package com.eCommerce.shoppingSite.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eCommerce.shoppingSite.DTO.ItemsOrderDTO;
import com.eCommerce.shoppingSite.DTO.OrderDTO;
import com.eCommerce.shoppingSite.DTO.ProductDTO;
import com.eCommerce.shoppingSite.repo.OrderItemsRepository;
import com.eCommerce.shoppingSite.repo.OrderRepository;
import com.eCommerce.shoppingSite.tables.ItemsOrder;
import com.eCommerce.shoppingSite.tables.order.Order;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    private final OrderRepository orderRepository;

    @Transactional
    public ItemsOrderDTO createOrderItem(Long orderId, ItemsOrder item) {
        // Validar se o pedido existe
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        // Associar o pedido ao item
        item.setOrder(order);

        // Salvar o item
        ItemsOrder savedItem = orderItemsRepository.save(item);

        // Retornar o DTO
        return convertToDTO(savedItem);
    }

    public ItemsOrder getItemById(Long id) {
        return orderItemsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));
    }

    public List<ItemsOrderDTO> findItemsByOrderId(Long orderId) {
        return orderItemsRepository.findByOrderId(orderId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public void deleteItemById(Long id) {
        orderItemsRepository.deleteById(id);
    }

    @Transactional
    public void deleteItensByOrderId(Long id) {
        orderItemsRepository.deleteAllByOrderId(id);
    }

    private ItemsOrderDTO convertToDTO(ItemsOrder item) {
        return new ItemsOrderDTO(item.getId(), new ProductDTO(
                item.getProduct().getId(),
                item.getProduct().getNameProduct(),
                item.getProduct().getDescription(),
                item.getProduct().getPrice(),
                item.getProduct().getCategoria(),
                item.getProduct().getSubCategoria(),
                item.getProduct().getGenero().getRole(),
                item.getProduct().getImageUrl()), item.getQuantidade(), item.getSubtotal(),
                new OrderDTO(
                        item.getOrder().getId(),
                        item.getOrder().getUser().getId(),
                        item.getOrder().getTotal(),
                        item.getOrder().getItems().stream()
                                .map(this::convertToDTO)
                                .toList()));
    }

}
