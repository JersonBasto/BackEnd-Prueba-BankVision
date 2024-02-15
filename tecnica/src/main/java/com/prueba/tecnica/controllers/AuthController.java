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
import com.prueba.tecnica.domain.dtos.DataDto;
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
    //U2FsdGVkX19uxGxomE7JeDLaZmtE8aRpk0COkGKDECnyUHxHQHdWn9synL6mPQ8vLILOUWBl/EqtEDWqBP5hiAdSIEd5nTv5bXPEo+b6GjznX/lLnNaOnJtwLk/2CBkf
    //U2FsdGVkX19uxGxomE7JeDLaZmtE8aRpk0COkGKDECnyUHxHQHdWn9synL6mPQ8vLILOUWBl/EqtEDWqBP5hiAdSIEd5nTv5bXPEo+b6GjznX/lLnNaOnJtwLk/2CBkf
    @PostMapping("encript")
    public String encript(@RequestBody DataDto entity) throws Exception {
        System.out.println(entity.getData());
        System.out.println(jwtService.validateToken(entity.getData()));
        //T data = encriptService.decryptJson(entity, valueType);
        //System.out.println("Desencriptado: " + data);
        return null;
    }

}
