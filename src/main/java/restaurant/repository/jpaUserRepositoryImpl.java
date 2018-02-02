package restaurant.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class jpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Transactional
    public boolean delete(int id) {
       // String hql ="DELETE FROM User u WHERE u.id=:id";
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public User get(int id) {
        return em.find(User.class, id);
    }

    public User getByEmail(String email) {
      //  String hql = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1";
        List<User> users = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    public List<User> getAll() {
      //  String hql ="SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email";
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }
}
