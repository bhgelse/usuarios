package com.gelse.app.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXLoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void login() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        // Aquí consumirías tu API REST usando HttpClient
        System.out.println("Usuario: " + user + " / Contraseña: " + pass);
    }
}
