package Checkers;

import Checkers.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUITest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
//        window = primaryStage;

        int bSize=500;
        Board board=new Board(8,bSize);
//        Board board=new Board(10,bSize);
        Scene scene=new Scene(board.getPane(),bSize,bSize);
        primaryStage.setTitle("Board");
        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        primaryStage.show();
//        scene.getHeight();/
//        primaryStage.getHeight();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
