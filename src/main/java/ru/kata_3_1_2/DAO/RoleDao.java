package ru.kata_3_1_2.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata_3_1_2.model.Role;

import java.util.List;
import java.util.Set;


public interface RoleDao  {
    List<Role> getAllRoles();

    Set<Role> getRoleByName(List<Long> userRole);

    Role findById(Long id);

    void addRole(Role role);
}

