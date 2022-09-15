package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.UserEntity;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age INT, " +
                "PRIMARY KEY (id) )";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS Users")) {

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (name, lastname, age) VALUES(?,?,?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE Id = ?");) {

            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntityList = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity();

                userEntity.setId(resultSet.getLong("id"));
                userEntity.setName(resultSet.getString("name"));
                userEntity.setLastName(resultSet.getString("lastname"));
                userEntity.setAge(resultSet.getByte("age"));

                userEntityList.add(userEntity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userEntityList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE Users")) {

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
