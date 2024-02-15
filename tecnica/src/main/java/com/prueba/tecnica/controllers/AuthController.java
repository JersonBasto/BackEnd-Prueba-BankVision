package com.prueba.tecnica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.application.services.AuthenticationService;
import com.prueba.tecnica.domain.dtos.LoginDto;
import com.prueba.tecnica.domain.models.UserModel;

@RestController
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("user/login")
    public UserModel loginUser(@RequestBody LoginDto data) throws Exception {
        return authenticationService.authenticateUser(data.getPassword(), data.getIdentificationNumber());
    }
}
