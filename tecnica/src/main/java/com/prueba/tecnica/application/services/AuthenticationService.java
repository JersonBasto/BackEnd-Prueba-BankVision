package com.prueba.tecnica.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.domain.interfaces.IAuthenticationService;
import com.prueba.tecnica.domain.models.UserModel;
import com.prueba.tecnica.exceptions.NotFoundException;
import com.prueba.tecnica.infrastructure.repositories.UserRepository;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Override
    public String authenticateUser(String password, String identificationNumber) throws Exception {
        UserModel userF = userRepository.findByInAndPassword(identificationNumber);
        if (userF != null) {
            boolean pass = passwordEncoder.matches(password, userF.getPassword());
            if (pass) {
                String jwt = jwtService.generateToken(userF);
                return jwt;
            } else {
                throw new NotFoundException("No son Validas las credenciales");
            }
        } else {
            throw new NotFoundException("No se encontro usuario");
        }

    }
}
