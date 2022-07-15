package ru.kata_3_1_2.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata_3_1_2.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public void addUser(User user) {
       entityManager.persist(user);
    }

    @Override
    public void removeUser(Long id) {
        Query query = entityManager.createQuery("delete from User where userId = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public User loadUserByUsername (String userName) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u " +
                "join fetch u.roles where u.username = :userName",User.class);
        query.setParameter("userName",userName);
        return query.getResultList().stream().findFirst().orElse(null);
    }
}
