package com.eCommerce.shoppingSite.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.shoppingSite.tables.ItemsOrder;

@Repository
public interface OrderItemsRepository extends JpaRepository<ItemsOrder, Long> {
    List<ItemsOrder> findByOrderId(Long orderId);

    void deleteAllByOrderId(Long orderId);

}
