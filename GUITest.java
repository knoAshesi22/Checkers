package Checkers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUITest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        int bSize=500;
        Board board=new Board(bSize);
        Scene scene=new Scene(board.getPane(),bSize,bSize);
        primaryStage.setTitle("Board");
        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        primaryStage.show();
//        scene.getHeight();
//        primaryStage.getHeight();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
