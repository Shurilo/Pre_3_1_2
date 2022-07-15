package ru.kata_3_1_2.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata_3_1_2.model.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> getAllUsers();

    void addUser(User user);


    void removeUser(Long id);

    void updateUser(User user);

    UserDetails loadUserByUsername(String username);

    User passwordCoder(User user);

}