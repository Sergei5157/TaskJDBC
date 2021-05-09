package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Alex", "Tompson", (byte) 33);
        System.out.println("User  с именем - Alex бодавлен в базу данных");
        userService.saveUser("Ray", "Bradbury", (byte) 45);
        System.out.println("User  с именем - Ray бодавлен в базу данных");
        userService.saveUser("George", " Orwell", (byte) 67);
        System.out.println("User  с именем - George бодавлен в базу данных");
        userService.saveUser("Aldous", "Huxley", (byte) 55);
        System.out.println("User  с именем - Aldous бодавлен в базу данных");
        userService.getAllUsers();
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}