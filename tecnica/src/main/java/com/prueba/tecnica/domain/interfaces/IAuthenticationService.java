package com.prueba.tecnica.domain.interfaces;


public interface IAuthenticationService {
    public String authenticateUser(String password, String identificationNumber) throws Exception;
}
