package ru.kata_3_1_2.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata_3_1_2.model.User;

import java.util.List;


public interface UserDao  {
    User getUserById(Long id);

    List<User> getAllUsers();

    void addUser(User user);


    void removeUser(Long id);

    void updateUser(User user);

    User loadUserByUsername (String userName);
}
