package org.example;


import org.example.controllers.UserController;
import org.example.model.*;
import org.example.views.ViewUser;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("users.csv");
        Repository repository = new RepositoryFileCsv(fileOperation);
        UserController controller = new UserController(repository);
        ViewUser view = new ViewUser(controller);
        view.run();
    }
}