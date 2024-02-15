package com.prueba.tecnica.domain.interfaces;

import com.prueba.tecnica.domain.models.UserModel;

public interface IAuthenticationService {
    public UserModel authenticateUser(String password, String identificationNumber) throws Exception;
}
