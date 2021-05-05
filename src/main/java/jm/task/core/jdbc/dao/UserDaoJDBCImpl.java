package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE `users_db`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT(3) NULL,PRIMARY KEY (`id`))");

        }  catch (SQLException ignored) {

        }

    }

    public void dropUsersTable() {
        try (Connection connection = util.getConnection();Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE `users_db`.`users`");
        } catch (SQLException ignored) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String sql = "INSERT INTO users VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        try (Connection connection = util.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setLong(1, 0);
            ps.setString(2, name);
            ps.setString(3, lastName);
            ps.setByte(4, age);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ps != null;
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void removeUserById(long id) {
        final String delete = "DELETE FROM users WHERE id = ?";
        try (Connection connection = util.getConnection(); PreparedStatement ps = connection.prepareStatement(delete)){
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        final String getAll = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = util.getConnection();Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
            for (User u : users) {
                System.out.println(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        final String clean = "DELETE FROM users";
        try (Connection connection = util.getConnection();Statement statement = connection.createStatement()) {
            statement.execute(clean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
