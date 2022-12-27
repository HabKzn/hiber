package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_hibernate";
    private static final String USER = "bestuser";
    private static final String PASSWORD = "bestuser";
    private static Connection connection;


    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed())  {
                return connection;
            } else {
                Driver driver;
                driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Session getSession() {
        Configuration config = new Configuration().addAnnotatedClass(User.class).configure("hibernate.cfg.xml");
        return config.buildSessionFactory().getCurrentSession();
    }
}
