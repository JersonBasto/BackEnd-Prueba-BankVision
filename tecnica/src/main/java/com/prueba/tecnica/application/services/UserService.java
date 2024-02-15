package com.prueba.tecnica.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.tecnica.domain.interfaces.IUserService;
import com.prueba.tecnica.domain.models.UserModel;
import com.prueba.tecnica.exceptions.NotFoundException;
import com.prueba.tecnica.infrastructure.repositories.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserModel registerUser(UserModel entity) throws Exception {
        if (entity != null) {
            String newPassword = passwordEncoder.encode(entity.getPassword());
            entity.setPassword(newPassword);
            return userRepository.save(entity);
        }
        throw new UnsupportedOperationException("El usuario no se ha podido crear");
    }

    @Override
    @Transactional
    public UserModel getUserById(String id) throws Exception {
        Optional<UserModel> userFind = userRepository.findById(id);
        return userFind.orElseThrow(() -> new NotFoundException("El usuario se ha encontrado"));
    }

    @Override
    public UserModel updateUser(String id, UserModel entity) throws Exception {
        UserModel userFind = userRepository.findById(id).orElse(null);
        if (userFind != null) {
            entity.setId(id);
            return userRepository.save(entity);
        } else {
            throw new NotFoundException("El usuario no se ha encontrado.");
        }

    }

    @Override
    @Transactional
    public UserModel deleteUser(String id) throws Exception {
        UserModel userFind = userRepository.findById(id).orElse(null);
        if (userFind != null) {
            userFind.setIsDelete(true);
            return userRepository.save(userFind);
        } else {
            throw new NotFoundException("El usuario no se ha encontrado.");
        }
    }

    @Override
    public UserModel getUserByIN(String identificationNumber) throws Exception {
        UserModel userFind = userRepository.findByInAndPassword(identificationNumber);
        if (userFind != null) {
            return userFind;
        }
        throw new UnsupportedOperationException("Unimplemented method 'getUserByIN'");
    }
}
