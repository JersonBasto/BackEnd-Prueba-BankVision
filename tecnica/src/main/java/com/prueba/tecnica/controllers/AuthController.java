package com.prueba.tecnica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.application.services.AuthenticationService;
import com.prueba.tecnica.application.services.EncriptService;
import com.prueba.tecnica.application.services.JWTService;
import com.prueba.tecnica.domain.dtos.LoginDto;
import com.prueba.tecnica.domain.dtos.ResLoginDto;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
        RequestMethod.PATCH })
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    EncriptService encriptService;

    @Autowired
    JWTService jwtService;

    @PostMapping("user/login")
    public ResLoginDto loginUser(@RequestBody LoginDto data) throws Exception {
        String jwt = authenticationService.authenticateUser(data.getPassword(), data.getIdentificationNumber());
        ResLoginDto res = new ResLoginDto();
        if (jwt != null) {
            res.setState(true);
            res.setJWT(jwt);
        }
        return res;
    }

}
