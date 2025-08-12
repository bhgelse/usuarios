package com.gelse.app;


import com.gelse.app.javafx.view.ViewLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp extends Application {

    private ConfigurableApplicationContext springContex;

    @Override
    public void init(){
        springContex = new SpringApplicationBuilder(SpringBootApplication.class)
                .web(WebApplicationType.NONE)
                .run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //aqui cargo la escena inicial
        ViewLoader.load(stage, "/fxml/login.fxml", "Login");
    }

    @Override
    public void stop(){
        springContex.close();
        Platform.exit();
    }

    public static void main(String[] args){
        launch(args);
    }

}
