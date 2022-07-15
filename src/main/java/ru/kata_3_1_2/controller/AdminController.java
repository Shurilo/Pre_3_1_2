package ru.kata_3_1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata_3_1_2.model.Role;
import ru.kata_3_1_2.model.User;
import ru.kata_3_1_2.service.RoleService;
import ru.kata_3_1_2.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
//import javax.validation.Valid;


@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;


    }


    @GetMapping("/admin")
    public String pageForAdmin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String pageCreateUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("nameRoles",roleService.getAllRoles());
        return "/create";
    }

    @PostMapping("/admin/new")
    public String pageCreate(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRoles", required = false) ArrayList<Long> roles) {
        user.setRoles(roleService.findRoleByName(roles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String pageDelete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String pageEditUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user",userService.getUserById(id));
        model.addAttribute("nameRoles", roleService.getAllRoles());
        return "edit";
    }

    @PutMapping("/admin/edit")
    public String pageEdit(@ModelAttribute("user") User user,
                           @RequestParam(value = "nameRoles", required = false) ArrayList<Long> roles) {
        user.setRoles(roleService.findRoleByName(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
