package com.eCommerce.shoppingSite.DTO;

import java.util.List;

import com.eCommerce.shoppingSite.tables.ItemsOrder;
import com.eCommerce.shoppingSite.tables.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private User user;
    private List<ItemsOrder> items;

}
