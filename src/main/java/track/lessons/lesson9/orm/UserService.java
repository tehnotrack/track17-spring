package track.lessons.lesson9.orm;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import track.lessons.lesson9.User;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void add(User user) {
        userDao.persist(user);
    }

    @Transactional
    public void addAll(Collection<User> users) {
        for (User user : users) {
            userDao.persist(user);
        }
    }

    @Transactional(readOnly = true)
    public List<User> listAll() {
        return userDao.findAll();

    }
}
