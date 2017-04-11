package track.lessons.lesson9.orm;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import track.lessons.lesson9.User;

@Component
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    public void persist(User user) {
        em.persist(user);
    }

    public List<User> findAll() {
        return em.createQuery(
                "SELECT p FROM users p").getResultList();
    }

}