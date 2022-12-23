package org.example.views;


import org.example.controllers.UserController;
import org.example.model.User;

import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            try {
                String command = prompt("Введите команду (HELP - список команд): ");
                com = Commands.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown command");
                continue;
            }
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    try {
                        String firstName = prompt("Имя: ");
                        String lastName = prompt("Фамилия: ");
                        String phone = prompt("Номер телефона: ");
                        userController.saveUser(new User(firstName, lastName, phone));
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(id);
                        System.out.println(user);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case LIST:
                    userController.readUsers().forEach(System.out::println);
                    break;
                case UPDATE:
                    String firstName = prompt("Имя: ");
                    String lastName = prompt("Фамилия: ");
                    String phone = prompt("Номер телефона: ");
                    String userID = prompt("Идентификатор пользователя: ");
                    userController.editUser(new User(userID, firstName, lastName, phone));
                    break;
                case DELETE:
                    String userId = prompt("Идентификатор пользователя: ");
                    try {
                        userController.deleteUser(userId);
                        System.out.println("OK");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case HELP:
                    System.out.println("Список команд:");
                    System.out.println("CREATE - создание нового пользователя");
                    System.out.println("READ - вывод пользователя по идентификатору");
                    System.out.println("LIST - вывод списка всех пользователей");
                    System.out.println("DELETE - удаление пользователя по идентификатору");
                    System.out.println("EXIT - выход из программы");
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}