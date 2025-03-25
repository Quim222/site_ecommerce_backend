package com.eCommerce.shoppingSite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.eCommerce.shoppingSite.tables.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    @Query("SELECT u.role FROM User u WHERE u.email = ?1")
    String findRoleByEmail(String email);
}
