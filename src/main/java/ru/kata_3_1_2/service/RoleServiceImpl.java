package ru.kata_3_1_2.service;

import org.springframework.stereotype.Service;
import ru.kata_3_1_2.model.Role;
import ru.kata_3_1_2.DAO.RoleDao;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return  roleDao.getAllRoles();
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public Set<Role> findRoleByName(List<Long> roleName) {
        return  roleDao.getRoleByName(roleName);
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }
    @PostConstruct
    public void addTestRoles(){
        roleDao.addRole(new Role("ROLE_ADMIN"));
        roleDao.addRole(new Role("ROLE_USER"));
    }



}