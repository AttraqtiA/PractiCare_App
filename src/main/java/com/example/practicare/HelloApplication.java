package com.example.practicare;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    private static Stage stageS;

    @Override
    public void start(Stage stage) throws IOException {
        stageS = stage;
        SwitchScene("choose_role");
        stageS.show();
    }


    public static void SwitchScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stageS.setTitle("PractiCare");
        stageS.setScene(scene);
        stageS.setMinWidth(600);
        stageS.setMaxWidth(600);
        stageS.setMinHeight(400);
        stageS.setMaxHeight(400);

        Image icon = new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("/imagesrc/practicare_logo.png")));
        stageS.getIcons().add(icon);
    }

    public static void main(String[] args) {
        launch();
    }
}