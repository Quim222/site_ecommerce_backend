package com.eCommerce.shoppingSite.DTO;

import com.eCommerce.shoppingSite.tables.user.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String nome;
    private String email;
    private UserRole role;
}
