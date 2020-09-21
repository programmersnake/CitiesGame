package com.kostin.UI.JavaFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class MainUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load( new File("C:/Users/kosti/IdeaProjects/CitiesGame/src/main/resources/javafx/main.fxml" ).toURL());
        stage.setTitle("Cities Game");
        stage.setScene(new Scene(root));
        stage.setResizable( false );
        stage.setOnCloseRequest( windowEvent -> {stage.close(); System.exit( 0 );} );
        stage.show();
    }

    public static void main(String[] args) {
        launch( args );
    }

}
