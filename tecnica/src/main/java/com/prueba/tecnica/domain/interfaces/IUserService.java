package com.prueba.tecnica.domain.interfaces;

import com.prueba.tecnica.domain.models.UserModel;

public interface IUserService {
    public UserModel registerUser(UserModel entity) throws Exception;

    public UserModel getUserById(String id) throws Exception;

    public UserModel getUserByIN(String identificationNumber) throws Exception;

    public UserModel updateUser(String id, UserModel entity) throws Exception;

    public UserModel deleteUser(String id) throws Exception;
}
