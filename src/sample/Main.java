package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.F;

public class Main extends Application
    {

        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Hellow JavaFX");
            primaryStage.setScene(new Scene(root,300,275));
            primaryStage.show();
//            GridPane root  = new GridPane();
//            root.setAlignment(Pos.CENTER);
//            root.setVgap(10);
//            root.setHgap(10);
//
//            primaryStage.setTitle("HellowFx");
//            primaryStage.setScene(new Scene(root,300, 275));
//
//            Label greeting = new Label("Welcome to JavaFx!");
//            greeting.setTextFill(Color.GREEN);
//            greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
//
//            root.getChildren().add(greeting);
            primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
