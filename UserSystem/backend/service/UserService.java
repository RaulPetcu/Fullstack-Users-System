package com.example.usersystem.service;

import com.example.usersystem.exception.UserNotFoundException;
import com.example.usersystem.model.User;
import com.example.usersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String createUser(User user) {
        List<User> listOfUsers = userRepository.findAll();
        for(User u : listOfUsers) {
            if(user.equals(u)) {
                return "This user is already in the database";
            }
        }

        userRepository.save(user);
        return "New user added successfully";
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User updateUser(long id, User user) {
        return userRepository.findById(id)
                .map( u -> {
                    u.setFirstName(user.getFirstName());
                    u.setLastName(user.getLastName());
                    u.setEmail(user.getEmail());
                    return userRepository.save(u);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public String deleteUser(long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        else {
            userRepository.deleteById(id);
            return "User with id " + id + " has been deleted";
        }
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
