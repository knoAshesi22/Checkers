package Checkers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppView extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Checkers Game");
        StackPane pane=new StackPane();


        Scene scene=new Scene(pane,300,250);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
