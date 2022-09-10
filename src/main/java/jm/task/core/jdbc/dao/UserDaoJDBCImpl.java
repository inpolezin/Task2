package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String NAME = "root";
    private static final String PASSWORD = "13062001";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE Users (id INT, name VARCHAR(50), lastname VARCHAR(50), age INT )");
        } catch (SQLSyntaxErrorException e){
            System.out.println("Таблица Users уже существует");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE Users");
            PEOPLE_COUNT = 0;
        } catch (SQLSyntaxErrorException e){
            System.out.println("Таблица Users уже была удалена/не создавалась");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO Users VALUES("+ ++PEOPLE_COUNT +",'" + name + "','" + lastName + "'," + age + ")");
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Users WHERE Id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE Users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
