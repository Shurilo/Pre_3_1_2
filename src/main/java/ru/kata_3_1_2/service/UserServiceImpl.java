package ru.kata_3_1_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata_3_1_2.model.Role;
import ru.kata_3_1_2.model.User;
import ru.kata_3_1_2.DAO.UserDao;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder ;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder,
                           RoleService roleService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }
    @Override
    public void updateUser(User user) {
        userDao.updateUser(passwordCoder(user));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDao.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User with this " + username + " User Name not found");
        }
        return userDetails;
    }
    @PostConstruct
    public void addTestUsers() {
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(roleService.findById(2L));
        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(roleService.findById(1L));
        roleAdmin.add(roleService.findById(2L));

        User admin = (new User("Shur", "Push", (byte)41, "alex@mail.com", "admin",
                "12345",roleAdmin));
        User user = (new User("Sveta", "Ryzh", (byte)48, "sveta@mail.com", "user",
                "00000", roleUser));
        addUser(admin);
        addUser(user);
    }
}