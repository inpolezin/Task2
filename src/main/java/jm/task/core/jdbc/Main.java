package jm.task.core.jdbc;

import jm.task.core.jdbc.model.UserEntity;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        List<UserEntity> userEntityList;

        userService.createUsersTable();

        userService.saveUser("Илья", "Полезин", (byte) 21);
        userService.saveUser("Даниил", "Зуев", (byte) 20);
        userService.saveUser("Дориан", "Могрейн", (byte) 24);
        userService.saveUser("Дмитрий", "Сларк", (byte) 18);

        userEntityList = userService.getAllUsers();

        for (UserEntity userEntity : userEntityList) {
            System.out.println(userEntity.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}
