package com.eCommerce.shoppingSite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.shoppingSite.DTO.LoginResponseDTO;
import com.eCommerce.shoppingSite.config.TokenService;
import com.eCommerce.shoppingSite.repo.UserRepository;
import com.eCommerce.shoppingSite.tables.user.AuthenticationDTO;
import com.eCommerce.shoppingSite.tables.user.RegisterDTO;
import com.eCommerce.shoppingSite.tables.user.User;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
        try {
            UsernamePasswordAuthenticationToken usernamePassWord = new UsernamePasswordAuthenticationToken(data.email(),
                    data.password());

            Authentication auth = this.authenticationManager.authenticate(usernamePassWord);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Erro de autenticação: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null) { // Se o email já existir, retorna erro
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.nomeUser(), data.email(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
