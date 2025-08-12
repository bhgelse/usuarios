package com.gelse.app.javafx.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLoader {

    public static void load(Stage stage, String fxmlPath, String title){
        try {
            FXMLLoader loader = new FXMLLoader(ViewLoader.class.getResource(fxmlPath));
            Parent root = loader.load();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
