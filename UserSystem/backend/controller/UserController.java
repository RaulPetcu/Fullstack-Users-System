package com.example.usersystem.controller;


import com.example.usersystem.model.User;
import com.example.usersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    private String addUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping(value = "/users")
    public List<User> showUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/user/{id}")
    public String deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PutMapping(value = "/user/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}
