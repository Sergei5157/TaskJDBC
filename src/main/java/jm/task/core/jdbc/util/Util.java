package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users_db?useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "51279913298Dsa";
    public Connection getConnection(){
        Connection connection= null;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
