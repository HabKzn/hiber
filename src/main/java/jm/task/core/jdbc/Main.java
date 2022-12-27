package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
       UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Zaur", "Tregulov", (byte) 32);
        userService.saveUser("Neil", "Alishev", (byte) 35);
        userService.saveUser("Ayrat", "Sharipov", (byte) 35);
        userService.saveUser("Ivan", "Ivanov", (byte) 39);
        userService.removeUserById(2);
        userService.getAllUsers().forEach(user -> System.out.println(user.toString()));

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
