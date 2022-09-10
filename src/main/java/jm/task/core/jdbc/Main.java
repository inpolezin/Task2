package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList;

        userService.createUsersTable();

        userService.saveUser("Илья", "Полезин", (byte) 21);
        userService.saveUser("Даниил", "Зуев", (byte) 20);
        userService.saveUser("Дориан", "Могрейн", (byte) 24);
        userService.saveUser("Дмитрий", "Сларк", (byte) 18);

        userList = userService.getAllUsers();

        for (User user : userList) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();





    }
}
