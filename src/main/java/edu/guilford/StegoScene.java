package edu.guilford;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StegoScene extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //Instantiate a BorderPane object
        BorderPane root = new BorderPane();

        root.getChildren().add(new StegoPane());

        //Get the width and height of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println("Width: " + screenBounds.getWidth() + " Height: " + screenBounds.getHeight());

        scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}