package ru.kata_3_1_2.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata_3_1_2.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role role").getResultList();
    }

    @Override
    public Set<Role> getRoleByName(List<Long> userRole) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT e FROM Role e WHERE e.id in :role", Role.class);
        query.setParameter("role", userRole);

        return new HashSet<>(query.getResultList());
    }

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
