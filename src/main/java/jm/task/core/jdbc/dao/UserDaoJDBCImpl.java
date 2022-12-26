package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    static Connection connection;
    static Statement statement;

    public void createUsersTable() {
        connection = Util.openConnection();
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS USERS(" +
                    "Id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), lastname varchar(30), age SMALLINT) ");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        connection = Util.openConnection();
        try {
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS USERS");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String userName, String userLastName, byte age) {
        connection = Util.openConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO USERS(name, lastname, age) VALUES(?, ?, ?)");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userLastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long userId) {
        connection = Util.openConnection();

        try {
            statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM USERS WHERE id = %d;", userId));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        connection = Util.openConnection();
        List<User> userList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                userList.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        connection = Util.openConnection();
        try {
            statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
