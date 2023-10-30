package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Monolingo");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws SQLException {
        DictionaryManagement.readData();
        launch(args);
    }
}