package com.prueba.tecnica.infrastructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.prueba.tecnica.domain.models.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {
    @Query("{'identificationNumber':?0}")
    UserModel findByInAndPassword(String identificationNumber);
}
