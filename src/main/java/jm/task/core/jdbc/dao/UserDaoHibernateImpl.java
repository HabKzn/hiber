package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS(" +
                        "Id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), lastname varchar(30), age SMALLINT) ")
                .executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS USERS")
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = Util.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        session.beginTransaction();
        session.createQuery(String.format("delete User where Id = %d", id)).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        session.beginTransaction();
        String stringQuery = "DELETE FROM User";
        Query query = session.createQuery(stringQuery);
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
