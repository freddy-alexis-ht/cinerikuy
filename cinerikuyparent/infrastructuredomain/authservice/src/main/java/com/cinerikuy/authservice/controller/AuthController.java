package com.cinerikuy.authservice.controller;

import com.cinerikuy.authservice.config.CustomUserDetails;
import com.cinerikuy.authservice.dto.AuthRequest;
import com.cinerikuy.authservice.dto.SignInRequest;
import com.cinerikuy.authservice.entity.UserCredential;
import com.cinerikuy.authservice.service.AuthService;
import com.cinerikuy.authservice.service.CommService;
import com.cinerikuy.authservice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CommService commService;
    @Autowired
    private CustomUserDetailsService userDetailsService;

//    @PostMapping("/register")
//    public String addNewUser(@RequestBody UserCredential user) {
//        return authService.saveUser(user);
//    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody SignInRequest request) throws UnknownHostException {
        // validar en auth-service que el username sea nuevo
//        if (userDetailsService.loadUserByUsername(request.getUsername()) != null )
//            return "Usuario ya existente";
//        System.out.println("Dentro de /register en auth-service");
        // validar en customer-ms que el dni sea nuevo
        // guardar en customer-ms
        if(! commService.saveCustomer(request))
            return "No se pudo guardar el customer";

        // guardar en auth-service
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(request.getUsername());
        userCredential.setPassword(request.getPassword());
        userCredential.setRoles("ROLE_CUSTOMER");
        userCredential.setActive(true);
        authService.saveUser(userCredential);
        return "user added";
    }

    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if(authenticate.isAuthenticated())
            return authService.generateToken(request.getUsername());
        else
            throw new RuntimeException("Invalid credentials");
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        authService.validateToken(token);
        return "Token is valid";
    }

}
