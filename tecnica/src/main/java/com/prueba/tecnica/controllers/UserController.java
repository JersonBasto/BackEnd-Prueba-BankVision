package com.prueba.tecnica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.application.services.UserService;
import com.prueba.tecnica.domain.models.UserModel;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController("")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
        RequestMethod.PATCH })
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("user")
    public UserModel createUser(@RequestBody UserModel entity) throws Exception {
        return userService.registerUser(entity);
    }

    @GetMapping("user/id/{id}")
    public UserModel getUserById(@PathVariable("id") String id) throws Exception {
        return userService.getUserById(id);
    }

    @PutMapping("user/update/{id}")
    public UserModel updateUser(@PathVariable("id") String id, @RequestBody UserModel entity) throws Exception {
        return userService.updateUser(id, entity);
    }

    @DeleteMapping("user/delete/{id}")
    public UserModel deleteUser(@PathVariable("id") String id) throws Exception {
        return userService.deleteUser(id);
    }
}
