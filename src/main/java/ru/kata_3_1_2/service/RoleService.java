package ru.kata_3_1_2.service;

import ru.kata_3_1_2.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Role findById(Long id);
    Set<Role> findRoleByName(List<Long> roleName);
    void addRole(Role role);

    //void addTestRoles();
}